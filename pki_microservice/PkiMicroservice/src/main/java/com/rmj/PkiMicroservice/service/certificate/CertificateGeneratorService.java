package com.rmj.PkiMicroservice.service.certificate;

import com.rmj.PkiMicroservice.model.IssuerData;
import com.rmj.PkiMicroservice.model.SubjectData;

import java.security.cert.X509Certificate;

public interface CertificateGeneratorService {

    X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData);
}
