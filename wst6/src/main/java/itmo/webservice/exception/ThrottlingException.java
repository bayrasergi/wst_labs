package itmo.webservice.exception;

public class ThrottlingException extends RuntimeException{

    public static ThrottlingException DEFAULT_INSTANCE = new ThrottlingException(
            "There are too many users trying get access to the server! Please, try later.");

    public ThrottlingException(String message) {
        super(message);
    }
}
