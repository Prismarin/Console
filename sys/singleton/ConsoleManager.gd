# dev kelvin

extends Node


var game_loaded : bool = false
var currently_loaded_game_name : String


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
		print("Failed to load game %s, %s is already loaded!" % [gamename, currently_loaded_game_name])
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
	
	currently_loaded_game_name = gamename
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
