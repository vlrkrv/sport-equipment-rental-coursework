package ua.opnu.labwork2.dto.location;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Запит на додавання нової локації")
public class LocationCreateRequest {

    @Schema(description = "Назва локації", example = "Центральний прокат", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Назва є обов'язковою")
    @Size(min = 2, max = 150, message = "Назва має містити від 2 до 150 символів")
    private String name;

    @Schema(description = "Місто", example = "Одеса", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Місто є обов'язковим")
    @Size(min = 2, max = 150, message = "Місто має містити від 2 до 150 символів")
    private String city;

    @Schema(description = "Адреса", example = "вул. Дерибасівська, 10", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Адреса є обов'язковою")
    @Size(min = 5, max = 200, message = "Адреса має містити від 5 до 200 символів")
    private String address;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}