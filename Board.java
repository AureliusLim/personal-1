import java.util.*;
public class Board {
    private ArrayList<Piece> pieces;
    
    public Board(){
        this.pieces = new ArrayList<Piece>();
        Pawn p1 = new Pawn("pawn",'W','a','2');pieces.add(p1);
        Pawn p2 = new Pawn("pawn",'W','b','2');pieces.add(p2);
        Pawn p3 = new Pawn("pawn",'W','c','2');pieces.add(p3);
        Pawn p4 = new Pawn("pawn",'W','d','2');pieces.add(p4);
        Pawn p5 = new Pawn("pawn",'W','e','2');pieces.add(p5);
        Pawn p6 = new Pawn("pawn",'W','f','2');pieces.add(p6);
        Pawn p7 = new Pawn("pawn",'W','g','2');pieces.add(p7);
        Pawn p8 = new Pawn("pawn",'W','h','2');pieces.add(p8);

        Pawn p11 = new Pawn("pawn",'B','a','7');pieces.add(p11);
        Pawn p22 = new Pawn("pawn",'B','b','7');pieces.add(p22);
        Pawn p33 = new Pawn("pawn",'B','c','7');pieces.add(p33);
        Pawn p44 = new Pawn("pawn",'B','d','7');pieces.add(p44);
        Pawn p55 = new Pawn("pawn",'B','e','7');pieces.add(p55);
        Pawn p66 = new Pawn("pawn",'B','f','7');pieces.add(p66);
        Pawn p77 = new Pawn("pawn",'B','g','7');pieces.add(p77);
        Pawn p88 = new Pawn("pawn",'B','h','7');pieces.add(p88);
    }
    public void assignPieces(White t1, Black t2){
        for (int i = 0; i < this.pieces.size(); i++){
            if (this.pieces.get(i).getTeam() == 'W'){
                t1.addPiece(this.pieces.get(i));
                continue;
            }
            t2.addPiece(this.pieces.get(i));
        }
    }
    public void draw(){
        System.out.println(this.pieces.get(2).getPos());
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                if (this.scanPos(col, row+1) != null){
                    System.out.print(this.scanPos(row,col).getIdentifier());
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
    public void play(String position){

    }
    public Piece scanPos(String position){
        for (int i = 0; i < this.pieces.size(); i++){
            if(this.pieces.get(i).getPos() == position){
                return this.pieces.get(i);
            }
        }
        return null;
    }
    public Piece scanPos(int x, int y){
       
        String translated = (""+ (char)(x + 97)) + Integer.toString(y);
        
        for (int i = 0; i < this.pieces.size(); i++){
            if(this.pieces.get(i).getPos() == translated){
                return this.pieces.get(i);
            }
        }
        return null;
    }

}
