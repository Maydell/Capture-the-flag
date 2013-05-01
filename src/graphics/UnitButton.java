package graphics;

import org.newdawn.slick.gui.GUIContext;

import world.Unit;

public class UnitButton extends Button {

	Unit unit;

	public UnitButton(GUIContext container, int x, int y, Unit unit, String name) {
		super(container, Unit.images[unit.getID()][unit.getTeam()], x, y, name);
		this.unit = unit;
	}
}
