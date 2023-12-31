package com.nikkah.app.service;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nikkah.app.Exception.MatrimonyProfileNotFoundException;
import com.nikkah.app.model.MatrimonyProfile;
import com.nikkah.app.model.MatrimonyProfilesResponse;
import com.nikkah.app.repo.MatrimonyProfileRepository;

@Service
public class MatrimonyProfileService {

    @Autowired
    private MatrimonyProfileRepository matrimonyProfileRepository;
    
    MatrimonyProfilesResponse response = null;

    // Create a new Matrimony Profile
    public MatrimonyProfile createMatrimonyProfile(MatrimonyProfile matrimonyProfile) {
        return matrimonyProfileRepository.save(matrimonyProfile);
    }

    // Retrieve all Matrimony Profiles
    public MatrimonyProfilesResponse getAllMatrimonyProfiles() {
    	response = new MatrimonyProfilesResponse();
    	response.setProfiles(matrimonyProfileRepository.findAll());
        return response;
    }

    // Retrieve a specific Matrimony Profile by Email
    public List<MatrimonyProfile> getMatrimonyProfileByEmail(String email) {
    	return matrimonyProfileRepository.findByEmail(email);
    }
    // Retrieve a specific Matrimony Profile by ID
    public Optional<MatrimonyProfile> getMatrimonyProfileById(Long id) {
        return matrimonyProfileRepository.findById(id);
    }

    // Update a Matrimony Profile
    public MatrimonyProfile updateMatrimonyProfile(Long id, MatrimonyProfile updatedProfile) {
        Optional<MatrimonyProfile> existingProfile = matrimonyProfileRepository.findById(id);
        if (existingProfile.isPresent()) {
            updatedProfile.setId(id);
            return matrimonyProfileRepository.save(updatedProfile);
        } else {
            // Handle not found scenario
            throw new MatrimonyProfileNotFoundException("Matrimony Profile not found with id: " + id);
        }
    }

    // Delete a Matrimony Profile
    public boolean deleteMatrimonyProfile(Long id) {
        if (matrimonyProfileRepository.existsById(id)) {
            matrimonyProfileRepository.deleteById(id);
            return true;
        } else {
            return false; // Matrimony Profile not found
        }
    }
    public List<MatrimonyProfile> getMatrimonyProfilesByOccupation(String occupation) {
        // Implement logic to retrieve profiles by occupation from the repository
        return matrimonyProfileRepository.findByOccupation(occupation);
    }

    public List<MatrimonyProfile> getMatrimonyProfilesByIncome(String income) {
        // Implement logic to retrieve profiles by income from the repository
        return matrimonyProfileRepository.findByIncome(income);
    }

    public List<MatrimonyProfile> getMatrimonyProfilesByEducation(String education) {
        // Implement logic to retrieve profiles by education from the repository
        return matrimonyProfileRepository.findByEducation(education);
    }
    
    public List<MatrimonyProfile> filterByField(String field, String value) {
        return matrimonyProfileRepository.filterByField(field, value);
    }

    public List<MatrimonyProfile> filterByFields(Map<String, String> filterParams) {
        return matrimonyProfileRepository.filterByFields(filterParams);
    }

    public MatrimonyProfile createMatrimonyProfile(MatrimonyProfile matrimonyProfile, MultipartFile image) {
        // Handle image upload and association with the MatrimonyProfile entity
        try {
            matrimonyProfile.setImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exception
        }

        // Save the MatrimonyProfile entity
        return matrimonyProfileRepository.save(matrimonyProfile);
    }


}
