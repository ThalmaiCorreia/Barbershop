package com.barbershop.entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIME)
    private Date openingTime;

    @Temporal(TemporalType.TIME)
    private Date lunchBreakStart;

    @Temporal(TemporalType.TIME)
    private Date lunchBreakEnd;

    @Temporal(TemporalType.TIME)
    private Date closingTime;

    @OneToMany(mappedBy = "schedule")
    private List<Appointment> appointments;

    @ManyToOne
    private Barber barber;

    public Schedule() {
        // Construtor padrão
    }

    public Schedule(Long id, Date openingTime, Date lunchBreakStart, Date lunchBreakEnd, Date closingTime, List<Appointment> appointments, Barber barber) {
        this.id = id;
        this.openingTime = openingTime;
        this.lunchBreakStart = lunchBreakStart;
        this.lunchBreakEnd = lunchBreakEnd;
        this.closingTime = closingTime;
        this.appointments = appointments;
        this.barber = barber;
    }
    public Schedule(Long id, Date openingTime, Date lunchBreakStart, Date lunchBreakEnd, Date closingTime, Barber barber) {
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

    public Date getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(Date openingTime) {
        this.openingTime = openingTime;
    }

    public Date getLunchBreakStart() {
        return lunchBreakStart;
    }

    public void setLunchBreakStart(Date lunchBreakStart) {
        this.lunchBreakStart = lunchBreakStart;
    }

    public Date getLunchBreakEnd() {
        return lunchBreakEnd;
    }

    public void setLunchBreakEnd(Date lunchBreakEnd) {
        this.lunchBreakEnd = lunchBreakEnd;
    }

    public Date getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(Date closingTime) {
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
