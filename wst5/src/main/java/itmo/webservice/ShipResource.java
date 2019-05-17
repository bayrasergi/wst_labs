package itmo.webservice;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

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
    public int createShip(Ship ship) {
        return new PostgresSQLDAO().createShip(ship);
    }

    @DELETE
    public boolean deleteShip(int id) {
        return new PostgresSQLDAO().deleteShip(id);
    }

    @PUT
    public boolean updateShip(Ship ship) {
        return new PostgresSQLDAO().updateShip(ship);
    }
}
