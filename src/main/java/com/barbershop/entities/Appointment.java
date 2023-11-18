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

    @ManyToOne
    private Schedule schedule; // Agenda associada ao agendamento

    @ManyToOne
    private BarberShop barberShop; // Barbearia associada ao agendamento

    private Date appointmentDate; // Data e hora do agendamento
    private int duration; // Duração do serviço em minutos
    private AppointmentStatus status;

    public Appointment() {
        // Construtor padrão
    }

    public Appointment(Customer customer, Barber barber, Services service, Schedule schedule, BarberShop barberShop, Date appointmentDate, int duration, AppointmentStatus status) {
        this.customer = customer;
        this.barber = barber;
        this.service = service;
        this.schedule = schedule;
        this.barberShop = barberShop;
        this.appointmentDate = appointmentDate;
        this.duration = duration;
        this.status = status;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public BarberShop getBarberShop() {
        return barberShop;
    }

    public void setBarberShop(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    public Date getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Date appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
