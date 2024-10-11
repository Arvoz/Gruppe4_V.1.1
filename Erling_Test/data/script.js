function submitForm() {
    var ssid = document.getElementById("ssid").value;
    var password = document.getElementById("password").value;
  
    // Opprette en POST-forespørsel
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/config", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4) {
        alert(xhr.responseText); // Svar fra ESP32 vises som en alert
      }
    };
    xhr.send("ssid=" + encodeURIComponent(ssid) + "&password=" + encodeURIComponent(password));
  }