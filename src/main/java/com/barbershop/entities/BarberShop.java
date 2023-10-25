package com.barbershop.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_barbershop")
public class BarberShop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;

    @OneToMany(mappedBy = "barberShop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Barber> barbers;

    @OneToMany(mappedBy = "barberShop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Services> services;

    @OneToMany(mappedBy = "barberShop", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointments;

    public BarberShop() {
    }

    public BarberShop(String name, String address, String phoneNumber, String email, String password) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<Barber> barbers) {
        this.barbers = barbers;
    }

    public List<Services> getServices() {
        return services;
    }

    public void setServices(List<Services> services) {
        this.services = services;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }
}

