package ua.opnu.labwork2.dto.rental;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Schema(description = "Відповідь з даними про оренду")
public class RentalResponse {

    @Schema(description = "Ідентифікатор оренди", example = "100", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Дата початку оренди", example = "2024-12-15")
    private LocalDate startDate;

    @Schema(description = "Дата завершення оренди", example = "2024-12-22")
    private LocalDate endDate;

    @Schema(description = "Статус оренди", example = "ACTIVE")
    private String status;

    @Schema(description = "Ідентифікатор клієнта", example = "1")
    private Long customerId;

    @Schema(description = "Ідентифікатор обладнання", example = "10")
    private Long equipmentId;

    @Schema(description = "Ідентифікатор локації", example = "5")
    private Long locationId;

    public RentalResponse() {}

    public RentalResponse(Long id, LocalDate startDate, LocalDate endDate, String status, Long customerId, Long equipmentId, Long locationId) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.customerId = customerId;
        this.equipmentId = equipmentId;
        this.locationId = locationId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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