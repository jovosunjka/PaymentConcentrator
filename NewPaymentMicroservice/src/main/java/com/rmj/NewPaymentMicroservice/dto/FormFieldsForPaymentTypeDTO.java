package com.rmj.NewPaymentMicroservice.dto;


import com.rmj.NewPaymentMicroservice.model.FormField;

import java.util.List;
import java.util.stream.Collectors;

public class FormFieldsForPaymentTypeDTO {
    private String paymentType;
    private List<FormFieldDTO> formFields;


    public FormFieldsForPaymentTypeDTO() {

    }

    public FormFieldsForPaymentTypeDTO(List<FormField> formFields) {
        this.paymentType = "new-payment";
        this.formFields = formFields.stream()
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
