package com.nikkah.app.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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

	// Create a new Matrimony Profile with image
	@PostMapping("/profile")
	public ResponseEntity<MatrimonyProfile> createMatrimonyProfile(@RequestParam("image") MultipartFile image,
			@RequestParam("matrimonyProfile") String matrimonyProfileJson) {
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			MatrimonyProfile matrimonyProfile = objectMapper.readValue(matrimonyProfileJson, MatrimonyProfile.class);
			MatrimonyProfile createdProfile = matrimonyProfileService.createMatrimonyProfile(matrimonyProfile, image);
			return new ResponseEntity<>(createdProfile, HttpStatus.CREATED);
		} catch (IOException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	// Retrieve the image by profile ID
	@GetMapping("/image/{id}")
	public ResponseEntity<byte[]> getProfileImage(@PathVariable Long id) {
		Optional<MatrimonyProfile> matrimonyProfileOptional = matrimonyProfileService.getMatrimonyProfileById(id);

		if (matrimonyProfileOptional.isPresent()) {
			MatrimonyProfile matrimonyProfile = matrimonyProfileOptional.get();
			byte[] image = matrimonyProfile.getImage();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.IMAGE_JPEG); // Set the content type based on your image type
			headers.setContentLength(image.length);

			return new ResponseEntity<>(image, headers, HttpStatus.OK);
		} else {
			// Handle not found scenario
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Retrieve all Matrimony Profiles
	@GetMapping
	public ResponseEntity<MatrimonyProfilesResponse> getAllMatrimonyProfiles() {
		MatrimonyProfilesResponse response = matrimonyProfileService.getAllMatrimonyProfiles();
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
	public ResponseEntity<MatrimonyProfile> updateMatrimonyProfile(@PathVariable Long id,
			@RequestBody MatrimonyProfile updatedProfile) {
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
	@GetMapping("/by-email/{email}")
	public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByEmail(@PathVariable String email) {
		List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfileByEmail(email);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(profiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Find Matrimony Profiles by Occupation
	@GetMapping("/by-occupation/{occupation}")
	public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByOccupation(@PathVariable String occupation) {
		List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByOccupation(occupation);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(profiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Find Matrimony Profiles by Income
	@GetMapping("/by-income/{income}")
	public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByIncome(@PathVariable String income) {
		List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByIncome(income);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(profiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Find Matrimony Profiles by Education
	@GetMapping("/by-education/{education}")
	public ResponseEntity<MatrimonyProfilesResponse> getMatrimonyProfilesByEducation(@PathVariable String education) {
		List<MatrimonyProfile> profiles = matrimonyProfileService.getMatrimonyProfilesByEducation(education);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(profiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Filter with one field and value
	@GetMapping("/filter/{field}/{value}")
	public ResponseEntity<MatrimonyProfilesResponse> filterByField(@PathVariable String field,
			@PathVariable String value) {
		List<MatrimonyProfile> profiles = matrimonyProfileService.filterByField(field, value);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(profiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// Filter with multiple field and values
	@GetMapping("/filter")
	public ResponseEntity<MatrimonyProfilesResponse> filterByFields(@RequestParam Map<String, String> filterParams) {
		List<MatrimonyProfile> filteredProfiles = matrimonyProfileService.filterByFields(filterParams);
		MatrimonyProfilesResponse response = new MatrimonyProfilesResponse();
		response.setProfiles(filteredProfiles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
