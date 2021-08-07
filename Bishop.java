
public class Bishop extends Piece{
    public Bishop(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat){
        int changeX = Math.abs(beforeX - afterX);
        int changeY = Math.abs(beforeY - afterY);

        if (changeX == changeY){
            this.x = (char)(afterX + 97);
            this.y = Character.forDigit(afterY, 10);
            return true;
        }

        return false;
    }
}
