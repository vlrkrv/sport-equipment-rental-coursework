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
import ua.opnu.labwork2.model.Location;
import ua.opnu.labwork2.service.LocationService;

import java.util.List;

@RestController
@RequestMapping("/locations")
@Tag(name = "Управління локаціями", description = "Контролер для роботи з локаціями прокату спортивного обладнання")
public class LocationController {
    @Autowired
    private LocationService service;

    @Operation(summary = "Отримання всіх локацій", description = "Повертає список всіх локацій прокату")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список локацій отримано", content = @Content(schema = @Schema(implementation = Location.class)))
    })
    @GetMapping
    public List<Location> getAll() { return service.getAll(); }

    @Operation(summary = "Отримання локації за ID", description = "Повертає інформацію про локацію за її ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Локацію знайдено", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Локацію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Location> getById(@Parameter(description = "Ідентифікатор локації", example = "1") @PathVariable Long id) {
        Location location = service.getById(id);
        return location != null ? ResponseEntity.ok(location) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Додавання нової локації", description = "Додає нову локацію прокату до системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Локацію додано", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Location> create(@RequestBody Location location) {
        return new ResponseEntity<>(service.create(location), HttpStatus.CREATED);
    }

    @Operation(summary = "Оновлення даних локації", description = "Оновлює інформацію про існуючу локацію")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані оновлено", content = @Content(schema = @Schema(implementation = Location.class))),
            @ApiResponse(responseCode = "404", description = "Локацію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Location> update(@Parameter(description = "Ідентифікатор локації", example = "1") @PathVariable Long id,
                                           @RequestBody Location location) {
        Location updated = service.update(id, location);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Видалення локації", description = "Видаляє локацію прокату з системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Локацію видалено"),
            @ApiResponse(responseCode = "404", description = "Локацію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Ідентифікатор локації для видалення", example = "1") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримання обладнання локації", description = "Повертає список обладнання у вказаній локації")
    @GetMapping("/{id}/equipment")
    public ResponseEntity<List<?>> getEquipment(@Parameter(description = "Ідентифікатор локації", example = "1") @PathVariable Long id) {
        return ResponseEntity.ok(List.of());
    }
}