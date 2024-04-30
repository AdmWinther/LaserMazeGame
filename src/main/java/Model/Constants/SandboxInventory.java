package Model.Constants;

import Model.Classes.Token.*;
import Model.Classes.Utils.Orientation;
import Model.Interfaces.Inventory;

import java.util.List;

public class SandboxInventory implements Inventory {
	public List<Token> getItems() {
		return List.of(new Block(true), new DoubleSidedMirror(true, Orientation.RIGHT), new OneSidedMirror(true, Orientation.RIGHT), new Checkpoint(true, Orientation.RIGHT), new Splitter(true, Orientation.RIGHT), new Target(true, Orientation.RIGHT), new LaserGun(true, Orientation.RIGHT));
	}
}
