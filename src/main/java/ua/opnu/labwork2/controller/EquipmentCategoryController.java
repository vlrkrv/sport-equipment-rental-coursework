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
import ua.opnu.labwork2.model.EquipmentCategory;
import ua.opnu.labwork2.service.EquipmentCategoryService;

import java.util.List;

@RestController
@RequestMapping("/equipment-categories")
@Tag(name = "Управління категоріями обладнання", description = "Контролер для роботи з категоріями спортивного обладнання")
public class EquipmentCategoryController {
    @Autowired
    private EquipmentCategoryService service;

    @Operation(summary = "Отримання всіх категорій", description = "Повертає список всіх категорій обладнання")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список категорій отримано", content = @Content(schema = @Schema(implementation = EquipmentCategory.class)))
    })
    @GetMapping
    public List<EquipmentCategory> getAll() { return service.getAll(); }

    @Operation(summary = "Отримання категорії за ID", description = "Повертає інформацію про категорію за її ідентифікатором")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категорію знайдено", content = @Content(schema = @Schema(implementation = EquipmentCategory.class))),
            @ApiResponse(responseCode = "404", description = "Категорію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EquipmentCategory> getById(@Parameter(description = "Ідентифікатор категорії", example = "1") @PathVariable Long id) {
        EquipmentCategory category = service.getById(id);
        return category != null ? ResponseEntity.ok(category) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Додавання нової категорії", description = "Додає нову категорію обладнання до системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Категорію додано", content = @Content(schema = @Schema(implementation = EquipmentCategory.class))),
            @ApiResponse(responseCode = "400", description = "Помилка валідації даних", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<EquipmentCategory> create(@RequestBody EquipmentCategory category) {
        return new ResponseEntity<>(service.create(category), HttpStatus.CREATED);
    }

    @Operation(summary = "Оновлення даних категорії", description = "Оновлює інформацію про існуючу категорію")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Дані оновлено", content = @Content(schema = @Schema(implementation = EquipmentCategory.class))),
            @ApiResponse(responseCode = "404", description = "Категорію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EquipmentCategory> update(@Parameter(description = "Ідентифікатор категорії", example = "1") @PathVariable Long id,
                                                    @RequestBody EquipmentCategory category) {
        EquipmentCategory updated = service.update(id, category);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Видалення категорії", description = "Видаляє категорію обладнання з системи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Категорію видалено"),
            @ApiResponse(responseCode = "404", description = "Категорію не знайдено", content = @Content(schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@Parameter(description = "Ідентифікатор категорії для видалення", example = "1") @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}