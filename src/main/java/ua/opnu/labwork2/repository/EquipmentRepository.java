package ua.opnu.labwork2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.opnu.labwork2.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
