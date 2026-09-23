package ua.opnu.labwork2.dto.equipmentcategory;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Запит на додавання категорії обладнання")
public class EquipmentCategoryCreateRequest {

    @Schema(description = "Назва категорії", example = "Велосипеди", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Назва є обов'язковою")
    @Size(min = 2, max = 150, message = "Назва має містити від 2 до 150 символів")
    private String name;

    @Schema(description = "Опис категорії", example = "Різні типи велосипедів", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Опис є обов'язковим")
    @Size(min = 10, max = 2000, message = "Опис має містити від 10 до 2000 символів")
    private String description;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}