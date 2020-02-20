package com.rmj.PkiMicroservice.service;

import com.rmj.PkiMicroservice.model.ApplicationAddress;
import com.rmj.PkiMicroservice.model.FtpApplication;
import com.rmj.PkiMicroservice.repository.FtpApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FtpApplicationServiceImpl implements FtpApplicationService {

    @Autowired
    private FtpApplicationRepository ftpApplicationRepository;

    @Override
    public FtpApplication getFtpApplication(String organizationalUnitName) {
        return ftpApplicationRepository.findByOrganizationalUnitName(organizationalUnitName);
    }

    @Override
    public FtpApplication getFtpApplication(Long id) {
        return ftpApplicationRepository.findById(id).get();
    }

    @Override
    public void setFtpApplicationData(String organizationalUnitName, String host, int port, String username, String password) {
        FtpApplication ftpApplication = ftpApplicationRepository.findByOrganizationalUnitName(organizationalUnitName);

        boolean changed = false;

        if(ftpApplication == null) {
            ftpApplication = new FtpApplication(organizationalUnitName, host, port, username, password);
            changed = true;
        }
        else {
            if(!ftpApplication.getHost().equals(host)) {
                ftpApplication.setHost(host);
                changed = true;
            }

            if(ftpApplication.getPort() != port) {
                ftpApplication.setPort(port);
                changed = true;
            }

            if(!ftpApplication.getUsername().equals(username)) {
                ftpApplication.setUsername(username);
                changed = true;
            }

            if(!ftpApplication.getPassword().equals(password)) {
                ftpApplication.setPassword(password);
                changed = true;
            }
        }

        if(changed) ftpApplicationRepository.save(ftpApplication);
    }
}
