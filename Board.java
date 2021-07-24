import java.util.*;
public class Board {
    private ArrayList<Piece> pieces;
    
    public Board(){
        this.pieces = new ArrayList<Piece>();
        Pawn p1 = new Pawn("P",'W','a','2');pieces.add(p1);
        Pawn p2 = new Pawn("P",'W','b','2');pieces.add(p2);
        Pawn p3 = new Pawn("P",'W','c','2');pieces.add(p3);
        Pawn p4 = new Pawn("P",'W','d','2');pieces.add(p4);
        Pawn p5 = new Pawn("P",'W','e','2');pieces.add(p5);
        Pawn p6 = new Pawn("P",'W','f','2');pieces.add(p6);
        Pawn p7 = new Pawn("P",'W','g','2');pieces.add(p7);
        Pawn p8 = new Pawn("P",'W','h','2');pieces.add(p8);

        Pawn p11 = new Pawn("P",'B','a','7');pieces.add(p11);
        Pawn p22 = new Pawn("P",'B','b','7');pieces.add(p22);
        Pawn p33 = new Pawn("P",'B','c','7');pieces.add(p33);
        Pawn p44 = new Pawn("P",'B','d','7');pieces.add(p44);
        Pawn p55 = new Pawn("P",'B','e','7');pieces.add(p55);
        Pawn p66 = new Pawn("P",'B','f','7');pieces.add(p66);
        Pawn p77 = new Pawn("P",'B','g','7');pieces.add(p77);
        Pawn p88 = new Pawn("P",'B','h','7');pieces.add(p88);
    }
    public void assignPieces(White t1, Black t2){ // assign piece for each team
        for (int i = 0; i < this.pieces.size(); i++){
            if (this.pieces.get(i).getTeam() == 'W'){
                t1.addPiece(this.pieces.get(i));
                continue;
            }
            t2.addPiece(this.pieces.get(i));
        }
    }
    public void draw(){ // draw board
        for (int row = 8; row > 0; row--){
            for (int col = 0; col < 8; col++){
                if (this.scanPos(col, row) != null){
                    System.out.print(this.scanPos(col,row).getIdentifier() + " ");
                }
                else{
                    System.out.print("- ");
                }
                
            }
            System.out.println();
        }
    }
    
    public void prePlay(String position){

    }
    public boolean play(String old, String next){
        Piece cur = this.scanPos(old);
     

        int beforeX = (int)(old.charAt(0) - 97);
        int beforeY = Character.getNumericValue(old.charAt(1));
        int afterX = (int)(next.charAt(0) - 97);
        int afterY = Character.getNumericValue(next.charAt(1));
        return cur.move(beforeX, beforeY, afterX, afterY);
       
    }
    public Piece scanPos(String position){ // determines if there is a piece on the chess coord
        for (int i = 0; i < this.pieces.size(); i++){
            if(this.pieces.get(i).getPos().equals(position)){
                return this.pieces.get(i);
            }
        }
        return null;
    }
    public Piece scanPos(int x, int y){ // converts index into chess coord and determine if there is a piece in it
       
        String translated = ((char)(x + 97)) + Integer.toString(y);
        
        for (int i = 0; i < this.pieces.size(); i++){
            if(this.pieces.get(i).getPos().equals(translated)){
                return this.pieces.get(i);
            }
        }
       
        return null;
    }

}
