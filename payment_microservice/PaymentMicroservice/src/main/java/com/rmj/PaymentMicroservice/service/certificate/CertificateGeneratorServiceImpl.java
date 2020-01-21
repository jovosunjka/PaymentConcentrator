package com.rmj.PaymentMicroservice.service.certificate;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.asn1.x509.Extension;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.rmj.PaymentMicroservice.model.certificate.IssuerData;
import com.rmj.PaymentMicroservice.model.certificate.SubjectData;

@Service
public class CertificateGeneratorServiceImpl implements CertificateGeneratorService {
	
	//@Value("${server.ssl.key-store-2}")
	//private Resource keyStoreResource;

	@Value("${server.ssl.key-alias}")
	private String alias;
	
	@Value("${server.ssl.key-store-password}")
	private char[] keyStorePassword;
	
	private KeyStore keyStore;
	

	public CertificateGeneratorServiceImpl() {
		try {
			keyStore = KeyStore.getInstance("JKS", "SUN");
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
	}

	//@EventListener(ApplicationReadyEvent.class)
	private void makeCertificate() {
		try {
			createCertificate(alias);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Certificate created!");
	}
	
	@Override
	public void loadKeyStore(Object fileOrFileName, char[] password) {
		FileInputStream fis = null;

		try {
			if(fileOrFileName != null) {
				if(fileOrFileName instanceof String) fis = new FileInputStream((String) fileOrFileName);
				else if(fileOrFileName instanceof File) fis = new FileInputStream((File) fileOrFileName);
				else throw new Exception("Argument fileOrFileName must be String or File!");
				keyStore.load(fis, password);
				fis.close();
			} else {
				//Ako je cilj kreirati novi KeyStore poziva se i dalje load, pri cemu je prvi parametar null
				keyStore.load(null, password);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void saveKeyStore(Object fileOrFileName, char[] password) {
		FileOutputStream fos = null;
		try {
			if(fileOrFileName instanceof String) fos = new FileOutputStream((String) fileOrFileName);
			else if(fileOrFileName instanceof File) fos = new FileOutputStream((File) fileOrFileName);
			else throw new Exception("Argument fileOrFileName must be String or File!");
			keyStore.store(fos, password);
			fos.close();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(fos != null) fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate) {
		try {
			keyStore.setKeyEntry(alias, privateKey, password, new Certificate[] {certificate});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData) {
		Security.addProvider(new BouncyCastleProvider());
		try {
			//Posto klasa za generisanje sertifiakta ne moze da primi direktno privatni kljuc pravi se builder za objekat
			//Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
			//Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifiakta
			JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");
			//Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
			builder = builder.setProvider("BC");

			//Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
			ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

			//Postavljaju se podaci za generisanje sertifiakta
			X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(issuerData.getX500name(),
					new BigInteger(subjectData.getSerialNumber()),
					subjectData.getStartDate(),
					subjectData.getEndDate(),
					subjectData.getX500name(),
					subjectData.getPublicKey());


			// JOVO DODAO, NIJE BILO U LUBURINOM KODU
			//----------------------------------------------------------------------------------------------------------
			// Na ovom linku je objasnjeno zasto treba koristiti subjectAlternativeNames:
			// https://support.google.com/chrome/a/answer/7391219?hl=en
			final List<ASN1Encodable> subjectAlternativeNames = new ArrayList<ASN1Encodable>();
			subjectAlternativeNames.add(new GeneralName(GeneralName.dNSName, "localhost"));
			subjectAlternativeNames.add(new GeneralName(GeneralName.iPAddress, "192.168.56.1"));
			// primeri sa neta
			//subjectAlternativeNames.add(new GeneralName(GeneralName.iPAddress, publicIPAddress));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.iPAddress, "10.0.0.1"));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.iPAddress, "10.1.1.1"));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.dNSName, "kubernetes"));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.dNSName, "kubernetes.default"));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.dNSName, "kubernetes.default.svc"));
			//subjectAlternativeNames.add(new GeneralName(GeneralName.dNSName, "kubernetes.default.svc.cluster.local"));
			final DERSequence subjectAlternativeNamesExtension = new DERSequence(
					subjectAlternativeNames.toArray(new ASN1Encodable[subjectAlternativeNames.size()]));
			//zastarelo
			//certGen.addExtension(X509Extensions.SubjectAlternativeName, false, subjectAlternativeNamesExtension);
			certGen.addExtension(Extension.subjectAlternativeName, false, subjectAlternativeNamesExtension);

			// Podesavanje ekstenzije za OCSP
			//*****************************************************************************************************
			//AccessDescription accessDescription = new AccessDescription(AccessDescription.id_ad_ocsp, new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(OCSP_URL)));
			//AccessDescription accessDescription = new AccessDescription(X509ObjectIdentifiers.ocspAccessMethod, new GeneralName(GeneralName.uniformResourceIdentifier, OCSP_URL));
			/*AccessDescription accessDescription = new AccessDescription(X509ObjectIdentifiers.ocspAccessMethod,
					new GeneralName(GeneralName.uniformResourceIdentifier, new DERIA5String(OCSP_URL+"/"+subjectData.getSerialNumber())));
			ASN1EncodableVector authorityInfoAccess_ASN = new ASN1EncodableVector();
			authorityInfoAccess_ASN.add(accessDescription);
			certGen.addExtension(Extension.authorityInfoAccess, false, new DERSequence(authorityInfoAccess_ASN));*/
			//******************************************************************************************************
			
			
			//----------------------------------------------------------------------------------------------------------

			//Generise se sertifikat
			X509CertificateHolder certHolder = certGen.build(contentSigner);

			//Builder generise sertifikat kao objekat klase X509CertificateHolder
			//Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
			JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
			certConverter = certConverter.setProvider("BC");

			//Konvertuje objekat u sertifikat
			return certConverter.getCertificate(certHolder);
		} catch (CertificateEncodingException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (OperatorCreationException e) {
			e.printStackTrace();
		} catch (CertificateException e) {
			e.printStackTrace();
		} catch (CertIOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
    public X509Certificate createCertificate(String commonName) throws Exception {
        //String str = Base64.encodeBase64String(generateKeyPair().getPublic().getEncoded());

        PublicKey publicKeyOfSubject;
        //PrivateKey privateKeyOfSubject = null;
        IssuerData issuerData;
        
        KeyPair keyPair = generateKeyPair();
        publicKeyOfSubject = keyPair.getPublic(); // u ovom slucaju RootCA je i Subject i Issuer
        PrivateKey privateKeyOfIssuer = keyPair.getPrivate(); // u ovom slucaju RootCA je i Subject i Issuer
        issuerData = generateIssuerData(privateKeyOfIssuer, commonName, "RS");

        SubjectData subjectData = generateSubjectData(commonName, publicKeyOfSubject);

        X509Certificate certificateX509 = generateCertificate(subjectData, issuerData);
        
        loadKeyStore(null, keyStorePassword);
        write(commonName, issuerData.getPrivateKey(), keyStorePassword, certificateX509);
        //saveKeyStore(keyStoreResource.getFile(), keyStorePassword);

        return certificateX509;
    }
	
	@Override
    public SubjectData generateSubjectData(String commonName, PublicKey publicKey) {
        LocalDateTime startLocalDateTime = LocalDateTime.now();
        //LocalDateTime startLocalDateTime = LocalDateTime.now().minusYears(ROOT_CERTIFICATE_DURATION).plusDays(10);

        long years = 1;

        LocalDateTime endLocalDateTime = startLocalDateTime.plusYears(years);
        //LocalDate endLocalDate = startLocalDate.plusDays(2);
        Date startDate = asDate(startLocalDateTime);
        Date endDate = asDate(endLocalDateTime);

        //Serijski broj sertifikata
        //String sn="1";
        //klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, commonName);
        //builder.addRDN(BCStyle.SURNAME, csr.getSurname());
        //builder.addRDN(BCStyle.GIVENNAME, csr.getGivenName());
        //builder.addRDN(BCStyle.O, csr.getOrganizationName());
        //builder.addRDN(BCStyle.OU, csr.getOrganizationalUnitName());
        builder.addRDN(BCStyle.C, "RS");
        //builder.addRDN(BCStyle.E, csr.getEmailAddress());
        //UID (USER ID) je ID korisnika
        //builder.addRDN(BCStyle.UID, csr.getUserId());

        //Kreiraju se podaci za sertifikat, sto ukljucuje:
        // - javni kljuc koji se vezuje za sertifikat
        // - podatke o vlasniku
        // - serijski broj sertifikata
        // - od kada do kada vazi sertifikat
        return new SubjectData(publicKey, builder.build(), startDate, endDate);
    }
	
	@Override
    public IssuerData generateIssuerData(PrivateKey issuerKey, String commonName, String countryCode) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, commonName);
        //builder.addRDN(BCStyle.SURNAME, "Luburic");
        //builder.addRDN(BCStyle.GIVENNAME, "Nikola");
        //builder.addRDN(BCStyle.O, organizationName);
        //builder.addRDN(BCStyle.OU, organizationalUnitName);
        builder.addRDN(BCStyle.C, countryCode);
        //builder.addRDN(BCStyle.E, "nikola.luburic@uns.ac.rs");
        //UID (USER ID) je ID korisnika
        //builder.addRDN(BCStyle.UID, userId);

        //Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
        // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
        // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
        return new IssuerData(issuerKey, builder.build());
    }
	
	 private KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
	 }
	 
	 private Date asDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	 }
}
