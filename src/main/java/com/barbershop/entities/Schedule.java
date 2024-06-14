package com.barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.data.util.Lazy;

import java.time.OffsetDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private OffsetDateTime openingTime;

    private OffsetDateTime lunchBreakStart;

    private OffsetDateTime lunchBreakEnd;

    private OffsetDateTime closingTime;

    @OneToMany(mappedBy = "schedule")
    @JsonIgnore
    private List<Appointment> appointments;

    @ManyToOne(fetch = FetchType.LAZY)
    private Barber barber;

    public Schedule() {
        // Construtor padrão
    }

    public Schedule(Long id, OffsetDateTime openingTime, OffsetDateTime lunchBreakStart, OffsetDateTime lunchBreakEnd, OffsetDateTime closingTime,  Barber barber) {
        this.id = id;
        this.openingTime = openingTime;
        this.lunchBreakStart = lunchBreakStart;
        this.lunchBreakEnd = lunchBreakEnd;
        this.closingTime = closingTime;
        this.barber = barber;
    }

    // Getters e setters

    public Long getId() {
        return id;
    }

    public OffsetDateTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(OffsetDateTime openingTime) {
        this.openingTime = openingTime;
    }

    public OffsetDateTime getLunchBreakStart() {
        return lunchBreakStart;
    }

    public void setLunchBreakStart(OffsetDateTime lunchBreakStart) {
        this.lunchBreakStart = lunchBreakStart;
    }

    public OffsetDateTime getLunchBreakEnd() {
        return lunchBreakEnd;
    }

    public void setLunchBreakEnd(OffsetDateTime lunchBreakEnd) {
        this.lunchBreakEnd = lunchBreakEnd;
    }

    public OffsetDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(OffsetDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Barber getBarber() {
        return barber;
    }

    public void setBarber(Barber barber) {
        this.barber = barber;
    }
}
