package ua.opnu.labwork2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.labwork2.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}
