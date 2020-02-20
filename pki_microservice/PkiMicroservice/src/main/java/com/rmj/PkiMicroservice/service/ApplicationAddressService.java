package com.rmj.PkiMicroservice.service;

import com.rmj.PkiMicroservice.model.ApplicationAddress;

public interface ApplicationAddressService {

    ApplicationAddress getApplicationAddress(String organizationalUnitName);

    void setApplicationAddress(String organizationalUnitName, String url);
}
