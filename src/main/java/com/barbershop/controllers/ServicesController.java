// Controller
package com.barbershop.controllers;

import com.barbershop.entities.Services;
import com.barbershop.services.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServicesController {

    private final ServicesService servicesService;

    @Autowired
    public ServicesController(ServicesService servicesService) {
        this.servicesService = servicesService;
    }

    @GetMapping
    public ResponseEntity<List<Services>> getAllServices() {
        List<Services> services = servicesService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{serviceId}")
    public ResponseEntity<Services> getServiceById(@PathVariable Long serviceId) {
        Services service = servicesService.getServiceById(serviceId);
        return ResponseEntity.ok(service);
    }

    @PostMapping
    public ResponseEntity<Services> createService(@RequestBody Services service) {
        Services createdService = servicesService.createService(service);
        return ResponseEntity.ok(createdService);
    }

    @DeleteMapping("/{serviceId}")
    public ResponseEntity<Void> deleteService(@PathVariable Long serviceId) {
        servicesService.deleteService(serviceId);
        return ResponseEntity.noContent().build();
    }
}
