
public class MatrixTiles extends Tiles {
    private byte[][] tiles;
    private int emptyRow;
    private int emptyCol;

    public MatrixTiles(String format) throws ConfigurationFormatException, InvalidConfigurationException{
        super(format);
        this.tiles = new byte[getSize()][getSize()];
        getConfiguration().initialize(this);
        emptyRowUpdate();
        emptyColUpdate();
    }

    private void emptyColUpdate(){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                if(getTile(i,j) == 0)
                    this.emptyCol = j;
            }
        }
    }
    protected int getEmptyRow(){

        return this.emptyRow;
    }
    private void emptyRowUpdate(){
        for(int i = 0; i < tiles.length; i++){
            for(int j = 0; j < tiles[i].length; j++){
                if(getTile(i,j) == 0)
                    this.emptyRow = i;
            }
        }
    }
    public byte getTile(int row, int col){
        if (row < 0 || row >= getSize()) {
            System.out.println("Error: Position Out Of The Board");
            System.exit(0);
        }
        if (col < 0 || col >= getSize()) {
            System.out.println("Error: Position Out Of The Board");
            System.exit(0);
        }
        return tiles[row][col];
    }
    public void setTile(int row, int col, byte value) {
        if (row < 0 || row >= getSize()) {
            System.out.println("Error: Position Out Of The Board");
            System.exit(0);
        }
        if (col < 0 || col >= getSize()) {
            System.out.println("Error:  Position Out Of The Board");
            System.exit(0);
        }
        tiles[row][col] = value;
    }
    public boolean isSolved() {
        boolean question = false;
        byte from1toSize = 1;
        for(int i = 0; i < tiles.length ; i++){
            for(int j = 0; j < tiles[i].length; j++){
                if(getTile(i,j) == from1toSize)
                    question = true;
                else if(question && from1toSize == getSize()*getSize() && getTile(getSize()-1,getSize()-1) == EMPTY){
                    question = true;
                }
                else
                    return false;
                from1toSize++;
            }

        }
        return question;
    }
    protected void moveImpl(Direction direction){
        byte inputValue;
        byte x = 0;
        byte y = 0;
        switch (direction){
            case DOWN:
                x = 1;
                break;

            case UP:
                x = -1;
                break;

            case LEFT:
                y = -1;
                break;

            case RIGHT:
                y = 1;
                break;
            case Q:
                System.out.println("Dont Give Up, You'll Do Better Next Time");
                System.exit(0);
            default:
                System.out.println("OOPS Wrong Input");
                break;
        }
        if(emptyCol + y >= 0 && emptyCol + y < getSize() && emptyRow + x >= 0 && emptyRow + x < getSize()) {
            inputValue = getTile(emptyRow + x, emptyCol + y);
            setTile(emptyRow, emptyCol, inputValue);
            setTile(emptyRow + x, emptyCol + y, EMPTY);
            this.emptyCol += y;
            this.emptyRow += x;
        }

    }
}
