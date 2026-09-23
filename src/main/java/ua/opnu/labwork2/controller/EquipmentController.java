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
import ua.opnu.labwork2.model.Equipment;
import ua.opnu.labwork2.service.EquipmentService;

import java.util.List;

@RestController
@RequestMapping("/equipment")
@Tag(name = "Управління обладнанням", description = "Контролер для роботи зі спортивним обладнанням")
public class EquipmentController {
    @Autowired
    private EquipmentService service;

    @Operation(summary = "Отримання всього обладнання", description = "Повертає список всього спортивного обладнання в системі")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список обладнання отримано", content = @Content(schema = @Schema(implementation = Equipment.class)))
    })
    @GetMapping
    public List<Equipment> getAll() { return service.getAll(); }

    @Operation(summary = "Отримання обладнання за ID", description = "Повертає інформацію про обладнання за його ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Обладнання знайдено", content = @Content(schema = @Schema(implementation = Equipment.class))),
            @ApiResponse(responseCode = "404", description = "Обладнання не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipment> getById(@Parameter(description = "Ідентифікатор обладнання", example = "1") @PathVariable Long id) {
        Equipment equipment = service.getById(id);
        return equipment != null ? ResponseEntity.ok(equipment) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Додавання нового обладнання", description = "Додає новий екземпляр спортивного обладнання до системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Обладнання додано", content = @Content(schema = @Schema(implementation = Equipment.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<Equipment> create(@RequestBody Equipment equipment) {
        return new ResponseEntity<>(service.create(equipment), HttpStatus.CREATED);
    }

    @Operation(summary = "Оновлення даних обладнання", description = "Оновлює інформацію про існуюче обладнання")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані оновлено", content = @Content(schema = @Schema(implementation = Equipment.class))),
            @ApiResponse(responseCode = "404", description = "Обладнання не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<Equipment> update(@Parameter(description = "Ідентифікатор обладнання", example = "1") @PathVariable Long id,
                                            @RequestBody Equipment equipment) {
        Equipment updated = service.update(id, equipment);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Видалення обладнання", description = "Видаляє обладнання з системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Обладнання видалено"),
            @ApiResponse(responseCode = "404", description = "Обладнання не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Ідентифікатор обладнання для видалення", example = "1") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Отримання обладнання за локацією", description = "Повертає список обладнання у вказаній локації")
    @GetMapping("/location/{locationId}")
    public ResponseEntity<List<Equipment>> getByLocation(@Parameter(description = "Ідентифікатор локації", example = "1") @PathVariable Long locationId) {
        return ResponseEntity.ok(List.of());
    }
}