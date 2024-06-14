package com.barbershop.services;

import com.barbershop.entities.Appointment;
import com.barbershop.entities.AppointmentStatus;
import com.barbershop.entities.Barber;
import com.barbershop.entities.Customer;
import com.barbershop.exceptions.AppointmentTimeUnavailableException;
import com.barbershop.exceptions.InvalidAppointmentUpdateException;
import com.barbershop.exceptions.AppointmentNotFoundException;
import com.barbershop.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final ScheduleService scheduleService;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, ScheduleService scheduleService) {
        this.appointmentRepository = appointmentRepository;
        this.scheduleService = scheduleService;
    }

    public Appointment createAppointment(Appointment appointment) {
        // Verifique se o horário está disponível para o barbeiro e serviço
        if (!scheduleService.isAppointmentTimeAvailable(appointment.getSchedule())) {
            throw new AppointmentTimeUnavailableException("Horário não disponível para agendamento.");
        }

        // Configure o status do agendamento (por exemplo, como "Agendado")
        appointment.setStatus(appointment.getStatus());

        // Salve o agendamento no banco de dados
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Long appointmentId, Appointment updatedAppointment) {
        Appointment existingAppointment = getAppointmentById(appointmentId);

        // Verifique se o novo horário não é anterior ao horário atual
        if (updatedAppointment.getAppointmentDate().before(existingAppointment.getAppointmentDate())) {
            throw new InvalidAppointmentUpdateException("Não é permitido alterar para um horário anterior ao horário atual.");
        }

        // Verifique se o campo "customer" não está sendo alterado
        if (existingAppointment.getCustomer().equals(updatedAppointment.getCustomer())) {
            throw new InvalidAppointmentUpdateException("Não é permitido alterar o cliente do agendamento.");
        }

        if (!scheduleService.isAppointmentTimeAvailable(updatedAppointment.getSchedule())) {
            throw new AppointmentTimeUnavailableException("Horário não disponível para agendamento.");
        }

        // Atualize outros campos, exceto "customer"
        existingAppointment.setBarber(updatedAppointment.getBarber());
        existingAppointment.setService(updatedAppointment.getService());
        existingAppointment.setAppointmentDate(updatedAppointment.getAppointmentDate());
        existingAppointment.setStatus(updatedAppointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }


    public void deleteAppointment(Long appointmentId) {
        // Obtém o compromisso com base no ID fornecido
        Appointment existingAppointment = getAppointmentById(appointmentId);

        // Verifica se o compromisso com o ID especificado existe
        if (existingAppointment != null) {
            // Remove o compromisso do banco de dados
            appointmentRepository.delete(existingAppointment);
        } else {
            // Se o compromisso não existe, lança uma exceção AppointmentNotFoundException
            throw new AppointmentNotFoundException("Compromisso não encontrado com o ID: " + appointmentId);
        }
    }


    public Appointment getAppointmentById(Long appointmentId) {
        // Tente encontrar o compromisso no banco de dados com base no ID
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Compromisso não encontrado com o ID: " + appointmentId));
    }


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public List<Appointment> findAppointmentsByBarber(Long barberId) {
        // Implemente a lógica para buscar compromissos por ID de barbeiro
        return appointmentRepository.findByBarberId(barberId);
    }
    public List<Appointment> findAppointmentsByCustomer(Customer customer){
        return appointmentRepository.findByCustomer(customer);
    }
    public List<Appointment> findAppointmentsByBarber(Barber barber) {
        return appointmentRepository.findByBarber(barber);
    }

}
