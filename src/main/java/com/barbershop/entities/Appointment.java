package com.barbershop.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Customer customer; // Cliente que fez o agendamento

    @ManyToOne
    private Barber barber; // Barbeiro que realizará o serviço

    @ManyToOne
    private Services service; // Serviço a ser realizado

    private Date appointmentDate; // Data e hora do agendamento

    private AppointmentStatus status;

    public Appointment() {
        // Construtor padrão
    }

    public Appointment(Customer customer, Barber barber, Services service, Date appointmentDate, AppointmentStatus status) {
        this.customer = customer;
        this.barber = barber;
        this.service = service;
        this.appointmentDate = appointmentDate;
        this.status = status;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }
}
