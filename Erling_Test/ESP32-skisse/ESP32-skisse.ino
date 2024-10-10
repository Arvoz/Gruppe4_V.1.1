#include <WiFi.h>
#include <HTTPClient.h>
#include <ArduinoJson.h>

// Wi-Fi konfigurasjon
const char* ssid = "ZyXEL17B116";
const char* password = "F84498DC6DAE";

// Spring Boot server URL (oppdater med din IP og port)
const char* serverName = "http://192.168.1.3:8080/api/wifi/configure";

void setup() {
  Serial.begin(115200);

  // Koble til WiFi
  WiFi.begin(ssid, password);
  Serial.print("Kobler til WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print(".");
  }
  Serial.println("\nTilkoblet til WiFi!");
}

void loop() {
  // Sjekk at vi er tilkoblet WiFi
  if (WiFi.status() == WL_CONNECTED) {
    HTTPClient http;

    // Opprett URL og initialiser HTTP
    http.begin(serverName);
    http.addHeader("Content-Type", "application/json");

    // Lag JSON data
    StaticJsonDocument<200> jsonDoc;
    jsonDoc["ssid"] = "ESP32SSID";
    jsonDoc["password"] = "ESP32Password";

    String jsonData;
    serializeJson(jsonDoc, jsonData);

    // Send POST-forespørsel
    int httpResponseCode = http.POST(jsonData);

    if (httpResponseCode > 0) {
      String response = http.getString();
      Serial.print("Respons fra server: ");
      Serial.println(response);
    } else {
      Serial.print("Error on sending POST: ");
      Serial.println(httpResponseCode);
    }

    // Avslutt forbindelsen
    http.end();
  } else {
    Serial.println("WiFi ikke tilkoblet");
  }

  // Vent før neste forespørsel
  delay(10000); // 10 sekunders intervall
}
