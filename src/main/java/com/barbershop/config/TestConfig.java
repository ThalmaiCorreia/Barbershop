package com.barbershop.config;

import com.barbershop.entities.*;
import com.barbershop.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private BarbershopRepository barbershopRepository;
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
        Services service1 = new Services("Haircut", 20.0, 40.0);
        Services service2 = new Services("Shave", 15.0, 40.0);
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
        Date openingTime = new Date();
        Date lunchBreakStart = new Date();
        Date lunchBreakEnd = new Date();
        Date closingTime = new Date();

        openingTime.setHours(9);
        openingTime.setMinutes(0);

        lunchBreakStart.setHours(13);
        lunchBreakStart.setMinutes(0);

        lunchBreakEnd.setHours(14);
        lunchBreakEnd.setMinutes(0);

        closingTime.setHours(20);
        closingTime.setMinutes(0);

        Schedule schedule1 = new Schedule(0L, openingTime, lunchBreakStart, lunchBreakEnd, closingTime, barber);
        Schedule schedule2 = new Schedule(0L, openingTime, lunchBreakStart, lunchBreakEnd, closingTime, barber1);

        scheduleRepository.saveAll(Arrays.asList(schedule1, schedule2));

        // Crie dois agendamentos em horários aleatórios
        Date appointmentTime1 = new Date();
        Date appointmentTime2 = new Date();

        appointmentTime1.setHours(10);
        appointmentTime1.setMinutes(30);

        appointmentTime2.setHours(15);
        appointmentTime2.setMinutes(30);

        Appointment appointment1 = new Appointment(customer,barber, service1, schedule1, barberShop, appointmentTime1,30, AppointmentStatus.SCHEDULED );
        Appointment appointment2 = new Appointment(customer1, barber1, service2, schedule2, barberShop, appointmentTime2,30,  AppointmentStatus.COMPLETED);

        appointmentRepository.saveAll(Arrays.asList(appointment1, appointment2));
    }
}
