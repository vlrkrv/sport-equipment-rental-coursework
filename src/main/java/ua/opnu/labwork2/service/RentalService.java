package ua.opnu.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.labwork2.exception.BadRequestException;
import ua.opnu.labwork2.exception.ConflictOperationException;
import ua.opnu.labwork2.exception.ResourceNotFoundException;
import ua.opnu.labwork2.model.Customer;
import ua.opnu.labwork2.model.Equipment;
import ua.opnu.labwork2.model.Location;
import ua.opnu.labwork2.model.Rental;
import ua.opnu.labwork2.repository.RentalRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class RentalService {

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private EquipmentService equipmentService;

    @Autowired
    private LocationService locationService;

    public List<Rental> getAll() {
        return rentalRepository.findAll();
    }

    public Rental getById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + id));
    }

    // Бізнес-правило 2: Перевірка існування пов'язаних сутностей
    // Бізнес-правило 3: Не можна створювати дублікати для активної оренди
    // Бізнес-правило 5: Валідація дат
    public Rental createFull(Rental rental, Long customerId, Long equipmentId, Long locationId) {
        Customer customer = customerService.getById(customerId);
        Equipment equipment = equipmentService.getById(equipmentId);
        Location location = locationService.getById(locationId);

        // Бізнес-правило 5: Перевірка дат
        if (rental.getStartDate() == null) {
            throw new BadRequestException("Start date is required");
        }
        if (rental.getStartDate().isBefore(LocalDate.now())) {
            throw new BadRequestException("Start date cannot be in the past");
        }
        if (rental.getEndDate() != null && rental.getEndDate().isBefore(rental.getStartDate())) {
            throw new BadRequestException("End date cannot be before start date");
        }

        // Бізнес-правило 3: Перевірка на активну оренду того ж обладнання
        boolean hasActiveRental = equipment.getRentals().stream()
                .anyMatch(r -> "ACTIVE".equals(r.getStatus()));
        if (hasActiveRental) {
            throw new ConflictOperationException("Equipment is already rented");
        }

        rental.setCustomer(customer);
        rental.setEquipment(equipment);
        rental.setLocation(location);
        rental.setStatus("ACTIVE");
        return rentalRepository.save(rental);
    }

    public Rental update(Long id, Rental details) {
        Rental rental = getById(id);

        if (details.getStartDate() != null) {
            if (details.getStartDate().isBefore(LocalDate.now())) {
                throw new BadRequestException("Start date cannot be in the past");
            }
            rental.setStartDate(details.getStartDate());
        }

        if (details.getEndDate() != null) {
            if (details.getEndDate().isBefore(rental.getStartDate())) {
                throw new BadRequestException("End date cannot be before start date");
            }
            rental.setEndDate(details.getEndDate());
        }

        if (details.getStatus() != null) {
            rental.setStatus(details.getStatus());
        }

        return rentalRepository.save(rental);
    }

    public List<Rental> getByCustomer(Long customerId) {
        return rentalRepository.findByCustomerId(customerId);
    }

    public List<Rental> getActive() {
        return rentalRepository.findByStatus("ACTIVE");
    }

    public void complete(Long id) {
        Rental rental = getById(id);
        rental.setStatus("COMPLETED");
        rentalRepository.save(rental);
    }
}