package com.barbershop.entities;

import com.barbershop.entities.Appointment;
import com.barbershop.entities.BarberShop;
import com.barbershop.entities.Schedule;
import com.barbershop.entities.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_barber")
@PrimaryKeyJoinColumn(name = "user_id")
public class Barber extends User {
    @ManyToOne
    private BarberShop barberShop; // Barbeiro associado a uma barbearia

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Commission> commission; // Comissão acumulada do barbeiro

    @OneToOne(mappedBy = "barber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Schedule schedule; // Agenda do barbeiro

    @OneToMany(mappedBy = "barber", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Appointment> appointments; // Agendamentos do barbeiro

        public Barber() {
        // Construtor padrão
    }

    public Barber(String name, String email, String phone, String password, BarberShop barberShop) {
        super(name, email, phone, password);
        this.barberShop=barberShop;
    }

    public Barber(Long barberId) {
    }

    public BarberShop getBarberShop() {
        return barberShop;
    }

    public void setBarberShop(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    public List<Commission> getCommission() {
        return commission;
    }

    public void setCommission(List<Commission> commission) {
        this.commission = commission;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


}
