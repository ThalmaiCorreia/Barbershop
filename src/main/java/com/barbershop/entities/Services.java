package com.barbershop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nome do serviço (por exemplo, "Corte", "Barba")
    private double price; // Preço do serviço
    private double commissionRate; // Taxa de comissão associada ao serviço

    public Services() {
        // Construtor padrão
    }

    public Services(String name, double price, double commissionRate) {
        this.name = name;
        this.price = price;
        this.commissionRate = commissionRate;
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

