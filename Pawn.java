public class Pawn extends Piece {
    public Pawn(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
    }
    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY){
        
        if ( (this.team == 'W' && afterY == beforeY + 1) || (this.team == 'B' && afterY == beforeY - 1) ){
            if(afterX == beforeX + 1 || afterX == beforeX - 1){
                //eat
            }
            this.x = (char)(afterX + 97);
            this.y = Character.forDigit(afterY, 10);
            
            System.out.println("newpos:"+ this.x + "" + this.y);
            return true;
        }
        
        return false;
    }
    @Override
    public void eat(Piece object){

    }
}
