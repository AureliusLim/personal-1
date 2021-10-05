
public class Bishop extends Piece{
    public Bishop(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
       if(this.pathchecker(beforeX, beforeY, afterX, afterY, toEat, board, current, enemy) == true){
           this.update(afterX, afterY);
           return true;
       }
        return false;
    }
    public boolean pathchecker(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        int changeX = Math.abs(beforeX - afterX);
        int changeY = Math.abs(beforeY - afterY);

        if (changeX == changeY){
            int tempX, tempY;
            tempX = beforeX;
            tempY = beforeY;
            while( (tempX != afterX && tempY != afterY)){
                if ( (afterX < beforeX) && (afterY < beforeY ) ){// down-left
                    tempX--;
                    tempY--;
                }
                else if ( (afterX < beforeX) && (afterY > beforeY) ){// up-left
                    tempX--;
                    tempY++;
                }

                else if( (afterX > beforeX) && (afterY < beforeY)){// down-right
                    tempX++;
                    tempY--;
                }
                else if( (afterX > beforeX) && (afterY > beforeY) ){// up-right
                    tempX++;
                    tempY++;
                }
                if (board.scanPos(tempX, tempY, current, enemy) != null){
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}

