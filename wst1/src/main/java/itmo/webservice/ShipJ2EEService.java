package itmo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebService(name = "ShipWebService", serviceName = "ShipService")
public class ShipJ2EEService implements ShipWebService {
    @Override
    @WebMethod(operationName = "getShipsByFields")
    public List<Ship> getShipsByFields(@WebParam(name = "fields") List<SqlFieldValue> fields) {
        return new PostgresSQLDAO(getConnection()).getShipsByFields(fields);
    }

    @Override
    @WebMethod(operationName = "getShips")
    public List<Ship> getShips() {
        return new PostgresSQLDAO(getConnection()).getShips();
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("itmo_wst");
            result = dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShipJ2EEService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
