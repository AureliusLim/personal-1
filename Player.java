import java.util.*;
abstract class Player{
    protected String team;
    protected ArrayList <Piece> pieces;
    protected boolean win;

    public Player(){
        this.pieces = new ArrayList<Piece>();
        this.win = false;
    }
    abstract public void addPiece(Piece added);
    abstract public void delPiece(Piece removed);
}
