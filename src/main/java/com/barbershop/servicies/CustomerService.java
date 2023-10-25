package com.barbershop.servicies;

import com.barbershop.entities.Customer;
import com.barbershop.exceptions.*;
import com.barbershop.repositories.CustomerRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private static final Logger auditLogger = (Logger) LoggerFactory.getLogger("audit");


    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
// Verificar se já existe um cliente com o mesmo e-mail
        Customer existingCustomerByEmail = customerRepository.findByEmail(customer.getEmail()).orElse(null);
        if (existingCustomerByEmail != null) {
            throw new CustomerAlreadyExistsException("Cliente com o mesmo e-mail já cadastrado.");
        }

        // Verificar se já existe um cliente com o mesmo telefone
        Customer existingCustomerByPhone = customerRepository.findByPhone(customer.getPhone()).orElse(null);
        if (existingCustomerByPhone != null) {
            throw new CustomerAlreadyExistsException("Cliente com o mesmo telefone já cadastrado.");
        }

        // Se não houver clientes com o mesmo e-mail ou telefone, crie o cliente
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + customerId));

        // Realize validações dos campos atualizados, se necessário
        if (!isValidEmail(updatedCustomer.getEmail())) {
            throw new InvalidEmailException("Endereço de e-mail inválido.");
        }

        // Verificar conflitos (por exemplo, se o novo e-mail já existe)
        Customer existingCustomerByEmail = customerRepository.findByEmail(updatedCustomer.getEmail())
                .orElse(null);
        if (existingCustomerByEmail != null && !existingCustomerByEmail.getId().equals(customerId)) {
            throw new EmailConflictException("Este e-mail já está em uso por outro cliente.");
        }

        // Registre a atualização no log de auditoria
        auditLogger.info("Cliente com ID {} atualizado em {} por {}."
        );

        // Atualize os campos do cliente com base nas informações fornecidas
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setEmail(updatedCustomer.getEmail());
        existingCustomer.setPhone(updatedCustomer.getPhone());
        // Outros campos podem ser atualizados da mesma forma

        // Salve as alterações no banco de dados
        return customerRepository.save(existingCustomer);
    }

    // Outros métodos

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }

    public void deleteCustomer(Long customerId) {
// Verificar se o cliente com o ID especificado existe
        Customer existingCustomer = customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + customerId));

        // Verificar se o cliente pode excluir a própria conta
        if (isUserAuthorizedToDeleteCustomer(existingCustomer, customerId)) {
            // Exclua o cliente do banco de dados
            customerRepository.delete(existingCustomer);
        } else {
            throw new AccessDeniedException("Você não tem permissão para excluir esta conta.");
        }
    }
    // Método de verificação de permissão (simplificado)
    private boolean isUserAuthorizedToDeleteCustomer(Customer customer, Long customerId) {
        // Verifique se o ID do cliente é igual ao ID do cliente que está sendo excluído
        return customer.getId().equals(customerId);
    }


    public Customer getCustomerById(Long customerId) {
        // Verificar se o cliente com o ID especificado existe
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Cliente não encontrado com o ID: " + customerId));
    }

    public List<Customer> getAllCustomers() {
        // Implemente a lógica para buscar todos os clientes
        return customerRepository.findAll();
    }
}
