package ua.opnu.labwork2.dto.equipmentcategory;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EquipmentCategoryUpdateRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    @Pattern(regexp = ".*\\S.*", message = "Name must not consist only of whitespace")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    private String description;
}