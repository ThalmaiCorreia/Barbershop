package com.barbershop.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_services")
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nome do serviço (por exemplo, "Corte", "Barba")
    private double price; // Preço do serviço


    @ManyToOne
    @JoinColumn(name = "barbershop_id")
    private BarberShop barberShop; // Relacionamento com BarberShop

    @OneToMany(mappedBy = "service")
    @JsonIgnoreProperties("service")
    private List<Appointment> appointments;

    public Services() {
        // Construtor padrão
    }

    public Services(String name, double price, double commissionRate) {
        this.name = name;
        this.price = price;
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


    public BarberShop getBarberShop() {
        return barberShop;
    }

    public void setBarberShop(BarberShop barberShop) {
        this.barberShop = barberShop;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Services services = (Services) o;
        return Double.compare(services.price, price) == 0 &&  Objects.equals(id, services.id) && Objects.equals(name, services.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
