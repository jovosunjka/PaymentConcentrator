package com.rmj.PkiMicroservice.service.keystore;

import java.io.File;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.List;

public interface KeyStoreWriterService {

    void loadKeyStore(Object fileOrFileName, char[] password);

    void saveKeyStore(Object fileOrFileName, char[] password);

    void write(String alias, PrivateKey privateKey, char[] password, Certificate certificate);

    void writePrivateKeyAndChainOfCertificates(File keystoreFile, String alias, PrivateKey privateKey,
                                               Certificate[] certificates, char[] password);

    void writeCertificate(String alias, Certificate certificate);

    void writeCertificates(Object fileOrFileName, char[] password, List<String> aliases, List<Certificate> certificates) throws Exception;

    String getOrganizationalUnitName(java.security.cert.Certificate certificate);
}
