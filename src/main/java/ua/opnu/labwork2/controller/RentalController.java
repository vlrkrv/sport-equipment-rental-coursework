package ua.opnu.labwork2.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.opnu.labwork2.dto.ApiErrorResponse;
import ua.opnu.labwork2.model.Rental;
import ua.opnu.labwork2.service.RentalService;

import java.util.List;

@RestController
@RequestMapping("/rentals")
@Tag(name = "Управління орендою", description = "Контролер для роботи з орендою спортивного обладнання")
public class RentalController {

    @Autowired
    private RentalService rentalService;

    @Operation(summary = "Отримання всіх оренд", description = "Повертає список всіх оренд у системі")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список оренд отримано", content = @Content(schema = @Schema(implementation = Rental.class)))
    })
    @GetMapping
    public List<Rental> getAll() {
        return rentalService.getAll();
    }

    @Operation(summary = "Отримання оренди за ID", description = "Повертає інформацію про оренду за її ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Оренду знайдено", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "404", description = "Оренду не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Rental> getById(@Parameter(description = "Ідентифікатор оренди", example = "1") @PathVariable Long id) {
        Rental rental = rentalService.getById(id);
        return ResponseEntity.ok(rental);
    }

    @Operation(summary = "Створення нової оренди", description = "Оформлює нову оренду обладнання для клієнта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Оренду створено", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Клієнта, обладнання або локацію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Обладнання вже в активній оренді", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Rental> create(@RequestBody Rental rental,
                                         @RequestParam Long customerId,
                                         @RequestParam Long equipmentId,
                                         @RequestParam Long locationId) {
        Rental created = rentalService.createFull(rental, customerId, equipmentId, locationId);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "Оновлення оренди", description = "Оновлює дані існуючої оренди")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Оренду оновлено", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "404", description = "Оренду не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Rental> update(@Parameter(description = "Ідентифікатор оренди", example = "1") @PathVariable Long id,
                                         @RequestBody Rental rental) {
        Rental updated = rentalService.update(id, rental);
        return ResponseEntity.ok(updated);
    }

    @Operation(summary = "Завершення оренди", description = "Змінює статус оренди на ЗАВЕРШЕНО")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Оренду завершено"),
            @ApiResponse(responseCode = "404", description = "Оренду не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> complete(@Parameter(description = "Ідентифікатор оренди для завершення", example = "1") @PathVariable Long id) {
        rentalService.complete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримання активних оренд", description = "Повертає список всіх активних оренд")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список активних оренд отримано", content = @Content(schema = @Schema(implementation = Rental.class)))
    })
    @GetMapping("/active")
    public ResponseEntity<List<Rental>> getActive() {
        return ResponseEntity.ok(rentalService.getActive());
    }
}