package com.barbershop.services;

import com.barbershop.entities.BarberShop;
import com.barbershop.exceptions.BarberShopAlreadyExistsException;
import com.barbershop.exceptions.BarberShopNameConflictException;
import com.barbershop.exceptions.BarberShopNotFoundException;
import com.barbershop.repositories.BarberShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberShopService {

    private final BarberShopRepository barberShopRepository;

    @Autowired
    public BarberShopService(BarberShopRepository barberShopRepository) {
        this.barberShopRepository = barberShopRepository;
    }

    public List<BarberShop> getAllBarberShops() {
        return barberShopRepository.findAll();
    }

    public BarberShop getBarberShopById(Long id) {
        return barberShopRepository.findById(id)
                .orElseThrow(() -> new BarberShopNotFoundException("BarberShop not found with ID: " + id));
    }

    public BarberShop createBarberShop(BarberShop barberShop) {
        if (barberShopRepository.existsByName(barberShop.getName())) {
            throw new BarberShopAlreadyExistsException("BarberShop with the same name already exists.");
        }
        return barberShopRepository.save(barberShop);
    }

    public BarberShop updateBarberShop(Long id, BarberShop updatedBarberShop) {
        // Verifica se existe um Barbershop com o ID especificado
        BarberShop existingBarberShop = barberShopRepository.findById(id)
                .orElseThrow(() -> new BarberShopNotFoundException("BarberShop not found with ID: " + id));

        // Verifica se já existe um Barbershop com o mesmo nome (ignorando o caso)
        String updatedName = updatedBarberShop.getName().toLowerCase();
        boolean nameAlreadyExists = barberShopRepository.existsByNameIgnoreCaseAndIdNot(updatedName, id);

        if (nameAlreadyExists) {
            throw new BarberShopNameConflictException("BarberShop with the same name already exists.");
        }

        // Atualiza os campos do Barbershop com base nas informações fornecidas
        existingBarberShop.setName(updatedBarberShop.getName());
        existingBarberShop.setAddress(updatedBarberShop.getAddress());
        existingBarberShop.setPhoneNumber(updatedBarberShop.getPhoneNumber());
        existingBarberShop.setEmail(updatedBarberShop.getEmail());
        existingBarberShop.setPassword(updatedBarberShop.getPassword());
        // Outros campos podem ser atualizados da mesma forma

        // Salva as alterações no banco de dados
        return barberShopRepository.save(existingBarberShop);
    }


    public void deleteBarberShop(Long id) {
        BarberShop existingBarberShop = barberShopRepository.findById(id)
                .orElseThrow(() -> new BarberShopNotFoundException("BarberShop not found with ID: " + id));

        barberShopRepository.delete(existingBarberShop);
    }
}
