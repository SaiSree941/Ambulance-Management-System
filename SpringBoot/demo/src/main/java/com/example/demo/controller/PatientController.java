package com.example.demo.controller;

import java.util.List;
import com.example.demo.model.Patient;
import com.example.demo.service.PatientService;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientService patientService;

    // @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/{patientId}/shareLocation")
    public ResponseEntity<Void> shareLocation(@PathVariable int patientId,
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam int priority) {
        patientService.shareLocation(patientId, latitude, longitude, priority);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getPatients();
        return ResponseEntity.ok(patients);
    }
}
