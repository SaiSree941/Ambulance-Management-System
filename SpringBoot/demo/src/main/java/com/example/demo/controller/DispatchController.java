package com.example.demo.controller;

//import com.example.demo.model.Ambulance;
import com.example.demo.model.Patient;
import com.example.demo.service.DispatchModule;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispatch")
public class DispatchController {

    private final DispatchModule dispatchModule;

    // @Autowired
    public DispatchController(DispatchModule dispatchModule) {
        this.dispatchModule = dispatchModule;
    }

    @PostMapping("/addPatient")
    public ResponseEntity<String> addPatient(@RequestBody Patient patient) {
        dispatchModule.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body("Patient added");
    }

    @GetMapping("/dispatch")
    public ResponseEntity<String> dispatchAmbulance() {
        dispatchModule.dispatchAmbulance();
        return ResponseEntity.ok("Ambulance dispatched");
    }
}
