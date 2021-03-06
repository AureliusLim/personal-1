public class Pawn extends Piece {
    boolean first;
    public Pawn(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
        this.first = true;
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
        if ( (this.team == 'W' && afterY == beforeY + 1) || (this.team == 'B' && afterY == beforeY - 1)){ // one square
            
            if(toEat){
                if(afterX == beforeX - 1 || afterX == beforeX + 1){
                    return true;
                }
            }
            else{ 
                if(afterX != beforeX - 1 && afterX != beforeX + 1){
                    this.first = false;
                    return true;
                }
            }
            
        }
        else if ( ((this.team == 'W' && afterY == beforeY + 2) || (this.team == 'B' && afterY == beforeY - 2)) && this.first == true  && beforeX == afterX){ // two squares
            if (!toEat){
                int tempX, tempY;
                tempX = beforeX;
                tempY = beforeY;
                while (tempY != afterY){
                    if (afterY > beforeY){
                        tempY++;
                    }
                    else if (afterY < beforeY){
                        tempY--;
                    }
                    if (board.scanPos(tempX, tempY, current, enemy) != null){
                        return false;
                    }
                }
                this.first = false;
                return true;
            }
        }
        return false;
    }
   
}
