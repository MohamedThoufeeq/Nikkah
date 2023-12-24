package com.nikkah.app.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nikkah.app.model.MatrimonyProfile;
import com.nikkah.app.model.MatrimonyProfilesResponse;
import com.nikkah.app.repo.MatrimonyProfileRepository;
import com.nikkah.app.service.MatrimonyProfileService;

@RestController
@RequestMapping("/api")
public class MatrimonyProfileController {

    @Autowired
    MatrimonyProfileRepository matrimonyProfileRepository;

    @Autowired
    private MatrimonyProfileService matrimonyProfileService;

    // Create a new Matrimony Profile
    @PostMapping
    public ResponseEntity<MatrimonyProfile> createMatrimonyProfile(@RequestBody MatrimonyProfile matrimonyProfile) {
        MatrimonyProfile createdProfile = matrimonyProfileService.createMatrimonyProfile(matrimonyProfile);
        return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
    }

    // Retrieve all Matrimony Profiles
    @GetMapping
    public ResponseEntity<MatrimonyProfilesResponse> getAllMatrimonyProfiles() {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getAllMatrimonyProfiles();
        MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
        response.setProfiles(profiles);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Retrieve a specific Matrimony Profile by ID
    @GetMapping("/{id}")
    public ResponseEntity<MatrimonyProfile> getMatrimonyProfileById(@PathVariable Long id) {
        Optional<MatrimonyProfile> matrimonyProfile = matrimonyProfileService.getMatrimonyProfileById(id);
        return matrimonyProfile.map(profile -> new ResponseEntity<>(profile, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Update a Matrimony Profile
    @PutMapping("/{id}")
    public ResponseEntity<MatrimonyProfile> updateMatrimonyProfile(@PathVariable Long id, @RequestBody MatrimonyProfile updatedProfile) {
        Optional<MatrimonyProfile> existingProfile = matrimonyProfileService.getMatrimonyProfileById(id);
        if (existingProfile.isPresent()) {
            MatrimonyProfile updated = matrimonyProfileService.updateMatrimonyProfile(id, updatedProfile);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a Matrimony Profile
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMatrimonyProfile(@PathVariable Long id) {
        if (matrimonyProfileService.deleteMatrimonyProfile(id)) {
        	String message = "Deleted Successfully";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Find Matrimony Profiles by Occupation
    @GetMapping("/by-occupation/{occupation}")
    public ResponseEntity<List<MatrimonyProfile>> getMatrimonyProfilesByOccupation(@PathVariable String occupation) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByOccupation(occupation);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // Find Matrimony Profiles by Income
    @GetMapping("/by-income/{income}")
    public ResponseEntity<List<MatrimonyProfile>> getMatrimonyProfilesByIncome(@PathVariable String income) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByIncome(income);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }

    // Find Matrimony Profiles by Education
    @GetMapping("/by-education/{education}")
    public ResponseEntity<List<MatrimonyProfile>> getMatrimonyProfilesByEducation(@PathVariable String education) {
        List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByEducation(education);
        return new ResponseEntity<>(profiles, HttpStatus.OK);
    }
    // Filter with one field and value
    @GetMapping("/filter/{field}/{value}")
    public ResponseEntity<List<MatrimonyProfile>> filterByField(@PathVariable String field, @PathVariable String value) {
        List<MatrimonyProfile> filteredProfiles = matrimonyProfileService.filterByField(field, value);
        return new ResponseEntity<>(filteredProfiles, HttpStatus.OK);
    }
    // Filter with multiple field and values
    @GetMapping("/filter")
    public ResponseEntity<List<MatrimonyProfile>> filterByFields(@RequestParam Map<String, String> filterParams) {
        List<MatrimonyProfile> filteredProfiles = matrimonyProfileService.filterByFields(filterParams);
        return new ResponseEntity<>(filteredProfiles, HttpStatus.OK);
    }

    
}
