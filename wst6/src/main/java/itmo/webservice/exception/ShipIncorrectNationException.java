package itmo.webservice.exception;

import javax.xml.ws.WebFault;
import java.util.Arrays;

@WebFault(faultBean = "itmo.webservice.exception.ShipServiceFault")
public class ShipIncorrectNationException extends Exception {
    public static ShipIncorrectNationException DEFAULT_INSTANCE = new ShipIncorrectNationException(
            "Ship nation should be one of this: "
                    + String.join(",", Arrays.asList("Sakura Empire", "Eagle Union", "Royal Navy", "Ironblood",
                    "Eastern Radiance", "North Union", "Iris Libre", "Vichya  Dominion")));

    public ShipIncorrectNationException(String message) {
        super(message);
    }
}
