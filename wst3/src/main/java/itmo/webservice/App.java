package itmo.webservice;

import javax.xml.ws.Endpoint;

public class App {
    public static void main(String[] args) {
        System.setProperty("com.sun.xml.ws.fault.SOAPFaultBuilder.disableCaptureStackTrace", "false");
        String url = "http://127.0.0.1:8080/ShipService";
        Endpoint.publish(url, new ShipWebServiceImpl());
    }
}