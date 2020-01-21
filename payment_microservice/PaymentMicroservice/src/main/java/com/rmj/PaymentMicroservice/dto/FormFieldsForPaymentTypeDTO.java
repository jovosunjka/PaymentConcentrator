package com.rmj.PaymentMicroservice.dto;


import com.rmj.PaymentMicroservice.model.FormFieldsForPaymentType;

public class FormFieldsForPaymentTypeDTO {
    private String paymentType;
    private String formFields;


    public FormFieldsForPaymentTypeDTO() {

    }

    public FormFieldsForPaymentTypeDTO(String paymentType, String formFields) {
        this.paymentType = paymentType;
        this.formFields = formFields;
    }

    public FormFieldsForPaymentTypeDTO(FormFieldsForPaymentType formFieldsForPaymentType) {
        this.paymentType = formFieldsForPaymentType.getPaymentType();
        this.formFields = formFieldsForPaymentType.getFormFields();
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFormFields() {
        return formFields;
    }

    public void setFormFields(String formFields) {
        this.formFields = formFields;
    }
}
