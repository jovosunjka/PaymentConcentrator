package com.rmj.PaymentMicroservice.model;

import javax.persistence.*;

@Entity
@Table(name = "form_fields_for_payment_type")
public class FormFieldsForPaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "payment_type", unique = true, nullable = false)
    private String paymentType;

    @Column(name = "form_fields", unique = false, nullable = false)
    private String formFields; // field_1,field_2,field_3,...

    public FormFieldsForPaymentType() {

    }

    public FormFieldsForPaymentType(String paymentType, String formFields) {
        this.paymentType = paymentType;
        this.formFields = formFields;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
