package com.example.demo.controller;

import com.example.demo.service.LocationBasedServices;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class LocationController {

    private final LocationBasedServices locationBasedServices;

    // @Autowired
    public LocationController(LocationBasedServices locationBasedServices) {
        this.locationBasedServices = locationBasedServices;
    }

    @GetMapping("/geocode")
    public ResponseEntity<String> geocode(@RequestParam String address) {
        try {
            String formattedAddress = locationBasedServices.geocode(address);
            return ResponseEntity.ok(formattedAddress);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing geocode request");
        }
    }

    @GetMapping("/reverseGeocode")
    public ResponseEntity<String> reverseGeocode(@RequestParam double latitude, @RequestParam double longitude) {
        try {
            String formattedAddress = locationBasedServices.reverseGeocode(latitude, longitude);
            return ResponseEntity.ok(formattedAddress);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing reverse geocode request");
        }
    }
}
