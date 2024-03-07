void setup() {
  // Set the LED pin as an output
  pinMode(13, OUTPUT);
}

void loop() {
  // Turn the LED on
  digitalWrite(13, HIGH);
  delay(2000); // Wait for 500 milliseconds (0.5 seconds)

  // Turn the LED off
  digitalWrite(13, LOW);
  delay(500); // Wait for another 500 milliseconds

  digitalWrite(13, HIGH);
  delay(1000);
  digitalWrite(13, LOW);
  delay(200);
  digitalWrite(13, HIGH);
  delay(500);
  digitalWrite(13, LOW);
  delay(500);
}
