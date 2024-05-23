package com.example.demo.service;

import com.example.demo.model.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;

@WebServlet("/patient_form")
public class FormServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract form data
        String location = request.getParameter("location");
        String accident_type = request.getParameter("accident-type");
        String apiKey = "Fr2KNLtGCw9nislPdWrsOm1BxenP9XNX";
        LocationBasedServices lbs = new LocationBasedServices(apiKey);
        // Create DTO object
        String[] coordinates = location.split(",");
        int priority, id;
        if (coordinates.length > 2) {
            coordinates = lbs.geocode(location).split(",");
        }
        double latitude = Double.parseDouble(coordinates[0]);
        double longitude = Double.parseDouble(coordinates[1]);
        double[] patientlocation = { latitude, longitude };
        Patient patient = new Patient(id, patientlocation, priority);
        // Perform business logic
        // For example, save patient to database
        // Return response
        response.getWriter().println("Form submitted successfully!");
    }
}
