package itmo.webservice.exception;

public class ShipServiceFault {
    protected String message;

    public static ShipServiceFault defaultInstance() {
        return new ShipServiceFault();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
