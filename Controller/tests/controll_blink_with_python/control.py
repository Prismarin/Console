import serial as cerial

port = cerial.Serial("COM13", 9600, cerial.EIGHTBITS, cerial.PARITY_NONE, cerial.STOPBITS_ONE)

while True:
    number = int(input())
    if number < 1:
        break
    port.write(bytearray([number % 255]))
