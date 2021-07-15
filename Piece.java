abstract class Piece{
    protected String identifier;
    protected boolean eaten;
    protected char team;
    protected char x;
    protected char y;
    
    public Piece(String identifier, char team, char x, char y){
        this.identifier = identifier;
        this.team = team;
        this.x = x;
        this.y = y;
        this.eaten = false;
    }
    abstract public void eat(Piece x);
    abstract public boolean move(String before, String after);
    public char getTeam(){
        return this.team;
    }
    public String getPos(){
        return "" + x + y;
    }
    public String getIdentifier(){
        return this.identifier;
    }
}