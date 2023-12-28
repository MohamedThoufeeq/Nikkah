function submitForm(event) {
	event.preventDefault(); // Prevent the default form submission

	// Fetch form data
	const name = document.getElementById('name').value;
	const gender = document.getElementById('gender').value;
	const dob = document.getElementById('dateOfBirth').value;
	const maritalStatus = document.getElementById('maritalStatus').value;
	const language = document.getElementById('language').value;
	const city = document.getElementById('city').value;
	const country = document.getElementById('country').value;
	const education = document.getElementById('education').value;
	const occupation = document.getElementById('occupation').value;
	const income = document.getElementById('income').value;
	const aboutMe = document.getElementById('aboutMe').value;
	const mobileNumber = document.getElementById('mobileNumber').value;
	const email = document.getElementById('email').value;
	const password = document.getElementById('password').value;

	// Fetch image file
	const profileImageInput = document.getElementById('profileImage');
	const profileImageFile = profileImageInput.files[0];

	if (!name || !gender || !dob || !maritalStatus || !language || !city || !country || !education || !occupation || !email || !password) {
		alert('Fill all the required fields');
		return;
	} else {
		try {
			const matrimonyProfile = {
				name: name,
				gender: gender,
				dateOfBirth: dob,
				maritalStatus: maritalStatus,
				language: language,
				city: city,
				country: country,
				education: education,
				occupation: occupation,
				income: income,
				aboutMe: aboutMe,
				mobileNumber: mobileNumber,
				email: email,
				password: password,
			};
			// Send data as JSON to the specified URL
			if (profileImageFile != null) {
				fetchWithImage(matrimonyProfile, profileImageFile);
			} else {
				fetchWithoutImage(matrimonyProfile);
			}
		} catch (error) {
			console.error('Error:', error);
			alert("An error occurred. Please try again later.");
			console.error('Error:', error);
		}
	}
}

function fetchWithoutImage(matrimonyProfile) {
	const url = 'http://localhost:8080/api';

	fetch(url, {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(matrimonyProfile),
	})
		.then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! Status: ${response.status}`);
			}
			return response.json();
		})
		.then(data => {
			console.log('Success:', data);
			alert('Profile registered successfully');
			window.location.replace('login.html');
		})
		.catch(error => {
			console.error('Error:', error.message);
			throw error; // Re-throw the error to handle it in the calling code
		});
}
function fetchWithImage(matrimonyProfile, imageFile) {
	const url = 'http://localhost:8080/api/profile';

	// Create FormData to handle file and JSON data
	const formData = new FormData();
	formData.append('image', imageFile);
	formData.append('matrimonyProfile', JSON.stringify(matrimonyProfile));

	return fetch(url, {
		method: 'POST',
		body: formData,
	})
		.then(response => {
			if (!response.ok) {
				throw new Error(`HTTP error! Status: ${response.status}`);
			}
			return response.json();
		})
		.then(data => {
			console.log('Success:', data);
			alert('Profile registered successfully');
			window.location.replace('login.html');
		})
		.catch(error => {
			console.error('Error:', error.message);
			throw error; // Re-throw the error to handle it in the calling code
		});
}
