GDPC                �                                                                         P   res://.godot/exported/133200997/export-1cced48bafe396bd9eb567cd67b075fc-test.scn�      q        �$��/�3�$��f�o    \   res://.godot/exported/133200997/export-3ed067b2582fea86221108cdda309941-controller_input.scn`      �      ���==��U�����l7�    \   res://.godot/exported/133200997/export-8868e6a2fe3b5162781625f289facd73-console_manager.scn �      �      ����Ss��H<�h^H    T   res://.godot/exported/133200997/export-b51bfc689818780863ab92e543d278fb-console.scn         �      ��<Q�n;�"7<H��    ,   res://.godot/global_script_class_cache.cfg  �2             ��Р�8���8~$}P�    D   res://.godot/imported/icon.svg-218a8f2b3041327d8a5756f3a245f83b.ctex0#      �      �̛�*$q�*�́        res://.godot/uid_cache.bin  �6      �       �W����̀�L��hS       res://icon.svg  �2      �      C��=U���^Qu��U3       res://icon.svg.import   0      �       )}b�-���Tˈ�.��~       res://project.binary`7      �      �uwP�Mg��aԡ�ߏ    $   res://sys/main/console.tscn.remap   �0      d       YS1� ��e���<�M    (   res://sys/singleton/ConsoleManager.gd         �      ���؀'�=�{A���]    (   res://sys/singleton/ControllerInput.gd         `      �㦟��ʬ�^�N_	    0   res://sys/singleton/console_manager.tscn.remap  �1      l       ���,L�\;���$    0   res://sys/singleton/controller_input.tscn.remap P1      m       �C�Gޏt"1�`e�C       res://sys/tests/MenuTest1.gd@      �       RX�b������}�҇��       res://sys/tests/Test.gd p!      �      ��kv�ʶ3A���\��        res://sys/tests/test.tscn.remap 02      a       I��S@dW�o�rG^    RSRC                    PackedScene            ��������                                                  resource_local_to_scene    resource_name 	   _bundled    script       Script    res://sys/tests/MenuTest1.gd ��������      local://PackedScene_hir6i          PackedScene          	         names "         Console    process_mode    Node    GameContainer    Menu    script    Node2D    Control    layout_mode    anchors_preset    offset_right    offset_bottom    VBoxContainer    anchor_right    anchor_bottom    grow_horizontal    grow_vertical 	   LineEdit    placeholder_text    Button    text    Button2    _on_button_pressed    pressed    _on_button_2_pressed    	   variants                                        @�D    @"D                 �?            path to game       Load       Just Switch       node_count             nodes     b   ��������       ����                            ����                             ����                                   ����          	      
                             ����         	                     	      	                    ����      	      
                    ����      	                          ����      	                   conn_count             conns                                                            node_paths              editable_instances              version             RSRC���q��	:b
��# dev Kelvin

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

RSRC                    PackedScene            ��������                                                  resource_local_to_scene    resource_name 	   _bundled    script       Script '   res://sys/singleton/ControllerInput.gd ��������      local://PackedScene_8ve66          PackedScene          	         names "         ControllerInput    process_mode    script    Node    	   variants                             node_count             nodes        ��������       ����                          conn_count              conns               node_paths              editable_instances              version             RSRCt�%��# dev kelvin

extends Node


var game_loaded : bool = false


func _physics_process(delta):
	if ControllerInput.is_button_just_pressed(ControllerInput.BUTTON_HOME):
		switch_to_menu()


func switch_to_menu() -> bool:
	print("Switching to menu...")
	var menu : Node2D = get_tree().get_nodes_in_group("Menu")[0]
	if menu == null:
		print("Fatal: Could not get menu!")
		return false
	var game_container : Node = get_tree().get_nodes_in_group("GameContainer")[0]
	if game_container == null:
		print("Fatal: Could not get game_container!")
		return false
	menu.process_mode = Node.PROCESS_MODE_ALWAYS
	menu.visible = true
	game_container.process_mode = Node.PROCESS_MODE_DISABLED
	print("Successfully switched to menu!")
	return true


func load_game(gamename : String) -> bool:
	if game_loaded:
		print("Failed to load game, a game is already loaded!")
		return false
	
	print("Loading game: %s..." % gamename)
	
	var path = "res://games/" + gamename + "/game.pck"
	var result = ProjectSettings.load_resource_pack(path, false)
	if not result:
		print("Failed loading %s, Step: ProjectSettings.load_resource_pack path: %s" % [gamename, path])
		print("ProjectSettings.load_resource_pack returned false, not attempting to load game further.")
		return false
	print("Loaded game file successfully!")
	
	var game_root : PackedScene = load("res://%s/game_root.tscn" % gamename)
	if game_root == null:
		print("Failed loading %s, Step: load game_root expected game_root path: res://%s/game_root.tscn" % [gamename, gamename])
		print("game_root is null, not attempting to load game further.")
		return false
	print("Loaded game_root successfully")
	
	print("Instantiating game_root...")
	var game_root_instance = game_root.instantiate()
	print("Games processing mode: %s" % str(game_root_instance.process_mode))
	var game_container : Node = get_tree().get_nodes_in_group("GameContainer")[0]
	if game_container == null:
		print("Fatal: Could not get game_container!")
		return false
	game_container.process_mode = Node.PROCESS_MODE_DISABLED
	game_container.add_child(game_root_instance)
	print("Game %s has been loaded successfully!" % gamename)
	
	game_loaded = true
	return true


func switch_to_game() -> bool:
	if not game_loaded:
		print("Switching to game scene failed: no game loaded!")
		return false
	
	print("Switching to game scene...")
	var menu : Node2D = get_tree().get_nodes_in_group("Menu")[0]
	if menu == null:
		print("Fatal: Could not get menu!")
		return false
	var game_container : Node = get_tree().get_nodes_in_group("GameContainer")[0]
	if game_container == null:
		print("Fatal: Could not get game_container!")
		return false
	menu.process_mode = Node.PROCESS_MODE_DISABLED
	menu.visible = false
	game_container.process_mode = Node.PROCESS_MODE_ALWAYS
	
	print("Successfully switched to game scene!")
	return true


func unload_game() -> bool:
	if not game_loaded:
		print("No game loaded!")
		return false
	
	print("Unloading game")
	
	var game_container : Node = get_tree().get_nodes_in_group("GameContainer")[0]
	if game_container == null:
		print("Fatal: Could not get game_container!")
		return false
	
	print("Fetching game nodes...")
	var game = game_container.get_children()
	var node_paths_to_free = []
	for game_node in game:
		node_paths_to_free.append(game_node.get_path())
	print("Freeing game nodes...")
	for path in node_paths_to_free:
		get_node(path).queue_free()
	print("Game unloaded.")
	
	game_loaded = false
	return true
RSRC                    PackedScene            ��������                                                  resource_local_to_scene    resource_name 	   _bundled    script       Script &   res://sys/singleton/ConsoleManager.gd ��������      local://PackedScene_7y07h          PackedScene          	         names "         ConsoleManager    process_mode    script    Node    	   variants                             node_count             nodes        ��������       ����                          conn_count              conns               node_paths              editable_instances              version             RSRCl��D���extends Node2D


func _on_button_pressed():
	ConsoleManager.load_game($Control/VBoxContainer/LineEdit.text)


func _on_button_2_pressed():
	ConsoleManager.switch_to_game()
{LRSRC                    PackedScene            ��������                                                  resource_local_to_scene    resource_name 	   _bundled    script       Script    res://sys/tests/Test.gd ��������      local://PackedScene_c4soj          PackedScene          	         names "         Test    script    Node2D    	   variants                       node_count             nodes     	   ��������       ����                    conn_count              conns               node_paths              editable_instances              version             RSRC�e��-Fٺ�]N��_�extends Node2D


# Called when the node enters the scene tree for the first time.
func _ready():
	pass # Replace with function body.


# Called every frame. 'delta' is the elapsed time since the previous frame.
func _process(_delta):
	if ControllerInput.is_button_pressed(ControllerInput.BUTTON_DPAD_UP):
		print("DPAD UP PRESSED!")
	if ControllerInput.is_button_just_pressed(ControllerInput.BUTTON_DPAD_LEFT):
		print("DPAD LEFT JUST PRESSED")
���GST2   �   �      ����               � �        �  RIFF�  WEBPVP8L�  /������!"2�H�$�n윦���z�x����դ�<����q����F��Z��?&,
ScI_L �;����In#Y��0�p~��Z��m[��N����R,��#"� )���d��mG�������ڶ�$�ʹ���۶�=���mϬm۶mc�9��z��T��7�m+�}�����v��ح�m�m������$$P�����එ#���=�]��SnA�VhE��*JG�
&����^x��&�+���2ε�L2�@��		��S�2A�/E���d"?���Dh�+Z�@:�Gk�FbWd�\�C�Ӷg�g�k��Vo��<c{��4�;M�,5��ٜ2�Ζ�yO�S����qZ0��s���r?I��ѷE{�4�Ζ�i� xK�U��F�Z�y�SL�)���旵�V[�-�1Z�-�1���z�Q�>�tH�0��:[RGň6�=KVv�X�6�L;�N\���J���/0u���_��U��]���ǫ)�9��������!�&�?W�VfY�2���༏��2kSi����1!��z+�F�j=�R�O�{�
ۇ�P-�������\����y;�[ ���lm�F2K�ޱ|��S��d)é�r�BTZ)e�� ��֩A�2�����X�X'�e1߬���p��-�-f�E�ˊU	^�����T�ZT�m�*a|	׫�:V���G�r+�/�T��@U�N׼�h�+	*�*sN1e�,e���nbJL<����"g=O��AL�WO!��߈Q���,ɉ'���lzJ���Q����t��9�F���A��g�B-����G�f|��x��5�'+��O��y��������F��2�����R�q�):VtI���/ʎ�UfěĲr'�g�g����5�t�ۛ�F���S�j1p�)�JD̻�ZR���Pq�r/jt�/sO�C�u����i�y�K�(Q��7őA�2���R�ͥ+lgzJ~��,eA��.���k�eQ�,l'Ɨ�2�,eaS��S�ԟe)��x��ood�d)����h��ZZ��`z�պ��;�Cr�rpi&��՜�Pf��+���:w��b�DUeZ��ڡ��iA>IN>���܋�b�O<�A���)�R�4��8+��k�Jpey��.���7ryc�!��M�a���v_��/�����'��t5`=��~	`�����p\�u����*>:|ٻ@�G�����wƝ�����K5�NZal������LH�]I'�^���+@q(�q2q+�g�}�o�����S߈:�R�݉C������?�1�.��
�ڈL�Fb%ħA ����Q���2�͍J]_�� A��Fb�����ݏ�4o��'2��F�  ڹ���W�L |����YK5�-�E�n�K�|�ɭvD=��p!V3gS��`�p|r�l	F�4�1{�V'&����|pj� ߫'ş�pdT�7`&�
�1g�����@D�˅ �x?)~83+	p �3W�w��j"�� '�J��CM�+ �Ĝ��"���4� ����nΟ	�0C���q'�&5.��z@�S1l5Z��]�~L�L"�"�VS��8w.����H�B|���K(�}
r%Vk$f�����8�ڹ���R�dϝx/@�_�k'�8���E���r��D���K�z3�^���Vw��ZEl%~�Vc���R� �Xk[�3��B��Ğ�Y��A`_��fa��D{������ @ ��dg�������Mƚ�R�`���s����>x=�����	`��s���H���/ū�R�U�g�r���/����n�;�SSup`�S��6��u���⟦;Z�AN3�|�oh�9f�Pg�����^��g�t����x��)Oq�Q�My55jF����t9����,�z�Z�����2��#�)���"�u���}'�*�>�����ǯ[����82һ�n���0�<v�ݑa}.+n��'����W:4TY�����P�ר���Cȫۿ�Ϗ��?����Ӣ�K�|y�@suyo�<�����{��x}~�����~�AN]�q�9ޝ�GG�����[�L}~�`�f%4�R!1�no���������v!�G����Qw��m���"F!9�vٿü�|j�����*��{Ew[Á��������u.+�<���awͮ�ӓ�Q �:�Vd�5*��p�ioaE��,�LjP��	a�/�˰!{g:���3`=`]�2��y`�"��N�N�p���� ��3�Z��䏔��9"�ʞ l�zP�G�ߙj��V�>���n�/��׷�G��[���\��T��Ͷh���ag?1��O��6{s{����!�1�Y�����91Qry��=����y=�ٮh;�����[�tDV5�chȃ��v�G ��T/'XX���~Q�7��+[�e��Ti@j��)��9��J�hJV�#�jk�A�1�^6���=<ԧg�B�*o�߯.��/�>W[M���I�o?V���s��|yu�xt��]�].��Yyx�w���`��C���pH��tu�w�J��#Ef�Y݆v�f5�e��8��=�٢�e��W��M9J�u�}]釧7k���:�o�����Ç����ս�r3W���7k���e�������ϛk��Ϳ�_��lu�۹�g�w��~�ߗ�/��ݩ�-�->�I�͒���A�	���ߥζ,�}�3�UbY?�Ӓ�7q�Db����>~8�]
� ^n׹�[�o���Z-�ǫ�N;U���E4=eȢ�vk��Z�Y�j���k�j1�/eȢK��J�9|�,UX65]W����lQ-�"`�C�.~8ek�{Xy���d��<��Gf�ō�E�Ӗ�T� �g��Y�*��.͊e��"�]�d������h��ڠ����c�qV�ǷN��6�z���kD�6�L;�N\���Y�����
�O�ʨ1*]a�SN�=	fH�JN�9%'�S<C:��:`�s��~��jKEU�#i����$�K�TQD���G0H�=�� �d�-Q�H�4�5��L�r?����}��B+��,Q�yO�H�jD�4d�����0*�]�	~�ӎ�.�"����%
��d$"5zxA:�U��H���H%jس{���kW��)�	8J��v�}�rK�F�@�t)FXu����G'.X�8�KH;���[ ���x8���F��[remap]

importer="texture"
type="CompressedTexture2D"
uid="uid://dj5uku40tea8a"
path="res://.godot/imported/icon.svg-218a8f2b3041327d8a5756f3a245f83b.ctex"
metadata={
"vram_texture": false
}
 �iy�"�����5�C[~[remap]

path="res://.godot/exported/133200997/export-b51bfc689818780863ab92e543d278fb-console.scn"
�b���D�]�D[remap]

path="res://.godot/exported/133200997/export-3ed067b2582fea86221108cdda309941-controller_input.scn"
��T[remap]

path="res://.godot/exported/133200997/export-8868e6a2fe3b5162781625f289facd73-console_manager.scn"
:΂5[remap]

path="res://.godot/exported/133200997/export-1cced48bafe396bd9eb567cd67b075fc-test.scn"
���-\��ӳ��BMlist=Array[Dictionary]([])
��m�<svg height="128" width="128" xmlns="http://www.w3.org/2000/svg"><rect x="2" y="2" width="124" height="124" rx="14" fill="#363d52" stroke="#212532" stroke-width="4"/><g transform="scale(.101) translate(122 122)"><g fill="#fff"><path d="M105 673v33q407 354 814 0v-33z"/><path fill="#478cbf" d="m105 673 152 14q12 1 15 14l4 67 132 10 8-61q2-11 15-15h162q13 4 15 15l8 61 132-10 4-67q3-13 15-14l152-14V427q30-39 56-81-35-59-83-108-43 20-82 47-40-37-88-64 7-51 8-102-59-28-123-42-26 43-46 89-49-7-98 0-20-46-46-89-64 14-123 42 1 51 8 102-48 27-88 64-39-27-82-47-48 49-83 108 26 42 56 81zm0 33v39c0 276 813 276 813 0v-39l-134 12-5 69q-2 10-14 13l-162 11q-12 0-16-11l-10-65H447l-10 65q-4 11-16 11l-162-11q-12-3-14-13l-5-69z"/><path d="M483 600c3 34 55 34 58 0v-86c-3-34-55-34-58 0z"/><circle cx="725" cy="526" r="90"/><circle cx="299" cy="526" r="90"/></g><g fill="#414042"><circle cx="307" cy="532" r="60"/><circle cx="717" cy="532" r="60"/></g></g></svg>
�W����@�`    �:v�l   res://icon.svg����I)   res://sys/singleton/controller_input.tscn�a���WQ   res://sys/tests/test.tscn\��.a#(   res://sys/singleton/console_manager.tscn��1��i   res://sys/main/console.tscnJ���*�<<� �ECFG      application/config/name         Console    application/run/main_scene$         res://sys/main/console.tscn    application/config/features$   "         4.1    Forward Plus       application/config/icon         res://icon.svg     autoload/ControllerInput4      *   *res://sys/singleton/controller_input.tscn     autoload/ConsoleManager4      )   *res://sys/singleton/console_manager.tscn   ��}^y�+#ꮼ�