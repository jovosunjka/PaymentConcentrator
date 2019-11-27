package com.rmj.IntegrationMicroservice.model;

import javax.persistence.*;

@Entity
public class ScienceCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "base_url", unique = true, nullable = false)
    private String baseUrl;


    public ScienceCenter() {

    }

    public ScienceCenter(String name, String baseUrl) {
        this.name = name;
        this.baseUrl = baseUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
