public class Black extends Player{
    public Black(){
        super();
        this.team = "Black";
        Pawn p11 = new Pawn("P",'B','a','7');pieces.add(p11);
        Pawn p22 = new Pawn("P",'B','b','7');pieces.add(p22);
        Pawn p33 = new Pawn("P",'B','c','7');pieces.add(p33);
        Pawn p44 = new Pawn("P",'B','d','7');pieces.add(p44);
        Pawn p55 = new Pawn("P",'B','e','7');pieces.add(p55);
        Pawn p66 = new Pawn("P",'B','f','7');pieces.add(p66);
        Pawn p77 = new Pawn("P",'B','g','7');pieces.add(p77);
        Pawn p88 = new Pawn("P",'B','h','7');pieces.add(p88);

        Bishop b11 = new Bishop("B", 'B', 'c', '8');pieces.add(b11);
        Bishop b22 = new Bishop("B", 'B', 'f', '8'); pieces.add(b22);
    }
  
}
