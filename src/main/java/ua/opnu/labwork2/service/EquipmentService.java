package ua.opnu.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.labwork2.model.Equipment;
import ua.opnu.labwork2.repository.EquipmentRepository;

import java.util.List;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    public List<Equipment> getAll() { return equipmentRepository.findAll(); }
    public Equipment getById(Long id) { return equipmentRepository.findById(id).orElse(null); }
    public Equipment create(Equipment equipment) { return equipmentRepository.save(equipment); }
    public Equipment update(Long id, Equipment details) {
        Equipment equipment = getById(id);
        if (equipment != null) {
            equipment.setName(details.getName());
            equipment.setDescription(details.getDescription());
            equipment.setCondition(details.getCondition());
            equipment.setAvailable(details.getAvailable());
            return equipmentRepository.save(equipment);
        }
        return null;
    }
    public void delete(Long id) { equipmentRepository.deleteById(id); }
}