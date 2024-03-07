int blinkspeed = 1000;
const int LED = 13;

void setup() {
  pinMode(LED, OUTPUT);
  while(!Serial);
}

void loop() {
  if (Serial.available()) {
    int input = Serial.read();

    if (input == -1) {
      blinkspeed = 5000;
    } else {
      blinkspeed = input * 10;
    }
  }

  digitalWrite(LED, (millis()/blinkspeed)&1);
}
