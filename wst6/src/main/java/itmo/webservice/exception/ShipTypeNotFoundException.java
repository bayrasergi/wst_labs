package itmo.webservice.exception;

import java.util.Arrays;

public class ShipTypeNotFoundException extends Exception {
    public static ShipTypeNotFoundException DEFAULT_INSTANCE = new ShipTypeNotFoundException(
            "Ship type should be one of this: "
                    + String.join(",", Arrays.asList(
                    "Destroyer", "Carrier", "Battleship", "Cruiser", "Repair Ship", "Submarine")));

    public ShipTypeNotFoundException(String message) {
        super(message);
    }
}
