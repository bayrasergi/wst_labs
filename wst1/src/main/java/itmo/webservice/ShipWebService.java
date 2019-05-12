
package itmo.webservice;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 */
@WebService(name = "ShipWebService", targetNamespace = "http://webservice.itmo/")
@XmlSeeAlso({
        ObjectFactory.class
})
public interface ShipWebService {


    /**
     * @param fields
     * @return returns java.util.List<itmo.webservice.Ship>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShipsByFields", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsByFields")
    @ResponseWrapper(localName = "getShipsByFieldsResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsByFieldsResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/getShipsByFieldsRequest", output = "http://webservice.itmo/ShipWebService/getShipsByFieldsResponse")
    public List<Ship> getShipsByFields(
            @WebParam(name = "fields", targetNamespace = "")
                    List<SqlFieldValue> fields);

    /**
     * @return returns java.util.List<itmo.webservice.Ship>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getShips", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShips")
    @ResponseWrapper(localName = "getShipsResponse", targetNamespace = "http://webservice.itmo/", className = "itmo.webservice.GetShipsResponse")
    @Action(input = "http://webservice.itmo/ShipWebService/getShipsRequest", output = "http://webservice.itmo/ShipWebService/getShipsResponse")
    public List<Ship> getShips();

}
