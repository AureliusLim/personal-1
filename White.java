public class White extends Player  {
    public White(){
        super();
        this.team = "White";
    }
    @Override
    public void addPiece(Piece added){
        this.pieces.add(added);
    }
    @Override
    public void delPiece(Piece removed){
        this.pieces.remove(removed);
    }
}
