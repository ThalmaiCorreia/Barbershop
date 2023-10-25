package com.barbershop.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_barber_service")
public class BarberServices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Barber barber; // Barbeiro associado ao serviço

    @ManyToOne
    private Services service; // Serviço oferecido pelo barbeiro

    private double price; // Preço do serviço específico do barbeiro
    private double commissionRate; // Taxa de comissão para o barbeiro

    public BarberServices() {
        // Construtor padrão
    }

    public BarberServices(Barber barber, Services service, double price, double commissionRate) {
        this.barber = barber;
        this.service = service;
        this.price = price;
        this.commissionRate = commissionRate;
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

    public Services getService() {
        return service;
    }

    public void setService(Services service) {
        this.service = service;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(double commissionRate) {
        this.commissionRate = commissionRate;
    }
}

