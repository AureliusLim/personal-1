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
    public boolean kingmate(Board board, Player current, Player enemy){
        boolean value = true;
        for (int k = 0; k < current.getPieces().size(); k++){
            String currentpos = current.getPieces().get(k).getPos();
            int x = (int)(currentpos.charAt(0) - 97);
            int y= Character.getNumericValue(currentpos.charAt(1));
            Piece occupied = null;
            int tempX = x;
            int tempY = y;
            if (current.getPieces().get(k).getIdentifier().equals("P")){ // pawn
                if (current.getPieces().get(k).getTeam() == 'W'){
                    
                    if (current.getPieces().get(k).move(x, y, x,y+2, false, board, current, enemy) == false){
                        current.getPieces().get(k).update(x, y+1);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    current.getPieces().get(k).update(x,y);
                    for (int i = 0; i < enemy.getPieces().size(); i++){
                       tempX -= 1;
                        tempY += 1;
                        String eatpos1 =(char)(tempX + 97) + Character.getNumericValue(tempY) + "";
                        tempX = x;
                        tempY = y;
                        tempX += 1;
                        tempY += 1;
                        String eatpos2 = (char)(tempX + 97) + Character.getNumericValue(tempY) + "";
                        if (enemy.getPieces().get(i).getPos().equals(eatpos1)|| enemy.getPieces().get(i).getPos().equals(eatpos2)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            enemy.getPieces().get(i).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }
                    }  
                  
                }
                else{
                    if (current.getPieces().get(k).move(x, y, x,y-2, false, board, current, enemy) == false){
                        current.getPieces().get(k).update(x, y-1);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    current.getPieces().get(k).update(x,y);
                    for (int i = 0; i < enemy.getPieces().size(); i++){
                       tempX -= 1;
                        tempY -= 1;
                        String eatpos1 =(char)(tempX + 97) + Character.getNumericValue(tempY) + "";
                        tempX = x;
                        tempY = y;
                        tempX += 1;
                        tempY -= 1;
                        String eatpos2 = (char)(tempX + 97) + Character.getNumericValue(tempY) + "";
                        if (enemy.getPieces().get(i).getPos().equals(eatpos1)|| enemy.getPieces().get(i).getPos().equals(eatpos2)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            enemy.getPieces().get(i).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }
                    }    
                  
                }
            }
            
            else if (current.getPieces().get(k).getIdentifier().equals("B")){ // bishop
                // upleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0){
                   
                    tempX -= 1;
                    tempY += 1;
                   
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        occupied.update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                   
                }
                
                // upright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0){
                    tempX += 1;
                    tempY += 1;
                   
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        occupied.update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                }
                   
                
                // downleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0){
                    tempX -= 1;
                    tempY -= 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        occupied.update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                }
                 
                // downright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0){
                    tempX += 1;
                    tempY -= 1;
                   
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        occupied.update(tempX, tempY);
                        value = this.kingcheck(board,current,enemy);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                }
               
            }
            else if (current.getPieces().get(k).getIdentifier().equals("H")){ // knight
                
                tempX -= 1;
                tempY += 2;
                occupied = null;
                
    
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
                tempX = x;
                tempY = y;
               
                tempX += 1;
                tempY += 2;
                  
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }

                tempX = x;
                tempY = y;
            
                tempX -= 2;
                tempY += 1;
              
            
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
                
                   
                
                tempX = x;
                tempY = y;
                
                tempX -= 2;
                tempY -= 1;
                
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
                
                
                
                tempX = x;
                tempY = y;
                
                tempX += 2;
                tempY -= 1;
                
            
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
                
                
                tempX = x;
                tempY = y;
                
                tempX += 2;
                tempY += 1;
                
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }

                tempX = x;
                tempY = y;
               
               
                tempX -= 1;
                tempY -= 2;
                
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
                tempX = x;
                tempY = y;
               
                tempX += 1;
                tempY -= 2;
                
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 )){
                    occupied = board.scanPos(tempX, tempY, current, enemy);
                    if (occupied == null){

                    
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x, y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if (occupied != null && occupied.getTeam() != current.getTeam().charAt(0) && !occupied.getIdentifier().equals("K")){
                        current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                        value = kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        board.scanPos(x, y, current, enemy).update(tempX, tempY);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }

                }
            }
            else if (current.getPieces().get(k).getIdentifier().equals("R")){ // rook
                //left
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                   
                }
               
                // up
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
                   
                
              
                // right
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
             
                // down
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY -= 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
                
            }
           
            else if (current.getPieces().get(k).getIdentifier().equals("Q")){// queen
                // upleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    tempY += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
              
                
                // upright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                   
                }
              
                // downleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    tempY -= 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                  
                }
                
                // downright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY -= 1;
                   
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }  
                }
               
                //left
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }  
                }
            
                // up
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
                
                // right
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }   
                }
                
                // down
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0){
                    tempY -= 1;

                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if (occupied == null  && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if (value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                    else if ((occupied != null && !(occupied.getIdentifier().equals("K"))) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                        if (occupied.getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                        }

                    }
                }
               
            }
            else if (current.getPieces().get(k).getIdentifier().equals("K")){// king
                //left
                tempX = x;
                tempY = y;
               
               
                tempX -= 1;
                
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    if (board.scanPos(tempX, tempY, current, enemy) != null){
                        if (board.scanPos(x, y, current, enemy).getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                           
                        }

                    }
                    else{
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if(value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                   
                    
                }
                   
                // up
                tempX = x;
                tempY = y;
               
                tempY += 1;
                
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    if (board.scanPos(tempX, tempY, current, enemy) != null){
                        if (board.scanPos(x, y, current, enemy).getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                           
                        }

                    }
                    else{
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if(value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                   
                    
                }

                // right
                tempX = x;
                tempY = y;
               
                tempX += 1;
              
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    if (board.scanPos(tempX, tempY, current, enemy) != null){
                        if (board.scanPos(x, y, current, enemy).getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                           
                        }

                    }
                    else{
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if(value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                   
                    
                }
                    
                // down
                tempX = x;
                tempY = y;
                
                tempY -= 1;
               
            
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    if (board.scanPos(tempX, tempY, current, enemy) != null){
                        if (board.scanPos(x, y, current, enemy).getTeam() == enemy.getTeam().charAt(0)){
                            current.take(""+(char)(x + 97) + Character.forDigit(y, 10), ""+(char)(tempX + 97) + Character.forDigit(tempY, 10), current, enemy, board, 0);
                            value = kingcheck(board, current, enemy);
                            current.getPieces().get(k).update(x,y);
                            board.scanPos(x, y, current, enemy).update(tempX, tempY);
                            if (value == false){
                                //System.out.println(currentpos);
                                return value;
                            }
                           
                        }

                    }
                    else{
                        current.getPieces().get(k).update(tempX, tempY);
                        value = this.kingcheck(board, current, enemy);
                        current.getPieces().get(k).update(x,y);
                        if(value == false){
                            //System.out.println(currentpos);
                            return value;
                        }
                    }
                   
                    
                }
            }
        
        
        
       
        }
        return true;
    }
    public boolean kingcheck(Board board, Player current, Player enemy){
        ArrayList<String> checkPos = new ArrayList<>();
        for (int k = 0; k < enemy.getPieces().size(); k++){
            String currentpos = enemy.getPieces().get(k).getPos();
            int x = (int)(currentpos.charAt(0) - 97);
            int y= Character.getNumericValue(currentpos.charAt(1));
            Piece occupied = null;
            int tempX = x;
            int tempY = y;
            if (enemy.getPieces().get(k).getIdentifier().equals("P")){ // pawn
                if (enemy.getTeam() == "White"){
                
                    // white upleft
                    tempX -= 1;
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                    if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0))
                    checkPos.add(""+valueX+valueY);

                    // white upright
                    tempX = x;
                    tempY = y;
                    tempX += 1;
                    tempY += 1;
                    valueX = (char)(tempX + 97);
                    valueY = (char)(Character.forDigit(tempY, 10));
                    if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0))
                    checkPos.add(""+valueX+valueY);
                }
                else{
                    // black downleft
                    tempX = x;
                    tempY = y;
                    tempX -= 1;
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                    if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0))
                    checkPos.add(""+valueX+valueY);

                    // white downright
                    tempX = x;
                    tempY = y;
                    tempX += 1;
                    tempY -= 1;
                    valueX = (char)(tempX + 97);
                    valueY = (char)(Character.forDigit(tempY, 10));
                    if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0))
                    checkPos.add(""+valueX+valueY);
                }
            }
            
            else if (enemy.getPieces().get(k).getIdentifier().equals("B")){ // bishop
                // upleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 && occupied == null){
                   
                    tempX -= 1;
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0)){
                    checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // upright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // downleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // downright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                    }
                   
                }
            }
            else if (enemy.getPieces().get(k).getIdentifier().equals("H")){ // knight
                
                tempX -= 1;
                tempY += 2;
                char valueX = (char)(tempX + 97);
                char valueY = (char)(Character.forDigit(tempY, 10));
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
              
                tempX = x;
                tempY = y;
               
                tempX += 1;
                tempY += 2;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));    
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);

                tempX = x;
                tempY = y;
            
                tempX -= 2;
                tempY += 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
            
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
                
                   
                
                tempX = x;
                tempY = y;
                
                tempX -= 2;
                tempY -= 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
                
                
                
                tempX = x;
                tempY = y;
                
                tempX += 2;
                tempY -= 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
            
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
                
                
                
                tempX = x;
                tempY = y;
                
                tempX += 2;
                tempY += 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);

                tempX = x;
                tempY = y;
               
               
                tempX -= 1;
                tempY -= 2;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                if ((tempX > -1 && tempX < 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
                
                tempX = x;
                tempY = y;
               
                tempX += 1;
                tempY -= 2;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                if ((tempX > -1 && tempX <= 8 && tempY < 9 && tempY >= 0 ))
                    checkPos.add(""+valueX+valueY);
            }
            else if (enemy.getPieces().get(k).getIdentifier().equals("R")){ // rook
                //left
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                    if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                        checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // up
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                    if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                        checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // right
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                    if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                        checkPos.add(""+valueX+valueY);
                    }
                   
                }
                // down
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                    if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                        checkPos.add(""+valueX+valueY);
                    }
                   
                }
            }
            else if (enemy.getPieces().get(k).getIdentifier().equals("Q")){// queen
                // upleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // upright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // downleft
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // downright
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                //left
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // up
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // right
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempX += 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
                // down
                tempX = x;
                tempY = y;
                occupied = null;
                while (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0  && occupied == null){
                    tempY -= 1;
                    char valueX = (char)(tempX + 97);
                    char valueY = (char)(Character.forDigit(tempY, 10));
                   occupied = board.scanPos(tempX, tempY, current, enemy);
                   if ((occupied == null || occupied.getIdentifier().equals("K") ) && (tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 )){
                    checkPos.add(""+valueX+valueY);
                }
                   
                }
            }
            else if (enemy.getPieces().get(k).getIdentifier().equals("K")){// king
                //left
                tempX = x;
                tempY = y;
               
               
                tempX -= 1;
                char valueX = (char)(tempX + 97);
                char valueY = (char)(Character.forDigit(tempY, 10));
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 ))
                    checkPos.add(""+valueX+valueY);
                // up
                tempX = x;
                tempY = y;
               
                tempY += 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 ))
                    checkPos.add(""+valueX+valueY);

                // right
                tempX = x;
                tempY = y;
               
                tempX += 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
                
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 ))
                    checkPos.add(""+valueX+valueY);
                    
                // down
                tempX = x;
                tempY = y;
                
                tempY -= 1;
                valueX = (char)(tempX + 97);
                valueY = (char)(Character.forDigit(tempY, 10));
            
                if ((tempX != -1 && tempX != 8 && tempY != 9 && tempY != 0 ))
                    checkPos.add(""+valueX+valueY);
            }
        }
        
        String currentKing = "";
        for (int i = 0; i < current.getPieces().size(); i++){
            if (current.getPieces().get(i).getIdentifier() == "K"){
                currentKing = current.getPieces().get(i).getPos();
                break;
            }
        }
        // for (int i = 0; i < checkPos.size(); i++){
        //     System.out.println(checkPos.get(i));
        // }
        for (int i = 0; i < checkPos.size(); i++){
            //System.out.println("KINGPOS:" + currentKing + "=" + checkPos.get(i));
            if (currentKing.equals(checkPos.get(i))){
                return true; // currentKing is checked
            }
        }
        return false;
   }

}
