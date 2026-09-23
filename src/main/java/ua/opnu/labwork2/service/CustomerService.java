package ua.opnu.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ua.opnu.labwork2.exception.ConflictOperationException;
import ua.opnu.labwork2.exception.DuplicateResourceException;
import ua.opnu.labwork2.exception.ResourceNotFoundException;
import ua.opnu.labwork2.model.Customer;
import ua.opnu.labwork2.repository.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    public Customer getById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
    }

    // Бізнес-правило 1: Не можна створювати записи з email, який уже існує
    public Customer create(Customer customer) {
        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new DuplicateResourceException("Customer with email " + customer.getEmail() + " already exists");
        }
        try {
            return customerRepository.save(customer);
        } catch (DataIntegrityViolationException e) {
            throw new ConflictOperationException("Failed to create customer: " + e.getMessage());
        }
    }

    public Customer update(Long id, Customer details) {
        Customer customer = getById(id);

        if (!customer.getEmail().equals(details.getEmail()) &&
                customerRepository.existsByEmail(details.getEmail())) {
            throw new DuplicateResourceException("Customer with email " + details.getEmail() + " already exists");
        }

        customer.setFirstName(details.getFirstName());
        customer.setLastName(details.getLastName());
        customer.setEmail(details.getEmail());
        customer.setPhone(details.getPhone());
        return customerRepository.save(customer);
    }

    // Бізнес-правило 4: Не можна видаляти сутність, якщо є активні оренди
    public void delete(Long id) {
        Customer customer = getById(id);
        boolean hasActiveRentals = customer.getRentals().stream()
                .anyMatch(rental -> "ACTIVE".equals(rental.getStatus()));
        if (hasActiveRentals) {
            throw new ConflictOperationException("Cannot delete customer with active rentals");
        }
        customerRepository.deleteById(id);
    }
}