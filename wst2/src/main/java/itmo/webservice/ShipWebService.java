
package itmo.webservice;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ShipWebService", targetNamespace = "http://webservice.itmo/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ShipWebService {


    /**
     * 
     * @param shipId
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteShip", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.DeleteShip")
    @ResponseWrapper(localName = "deleteShipResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.DeleteShipResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/deleteShipRequest", output = "http://webservice.itmo/ShipWebService/deleteShipResponse")
    public boolean deleteShip(
        @WebParam(name = "shipId", targetNamespace = "")
        int shipId);

    /**
     * 
     * @param shipEdits
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateShip", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.UpdateShip")
    @ResponseWrapper(localName = "updateShipResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.UpdateShipResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/updateShipRequest", output = "http://webservice.itmo/ShipWebService/updateShipResponse")
    public boolean updateShip(
        @WebParam(name = "shipEdits", targetNamespace = "")
        Ship shipEdits);

    /**
     * 
     * @return
     *     returns java.util.List<itmo.webservice.Ship>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShips", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShips")
    @ResponseWrapper(localName = "getShipsResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/getShipsRequest", output = "http://webservice.itmo/ShipWebService/getShipsResponse")
    public List<Ship> getShips();

    /**
     * 
     * @param ship
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createShip", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.CreateShip")
    @ResponseWrapper(localName = "createShipResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.CreateShipResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/createShipRequest", output = "http://webservice.itmo/ShipWebService/createShipResponse")
    public int createShip(
        @WebParam(name = "ship", targetNamespace = "")
        Ship ship);

    /**
     * 
     * @param fields
     * @return
     *     returns java.util.List<itmo.webservice.Ship>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShipsByFields", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsByFields")
    @ResponseWrapper(localName = "getShipsByFieldsResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsByFieldsResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/getShipsByFieldsRequest", output = "http://webservice.itmo/ShipWebService/getShipsByFieldsResponse")
    public List<Ship> getShipsByFields(
        @WebParam(name = "fields", targetNamespace = "")
        List<SqlFieldValue> fields);

}
