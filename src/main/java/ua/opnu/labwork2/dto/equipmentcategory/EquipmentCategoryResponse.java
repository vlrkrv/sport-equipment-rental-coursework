package ua.opnu.labwork2.dto.equipmentcategory;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Відповідь з даними категорії обладнання")
public class EquipmentCategoryResponse {

    @Schema(description = "Ідентифікатор категорії", example = "3", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Назва категорії", example = "Велосипеди")
    private String name;

    @Schema(description = "Опис категорії", example = "Різні типи велосипедів")
    private String description;

    public EquipmentCategoryResponse() {}

    public EquipmentCategoryResponse(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}