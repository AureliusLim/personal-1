public class Board {
    public void draw(){
        for (int row = 0; row < 8; row++){
            for (int col = 0; col < 8; col++){
                System.out.print("- ");
            }
            System.out.println();
        }
    }
}
