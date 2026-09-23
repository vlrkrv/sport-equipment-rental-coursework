package ua.opnu.labwork2.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")

public class SearchController {
    @GetMapping("/equipment")
    public ResponseEntity<String> searchEquipment(@RequestParam String query) {
        return ResponseEntity.ok("GET /search/equipment?query=" + query + " OK");
    }

    @GetMapping("/customers")
    public ResponseEntity<String> searchCustomers(@RequestParam String query) {
        return ResponseEntity.ok("GET /search/customers?query=" + query + " OK");
    }

    @GetMapping("/rentals")
    public ResponseEntity<String> searchRentals(@RequestParam String query) {
        return ResponseEntity.ok("GET /search/rentals?query=" + query + " OK");
    }
}
