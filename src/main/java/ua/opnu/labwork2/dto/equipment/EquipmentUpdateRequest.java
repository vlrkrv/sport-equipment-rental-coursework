package ua.opnu.labwork2.dto.equipment;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class EquipmentUpdateRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    @Pattern(regexp = ".*\\S.*", message = "Name must not consist only of whitespace")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 2000, message = "Description must be between 10 and 2000 characters")
    private String description;

    @NotBlank(message = "Condition is required")
    @Pattern(regexp = "^(NEW|GOOD|WORNOUT|DAMAGED)$", message = "Condition must be NEW, GOOD, WORNOUT, or DAMAGED")
    private String condition;

    @NotNull(message = "Available is required")
    private Boolean available;
}