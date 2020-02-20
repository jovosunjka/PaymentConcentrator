package com.rmj.PkiMicroservice.service.certificate;

public interface CertificateReaderService {

    void readFromBase64EncFile();

    void readFromBinEncFile();
}
