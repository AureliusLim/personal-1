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
            if (turn == 1){
                if (board.kingcheck(board, white, black) == true){
                    System.out.println("Check!");
                    //System.out.println((board.kingmate(board, white, black)));
                    if (board.kingmate(board, white, black) == true){
                        winner = "Black";
                        break;
                    }
                }
            }
                
            else{
                if (board.kingcheck(board, black, white) == true){
                    System.out.println("Check!");
                    //System.out.println((board.kingmate(board, black, white)));
                    if (board.kingmate(board,black, white) == true){
                        winner = "White";
                        break;
                    }
                }
            }
               

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
                       
                         valid = white.take(current, nextPos, white, black, board, 1);
                    }
                    else{
                        valid = black.take(current,nextPos,black, white, board, 1);
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
            
          
            

            turn *= -1;
            valid = false;
        }
        System.out.println(winner + " Wins!");
        in.close();
    
    }
}