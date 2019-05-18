package itmo.webservice.exception;

public class ShipNullFieldException extends Exception {
    public static ShipNullFieldException DEFAULT_INSTANCE = new ShipNullFieldException(
            "Ship field should not be null");

    public ShipNullFieldException(String message) {
        super(message);
    }
}
