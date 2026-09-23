package ua.opnu.labwork2.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Локальний сервер розробки")
                ))
                .info(new Info()
                        .title("Система оренди спортивного обладнання REST API")
                        .version("1.0.0")
                        .description("""
                                REST API для автоматизації роботи системи прокату спортивного обладнання.
                                Система забезпечує управління клієнтами, обладнанням, категоріями обладнання та локаціями прокату.
                                Також система надає можливість оформлення та відстеження оренди, аналітики та метрики системи,
                                пошуку за різними критеріями та моніторингу стану сервісу. У розробці використано такі технології:
                                Spring Boot 3.2.5, Spring Data JPA з Hibernate,PostgreSQL, Bean Validation з Hibernate Validator, SpringDoc OpenAPI 2.5.0 та Lombok.
                                Система дотримується наступних бізнес-правил: email клієнта має бути унікальним, не можна видалити клієнта з активними орендами, обладнання не може бути орендоване повторно,
                                якщо воно вже в активній оренді, дата початку оренди не може бути в минулому, а дата завершення не може бути раніше дати початку.
                                """)
                        .contact(new Contact()
                                .name("Кафедра комп'ютерних систем ОНПУ")
                                .email("support@op.edu.ua")
                                .url("https://op.edu.ua"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                );
    }
}