package com.rmj.PaymentMicroservice.dto;

public class PaymentTypeDTO {
    private String name;
    private String url;


    public PaymentTypeDTO() {

    }

    public PaymentTypeDTO(String name, String url) {
        this.name = name;
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
