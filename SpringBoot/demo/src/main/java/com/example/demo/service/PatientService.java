package com.example.demo.service;

import java.util.*;
import com.example.demo.model.*;
//import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private List<Patient> patients = new ArrayList<>();

    public void shareLocation(int patientId, double latitude, double longitude, int priority) {
        // Save the patient's location in the database
        double[] location = { latitude, longitude };
        Patient patient = new Patient(patientId, location, priority);

        patients.add(patient);
    }

    public List<Patient> getPatients() {
        return patients;
    }
}
