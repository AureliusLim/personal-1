public class Pawn extends Piece {
    public Pawn(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    @Override
    public boolean move(String before, String after){
        return false;
    }
    @Override
    public void eat(Piece object){

    }
}
