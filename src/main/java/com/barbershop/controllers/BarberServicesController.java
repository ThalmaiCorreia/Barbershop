// Controller
package com.barbershop.controllers;

import com.barbershop.entities.BarberServices;
import com.barbershop.services.BarberServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/barber-services")
public class BarberServicesController {

    private final BarberServicesService barberServicesService;

    @Autowired
    public BarberServicesController(BarberServicesService barberServicesService) {
        this.barberServicesService = barberServicesService;
    }

    @GetMapping
    public ResponseEntity<List<BarberServices>> getAllBarberServices() {
        List<BarberServices> barberServices = barberServicesService.getAllBarberServices();
        return ResponseEntity.ok(barberServices);
    }

    @GetMapping("/{barberServiceId}")
    public ResponseEntity<BarberServices> getBarberServiceById(@PathVariable Long barberServiceId) {
        BarberServices barberService = barberServicesService.getBarberServiceById(barberServiceId);
        return ResponseEntity.ok(barberService);
    }

    @PostMapping
    public ResponseEntity<BarberServices> createBarberService(@RequestBody BarberServices barberService) {
        BarberServices createdBarberService = barberServicesService.createBarberService(barberService);
        return ResponseEntity.ok(createdBarberService);
    }

    @DeleteMapping("/{barberServiceId}")
    public ResponseEntity<Void> deleteBarberService(@PathVariable Long barberServiceId) {
        barberServicesService.deleteBarberService(barberServiceId);
        return ResponseEntity.noContent().build();
    }
}
