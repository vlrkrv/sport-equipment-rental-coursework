package ua.opnu.labwork2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.opnu.labwork2.model.Location;
import ua.opnu.labwork2.repository.LocationRepository;

import java.util.List;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAll() { return locationRepository.findAll(); }
    public Location getById(Long id) { return locationRepository.findById(id).orElse(null); }
    public Location create(Location location) { return locationRepository.save(location); }
    public Location update(Long id, Location details) {
        Location location = getById(id);
        if (location != null) {
            location.setName(details.getName());
            location.setCity(details.getCity());
            location.setAddress(details.getAddress());
            return locationRepository.save(location);
        }
        return null;
    }
    public void delete(Long id) { locationRepository.deleteById(id); }
}
