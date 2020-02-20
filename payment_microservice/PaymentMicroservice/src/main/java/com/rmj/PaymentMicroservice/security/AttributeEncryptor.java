package com.rmj.PaymentMicroservice.security;


import org.bouncycastle.crypto.generators.BCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.persistence.AttributeConverter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

// https://sultanov.dev/blog/database-column-level-encryption-with-spring-data-jpa/

@Component
public class AttributeEncryptor implements AttributeConverter<String, String> {

    @Value("${attribute-encryptor.algorithm}")
    private String ALGORITHM;

    @Value("${attribute-encryptor.password}")
    private String PASSWORD;  // AES-128 // PASSWORD mora imati 16 karaktera, jer 1 byte = 1 char (karakter),
                             // pa sledi 16 bytes = 128 bits, a AES-128 zahteva kljuc od 128 bita

    private Key key;
    private Cipher cipher;

    // ovu anotaciju smo stavili da bi se ova metoda izvrsila kada bude spreman Spring-ov Context,
    // tj. nakon sto bude ucitane vrednosti za ALGORITHM i PASSWORD iz application.yml
    @PostConstruct
    private void initialize() throws Exception {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);

        // https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
        // https://www.baeldung.com/java-password-hashing
        // Best practices - algorithms: PBKDF2 (PBKDF2WithHmacSHA1, PBKDF2WithHmacSHA256), BCrypt and SCrypt.
        // Zasto se preporucuju?
        // Svaki od njih je spor, i svaki od njih ima sjajnu karakteristiku da mu se lako moze podesiti snaga (strength).
        // To znaci da, kako racunari jacaju, mi mozemo da usporimo algoritam promenom konfiguracionih parametara.
        //KeySpec spec = new PBEKeySpec("password".toCharArray(), salt, 65536, 256); // AES-256
        // Java na ovom racunaru ne podrzava AES-256, ali podrzava AES-128
        // KeySpec spec = new PBEKeySpec(PASSWORD.toCharArray(), salt, 65536, 128); // AES-128
        //SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        //SecretKeyFactory f = SecretKeyFactory.getInstance("BCrypt");
        // Bcrypt jos nije podrzan u samoj Javi, moraju se koristite neke biblioteke
        // PBKDF2WithHmacSHA256 je jedan od preporucenih kriptografskih algoritama, ali je i dosta spor. Posto radimo sifrovanje/desifrovanje
        // atributa velikog broja entiteta u bazi, mogli bismo znacajno usporiti nas microservice. Zato ce BCrypt algoritam biti iskoriscen
        // samo na pocetku (pri pokretanju aplikacije), da dobijemo key iz password-a, koji ce biti koriscen za AES algoritam.
        // AES 256 algoritam je dovoljno pouzdan i ne previse spor algoritam koji ce biti koriscen za sifrovanje/desifrovanje
        // atributa prilikom upisivanja/citanja entiteta iz baze. Nazalost Java na ovom racunaru ne podrzava AES-256, ali podrzava AES-128,
        // zato ce i biti koriscen AES-128 za sifrovanje atributa prilikom upisivanja/citanja entiteta iz baze podataka.
        /// byte[] keyBytes = f.generateSecret(spec).getEncoded();
        byte[] keyBytes = PASSWORD.getBytes();  // AES-128 // PASSWORD mora imati 16 karaktera, jer 1 byte = 1 char (karakter),
                                                // pa sledi 16 bytes = 128 bits, a AES-128 zahteva kljuc od 128 bita
        key = new SecretKeySpec(keyBytes, ALGORITHM);
        cipher = Cipher.getInstance(ALGORITHM);
    }

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) {
            return null;
        }

        String retValue = null;
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
            retValue = Base64.getEncoder().encodeToString(cipher.doFinal(attribute.getBytes()));
        } catch (IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            throw new IllegalStateException(e);
        }

        return retValue;
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        String retValue = null;
        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
            retValue = new String(cipher.doFinal(Base64.getDecoder().decode(dbData)));
        } catch (InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new IllegalStateException(e);
        }

        return retValue;
    }
}
