package dev.duuduu.controllerserver;

/**
 * <h1>Controller Commands</h1>
 * A controller cmd is encoded into 3 bytes <br>
 * The first two bytes are a short unique to a controller <br>
 * The third byte is the command itself: <br>
 * bit 0: if 1 its just a pinging indicator <br>
 * bit 1: 1 for press action, 0 for released <br>
 * bit 2 - 6: 5 bit number, the index of the button that was <br>
 */
public class ControllerCmd {
    public boolean b_justPing;
    public boolean b_press;
    public short int5_button_index;
    public short int16_unique_controller_id;

    public ControllerCmd() {
        b_justPing = false;
        b_press = false;
        int5_button_index = 0;
    }

    public void assign(short id, byte b) {
        b_justPing = (b & 0b00000001) == 0b00000001;
        b_press = ((b >> 1) & 0b00000001) == 0b00000001;
        int5_button_index = (short) ((b >> 2) & 0b00011111);
        int16_unique_controller_id = id;
    }
}
