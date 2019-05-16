package itmo.webservice;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresSQLDAO {
    private Connection connection;

    public PostgresSQLDAO() {
        connection = ConnectionUtil.getConnection();
    }

    public PostgresSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Ship> getShips() {
        return getShipsFromStatement("select * from ship");
    }

    public List<Ship> getShipsByFields(List<SqlFieldValue> fields) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from ship");
        if (fields.isEmpty()) {
            return getShipsFromStatement("select * from ship");
        }
        sql.append(" where ");
        int whereCounter = 0;
        for (SqlFieldValue fieldValue : fields) {
            String field = fieldValue.getField();
            String value = fieldValue.getValue();
            if (whereCounter != 0) {
                sql.append(" and ");
            }
            sql.append(String.format("%s='%s'", field, value));
            ++whereCounter;
        }
        return getShipsFromStatement(sql.toString());
    }

    private List<Ship> getShipsFromStatement(String sql) {
        List<Ship> ships = new ArrayList<>();
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String rarity = rs.getString("rarity");
                String nation = rs.getString("nation");
                int level = rs.getInt("level");

                Ship ship = new Ship(id, name, nation, rarity, type, level);
                ships.add(ship);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ships;
    }
}