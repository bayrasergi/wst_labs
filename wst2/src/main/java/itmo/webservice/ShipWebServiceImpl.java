package itmo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

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
    public int createShip(@WebParam(name = "ship") Ship ship) {
        return new PostgresSQLDAO().createShip(ship);
    }

    @Override
    @WebMethod(operationName = "updateShip")
    public boolean updateShip(@WebParam(name = "shipEdits") Ship edits) {
        return new PostgresSQLDAO().updateShip(edits);
    }

    @Override
    @WebMethod(operationName = "deleteShip")
    public boolean deleteShip(@WebParam(name = "shipId") int id) {
        return new PostgresSQLDAO().deleteShip(id);
    }
}
