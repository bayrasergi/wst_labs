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

enum MenuOption {ADD, PRINT, CLEAR, FIND, EXIT}

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
            case ADD:
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
            case EXIT:
                exit();
                break;
        }
    }

    private String getOptionText(MenuOption menuOption) {
        switch (menuOption) {
            case ADD:
                return "ADD field";
            case FIND:
                return "FIND results";
            case PRINT:
                return "PRINT saved fieldsValues";
            case CLEAR:
                return "CLEAR saved fieldsValues";
            case EXIT:
                return "EXIT";
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

        SqlFieldValue condition = new SqlFieldValue(fields[fieldNumber - 1].getName(), value);
        this.fieldsValues.add(condition);

        System.out.println("Field's value saved: " + condition);
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
}
