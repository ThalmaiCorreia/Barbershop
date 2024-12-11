package com.barbershop.config;

import com.barbershop.entities.*;
import com.barbershop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BarberShopRepository barbershopRepository;
    @Autowired
    private BarberRepository barberRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public void run(String... args) throws Exception {


        // Crie Barbearia
        BarberShop barberShop = new BarberShop("Barbearia", "rua da barbearia", "123456789", "barbearia@barbearia.com", "12345");
        barbershopRepository.save(barberShop);

        // Crie serviços
        Services service1 = new Services("Haircut", 20.0, 0.40);
        Services service2 = new Services("Shave", 15.0, 0.40);
        servicesRepository.saveAll(Arrays.asList(service1, service2));

        // Crie um barbeiro
        Barber barber = new Barber("John Barber", "john@gmail.com", "987654321", "barber123", barberShop);
        barberRepository.save(barber);
        Barber barber1 = new Barber("Joy Barber", "joy@gmail.com", "123456789", "barber123", barberShop);
        barberRepository.save(barber1);

        // Crie um cliente
        Customer customer = new Customer("Alice Customer", "alice@gmail.com", "123456789", "customer123");
        customerRepository.save(customer);
        Customer customer1 = new Customer("Théo Customer", "theo@gmail.com", "123456789", "customer123");
        customerRepository.save(customer1);

        // Crie duas agendas com os horários fornecidos

        OffsetDateTime openingTime = OffsetDateTime.of(2024, 6, 13, 9, 0, 0, 0, ZoneOffset.UTC);

        OffsetDateTime lunchBreakStart = OffsetDateTime.of(2024, 6, 13, 13, 0, 0, 0, ZoneOffset.UTC);

        OffsetDateTime lunchBreakEnd = OffsetDateTime.of(2024, 6, 13, 14, 0, 0, 0, ZoneOffset.UTC);

        OffsetDateTime closingTime = OffsetDateTime.of(2024, 6, 13, 20, 0, 0, 0, ZoneOffset.UTC);

        Schedule schedule1 = new Schedule(0L, openingTime, lunchBreakStart, lunchBreakEnd, closingTime, barber);
        Schedule schedule2 = new Schedule(0L, openingTime, lunchBreakStart, lunchBreakEnd, closingTime, barber1);

        scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));

        // Crie dois agendamentos em horários aleatórios

        OffsetDateTime appointmentTime1 = OffsetDateTime.of(2024, 6, 13, 10, 30, 0, 0, ZoneOffset.UTC);

        OffsetDateTime appointmentTime2 = OffsetDateTime.of(2024, 6, 13, 15, 30, 0, 0, ZoneOffset.UTC);

        OffsetDateTime appointmentTime3 = OffsetDateTime.of(2024, 6, 13, 14, 30, 0, 0, ZoneOffset.UTC);

        Appointment appointment1 = new Appointment(customer,barber, service1, schedule1, barberShop, appointmentTime1,30, AppointmentStatus.SCHEDULED, service1.getPrice() );
        Appointment appointment2 = new Appointment(customer1, barber1, service2, schedule2, barberShop, appointmentTime2,30,  AppointmentStatus.COMPLETED, service2.getPrice());
        Appointment appointment3 = new Appointment(customer1, barber1, service2, schedule2, barberShop, appointmentTime3,30,  AppointmentStatus.CANCELED, service2.getPrice());

        appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2, appointment3));
    }
}
