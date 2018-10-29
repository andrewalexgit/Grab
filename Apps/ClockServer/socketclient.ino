/*
    This sketch sends a message to a TCP server

*/

#include <ESP8266WiFi.h>
#include <ESP8266WiFiMulti.h>
#include <Wire.h>
#include <ACROBOTIC_SSD1306.h>

ESP8266WiFiMulti WiFiMulti;

char ssid[] = "Fios-LBPBU";          //  your network SSID (name) 
char pass[] = "end9823wet8602meat";   // your network password

void setup() {

  Wire.begin();
  oled.init();
  oled.clearDisplay();
  oled.setTextXY(0,0);            
  oled.putString("Connecting...");
  
  Serial.begin(115200);
  delay(10);

  // We start by connecting to a WiFi network
  WiFi.mode(WIFI_STA);
  WiFiMulti.addAP(ssid, pass);

  Serial.println();
  Serial.println();
  Serial.print("Wait for WiFi... ");

  while (WiFiMulti.run() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }

  Serial.println("");
  Serial.println("WiFi connected");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  delay(500);
}


void loop() {
  const uint16_t port = 5000;
  const char * host = "192.168.1.151"; // ip or dns



  Serial.print("connecting to ");
  Serial.println(host);
  oled.setTextXY(1,0);
  oled.putString("Requesting server...");

  // Use WiFiClient class to create TCP connections
  WiFiClient client;

  if (!client.connect(host, port)) {
    Serial.println("connection failed");
    Serial.println("wait 5 sec...");
    delay(5000);
    return;
  }

  oled.clearDisplay();

 while(true) {
    oled.setTextXY(0,0);
    client.println("datetime@\n");
    client.println("end\n");
    //read back one line from server
    String line = client.readStringUntil('\r');
    oled.putString(line);
    Serial.println(line);
    delay(100);
 }
}
