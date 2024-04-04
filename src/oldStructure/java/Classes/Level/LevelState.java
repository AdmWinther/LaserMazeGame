package Classes.Level;

public enum LevelState {

    /**
     * Level is starting
     */
    STARTING,

    /**
     * The user need to place a new token
     */
    NEED_USER_INPUT,

    /**
     * Level is finished
     */
    FINISHED,

    /**
     * Level's solution is being checked
     */
    CHECKING_SOLUTION,


}
