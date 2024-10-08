// Funksjon for å vise tilgjengelige IoT-enheter
document.getElementById('searchDevicesButton').addEventListener('click', function() {
    // Vis listen med enheter når søket starter
    document.getElementById('devicesList').style.display = 'block';
});

// Funksjon for å legge en enhet til en gruppe
document.getElementById('addToGroup').addEventListener('click', function() {
    const groupName = document.getElementById('groupName').value;
    const selectedDevice = document.getElementById('deviceSelect').value;

    // Hvis gruppenavnet er tomt, gjør ingenting
    if (groupName === "") {
        alert("Vennligst skriv inn et gruppenavn.");
        return;
    }

    // Opprett en liste for gruppen hvis den ikke eksisterer
    let groupList = document.getElementById('groupList');
    let existingGroup = document.getElementById(groupName);
    
    if (!existingGroup) {
        // Lag et nytt gruppeelement
        const newGroup = document.createElement('li');
        newGroup.id = groupName;
        newGroup.innerHTML = `<strong>${groupName}</strong><ul></ul>`;
        groupList.appendChild(newGroup);
        existingGroup = newGroup;
    }

    // Legg enheten til i gruppens liste
    const groupDevicesList = existingGroup.querySelector('ul');
    const newDevice = document.createElement('li');
    newDevice.textContent = selectedDevice;
    groupDevicesList.appendChild(newDevice);

    // Tøm input-feltet for gruppenavn
    document.getElementById('groupName').value = "";
});
