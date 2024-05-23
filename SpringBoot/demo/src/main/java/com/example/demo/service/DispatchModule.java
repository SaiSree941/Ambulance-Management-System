package com.example.demo.service;

import com.example.demo.model.Ambulance;
import com.example.demo.model.Patient;

import java.util.ArrayList;
import java.util.List;

public class DispatchModule {
    private List<Ambulance> ambulances;
    private List<Patient> patientsQueue;

    public DispatchModule() {
        this.ambulances = new ArrayList<>();
        this.patientsQueue = new ArrayList<>();
    }

    public void addAmbulance(Ambulance ambulance) {
        ambulances.add(ambulance);
    }

    public void addPatient(Patient patient) {
        patientsQueue.add(patient);
    }

    public void dispatchAmbulance() {
        if (patientsQueue.isEmpty()) {
            System.out.println("No pending requests");
            return;
        }

        Ambulance nearestAmbulance = null;
        double minDistance = Double.POSITIVE_INFINITY;

        for (Ambulance ambulance : ambulances) {
            if (ambulance.isAvailable()) {
                double distance = calculateDistance(ambulance.getLocation(), patientsQueue.get(0).getLocation());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestAmbulance = ambulance;
                }
            }
        }

        if (nearestAmbulance != null) {
            Patient patient = patientsQueue.remove(0);
            nearestAmbulance.setAvailable(false);
            System.out.println("Ambulance " + nearestAmbulance.getId() + " dispatched to Patient " + patient.getId());
        }
    }

    private double calculateDistance(double[] loc1, double[] loc2) {
        double deltaX = loc1[0] - loc2[0];
        double deltaY = loc1[1] - loc2[1];
        return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
