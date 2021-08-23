
public class ArrayTiles extends Tiles{
    private byte[] tiles;
    private int emptyPos;
    private int emptyRow;

    public ArrayTiles(String format) throws ConfigurationFormatException, InvalidConfigurationException{
        super(format);
        this.tiles = new byte[getSize() * getSize()];
        getConfiguration().initialize(this);
        emptyPosUpdate();
    }

    private void emptyPosUpdate(){
        for (int i = 0; i <tiles.length ; i++) {
            if(getTile(i) == 0)
                this.emptyPos = i;
        }
    }
    protected int getEmptyRow(){
        setEmptyRow();
        return this.emptyRow;
    }
    private void setEmptyRow(){
            if(emptyPos < getSize())
                this.emptyRow = 0;
            else if(emptyPos > getSize() && emptyPos < getSize() * 2)
                this.emptyRow = 1;
            else if(emptyPos > 2 * getSize() && emptyPos < getSize() * 3)
                this.emptyRow = 2;
            else if(emptyPos > 3 * getSize() && emptyPos < getSize() * 4)
                this.emptyRow = 3;
            else
                this.emptyRow = 5;
    }
    private byte getTile(int pos){
        if(pos < 0 || pos >= getSize() * getSize()){
            System.out.println("ERROR: Position out of the board");
            System.exit(0);
        }
        return tiles[pos];
    }
    private void setTile(int pos, byte value){
        if(pos < 0 || pos >= getSize() * getSize()){
            System.out.println("Error: Position out of the board");
            System.exit(0);
        }
        tiles[pos] = value;
    }
    public byte getTile(int row, int col){

        return getTile((row * getSize() + col));
    }
    public void setTile(int row, int col, byte value){

        setTile(row * getSize() + col, value);
    }
    public boolean isSolved(){
        boolean question = false;
        for (int i = 0; i < getSize() * getSize() - 1; i++) {
            if(getTile(i) == i+1)
                question = true;
            else
                return false;
        }
        return (question && getTile(getSize()*getSize()-1) == EMPTY);
    }
    protected void moveImpl(Direction direction){
        byte inputValue;
        int downAndUp = 0;
        int l = 0;
        int r = 0;
        switch (direction){
            case DOWN:
                downAndUp = getSize();
                break;

            case UP:
                downAndUp = -getSize();
                break;

            case LEFT:
                r = -1;
                break;

            case RIGHT:
                l = 1;
                break;
            case Q:
                System.out.println("Dont Give Up, You'll Do Better Next Time");
                System.exit(0);
            default:
                System.out.println("Wrong Input");
                r = 0;
                l = 0;
                downAndUp = 0;
                break;
        }
        if(emptyPos + r >= 0 && emptyPos + r < getSize()*getSize() && ((emptyPos + r) % getSize()) < getSize()-1 && l == 0 && downAndUp == 0){
            inputValue = getTile(emptyPos + r);
            setTile(emptyPos, inputValue);
            setTile(emptyPos + r, EMPTY);
            this.emptyPos += r;
        }
        else if( emptyPos + l > 0 && emptyPos + l <= getSize()*getSize() && ((emptyPos + l) % getSize() != 0  && r == 0 && downAndUp == 0)){
            inputValue = getTile(emptyPos + l);
            setTile(emptyPos, inputValue);
            setTile(emptyPos + l, EMPTY);
            this.emptyPos += l;
        }
        else if(emptyPos + downAndUp >= 0 && emptyPos + downAndUp < getSize()*getSize() && l == 0 && r == 0){
            inputValue = getTile(emptyPos + downAndUp);
            setTile(emptyPos, inputValue);
            setTile(emptyPos + downAndUp, EMPTY);
            this.emptyPos += downAndUp;
        }
    }
}
