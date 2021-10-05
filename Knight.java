public class Knight extends Piece{
    public Knight(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        if (this.pathchecker(beforeX, beforeY, afterX, afterY, toEat, board, current, enemy) == true){
            this.update(afterX, afterY);
            return true;
        }
        return false;
    }
    public boolean pathchecker(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        if( (afterX == beforeX - 1) && (afterY == beforeY + 2) || (afterX == beforeX + 1) && (afterY == beforeY + 2) || (afterX == beforeX - 2) && (afterY == beforeY + 1) || (afterX == beforeX - 2) && (afterY == beforeY - 1) || (afterX == beforeX + 2) && (afterY == beforeY - 1) || (afterX == beforeX + 2) && (afterY == beforeY + 1) || (afterX == beforeX - 1) && (afterY == beforeY - 2) || (afterX == beforeX + 1) && (afterY == beforeY - 2)){
            return true;
        }
        return false;
    }
}
