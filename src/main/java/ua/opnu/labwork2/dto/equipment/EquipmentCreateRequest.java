package ua.opnu.labwork2.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Запит на додавання нового обладнання")
public class EquipmentCreateRequest {

    @Schema(description = "Назва обладнання", example = "Гірський велосипед", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Назва є обов'язковою")
    @Size(min = 2, max = 150, message = "Назва має містити від 2 до 150 символів")
    private String name;

    @Schema(description = "Опис обладнання", example = "Велосипед з 21 швидкістю", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Опис є обов'язковим")
    @Size(min = 10, max = 2000, message = "Опис має містити від 10 до 2000 символів")
    private String description;

    @Schema(description = "Стан обладнання", example = "GOOD", allowableValues = {"NEW", "GOOD", "WORNOUT", "DAMAGED"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Стан є обов'язковим")
    @Pattern(regexp = "^(NEW|GOOD|WORNOUT|DAMAGED)$", message = "Стан має бути: NEW, GOOD, WORNOUT або DAMAGED")
    private String condition;

    @Schema(description = "Доступність", example = "true", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Доступність є обов'язковою")
    private Boolean available;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}