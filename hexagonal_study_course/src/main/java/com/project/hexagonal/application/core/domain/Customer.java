package com.project.hexagonal.application.core.domain;

public class Customer {

    public Customer() {
        this.isValidCpf = false;
    }

    public Customer(String id, String street, String city, Address address, String cpf, Boolean isValidCpf) {
        this.id = id;
        this.street = street;
        this.city = city;
        this.address = address;
        this.cpf = cpf;
        this.isValidCpf = isValidCpf;
    }

    private String id;

    private String street;

    private String city;

    private Address address;

    private String cpf;

    private Boolean isValidCpf;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Boolean getValidCpf() {
        return isValidCpf;
    }

    public void setValidCpf(Boolean validCpf) {
        isValidCpf = validCpf;
    }
}

