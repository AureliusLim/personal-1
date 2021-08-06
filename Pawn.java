public class Pawn extends Piece {
    boolean first;
    public Pawn(String identifier, char team, char x, char y){
        super(identifier, team, x, y);
        this.first = true;
    }
    @Override
    public boolean move(int beforeX, int beforeY, int afterX, int afterY, boolean toEat){
        
        if ( (this.team == 'W' && afterY == beforeY + 1) || (this.team == 'B' && afterY == beforeY - 1)){ // one square
            
            if(toEat){
                if(afterX == beforeX - 1 || afterX == beforeX + 1){
                    this.first = false;
                    this.x = (char)(afterX + 97);
                    this.y = Character.forDigit(afterY, 10);
            
                    System.out.println("newpos:"+ this.x + "" + this.y);
                    return true;
                }
            }
            else{ 
                if(afterX != beforeX - 1 && afterX != beforeX + 1){
                    this.first = false;
                    this.x = (char)(afterX + 97);
                    this.y = Character.forDigit(afterY, 10);
            
                    System.out.println("newpos:"+ this.x + "" + this.y);
                    return true;
                }
            }
            
        }
        else if ( ((this.team == 'W' && afterY == beforeY + 2) || (this.team == 'B' && afterY == beforeY - 2)) && this.first == true  && beforeX == afterX){ // two squares
            if (!toEat){
                this.x = (char)(afterX + 97);
                this.y = Character.forDigit(afterY, 10);
                
                System.out.println("newpos:"+ this.x + "" + this.y);
                this.first = false;
                return true;
            }
        }
        
        
        return false;
    }
   
}
