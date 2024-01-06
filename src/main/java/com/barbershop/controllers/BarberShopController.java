package com.barbershop.controllers;

import com.barbershop.entities.BarberShop;
import com.barbershop.services.BarberShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbershops")
public class BarberShopController {

    private final BarberShopService barberShopService;

    @Autowired
    public BarberShopController(BarberShopService barberShopService) {
        this.barberShopService = barberShopService;
    }

    @GetMapping
    public ResponseEntity<List<BarberShop>> getAllBarberShops() {
        List<BarberShop> barberShops = barberShopService.getAllBarberShops();
        return new ResponseEntity<>(barberShops, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarberShop> getBarberShopById(@PathVariable Long id) {
        BarberShop barberShop = barberShopService.getBarberShopById(id);
        return new ResponseEntity<>(barberShop, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BarberShop> createBarberShop(@RequestBody BarberShop barberShop) {
        BarberShop createdBarberShop = barberShopService.createBarberShop(barberShop);
        return new ResponseEntity<>(createdBarberShop, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BarberShop> updateBarberShop(@PathVariable Long id, @RequestBody BarberShop updatedBarberShop) {
        BarberShop updatedShop = barberShopService.updateBarberShop(id, updatedBarberShop);
        return new ResponseEntity<>(updatedShop, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBarberShop(@PathVariable Long id) {
        barberShopService.deleteBarberShop(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
