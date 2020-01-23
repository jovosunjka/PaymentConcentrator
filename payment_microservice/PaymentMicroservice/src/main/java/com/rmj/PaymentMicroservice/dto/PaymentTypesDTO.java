package com.rmj.PaymentMicroservice.dto;

import java.util.List;

public class PaymentTypesDTO {
    private List<PaymentTypeDTO> types;


    public PaymentTypesDTO() {

    }

    public PaymentTypesDTO(List<PaymentTypeDTO> types) {
        this.types = types;
    }

    public List<PaymentTypeDTO> getTypes() {
        return types;
    }

    public void setTypes(List<PaymentTypeDTO> types) {
        this.types = types;
    }
}
