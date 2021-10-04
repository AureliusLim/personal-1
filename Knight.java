public class Knight extends Piece{
    public Knight(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat, Board board, Player current, Player enemy){
        System.out.println("Before:" + beforeX + " " + beforeY);
        System.out.println("After:" + afterX + " " + afterY);
        if( (afterX == beforeX - 1) && (afterY == beforeY + 2) || (afterX == beforeX + 1) && (afterY == beforeY + 2) || (afterX == beforeX - 2) && (afterY == beforeY + 1) || (afterX == beforeX - 2) && (afterY == beforeY - 1) || (afterX == beforeX + 2) && (afterY == beforeY - 1) || (afterX == beforeX + 2) && (afterY == beforeY - 1) || (afterX == beforeX - 1) && (afterY == beforeY - 2) || (afterX == beforeX + 1) && (afterY == beforeY - 2)){
            this.x = (char)(afterX + 97);
            this.y = Character.forDigit(afterY, 10);
            return true;
        }
        return false;
    }
}
