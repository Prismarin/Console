extends TileMap


@export var x_shift : int = 0
@export var y_shift : int = 0
@export var width : int = 36
@export var height : int = 20

var bg_tiles : Array
var noise : FastNoiseLite


func _ready():
	bg_tiles = [Vector2i(19, 14), Vector2i(20, 14), Vector2i(21, 14), \
		Vector2i(19, 15), Vector2i(20, 15), Vector2i(21, 15)]
	
	noise = FastNoiseLite.new()
	noise.seed = 1
	noise.noise_type = FastNoiseLite.TYPE_PERLIN
	noise.fractal_octaves = 3
	noise.frequency = 0.2
	
	for y in height:
		for x in width:
			if noise.get_noise_2d(x, y) > 0.3:
				set_cell(0, Vector2i(x + x_shift, y + y_shift), tile_set.get_source_id(0), bg_tiles.pick_random())
	

