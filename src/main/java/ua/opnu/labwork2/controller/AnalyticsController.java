package ua.opnu.labwork2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")

public class AnalyticsController {
    @GetMapping("/equipment/count")
    public ResponseEntity<String> getEquipmentCount() {
        return ResponseEntity.ok("GET /analytics/equipment/count OK");
    }

    @GetMapping("/customers/count")
    public ResponseEntity<String> getCustomersCount() {
        return ResponseEntity.ok("GET /analytics/customers/count OK");
    }

    @GetMapping("/rentals/active")
    public ResponseEntity<String> getActiveRentals() {
        return ResponseEntity.ok("GET /analytics/rentals/active OK");
    }

    @GetMapping("/equipment/by-category")
    public ResponseEntity<String> getEquipmentByCategory() {
        return ResponseEntity.ok("GET /analytics/equipment/by-category OK");
    }

    @GetMapping("/locations/workload")
    public ResponseEntity<String> getLocationsWorkload() {
        return ResponseEntity.ok("GET /analytics/locations/workload OK");
    }
}
