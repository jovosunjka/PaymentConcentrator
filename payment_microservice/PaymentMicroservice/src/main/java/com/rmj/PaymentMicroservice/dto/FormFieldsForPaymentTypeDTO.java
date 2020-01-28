package com.rmj.PaymentMicroservice.dto;


import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;

import java.util.List;
import java.util.stream.Collectors;

public class FormFieldsForPaymentTypeDTO {
    private String paymentType;
    private List<FormFieldDTO> formFields;


    public FormFieldsForPaymentTypeDTO() {

    }

    public FormFieldsForPaymentTypeDTO(String paymentType, List<FormFieldDTO> formFields) {
        this.paymentType = paymentType;
        this.formFields = formFields;
    }

    public FormFieldsForPaymentTypeDTO(FormFieldsForPaymentType formFieldsForPaymentType) {
        this.paymentType = formFieldsForPaymentType.getPaymentType();
        this.formFields = formFieldsForPaymentType.getFormFields().stream()
                                    .map(ff -> new FormFieldDTO(ff.getName(), ff.getType(), ff.getOptional()))
                                    .collect(Collectors.toList());
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public List<FormFieldDTO> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormFieldDTO> formFields) {
        this.formFields = formFields;
    }
}
