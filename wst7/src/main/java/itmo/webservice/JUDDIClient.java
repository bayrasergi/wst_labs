package itmo.webservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


enum MenuOption {REGISTER, FIND, EXIT}


public class JUDDIClient {
    private final String username = "uddiadmin";
    private final String password = "uddiadmin";

    private ServiceBrowse serviceBrowser;
    private ServiceRegister servicePublish;

    private boolean exit = false;

    public static void main(String[] args) {
        JUDDIClient client = new JUDDIClient();
        client.startListening();
    }

    private void startListening() {
        this.serviceBrowser = new ServiceBrowse(username, password);
        this.servicePublish = new ServiceRegister(username, password);

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        try {
            while (!exit) {
                printMenu();
                processOption(in);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("------------Menu------------");

        MenuOption[] options = MenuOption.values();
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%2d. %s\n", i + 1, getOptionText(options[i]));
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

    private void processOption(BufferedReader in) throws Exception {
        int option = readOption(in);
        if (option < 1 || option > MenuOption.values().length) {
            System.err.println("Wrong option");
            return;
        }

        MenuOption menuOption = MenuOption.values()[option - 1];
        switch (menuOption) {
            case REGISTER:
                registerService(in);
                break;
            case FIND:
                findService(in);
                break;
            case EXIT:
                exit();
                break;
        }
    }

    private String getOptionText(MenuOption menuOption) {
        switch (menuOption) {
            case REGISTER:
                return "Register service";
            case FIND:
                return "Find service";
            case EXIT:
                return "Exit";
            default:
                return "Option not supported";
        }
    }

    private void registerService(BufferedReader in) throws IOException {
        System.out.println("Print business name:");
        String business = in.readLine();

        System.out.println("Print service name:");
        String service = in.readLine();

        System.out.println("Print service endPoint:");
        String endPoint = in.readLine();

        this.servicePublish.publish(business, service, endPoint);
    }

    private void findService(BufferedReader in) throws Exception {
        System.out.println("Print service name:");
        String name = in.readLine();
        serviceBrowser.printFoundServices(name);
    }

    private void exit() {
        exit = true;
    }
}
