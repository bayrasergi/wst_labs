package itmo.webservice.exception;

import java.util.Arrays;

public class ShipIncorrectRarityException extends Exception {
    public static ShipIncorrectRarityException DEFAULT_INSTANCE = new ShipIncorrectRarityException(
            "Ship rarity should be one of this: "
                    + String.join(",", Arrays.asList(
                    "Common", "Rare", "Elite", "Super Rare", "Priority", "Ultra Rare")));

    public ShipIncorrectRarityException(String message) {
        super(message);
    }
}
