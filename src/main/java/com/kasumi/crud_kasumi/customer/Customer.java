package com.kasumi.crud_kasumi.customer;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String lastname;
    private String email;
    private String phonenumber;
    private LocalDate fecha;
    @Transient
    private int antiguedad;

    public Customer() {
    }

    public Customer(Long id, String name, String lastname, String email, String phonenumber, LocalDate fecha) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.fecha = fecha;
    }

    public Customer(String name, String lastname, String email, String phonenumber, LocalDate fecha) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
        this.fecha = fecha;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getAntiguedad() {
        return Period.between(this.fecha, LocalDate.now()).getYears();
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }
}
