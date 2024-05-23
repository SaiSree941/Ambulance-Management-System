package com.example.demo.controller;

import java.util.List;
import com.example.demo.model.Ambulance;
import com.example.demo.service.AmbulanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ambulance")
public class AmbulanceController {

    private final AmbulanceService ambulanceService;

    // @Autowired
    public AmbulanceController(AmbulanceService ambulanceService) {
        this.ambulanceService = ambulanceService;
    }

    @PostMapping("/{ambulanceId}/updateLocation")
    public ResponseEntity<Void> updateAmbulanceLocation(@PathVariable int ambulanceId,
            @RequestParam double latitude,
            @RequestParam double longitude) {
        ambulanceService.updateAmbulanceLocation(ambulanceId, latitude, longitude);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Ambulance>> getAllAmbulances() {
        List<Ambulance> ambulances = ambulanceService.getAmbulances();
        return ResponseEntity.ok(ambulances);
    }
}
