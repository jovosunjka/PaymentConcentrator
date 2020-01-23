package com.rmj.PaymentMicroservice.model;

import javax.persistence.*;

@Entity
@Table(name = "property")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "identifier", unique = false, nullable = false)
    private String identifier; // account number, paypal account, bitcoin account, ...

    @Column(name = "value", unique = false, nullable = false)
    private String value;

    public Property() {

    }

    public Property(String identifier, String value) {
        this.identifier = identifier;
        this.value = value;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
