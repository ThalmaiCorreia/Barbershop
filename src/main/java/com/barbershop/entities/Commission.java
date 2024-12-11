package com.barbershop.entities;

import jakarta.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "tb_commission")
public class Commission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Barber barber; // Barbeiro associado à comissão

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment; // Agendamento relacionado à comissão

    @Column(name = "commission_date") // Nome da coluna no banco de dados
    private OffsetDateTime commissionDate; // Data da comissão



    public Commission() {
        // Construtor padrão
    }

    public Commission(Barber barber, Appointment appointment, OffsetDateTime commissionDate) {
        this.barber = barber;
        this.appointment = appointment;
        this.commissionDate = commissionDate;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public OffsetDateTime getCommissionDate() {
        return commissionDate;
    }

    public void setCommissionDate(OffsetDateTime commissionDate) {
        this.commissionDate = commissionDate;
    }


}
