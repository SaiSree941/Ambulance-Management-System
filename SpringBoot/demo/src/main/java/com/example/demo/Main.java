package com.example.demo;

import com.example.demo.model.Ambulance;
import com.example.demo.model.Patient;
import com.example.demo.service.DispatchModule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class })

public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args); // Start the Spring Boot application

        // After the Spring Boot application has started, perform initialization tasks
        initializeDispatchModule();
    }

    private static void initializeDispatchModule() {
        // Instantiate DispatchModule
        DispatchModule dispatch = new DispatchModule();

        // Add some ambulances
        dispatch.addAmbulance(new Ambulance(1, new double[] { 10, 20 }));
        dispatch.addAmbulance(new Ambulance(2, new double[] { 15, 25 }));
        dispatch.addAmbulance(new Ambulance(3, new double[] { 30, 40 }));

        // Add some patients to the queue
        dispatch.addPatient(new Patient(1, new double[] { 12, 22 }, 5));
        dispatch.addPatient(new Patient(2, new double[] { 35, 45 }, 3));
        dispatch.addPatient(new Patient(3, new double[] { 18, 28 }, 2));

        // Dispatch an ambulance
        dispatch.dispatchAmbulance();
    }
}
