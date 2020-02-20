package com.rmj.PaymentMicroservice.model;

import com.rmj.PaymentMicroservice.security.AttributeEncryptor;

import javax.persistence.*;

import java.util.ArrayList;
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

    @Convert(converter = AttributeEncryptor.class) // iako ovaj atribut nije nesto posebno bitan, ne zelimo da
                                                    // potencijalni kradljivac podataka zna koje usere (name-ove user-a) imamo
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    //@Convert(converter = AttributeEncryptor.class) za password necemo koristiti enkriptovanje, jer se vec koristi hash-ovanje
    @Column(name = "password", unique = false, nullable = false)
    private String password;
   
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "role_id", nullable = false, updatable = false) })
    private Set<Role> roles = new HashSet<Role>();
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_accounts", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
            @JoinColumn(name = "payment_account_id", nullable = false, updatable = false) })
    private List<PaymentAccount> accounts = new ArrayList<PaymentAccount>();


    public User() {

    }

    public User(String name, String username, String password, List<PaymentAccount> accounts) {
    	this.name = name;
    	this.username = username;
        this.password = password;
        this.accounts = accounts;
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
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PaymentAccount> getAccounts() { return accounts; }

    public void setAccounts(List<PaymentAccount> accounts) { this.accounts = accounts; }
}
