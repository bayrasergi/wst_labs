package itmo.webservice.j2ee;

import itmo.webservice.PostgresSQLDAO;
import itmo.webservice.Ship;
import itmo.webservice.SqlFieldValue;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

@Path("/ships")
@Produces({MediaType.APPLICATION_JSON})
public class ShipJ2EEResource {
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
                new PostgresSQLDAO(getConnection()).getShips()
                : new PostgresSQLDAO(getConnection()).getShipsByFields(fields);
    }

    private Connection getConnection() {
        Connection result = null;
        try {
            InitialContext ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("ifmo-oracle");
            result = dataSource.getConnection();
        } catch (SQLException | NamingException ex) {
            Logger.getLogger(ShipJ2EEResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
