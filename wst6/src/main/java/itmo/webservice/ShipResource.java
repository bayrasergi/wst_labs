package itmo.webservice;

import itmo.webservice.exception.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

import static java.util.Objects.nonNull;

@Path("/ships")
@Produces({MediaType.APPLICATION_JSON})
public class ShipResource {
    @GET
    public List<Ship> getShips(
            @QueryParam("id") int id,
            @QueryParam("name") String name,
            @QueryParam("nation") String nation,
            @QueryParam("rarity") String rarity,
            @QueryParam("type") String type,
            @QueryParam("level") int level) {
        List<SqlFieldValue> fields = new ArrayList<>();
        if (id > 0) {
            fields.add(new SqlFieldValue("id", Integer.toString(id)));
        }
        if (nonNull(name)) {
            fields.add(new SqlFieldValue("name", name));
        }
        if (nonNull(nation)) {
            fields.add(new SqlFieldValue("nation", nation));
        }
        if (nonNull(rarity)) {
            fields.add(new SqlFieldValue("rarity", rarity));
        }
        if (nonNull(type)) {
            fields.add(new SqlFieldValue("type", type));
        }
        if (nonNull(name)) {
            fields.add(new SqlFieldValue("name", name));
        }
        return fields.isEmpty() ?
                new PostgresSQLDAO().getShips()
                : new PostgresSQLDAO().getShipsByFields(fields);
    }

    @POST
    public int createShip(Ship ship) throws ShipIncorrectNationException, ShipIncorrectRarityException,
            ShipIncorrectLevelException, ShipTypeNotFoundException, ShipNullFieldException {
        checkShipFieldsNotNull(ship);
        checkShipType(ship.getType());
        checkShipLevel(ship.getLevel());
        checkShipRarity(ship.getRarity());
        checkShipNation(ship.getNation());
        return new PostgresSQLDAO().createShip(ship);
    }

    @DELETE
    public boolean deleteShip(int id) throws ShipIdNotFoundException {
        checkShipIdExist(id);
        return new PostgresSQLDAO().deleteShip(id);
    }

    @PUT
    public boolean updateShip(Ship edits) throws ShipIncorrectNationException, ShipIncorrectRarityException,
            ShipIncorrectLevelException, ShipTypeNotFoundException {
        if (Objects.nonNull(edits.getType())) {
            checkShipType(edits.getType());
        }
        if (edits.getLevel() != 0) {
            checkShipLevel(edits.getLevel());
        }
        if (Objects.nonNull(edits.getRarity())) {
            checkShipRarity(edits.getRarity());
        }
        if (Objects.nonNull(edits.getNation())) {
            checkShipNation(edits.getNation());
        }
        return new PostgresSQLDAO().updateShip(edits);
    }

    private void checkShipIdExist(int id) throws ShipIdNotFoundException {
        if (new PostgresSQLDAO().getShipsByFields(
                Collections.singletonList(new SqlFieldValue("id", Integer.toString(id))))
                .isEmpty()) {
            throw new ShipIdNotFoundException("Ship's id not found!");
        }
    }

    private void checkShipType(String shipType) throws ShipTypeNotFoundException {
        List<String> types = Arrays.asList("Destroyer", "Carrier", "Battleship", "Cruiser", "Repair Ship", "Submarine");
        if (!types.contains(shipType)) {
            throw new ShipTypeNotFoundException("Ship's type not found, should be one of: "
                    + String.join(",", types));
        }
    }

    private void checkShipLevel(int shipLevel) throws ShipIncorrectLevelException {
        if (shipLevel < 0 || shipLevel > 110) {
            throw new ShipIncorrectLevelException("Ship's level should be between or equal 1 and 110");
        }
    }

    private void checkShipRarity(String shipRarity) throws ShipIncorrectRarityException {
        List<String> rarities = Arrays.asList("Common", "Rare", "Elite", "Super Rare", "Priority", "Ultra Rare");
        if (!rarities.contains(shipRarity)) {
            throw new ShipIncorrectRarityException("Ship's rarity should be one of: "
                    + String.join(",", rarities));
        }
    }

    private void checkShipNation(String shipNation) throws ShipIncorrectNationException {
        List<String> nations = Arrays.asList("Sakura Empire", "Eagle Union", "Royal Navy", "Ironblood",
                "Eastern Radiance", "North Union", "Iris Libre", "Vichya  Dominion");
        if (!nations.contains(shipNation)) {
            throw new ShipIncorrectNationException("Ship's nation should be one of: "
                    + String.join(",", nations));
        }
    }

    private void checkShipFieldsNotNull(Ship ship) throws ShipNullFieldException {
        if (Objects.isNull(ship.getName()) || ship.getName().isEmpty()) {
            throw new ShipNullFieldException("Ship's name shouldn't be null or empty!");
        }
        if (Objects.isNull(ship.getNation()) || ship.getNation().isEmpty()) {
            throw new ShipNullFieldException("Ship's nation shouldn't be null or empty!");
        }
        if (Objects.isNull(ship.getRarity()) || ship.getRarity().isEmpty()) {
            throw new ShipNullFieldException("Ship's rarity shouldn't be null or empty!");
        }
        if (Objects.isNull(ship.getType()) || ship.getType().isEmpty()) {
            throw new ShipNullFieldException("Ship's type shouldn't be null or empty!");
        }
    }
}
