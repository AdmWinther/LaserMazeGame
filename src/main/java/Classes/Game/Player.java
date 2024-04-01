package Classes.Game;
public class Player {
    private String name;
    private int level;

    Player(String name){
        this.name = name;
        this.level=1;
    }

    Player(){
        this("No Name");
    }

    public void setName(String newName){
        this.name = newName;
    }

    public String getName() {
        return this.name;
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
