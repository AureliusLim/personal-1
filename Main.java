import java.util.*;

class Main{
    static boolean parseTurn(int num, char c){
        if(c == 'W' && num == 1 || c == 'B' && num == -1){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        int turn = 1;
        boolean valid = false;
        String winner = "null";
        String current = "null",nextPos = "null";
        Scanner in = new Scanner(System.in);
        Board board = new Board();
        White white = new White();
        Black black = new Black();

        //board.assignPieces(white, black);
        while(winner.equals("null")){ // continue game until it is over
            board.draw(white,black);
            if (turn == 1){ // White's turn
                System.out.println("White Moves!");
            }
            else{ // Black's Turn
                System.out.println("Black Moves!");
            }
            do{
                System.out.print("Enter coord of piece to move(a1,b2...):"); 
                current = in.nextLine();
            }while(board.scanPos(current, white, black) == null || !parseTurn(turn, board.scanPos(current, white, black).getTeam()) ); // scan if selected piece is valid and of the same team
            
            while(!valid){
                System.out.print("Enter new position (a1,b2...):");
                nextPos = in.nextLine();
                if(board.scanPos(nextPos,white,black) != null && !parseTurn(turn,board.scanPos(nextPos,white,black).getTeam())){ // if player intends to eat piece
                    if(turn == 1){
                         valid = white.take(current, nextPos, white, black, board);
                    }
                    else{
                        valid = black.take(current,nextPos,black, white, board);
                    }
                }
                else{ // if player intends to move piece
                    if(turn == 1){
                        valid = white.play(current, nextPos, white, black, board);
                   }
                   else{
                       valid = black.play(current,nextPos,black, white, board);
                   }
                } 
            }
            
           
            // if newpos is free from same team and piece can perform it then proceed
           
            

            turn *= -1;
            valid = false;
        }
        in.close();
    
    }
}