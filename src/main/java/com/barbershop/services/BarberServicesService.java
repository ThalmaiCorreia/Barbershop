// Service
package com.barbershop.services;

import com.barbershop.entities.BarberServices;
import com.barbershop.repositories.BarberServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberServicesService {

    private final BarberServicesRepository barberServicesRepository;

    @Autowired
    public BarberServicesService(BarberServicesRepository barberServicesRepository) {
        this.barberServicesRepository = barberServicesRepository;
    }

    public List<BarberServices> getAllBarberServices() {
        return barberServicesRepository.findAll();
    }

    public BarberServices getBarberServiceById(Long barberServiceId) {
        return barberServicesRepository.findById(barberServiceId)
                .orElseThrow(() -> new RuntimeException("Serviço do barbeiro não encontrado com o ID: " + barberServiceId));
    }

    public BarberServices createBarberService(BarberServices barberService) {
        return barberServicesRepository.save(barberService);
    }

    public void deleteBarberService(Long barberServiceId) {
        barberServicesRepository.deleteById(barberServiceId);
    }
}
