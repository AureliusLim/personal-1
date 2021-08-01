public class Black extends Player{
    public Black(){
        super();
        this.team = "Black";
    }
    @Override
    public void addPiece(Piece added){
        this.pieces.add(added);
    }
    @Override
    public void delPiece(Piece removed){
        System.out.println(this.pieces.size());
        this.pieces.remove(removed);
        System.out.println(this.pieces.size());
    }
}
