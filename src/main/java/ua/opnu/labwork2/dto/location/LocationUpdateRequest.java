package ua.opnu.labwork2.dto.location;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class LocationUpdateRequest {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 150, message = "Name must be between 2 and 150 characters")
    @Pattern(regexp = ".*\\S.*", message = "Name must not consist only of whitespace")
    private String name;

    @NotBlank(message = "City is required")
    @Size(min = 2, max = 150, message = "City must be between 2 and 150 characters")
    @Pattern(regexp = ".*\\S.*", message = "City must not consist only of whitespace")
    private String city;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 200, message = "Address must be between 5 and 200 characters")
    @Pattern(regexp = ".*\\S.*", message = "Address must not consist only of whitespace")
    private String address;
}