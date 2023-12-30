document.addEventListener('DOMContentLoaded', function() {
	// Fetch Matrimony profiles from the backend
	fetch('http://localhost:8080/api')
		.then(response => {
			if (!response.ok) {
				throw new Error('Network response was not ok');
			}
			return response.json();
		})
		.then(data => {
			const profiles = data; // Access the profiles array
			console.log(profiles);
			// Display profiles in the table
			displayProfiles(profiles);
		})
		.catch(error => console.error('Error fetching profiles:', error));
});

function displayProfiles(profiles) {
	const tableBody = document.querySelector('#profileTableBody');
	tableBody.innerHTML = '';

	profiles.profiles.forEach(profile => {
		const row = document.createElement('tr');
		const table = document.querySelector('.table');
		row.innerHTML = `
            	<td>${profile.id}</td>
            	<td>${profile.name}</td>
            	<td>${profile.gender}</td>
            	<td>${profile.dateOfBirth}</td>
            	<td>${profile.age}</td>
            	<td>${profile.maritalStatus}</td>
            	<td>${profile.language}</td>
            	<td>${profile.city}</td>
            	<td>${profile.country}</td>
            	<td>${profile.education}</td>
            	<td>${profile.occupation}</td>
            	<td>${profile.income}</td>
            	<td>${profile.aboutMe}</td>
            	<td>${profile.email}</td>
        `;
		tableBody.appendChild(row);
	});
}
