
public class Configuration {
    private int size;
    private String data;

    public Configuration(String format) throws ConfigurationFormatException{

        if(format.isEmpty())
            throw new ConfigurationFormatException("Please Specify Configuration");
        if(!spaceChecker(format))
            throw new ConfigurationFormatException("Invalid configuration format: Incorrect number of " +
                    "fields in configuration (found 1).");
        this.data = format;
        String[] sizeTaker = data.split(": ", 2);
        if(!isANumber(sizeTaker[0]))
            throw new ConfigurationFormatException("Invalid configuration format: Could not interpret the " +
                            "size field as a number (’"+ sizeTaker[0] +"’ given).");
        this.size = Integer.parseInt(sizeTaker[0]);
        if(size < 3 || size > 5)
            throw new ConfigurationFormatException("Invalid Configuration format: Could not " +
                    " build a " + size + "Puzzle.");
        this.data = sizeTaker[1];
    }
    public String getData(){

        return data;
    }
    public int getSize(){

        return size;
    }
    public void setSize(int s){
        this.size = s;
    }
    public  void initialize(Tiles tiles) throws ConfigurationFormatException, InvalidConfigurationException{
        String[] row = getData().split(" : ");
        if(row.length < getSize())
            throw new ConfigurationFormatException("Invalid configuration format: Incorrect number of " +
                    "rows in configuration (found "+ row.length +").");
        for (int i = 0; i < getSize(); i++) {
            String[] rowEntries = row[i].split(" ");
            if(size != rowEntries.length)
                throw new ConfigurationFormatException("Invalid configuration format: Incorrect number of" +
                        " columns in configuration (found" + rowEntries.length +"). ");
            for (int j = 0; j < getSize() ; j++) {
                //if ()
                if(!isANumber(rowEntries[j]))
                    throw new ConfigurationFormatException("Invalid configuration format: Malformed " +
                            "configuration ’1 2 3 4 : 5 6 7 8 : 9 " + rowEntries[j] +
                            " 11 12 : 0 13 14 15’.");
                byte entry = Byte.parseByte(rowEntries[j]);
                tiles.setTile(i, j, entry);
            }
        }
        tiles.ensureValidity();
    }
    private boolean isANumber(String userInput){
        boolean question = false;
        for (int i = 0; i < userInput.length() ; i++) {
            if(userInput.charAt(i) == '0' || userInput.charAt(i) == '1' || userInput.charAt(i) == '2' ||
                    userInput.charAt(i) == '3' || userInput.charAt(i) == '4' || userInput.charAt(i) == '5' ||
                    userInput.charAt(i) == '6' || userInput.charAt(i) == '7' || userInput.charAt(i) == '8' ||
                    userInput.charAt(i) == '9')
                question = true;
            else
                return false;
        }
        return question;
    }
    private boolean spaceChecker(String userInput){

        return userInput.contains(" ");
    }
}
