ooad
====

ooad final project

- Can implement decorator using composite with board.
- observer pull when initializing and push during the game.
- controller only affects the model (not the view).

Add to project:
- Interface GameControl
	public int control(Object arg)

Interface GameGraphic
- add member ControlFunction control; //this function will only be used in the menu items, and could possibly be used for the graphic interface.

- add composite class MenuGraphic
- add leaf class MenuItemGraphic

When creating the game, the default constructor creates our view, and model.
Make another constructor getting view and controller as members.

vsHumanControl extends FourInARowControoler Implements GameControl
	public int control(Object arg)
		_player[0] = 
	