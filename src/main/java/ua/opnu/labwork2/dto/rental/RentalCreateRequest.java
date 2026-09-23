package ua.opnu.labwork2.dto.rental;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "Запит на створення нової оренди")
public class RentalCreateRequest {

    @Schema(description = "Дата початку оренди", example = "2024-12-15", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Дата початку є обов'язковою")
    private LocalDate startDate;

    @Schema(description = "Дата завершення оренди", example = "2024-12-22")
    private LocalDate endDate;

    @Schema(description = "Статус оренди", example = "ACTIVE", allowableValues = {"ACTIVE", "COMPLETED", "CANCELLED"}, requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Статус є обов'язковим")
    @Pattern(regexp = "^(ACTIVE|COMPLETED|CANCELLED)$", message = "Статус має бути: ACTIVE, COMPLETED або CANCELLED")
    private String status;

    @Schema(description = "Ідентифікатор клієнта", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID клієнта є обов'язковим")
    private Long customerId;

    @Schema(description = "Ідентифікатор обладнання", example = "10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID обладнання є обов'язковим")
    private Long equipmentId;

    @Schema(description = "Ідентифікатор локації", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "ID локації є обов'язковим")
    private Long locationId;

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
    public Long getEquipmentId() { return equipmentId; }
    public void setEquipmentId(Long equipmentId) { this.equipmentId = equipmentId; }
    public Long getLocationId() { return locationId; }
    public void setLocationId(Long locationId) { this.locationId = locationId; }
}