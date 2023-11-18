package com.barbershop.servicies;

import com.barbershop.entities.Barber;
import com.barbershop.exceptions.*;
import com.barbershop.repositories.BarberRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    private final BarberRepository barberRepository;
    private static final Logger auditLogger = LoggerFactory.getLogger(BarberService.class);



    @Autowired
    public BarberService(BarberRepository barberRepository) {
        this.barberRepository = barberRepository;
    }

    public Barber createBarber(Barber barber) {
        // Verificar se já existe um barbeiro com o mesmo e-mail
        Barber existingBarberByEmail = (Barber) barberRepository.findByEmail(barber.getEmail()).orElse(null);
        if (existingBarberByEmail != null) {
            throw new BarberAlreadyExistsException("Barbeiro com o mesmo e-mail já cadastrado.");
        }

        // Verificar se já existe um barbeiro com o mesmo telefone
        Barber existingBarberByPhone = (Barber) barberRepository.findByPhone(barber.getPhone()).orElse(null);
        if (existingBarberByPhone != null) {
            throw new BarberAlreadyExistsException("Barbeiro com o mesmo telefone já cadastrado.");
        }

        // Se não houver barbeiros com o mesmo e-mail ou telefone, crie o barbeiro
        return barberRepository.save(barber);
    }

    public Barber updateBarber(Long barberId, Barber updatedBarber) {
        Barber existingBarber = barberRepository.findById(barberId)
                .orElseThrow(() -> new BarberNotFoundException("Barbeiro não encontrado com o ID: " + barberId));

        // Realize validações dos campos atualizados, se necessário
        if (!isValidEmail(updatedBarber.getEmail())) {
            throw new InvalidEmailException("Endereço de e-mail inválido.");
        }

        // Verificar conflitos (por exemplo, se o novo e-mail já existe)
        Barber existingBarberByEmail = (Barber) barberRepository.findByEmail(updatedBarber.getEmail())
                .orElse(null);
        if (existingBarberByEmail != null && !existingBarberByEmail.getId().equals(barberId)) {
            throw new EmailConflictException("Este e-mail já está em uso por outro barbeiro.");
        }

        // Registre a atualização no log de auditoria
        auditLogger.info("Barbeiro com ID {} atualizado em {} por {} em {}."
        );

        // Atualize os campos do barbeiro com base nas informações fornecidas
        existingBarber.setName(updatedBarber.getName());
        existingBarber.setEmail(updatedBarber.getEmail());
        existingBarber.setPhone(updatedBarber.getPhone());
        // Outros campos podem ser atualizados da mesma forma

        // Salve as alterações no banco de dados
        return barberRepository.save(existingBarber);
    }

    // Outros métodos

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public void deleteBarber(Long barberId) {
        // Verificar se o barbeiro com o ID especificado existe
        Barber existingBarber = barberRepository.findById(barberId)
                .orElseThrow(() -> new BarberNotFoundException("Barbeiro não encontrado com o ID: " + barberId));

        // Verificar se o barbeiro pode excluir sua própria conta
        if (isUserAuthorizedToDeleteBarber(existingBarber, barberId)) {
            // Exclua o barbeiro do banco de dados
            barberRepository.delete(existingBarber);
        } else {
            throw new AccessDeniedException("Você não tem permissão para excluir esta conta.");
        }
    }

    // Método de verificação de permissão (simplificado)
    private boolean isUserAuthorizedToDeleteBarber(Barber barber, Long barberId) {
        // Verifique se o ID do barbeiro é igual ao ID do barbeiro que está sendo excluído
        return barber.getId().equals(barberId);
    }

    public Barber getBarberById(Long barberId) {
        // Verificar se o barbeiro com o ID especificado existe
        return barberRepository.findById(barberId)
                .orElseThrow(() -> new BarberNotFoundException("Barbeiro não encontrado com o ID: " + barberId));
    }

    public List<Barber> getAllBarbers() {
        // Implemente a lógica para buscar todos os barbeiros
        return barberRepository.findAll();
    }
}
