package ua.opnu.labwork2.dto.customer;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Відповідь з даними клієнта")
public class CustomerResponse {

    @Schema(description = "Унікальний ідентифікатор клієнта", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Ім'я клієнта", example = "Іван")
    private String firstName;

    @Schema(description = "Прізвище клієнта", example = "Петренко")
    private String lastName;

    @Schema(description = "Електронна пошта", example = "ivan.petrenko@example.com")
    private String email;

    @Schema(description = "Номер телефону", example = "+380501234567")
    private String phone;

    public CustomerResponse() {}

    public CustomerResponse(Long id, String firstName, String lastName, String email, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}