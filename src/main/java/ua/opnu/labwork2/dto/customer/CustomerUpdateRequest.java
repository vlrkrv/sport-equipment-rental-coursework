package ua.opnu.labwork2.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Запит на оновлення даних клієнта")
public class CustomerUpdateRequest {

    @Schema(description = "Ім'я клієнта", example = "Іван", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Ім'я є обов'язковим")
    @Size(min = 2, max = 50, message = "Ім'я має містити від 2 до 50 символів")
    private String firstName;

    @Schema(description = "Прізвище клієнта", example = "Петренко", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Прізвище є обов'язковим")
    @Size(min = 2, max = 50, message = "Прізвище має містити від 2 до 50 символів")
    private String lastName;

    @Schema(description = "Електронна пошта", example = "ivan.petrenko@example.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Електронна пошта є обов'язковою")
    @Email(message = "Електронна пошта має бути коректною")
    private String email;

    @Schema(description = "Номер телефону", example = "+380501234567", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "Номер телефону є обов'язковим")
    @Pattern(regexp = "^[\\d+\\s]{10,20}$", message = "Телефон має містити 10-20 цифр")
    private String phone;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}