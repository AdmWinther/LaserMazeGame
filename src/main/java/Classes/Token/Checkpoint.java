package Classes.Token;

import Classes.Utils.Orientation;

import java.util.HashSet;
import java.util.Set;

public class Checkpoint extends Token{
    boolean checked;

    public Checkpoint(boolean movable) {
        super(movable);
        this.checked = false;
    }

    @Override
    public Set<Orientation> propagateLaser(Orientation orientation) {
        checked = true;  //if the propagate laser method is called that means the checkpoint was passed
        return null;
    }

    public boolean isChecked(){
        return checked;
    }


}
