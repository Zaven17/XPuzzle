
public class InvalidConfigurationException extends Exception{

    public InvalidConfigurationException(){

        super("InvalidConfigurationException");
    }
    public InvalidConfigurationException(String format){

        super(format);
    }
}
