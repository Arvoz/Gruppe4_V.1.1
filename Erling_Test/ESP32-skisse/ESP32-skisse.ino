#include <WiFi.h>
#include <WebServer.h>

// Sett opp WiFi og server
const char* ssid = "YourSSID";
const char* password = "YourPassword";
WebServer server(80);

// Funksjon for å håndtere data sendt fra backend
void handleData() {
  if (server.hasArg("plain")) {
    String receivedData = server.arg("plain");
    Serial.println("Data mottatt fra backend: " + receivedData);
    server.send(200, "text/plain", "Data mottatt på ESP32");
  } else {
    server.send(400, "text/plain", "Ingen data mottatt");
  }
}

void setup() {
  Serial.begin(115200);
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.println("Kobler til WiFi...");
  }

  Serial.println("WiFi tilkoblet!");
  Serial.print("ESP32 IP-adresse: ");
  Serial.println(WiFi.localIP());  // Skriver ut ESP32's IP-adresse
  
  // Definer endepunkt for å motta data
  server.on("/data", HTTP_POST, handleData);

  // Start serveren
  server.begin();
  Serial.println("Server startet!");
}

void loop() {
  server.handleClient();
}
