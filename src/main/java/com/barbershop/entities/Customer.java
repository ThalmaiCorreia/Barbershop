package com.barbershop.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_customer")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Appointment> appointmentHistory;

    public Customer() {
        // Construtor padrão
    }

    public Customer(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    public List<Appointment> getAppointmentHistory() {
        return appointmentHistory;
    }

    public void setAppointmentHistory(List<Appointment> appointmentHistory) {
        this.appointmentHistory = appointmentHistory;
    }
}

