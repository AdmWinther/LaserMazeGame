package Classes.Token;

import Classes.Utils.Orientation;

public class Checkpoint extends Token{
    boolean checked;

    public Checkpoint(boolean movable) {
        super(movable);
        this.checked = false;
    }

    @Override
    public Orientation propagateLaser(Orientation orientation) {
        checked = true;  //if the propagate laser method is called that means the checkpoint was passed
        return orientation;
    }

    public boolean isChecked(){
        return checked;
    }


}
