package ua.opnu.labwork2.dto.rental;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RentalUpdateRequest {
    private LocalDate startDate;
    private LocalDate endDate;

    @Pattern(regexp = "^(ACTIVE|COMPLETED|CANCELLED)$", message = "Status must be ACTIVE, COMPLETED, or CANCELLED")
    private String status;
}