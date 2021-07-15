import java.util.*;
class Main{

    public static void main(String[] args){
        int turn = 1;
        String winner = "null";
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        while(winner.equals("null")){
            board.draw();
            if (turn = 1){
                System.out.println("White Moves!");
                System.out.print("Enter coord of piece to move(x,y):")
                Integer.parse
                in.nextLine()

            }
            else{
                System.out.println("Black Moves!");
                System.out.print("Enter coord of piece to move(x,y):")
            }


            turn *= -1;
        }
        in.close();
    
    }
}