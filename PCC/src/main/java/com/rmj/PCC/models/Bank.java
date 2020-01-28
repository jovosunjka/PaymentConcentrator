package com.rmj.PCC.models;

import javax.persistence.*;

@Entity
@Table(name = "bank")
public class Bank {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bin")
    private int bin;


    public Bank(){}

    public Bank(String name, int bin)
    {
        this.name = name;
        this.bin = bin;
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

    public int getBin() {
        return bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
    }




}
