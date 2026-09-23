package ua.opnu.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.labwork2.model.EquipmentCategory;
import ua.opnu.labwork2.repository.EquipmentCategoryRepository;

import java.util.List;

@Service
public class EquipmentCategoryService {
    @Autowired
    private EquipmentCategoryRepository categoryRepository;

    public List<EquipmentCategory> getAll() { return categoryRepository.findAll(); }
    public EquipmentCategory getById(Long id) { return categoryRepository.findById(id).orElse(null); }
    public EquipmentCategory create(EquipmentCategory category) { return categoryRepository.save(category); }
    public EquipmentCategory update(Long id, EquipmentCategory details) {
        EquipmentCategory category = getById(id);
        if (category != null) {
            category.setName(details.getName());
            category.setDescription(details.getDescription());
            return categoryRepository.save(category);
        }
        return null;
    }
    public void delete(Long id) { categoryRepository.deleteById(id); }
}
