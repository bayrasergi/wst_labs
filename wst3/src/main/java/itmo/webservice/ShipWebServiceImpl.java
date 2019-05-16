package itmo.webservice;

import itmo.webservice.exception.*;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@WebService(name = "ShipWebService", serviceName = "ShipService")
public class ShipWebServiceImpl implements ShipWebService {
    @Override
    @WebMethod(operationName = "getShipsByFields")
    public List<Ship> getShipsByFields(@WebParam(name = "fields") List<SqlFieldValue> fields) {
        return new PostgresSQLDAO().getShipsByFields(fields);
    }

    @Override
    @WebMethod(operationName = "getShips")
    public List<Ship> getShips() {
        return new PostgresSQLDAO().getShips();
    }

    @Override
    @WebMethod(operationName = "createShip")
    public int createShip(@WebParam(name = "ship") Ship ship) throws ShipNullFieldException, ShipTypeNotFoundException,
            ShipIncorrectLevelException, ShipIncorrectRarityException, ShipIncorrectNationException {
        checkShipFieldsNotNull(ship);
        checkShipType(ship.getType());
        checkShipLevel(ship.getLevel());
        checkShipRarity(ship.getRarity());
        checkShipNation(ship.getNation());
        return new PostgresSQLDAO().createShip(ship);
    }

    @Override
    @WebMethod(operationName = "updateShip")
    public boolean updateShip(@WebParam(name = "shipEdits") Ship edits) throws ShipTypeNotFoundException,
            ShipIncorrectLevelException, ShipIncorrectRarityException, ShipIncorrectNationException {
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

    @Override
    @WebMethod(operationName = "deleteShip")
    public boolean deleteShip(@WebParam(name = "shipId") int id) throws ShipIdNotFoundException {
        checkShipIdExist(id);
        return new PostgresSQLDAO().deleteShip(id);
    }

    private void checkShipIdExist(int id) throws ShipIdNotFoundException {
        if (new PostgresSQLDAO().getShipsByFields(
                Collections.singletonList(new SqlFieldValue("id", Integer.toString(id))))
                .isEmpty()) {
            throw new ShipIdNotFoundException("Ship's id not found!", ShipServiceFault.defaultInstance());
        }
    }

    private void checkShipType(String shipType) throws ShipTypeNotFoundException {
        List<String> types = Arrays.asList("Destroyer", "Carrier", "Battleship", "Cruiser", "Repair Ship", "Submarine");
        if (!types.contains(shipType)) {
            throw new ShipTypeNotFoundException("Ship's type not found, should be one of: "
                    + String.join(",", types),
                    ShipServiceFault.defaultInstance());
        }
    }

    private void checkShipLevel(int shipLevel) throws ShipIncorrectLevelException {
        if (shipLevel < 0 || shipLevel > 110) {
            throw new ShipIncorrectLevelException("Ship's level should be between or equal 1 and 110",
                    ShipServiceFault.defaultInstance());
        }
    }

    private void checkShipRarity(String shipRarity) throws ShipIncorrectRarityException {
        List<String> rarities = Arrays.asList("Common", "Rare", "Elite", "Super Rare", "Priority", "Ultra Rare");
        if (!rarities.contains(shipRarity)) {
            throw new ShipIncorrectRarityException("Ship's rarity should be one of: "
                    + String.join(",", rarities), ShipServiceFault.defaultInstance());
        }
    }

    private void checkShipNation(String shipNation) throws ShipIncorrectNationException {
        List<String> nations = Arrays.asList("Sakura Empire", "Eagle Union", "Royal Navy", "Ironblood",
                "Eastern Radiance", "North Union", "Iris Libre", "Vichya  Dominion");
        if (!nations.contains(shipNation)) {
            throw new ShipIncorrectNationException("Ship's nation should be one of: "
                    + String.join(",", nations), ShipServiceFault.defaultInstance());
        }
    }

    private void checkShipFieldsNotNull(Ship ship) throws ShipNullFieldException {
        if (Objects.isNull(ship.getName()) && !ship.getName().isEmpty()) {
            throw new ShipNullFieldException("Ship's name shouldn't be null!", ShipServiceFault.defaultInstance());
        }
        if (Objects.isNull(ship.getNation()) && !ship.getNation().isEmpty()) {
            throw new ShipNullFieldException("Ship's nation shouldn't be null!", ShipServiceFault.defaultInstance());
        }
        if (Objects.isNull(ship.getRarity()) && !ship.getRarity().isEmpty()) {
            throw new ShipNullFieldException("Ship's rarity shouldn't be null!", ShipServiceFault.defaultInstance());
        }
        if (Objects.isNull(ship.getType()) && !ship.getType().isEmpty()) {
            throw new ShipNullFieldException("Ship's type shouldn't be null!", ShipServiceFault.defaultInstance());
        }
    }
}
