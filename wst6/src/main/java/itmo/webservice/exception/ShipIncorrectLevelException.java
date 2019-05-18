package itmo.webservice.exception;

public class ShipIncorrectLevelException extends Exception {
  public static ShipIncorrectLevelException DEFAULT_INSTANCE = new ShipIncorrectLevelException(
          "Ship level must be between or equals 1 and 110");

    public ShipIncorrectLevelException(String message) {
        super(message);
    }
}
