package itmo.webservice.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "itmo.webservice.exception.ShipServiceFault")
public class ShipIncorrectRarityException extends Exception {
    private final ShipServiceFault fault;

    public ShipIncorrectRarityException(String message, ShipServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ShipServiceFault getFault() {
        return fault;
    }
}
