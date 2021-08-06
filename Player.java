import java.util.*;
abstract class Player{
    protected String team;
    protected ArrayList <Piece> pieces;
    protected boolean win;

    public Player(){
        this.pieces = new ArrayList<Piece>();
        this.win = false;
    }
    public ArrayList <Piece> getPieces(){
        return this.pieces;
    }
    public void addPiece(Piece added){
        this.pieces.add(added);
    }
    
    public void delPiece(Piece removed){
        char c = removed.getTeam();
        this.pieces.remove(removed);System.out.println( c +": deleted");
        
    }
    public boolean take(String old, String next, Player current, Player enemy, Board board){
        
        Piece taken =  board.scanPos(next, current, enemy);
        boolean res = this.play(old,next, current, enemy, board);
        if(res){
            enemy.delPiece(taken);
           
            return true;
        }
        return false;
    }
    public boolean play(String old, String next, Player current, Player enemy, Board board){
        int beforeX,beforeY, afterX, afterY;
        boolean toEat = true;
        Piece cur = board.scanPos(old, current, enemy);
        Piece taken = board.scanPos(next, current, enemy);

        if (taken == null){
            toEat = false;
        }
        

        try{
            beforeX = (int)(old.charAt(0) - 97);
            beforeY = Character.getNumericValue(old.charAt(1));
            afterX = (int)(next.charAt(0) - 97);
            afterY = Character.getNumericValue(next.charAt(1));
        }
        catch(StringIndexOutOfBoundsException e){
            System.out.println("Invalid input");
            return false;
        }
        
        
        return cur.move(beforeX, beforeY, afterX, afterY, toEat);
       
    }
}
