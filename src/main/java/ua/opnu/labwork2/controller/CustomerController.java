package ua.opnu.labwork2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.ApiErrorResponse;
import ua.opnu.labwork2.dto.customer.CustomerCreateRequest;
import ua.opnu.labwork2.dto.customer.CustomerResponse;
import ua.opnu.labwork2.dto.customer.CustomerUpdateRequest;
import ua.opnu.labwork2.model.Customer;
import ua.opnu.labwork2.service.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
@Tag(name = "Управління клієнтами", description = "Контролер для роботи з клієнтами системи прокату спортивного обладнання")
public class CustomerController {

    @Autowired
    private CustomerService service;

    private CustomerResponse toResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    @Operation(summary = "Отримання всіх клієнтів", description = "Повертає список всіх зареєстрованих клієнтів у системі")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список клієнтів успішно отримано", content = @Content(schema = @Schema(implementation = CustomerResponse.class)))
    })
    @GetMapping
    public List<CustomerResponse> getAll() {
        return service.getAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Отримання клієнта за ідентифікатором", description = "Повертає інформацію про клієнта за його унікальним ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клієнта знайдено", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Клієнта не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getById(@Parameter(description = "Ідентифікатор клієнта", example = "1") @PathVariable Long id) {
        Customer customer = service.getById(id);
        return ResponseEntity.ok(toResponse(customer));
    }

    @Operation(summary = "Реєстрація нового клієнта", description = "Додає нового клієнта до системи. Email має бути унікальним.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Клієнта успішно зареєстровано", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації вхідних даних", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Клієнт з таким email вже існує", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerCreateRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer created = service.create(customer);
        return new ResponseEntity<>(toResponse(created), HttpStatus.CREATED);
    }

    @Operation(summary = "Оновлення даних клієнта", description = "Оновлює профіль існуючого клієнта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані клієнта оновлено", content = @Content(schema = @Schema(implementation = CustomerResponse.class))),
            @ApiResponse(responseCode = "404", description = "Клієнта не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Email вже використовується іншим клієнтом", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@Parameter(description = "Ідентифікатор клієнта", example = "1") @PathVariable Long id,
                                                   @Valid @RequestBody CustomerUpdateRequest request) {
        Customer customer = new Customer();
        customer.setFirstName(request.getFirstName());
        customer.setLastName(request.getLastName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer updated = service.update(id, customer);
        return ResponseEntity.ok(toResponse(updated));
    }

    @Operation(summary = "Видалення клієнта", description = "Видаляє клієнта з системи. Неможливо видалити клієнта з активними орендами.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Клієнта успішно видалено"),
            @ApiResponse(responseCode = "404", description = "Клієнта не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Неможливо видалити клієнта з активними орендами", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Ідентифікатор клієнта для видалення", example = "1") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}