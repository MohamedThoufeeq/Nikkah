document.addEventListener('DOMContentLoaded', function () {
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
