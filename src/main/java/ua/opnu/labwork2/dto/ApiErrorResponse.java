package ua.opnu.labwork2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;
import java.util.Map;

@Schema(description = "Стандартизована відповідь про помилку")
public class ApiErrorResponse {

    @Schema(description = "Час виникнення помилки", example = "2024-12-15T10:30:00")
    private LocalDateTime timestamp;

    @Schema(description = "HTTP статус-код", example = "400")
    private int status;

    @Schema(description = "Тип помилки", example = "Помилка валідації")
    private String error;

    @Schema(description = "Детальний опис помилки", example = "Перевірка вхідних даних не пройдена")
    private String message;

    @Schema(description = "Шлях до ендпоїнту", example = "/customers")
    private String path;

    @Schema(description = "Деталі помилок валідації", example = "{\"email\": \"Електронна пошта має бути коректною\"}")
    private Map<String, String> errors;

    public ApiErrorResponse() {}

    public ApiErrorResponse(LocalDateTime timestamp, int status, String error, String message, String path, Map<String, String> errors) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
    public String getError() { return error; }
    public void setError(String error) { this.error = error; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public Map<String, String> getErrors() { return errors; }
    public void setErrors(Map<String, String> errors) { this.errors = errors; }
}