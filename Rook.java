public class Rook extends Piece{
    public Rook(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        
        if(this.pathchecker(beforeX, beforeY, afterX, afterY, toEat, board, current, enemy) == true){
            this.update(afterX, afterY);
            return true;
        }
        return false;
    }
    public boolean pathchecker(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        
        if (afterX == beforeX && afterY != beforeY || afterX != beforeX && afterY == beforeY){
            if ( afterX < beforeX && afterY == beforeY || afterX == beforeX && afterY > beforeY || afterX > beforeX && afterY == beforeY || afterX == beforeX && afterY < beforeY){
                int tempX, tempY;
                tempX = beforeX;
                tempY = beforeY;
                
                while(tempX != afterX || tempY != afterY){   
                    if (afterX < beforeX && afterY == beforeY){// left
                        tempX--;
                    }
                    else if(afterX == beforeX && afterY > beforeY){ // up
                        tempY++;
                    }
                    else if(afterX > beforeX && afterY == beforeY){ // right
                        tempX++;
                    }
                    else if (afterX == beforeX && afterY < beforeY){ // down
                        tempY--;
                    }
                    if (board.scanPos(tempX, tempY, current, enemy) != null && tempX != afterX && tempY!= afterY){
                        return false;
                    }
                }
                return true;
            }
    }
        return false;
    }
}
