package ua.opnu.labwork2.dto.location;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Відповідь з даними локації")
public class LocationResponse {

    @Schema(description = "Ідентифікатор локації", example = "5", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Назва локації", example = "Центральний прокат")
    private String name;

    @Schema(description = "Місто", example = "Одеса")
    private String city;

    @Schema(description = "Адреса", example = "вул. Дерибасівська, 10")
    private String address;

    public LocationResponse() {}

    public LocationResponse(Long id, String name, String city, String address) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}