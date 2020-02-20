package com.rmj.CardPaymentMicroservice.model;

import javax.persistence.*;

@Entity
@Table(name = "form_field")
public class FormField {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "type", unique = false, nullable = false)
    private String type;

    @Column(name = "optional", unique = false, nullable = false)
    private boolean optional;

    public FormField() {

    }

    public FormField(String name, String type, boolean optional) {
        this.name = name;
        this.type = type;
        this.optional = optional;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public boolean getOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
