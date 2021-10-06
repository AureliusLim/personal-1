import java.util.ArrayList;

abstract class Piece{
    protected String identifier;
    protected boolean eaten;
    protected char team;
    protected char x;
    protected char y;
    protected ArrayList<String> checkPos;

    public Piece(String identifier, char team, char x, char y){
        this.identifier = identifier;
        this.team = team;
        this.x = x;
        this.y = y;
        this.eaten = false;
    }
    
    abstract boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy);
    abstract boolean pathchecker(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy);
    public char getTeam(){
        return this.team;
    }
    public String getPos(){
        return "" + x + y;
    }
    public String getIdentifier(){
        return this.identifier;
    }
    public void update(int afterX, int afterY){
        this.x = (char)(afterX + 97);
        this.y = Character.forDigit(afterY, 10);
    }
}