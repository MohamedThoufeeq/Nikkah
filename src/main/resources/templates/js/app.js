document.addEventListener('DOMContentLoaded', function () {
    // Fetch Matrimony profiles from the backend
    fetch('http://localhost:8080/api')
        .then(response => response.json())
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

    profiles.forEach(profile => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${profile.id}</td>
            <td>${profile.firstName}</td>
            <td>${profile.lastName}</td>
            <td>${profile.gender}</td>
            <td>${profile.dateOfBirth}</td>
            <!-- Add more table cells based on your entity fields -->
        `;
        tableBody.appendChild(row);
    });
}
