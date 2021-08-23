
public class ConfigurationFormatException extends Exception {

    public ConfigurationFormatException(){

        super("ConfigurationFormatException");
    }
    public ConfigurationFormatException(String format){
        super(format);
    }
}
