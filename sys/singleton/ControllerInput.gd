# dev Kelvin

extends Node


const CONTROLLER_BUTTON_COUNT = 11
const CONTROLLER_SHIFT = 16


const BUTTON_DPAD_UP : int = 0x30
const BUTTON_DPAD_LEFT : int = 0x31
const BUTTON_DPAD_DOWN : int = 0x32
const BUTTON_DPAD_RIGHT : int = 0x33
const BUTTON_BLUE : int = 0x34
const BUTTON_YELLOW : int = 0x35
const BUTTON_GREEN : int = 0x36
const BUTTON_RED : int = 0x37
const BUTTON_SHOULDER_LEFT : int = 0x38
const BUTTON_SHOULDER_RIGHT : int = 0x39
const BUTTON_HOME : int = 0x3A


var pressed_keys
var just_pressed_keys


func _init():
	pressed_keys = []
	just_pressed_keys = []
	for i in 2048:
		pressed_keys.append(false)
		just_pressed_keys.append(false)


func _unhandled_key_input(event):
	if event.keycode > 2047:
		return
	KEY_0
	pressed_keys[event.keycode] = event.is_pressed()
	var just_pressed = event.is_pressed() and not event.is_echo()
	if just_pressed:
		just_pressed_keys[event.keycode] = true
	else:
		just_pressed_keys[event.keycode] = false


func is_button_pressed(button_code : int, controller_index : int = 0) -> bool:
	return pressed_keys[button_code + CONTROLLER_SHIFT * controller_index]


func is_button_just_pressed(button_code : int, controller_index : int = 0) -> bool:
	if just_pressed_keys[button_code + CONTROLLER_SHIFT * controller_index]:
		just_pressed_keys[button_code + CONTROLLER_SHIFT * controller_index] = false
		return true
	return false

