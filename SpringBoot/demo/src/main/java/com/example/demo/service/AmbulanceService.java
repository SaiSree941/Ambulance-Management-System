package com.example.demo.service;

import java.util.*;
import com.example.demo.model.*;

public class AmbulanceService {

    private List<Ambulance> ambulances = new ArrayList<>();

    public void updateAmbulanceLocation(int ambulanceId, double latitude, double longitude) {
        // Update the location of the ambulance in the database
        for (Ambulance ambulance : ambulances) {
            if (ambulance.getId() == (ambulanceId)) {
                ambulance.setLatitude(latitude);
                ambulance.setLongitude(longitude);
                break;
            }
        }
    }

    public List<Ambulance> getAmbulances() {
        return ambulances;
    }
}