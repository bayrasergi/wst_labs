package itmo.webservice.exception;

import javax.xml.ws.WebFault;

@WebFault(faultBean = "itmo.webservice.exception.ShipServiceFault")
public class ShipNullFieldException extends Exception {
    private final ShipServiceFault fault;

    public ShipNullFieldException(String message, ShipServiceFault fault) {
        super(message);
        this.fault = fault;
    }

    public ShipServiceFault getFault() {
        return fault;
    }
}
