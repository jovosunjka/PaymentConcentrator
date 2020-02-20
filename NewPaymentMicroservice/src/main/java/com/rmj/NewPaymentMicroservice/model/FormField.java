package com.rmj.NewPaymentMicroservice.model;


public class FormField {
    private Long id;
    private String name;
    private String type;
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
