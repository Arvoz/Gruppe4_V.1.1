package no.gruppe4.iot;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/wifi")
public class WiFiController {

    @PostMapping("/configure")
    public ResponseEntity<String> configureWiFi(@RequestBody WiFiCredentials credentials) {
        String ssid = credentials.getSsid();
        String password = credentials.getPassword();
        
        // Legg til logikk for å behandle SSID og passord her.
        System.out.println("SSID mottatt: " + ssid);
        System.out.println("Password mottatt: " + password);

        // Returner en suksessrespons
        return ResponseEntity.ok("WiFi-konfigurasjon mottatt.");
    }
    
    @GetMapping("/test")
        public ResponseEntity<String> testEndpoint() {
        return ResponseEntity.ok("Test endpoint fungerer!");
    }
}