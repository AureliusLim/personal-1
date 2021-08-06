import java.util.*;

public class Board {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public Board(){
     
    }
   
    
    public void draw(Player w, Player b){ // draw board
        for (int row = 8; row > 0; row--){
            for (int col = 0; col < 8; col++){
                    if(this.scanPos(col, row, w, b) != null){
                        if(this.scanPos(col, row, w,b).getTeam() == 'B'){
                            System.out.print(ANSI_PURPLE + this.scanPos(col,row,w,b).getIdentifier() + ANSI_RESET  + " ");continue;
                        }
                        System.out.print(this.scanPos(col,row,w,b).getIdentifier()  + " ");
                    }
                    else{
                        System.out.print("- ");
                    }
            }
            System.out.println();
        }
    }

    public Piece scanPos(String position, Player w, Player b){ // determines if there is a piece on the chess coord
        for (int index = 0; index < w.getPieces().size(); index++){
            if (w.getPieces().get(index).getPos().equals(position)){
                return w.getPieces().get(index);
            }
         }
         for (int index = 0; index < b.getPieces().size(); index++){
             if (b.getPieces().get(index).getPos().equals(position)){
                 return b.getPieces().get(index);
             }
         }
        return null;
    }
    public Piece scanPos(int x, int y, Player w, Player b){ // converts index into chess coord and determine if there is a piece in it
       
        String translated = ((char)(x + 97)) + Integer.toString(y);

        for (int index = 0; index < w.getPieces().size(); index++){
           if (w.getPieces().get(index).getPos().equals(translated)){
               return w.getPieces().get(index);
           }
        }
        for (int index = 0; index < b.getPieces().size(); index++){
            if (b.getPieces().get(index).getPos().equals(translated)){
                return b.getPieces().get(index);
            }
        }
        
       
        return null;
    }

}
