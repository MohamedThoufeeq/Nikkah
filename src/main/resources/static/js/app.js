document.addEventListener('DOMContentLoaded', function() {
	// Fetch Matrimony profiles from the backend
	fetch('http://localhost:8080/api')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(profiles => {
			console.log(profiles);
			// Display profiles in the table
			displayProfiles(profiles);
		})
		.catch(error => console.error('Error fetching profiles:', error));
});

function displayProfiles(profiles) {
	const tableBody = document.querySelector('#matrimony-table-body');
	tableBody.innerHTML = '';

	profiles.profiles.forEach(profile => {
		const row = document.createElement('tr');
		row.innerHTML = `
            <td>${profile.id}</td>
            <td>${profile.firstName}</td>
            <td>${profile.lastName}</td>
            <td>${profile.gender}</td>
            <td>${profile.dateOfBirth}</td>
            <td>${profile.age}</td>
            <td>${profile.address}</td>
            <td>${profile.mobileNumber}</td>
            <td>${profile.email}</td>
            <td>${profile.religion}</td>
            <td>${profile.education}</td>
            <td>${profile.occupation}</td>
            <td>${profile.income}</td>
            <td>${profile.maritalStatus}</td>
            <td>${profile.aboutMe}</td>
        `;
		tableBody.appendChild(row);
	});
}

function submitForm() {
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
	const formData = {
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
	console.log(JSON.stringify(formData));
	if (!name || !gender || !dob || !maritalStatus || !language || !city || !country || !education || !occupation || !email || !password) {
		alert('Fill all the required fields');
		return;
	}
	// Send data as JSON to the specified URL
	fetch('http://localhost:8080/api', {
		method: 'POST',
		headers: {
			'Content-Type': 'application/json',
		},
		body: JSON.stringify(formData),

	})
		.then(response => {
			if (response.status === 201) {
				alert("Profile registered successfully!");
				window.location.replace("login.html");
			} else {
				alert("Try again later. Sorry for the inconvenience.");
			}
		})
		.catch(error => {
			console.error('Error:', error);
			alert("An error occurred. Please try again later.");
		});
}