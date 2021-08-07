public class White extends Player  {
    public White(){
        super();
        Pawn p1 = new Pawn("P",'W','a','2');pieces.add(p1);
        Pawn p2 = new Pawn("P",'W','b','2');pieces.add(p2);
        Pawn p3 = new Pawn("P",'W','c','2');pieces.add(p3);
        Pawn p4 = new Pawn("P",'W','d','2');pieces.add(p4);
        Pawn p5 = new Pawn("P",'W','e','2');pieces.add(p5);
        Pawn p6 = new Pawn("P",'W','f','2');pieces.add(p6);
        Pawn p7 = new Pawn("P",'W','g','2');pieces.add(p7);
        Pawn p8 = new Pawn("P",'W','h','2');pieces.add(p8);

        Bishop b1 = new Bishop("B", 'W', 'c', '1');pieces.add(b1);
        Bishop b2 = new Bishop("B", 'W', 'f', '1'); pieces.add(b2);
        this.team = "White";
    }
    
}
