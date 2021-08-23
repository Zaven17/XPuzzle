
public abstract class Tiles{

    public enum Direction{Q, UP, DOWN, LEFT, RIGHT}

    public static final byte EMPTY = 0;
    private int moves;
    private Configuration configuration;
    private int inversionCount;

    public abstract byte getTile(int row, int col);
    public abstract void setTile(int row, int col, byte value);
    public abstract boolean isSolved();
    protected abstract void moveImpl(Direction direction);
    protected abstract int getEmptyRow();

    public void move(Direction direction){
        moveImpl(direction);
        incrementMoveCount();
    }
    public Tiles(String format) throws ConfigurationFormatException{
            this.moves = 0;
            configuration = new Configuration(format);
    }
    protected void incrementMoveCount(){

        this.moves++;
    }
    public int getMoveCount(){

        return moves;
    }
    public int getSize(){

        return configuration.getSize();
    }
    public int getInversionCount(){

        return this.inversionCount;
    }
    protected Configuration getConfiguration(){

        return configuration;
    }
    public void ensureValidity() throws InvalidConfigurationException{
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                if(getTile(i,j) < 0 || getTile(i,j) > getSize()* getSize() - 1)
                    throw new InvalidConfigurationException("Invalid configuration: incorrect tile value "+
                            getTile(i,j)+".");
                if(!emptyTileChecker())
                    throw new InvalidConfigurationException("Invalid configuration: multiple empty spaces.");
                if(!isTheOnlyTile())
                    throw new InvalidConfigurationException("Invalid configuration: multiple tiles with the " +
                            "same value.");
            }
        }
    }
    //methods used in ensureValidity to see if there is InvalidConfigurationException;
    private boolean isTheOnlyTile(){
        boolean checkerBoolean = false;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                byte checker = getTile(i,j);//00
                for (int k = 0; k < getSize(); k++) {
                    for (int l = 0; l < getSize(); l++) {
                        if(i != k || j != l){
                            if (checker != getTile(k,l))
                                checkerBoolean = true;
                            else
                                return false;
                        }
                    }
                }
            }
        }
        return checkerBoolean;
    }
    private boolean emptyTileChecker(){
        boolean booleanChecker = true;
        for (int i = 0; i < getSize(); i++) {
            for (int j = 0; j < getSize(); j++) {
                byte checker = getTile(i,j);
                for (int k = j +1; k < getSize(); k++) {
                    for (int l = 0; l < getSize(); l++) {
                        if(i != k || j != l)
                            if(getTile(k,l) == checker && checker == EMPTY)
                                booleanChecker = false;
                    }
                }
            }
        }
        return booleanChecker;
    }
    //isSolvable method to see if the initial configuration is a solvable configuration;
    public boolean isSolvable(){
        boolean question = false;
        if(getSize() % 2 == 0){
            for (int i = 0; i < getSize(); i++) {
                for (int j = 0; j < getSize(); j++) {
                    byte check = getTile(i,j);
                    if(check != 0 && check != 1){
                        for (int k = 0; k < getSize(); k++) {
                            for (int l = 0; l < getSize(); l++) {
                                if(i >= k && j < l){
                                    if(check > getTile(k,l) && getTile(k,l) != EMPTY)
                                        this.inversionCount++;
                                }
                            }
                        }
                    }
                }
            }
            question =(getInversionCount() % 2 == 0 && getEmptyRow() % 2 == 1 ||
                    getInversionCount() % 2 == 1 && getEmptyRow() % 2 == 0);
        }
        else if(getSize() % 2 == 1){
            for (int i = 0; i < getSize(); i++) {
                for (int j = 0; j < getSize(); j++) {
                    byte checker = getTile(i,j);
                    if(checker != 0 && checker != 1){
                        for (int k = 0; k < getSize(); k++) {
                            for (int l = 0; l < getSize(); l++) {
                                if(i != k || j != l){
                                    if(checker > getTile(k,l) && getTile(k,l) != EMPTY)
                                        inversionCount++;
                                }
                            }
                        }
                    }
                }
            }
            question = (getInversionCount() % 2 == 0);
        }
        return question;
    }
}
