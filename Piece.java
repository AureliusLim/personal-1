abstract class Piece{
    protected String identifier;
    protected boolean eaten;
    protected char team;
    protected int posX;
    protected int posY;
    
    abstract public void eat(Piece x);
    abstract public void move();
}