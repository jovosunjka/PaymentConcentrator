package com.rmj.PaymentMicroservice.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "form_fields_for_payment_type")
public class FormFieldsForPaymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "payment_type", unique = true, nullable = false)
    private String paymentType;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<FormField> formFields;


    public FormFieldsForPaymentType() {

    }

    public FormFieldsForPaymentType(String paymentType, List<FormField> formFields) {
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

    public List<FormField> getFormFields() {
        return formFields;
    }

    public void setFormFields(List<FormField> formFields) {
        this.formFields = formFields;
    }
}
