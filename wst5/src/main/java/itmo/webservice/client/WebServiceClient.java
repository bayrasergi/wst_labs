package itmo.webservice.client;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import itmo.webservice.Ship;
import itmo.webservice.SqlFieldValue;

import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

enum MenuOption {ADD_FIELD, PRINT, CLEAR, FIND, CREATE, DELETE, UPDATE, EXIT}

public class WebServiceClient {
    private boolean exit = false;

    private static final String standaloneUrl = "http://localhost:8080/rest/ships";

    private String url;
    private Client client;

    private List<SqlFieldValue> fieldsValues = new ArrayList<>();

    public WebServiceClient(Client client, String serviceUrl) {
        this.client = client;
        this.url = serviceUrl;
    }

    public static void main(String[] args) throws MalformedURLException {
        try {
            WebServiceClient client = new WebServiceClient(Client.create(), standaloneUrl);
            client.startListening();

        } catch (WebServiceException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void startListening() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                try {
                    printMenu();
                    processOption(in);
                    if (exit) {
                        break;
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage());
                }
            }

        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("------------Menu------------");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.println(String.format("%2d. %s", i + 1, getOptionText(options[i])));
        }
    }

    private int readOption(BufferedReader in) throws IOException {
        int option = -1;

        String input = in.readLine();
        try {
            option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.err.println("Wrong option");
        }

        return option;
    }

    private void processOption(BufferedReader in) throws IOException {
        int option = readOption(in);

        if (option < 1 || option > MenuOption.values().length) {
            System.err.println("Wrong option");
            return;
        }

        MenuOption menuOption = MenuOption.values()[option - 1];

        switch (menuOption) {
            case ADD_FIELD:
                addCondition(in);
                break;
            case FIND:
                findResults();
                break;
            case PRINT:
                printConditions();
                break;
            case CLEAR:
                clearConditions();
                break;
            case CREATE:
                createShip(in);
                break;
            case DELETE:
                deleteShip(in);
                break;
            case UPDATE:
                updateShip(in);
                break;
            case EXIT:
                exit();
                break;
        }
    }

    private String getOptionText(MenuOption menuOption) {
        switch (menuOption) {
            case ADD_FIELD:
                return "Add field";
            case FIND:
                return "Find results";
            case PRINT:
                return "Show saved fields";
            case CLEAR:
                return "Clear saved fields";
            case UPDATE:
                return "Update ship";
            case CREATE:
                return "Create ship";
            case DELETE:
                return "Delete ship by id";
            case EXIT:
                return "Exit";
            default:
                return "Option not supported";
        }
    }

    private void addCondition(BufferedReader in) throws IOException {
        System.out.println("Choose field:");

        Field[] fields = Ship.class.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, fields[i].getName());
        }

        int fieldNumber = readOption(in);

        if (fieldNumber < 1 || fieldNumber > fields.length) {
            System.err.println("Wrong option");
        }

        System.out.println("PRINT expected field value:");
        String value = in.readLine();

        SqlFieldValue field = new SqlFieldValue(fields[fieldNumber - 1].getName(), value);
        this.fieldsValues.add(field);

        System.out.println("Field's value saved: " + field);
    }

    private void findResults() {
        WebResource webResource = client.resource(this.url);
        for (SqlFieldValue fieldValue : fieldsValues) {
            webResource = webResource.queryParam(
                    fieldValue.getField(),
                    fieldValue.getValue());
        }

        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }

        GenericType<List<Ship>> type = new GenericType<List<Ship>>() {
        };
        printShips(response.getEntity(type));
    }

    private void printConditions() {
        if (this.fieldsValues.size() == 0) {
            System.out.println("No fieldsValues saved");
            return;
        }

        System.out.println("Saved fieldsValues:");

        for (SqlFieldValue fieldValue : this.fieldsValues) {
            System.out.println(fieldValue);
        }
    }

    private void clearConditions() {
        this.fieldsValues.clear();
        System.out.println("Saved fieldsValues cleared");
    }

    private void exit() {
        exit = true;
    }

    private void printShips(List<Ship> ships) {
        for (Ship ship : ships) {
            System.out.println(ship);
        }
        System.out.println("Total ships: " + ships.size());
    }

    private void createShip(BufferedReader in) throws IOException {
        Ship ship = new Ship();
        System.out.println("Input name:");
        ship.setName(in.readLine());
        System.out.println("Input nation:");
        ship.setNation(in.readLine());
        System.out.println("Input rarity");
        ship.setRarity(in.readLine());
        System.out.println("Input type:");
        ship.setType(in.readLine());
        System.out.println("Input level:");
        ship.setLevel(Integer.parseInt(in.readLine()));

        WebResource webResource = client.resource(this.url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, ship);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        int shipId = response.getEntity(Integer.class);
        if (shipId < 0) {
            System.out.println("Error then creating ship!");
            return;
        }
        System.out.println("Ship created! Id=" + shipId);
    }

    private void updateShip(BufferedReader in) throws IOException {
        Ship ship = new Ship();
        System.out.println("Input id:");
        String input = in.readLine();
        ship.setId(Integer.parseInt(input));
        System.out.println("Input name(enter to skip):");
        input = in.readLine();
        if (!input.isEmpty()) {
            ship.setName(input);
        }
        System.out.println("Input nation(enter to skip):");
        input = in.readLine();
        if (!input.isEmpty()) {
            ship.setNation(input);
        }
        System.out.println("Input rarity(enter to skip):");
        input = in.readLine();
        if (!input.isEmpty()) {
            ship.setRarity(input);
        }
        System.out.println("Input type(enter to skip):");
        input = in.readLine();
        if (!input.isEmpty()) {
            ship.setType(input);
        }
        System.out.println("Input level(enter to skip):");
        input = in.readLine();
        if (!input.isEmpty()) {
            ship.setLevel(Integer.parseInt(input));
        } else {
            ship.setLevel(0);
        }
        WebResource webResource = client.resource(this.url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).put(ClientResponse.class, ship);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        boolean updateResult = response.getEntity(Boolean.class);
        if (!updateResult) {
            System.out.println("Error then updating ship!");
            return;
        }
        System.out.println("Ship updated!");
    }

    private void deleteShip(BufferedReader in) throws IOException {
        System.out.println("Input ship's id:");
        int id = Integer.parseInt(in.readLine());
        WebResource webResource = client.resource(this.url);
        ClientResponse response = webResource.accept(MediaType.APPLICATION_JSON).delete(ClientResponse.class, id);
        if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
            throw new IllegalStateException("Request failed");
        }
        boolean deleteResult = response.getEntity(Boolean.class);
        if (!deleteResult) {
            System.out.println("Error then deleting ship!");
            return;
        }
        System.out.println("Ship deleted!");
    }
}
