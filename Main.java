import java.util.*;
class Main{

    public static void main(String[] args){
        int turn = 1;
        String winner = "null";
        String current = "null",nextPos = "null";
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        White white = new White();
        Black black = new Black();

        board.assignPieces(white, black);
        while(winner.equals("null")){
            board.draw();
            if (turn == 1){
                System.out.println("White Moves!");
               
            }
            else{
                System.out.println("Black Moves!");
            }
            do{
                System.out.print("Enter coord of piece to move(a1,b2...):");
                current = in.nextLine();
            }while((board.scanPos(current)) == null);
            
            
            System.out.print("Enter new position (a1,b2...):");
            nextPos = in.nextLine();
            
            // if pos is free, then proceed
            // if piece can perform move, then proceed
            

            turn *= -1;
        }
        in.close();
    
    }
}