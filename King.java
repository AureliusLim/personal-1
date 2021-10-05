public class King extends Piece{

    public King(String identifier, char team, char x, char y) {
        super(identifier, team, x, y);
    }

    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current,
            Player enemy) {
        if (this.pathchecker(beforeX, beforeY, afterX, afterY, toEat, board, current, enemy) == true){
            this.update(afterX, afterY);
            return true;
        }
        return false;
    }

    @Override
    public boolean pathchecker(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current,
            Player enemy) {
        if (afterX == beforeX - 1 && afterY == beforeY || afterX == beforeX && afterY == beforeY + 1 || afterX == beforeX + 1 && afterY == beforeY || afterX == beforeX && afterY == beforeY - 1 || afterX == beforeX - 1 && afterY == beforeY - 1 || afterX == beforeX - 1 && afterY == beforeY + 1 || afterX == beforeX + 1 && afterY == beforeY + 1 || afterX == beforeX + 1 || afterY == beforeY - 1){
            if (board.scanPos(afterX, afterY, current, enemy) != null && toEat == false){
                return false;
            }
            return true;
        }
        return false;
    }
    
}
