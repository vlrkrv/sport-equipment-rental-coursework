package ua.opnu.labwork2.dto.equipment;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Відповідь з даними обладнання")
public class EquipmentResponse {

    @Schema(description = "Ідентифікатор обладнання", example = "10", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Назва обладнання", example = "Гірський велосипед")
    private String name;

    @Schema(description = "Опис обладнання", example = "Велосипед з 21 швидкістю")
    private String description;

    @Schema(description = "Стан обладнання", example = "GOOD")
    private String condition;

    @Schema(description = "Доступність", example = "true")
    private Boolean available;

    public EquipmentResponse() {}

    public EquipmentResponse(Long id, String name, String description, String condition, Boolean available) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.condition = condition;
        this.available = available;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
}