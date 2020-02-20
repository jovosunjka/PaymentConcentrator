package com.rmj.PkiMicroservice.service;

import com.rmj.PkiMicroservice.model.FtpApplication;

public interface FtpApplicationService {

    FtpApplication getFtpApplication(String organizationalUnitName);

    FtpApplication getFtpApplication(Long id);

    void setFtpApplicationData(String organizationalUnitName, String host, int port, String username, String password);
}
