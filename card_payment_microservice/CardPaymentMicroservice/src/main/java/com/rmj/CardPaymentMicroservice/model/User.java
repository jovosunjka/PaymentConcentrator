package com.rmj.CardPaymentMicroservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username")
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
   
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    private Set<Role> roles = new HashSet<Role>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_properties", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "property_id", nullable = false, updatable = false) })
    private List<Property> properties;

    @Column(name = "currency", unique = false, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Currency currency;


    public User() {

    }

    public User(String username, String password, List<Property> properties, Currency currency) {
    	this.username = username;
        this.password = password;
        this.properties = properties;
        this.currency = currency;
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
   
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
