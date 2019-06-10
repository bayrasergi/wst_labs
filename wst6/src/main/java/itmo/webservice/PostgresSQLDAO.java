package itmo.webservice;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.Objects.nonNull;

public class PostgresSQLDAO {
    public int createShip(Ship ship) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     String.format("INSERT INTO SHIP(name,nation,rarity,type,level) VALUES('%s','%s','%s','%s',%d)",
                             ship.getName(), ship.getNation(), ship.getRarity(), ship.getType(), ship.getLevel()),
                     new String[]{"id"})) {
            int insertedRows = stmt.executeUpdate();
            if (insertedRows == 0) {
                return -1;
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public boolean deleteShip(int id) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(String.format("DELETE FROM ship WHERE id=%d", id))) {
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean updateShip(Ship ship) {
        StringBuilder sql = new StringBuilder("UPDATE ship SET ");
        int id = ship.getId();
        String name = ship.getName();
        String nation = ship.getNation();
        String rarity = ship.getRarity();
        String type = ship.getType();
        int level = ship.getLevel();
        if (nonNull(name) && !name.isEmpty()) {
            sql.append(String.format("\"name\"='%s', ", name));
        }
        if (nonNull(nation) && !nation.isEmpty()) {
            sql.append(String.format("nation='%s', ", nation));
        }
        if (nonNull(rarity) && !rarity.isEmpty()) {
            sql.append(String.format("rarity='%s', ", rarity));
        }
        if (nonNull(type) && !type.isEmpty()) {
            sql.append(String.format("\"type\"='%s', ", type));
        }
        if (level > 0) {
            sql.append(String.format("\"level\"=%d, ", level));
        }
        sql.delete(sql.length() - 2, sql.length() - 1);
        sql.append(" WHERE id=").append(ship.getId());
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql.toString())) {
            return statement.executeUpdate() > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
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

    public User getUser(String username, String password) {
        User user = null;
        try (Connection connection = ConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM \"users\" WHERE \"username\"='%s' AND \"password\"='%s'", username, password))) {
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return user;
    }

    public int createUser(User user) {
        try (Connection connection = ConnectionUtil.getConnection();
             PreparedStatement stmt = connection.prepareStatement(
                     String.format("INSERT INTO \"users\"(\"username\",\"password\") VALUES('%s','%s')",
                             user.getUsername(), new String(MessageDigest.getInstance("SHA-256").digest(
                                     user.getPassword().getBytes(StandardCharsets.UTF_8)))),
                     new String[]{"id"})) {
            int insertedRows = stmt.executeUpdate();
            if (insertedRows == 0) {
                return -1;
            }
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException | NoSuchAlgorithmException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    private List<Ship> getShipsFromStatement(String sql) {
        List<Ship> ships = new ArrayList<>();
        try (Connection connection = ConnectionUtil.getConnection();
             Statement stmt = connection.createStatement();
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
        }
        return ships;
    }
}