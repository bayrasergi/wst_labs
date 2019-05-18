package itmo.webservice.exception;

public class ShipIdNotFoundException extends Exception {
    public static ShipIdNotFoundException DEFAULT_INSTANCE = new ShipIdNotFoundException(
            "Ship with this id not found!");

    public ShipIdNotFoundException(String message) {
        super(message);
    }
}
