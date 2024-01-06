// Service
package com.barbershop.services;

import com.barbershop.entities.Services;
import com.barbershop.repositories.ServicesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicesService {

    private final ServicesRepository servicesRepository;

    @Autowired
    public ServicesService(ServicesRepository servicesRepository) {
        this.servicesRepository = servicesRepository;
    }

    public List<Services> getAllServices() {
        return servicesRepository.findAll();
    }

    public Services getServiceById(Long serviceId) {
        return servicesRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado com o ID: " + serviceId));
    }

    public Services createService(Services service) {
        return servicesRepository.save(service);
    }

    public void deleteService(Long serviceId) {
        servicesRepository.deleteById(serviceId);
    }
}
