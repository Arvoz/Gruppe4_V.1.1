package no.gruppe4.iot;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CommunicationController {

    private final String esp32Url = "http://192.168.1.100/data";

    // Enkelt GET-endepunkt som returnerer en melding
    @CrossOrigin(origins = "*")  // Tillater forespørsler fra alle opprinnelser
    @GetMapping("/hello")
    public String sayHello() {
        return "Hei fra Spring Boot backend!";
    }

    // POST-endepunkt som mottar en parameter fra frontend
    @CrossOrigin(origins = "*")  // Tillater forespørsler fra alle opprinnelser
    @PostMapping("/greet")
    public String greetUser(@RequestParam String name) {
        return "Hei, " + name + "! Velkommen til vår Spring Boot backend!";
    }

    public String sendToEsp32(@RequestParam String data) {
        // Opprett en RestTemplate for å sende forespørsel til ESP32
        RestTemplate restTemplate = new RestTemplate();
        
        // Sett opp headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);  // Vi sender vanlig tekst

        // Bygg forespørselen med data
        HttpEntity<String> request = new HttpEntity<>(data, headers);

        // Send POST-forespørselen til ESP32
        try {
            ResponseEntity<String> response = restTemplate.exchange(esp32Url, HttpMethod.POST, request, String.class);
            return "Data sendt til ESP32. Respons fra ESP32: " + response.getBody();
        } catch (Exception e) {
            return "Feil ved sending til ESP32: " + e.getMessage();
        }
    }
}
