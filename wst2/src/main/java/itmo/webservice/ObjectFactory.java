
package itmo.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the itmo.webservice package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetShipsResponse_QNAME = new QName("http://webservice.itmo/", "getShipsResponse");
    private final static QName _GetShips_QNAME = new QName("http://webservice.itmo/", "getShips");
    private final static QName _GetShipsByFields_QNAME = new QName("http://webservice.itmo/", "getShipsByFields");
    private final static QName _GetShipsByFieldsResponse_QNAME = new QName("http://webservice.itmo/", "getShipsByFieldsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: itmo.webservice
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetShips }
     */
    public GetShips createGetShips() {
        return new GetShips();
    }

    /**
     * Create an instance of {@link GetShipsResponse }
     */
    public GetShipsResponse createGetShipsResponse() {
        return new GetShipsResponse();
    }

    /**
     * Create an instance of {@link GetShipsByFieldsResponse }
     */
    public GetShipsByFieldsResponse createGetShipsByFieldsResponse() {
        return new GetShipsByFieldsResponse();
    }

    /**
     * Create an instance of {@link GetShipsByFields }
     */
    public GetShipsByFields createGetShipsByFields() {
        return new GetShipsByFields();
    }

    /**
     * Create an instance of {@link SqlFieldValue }
     */
    public SqlFieldValue createSqlFieldValue() {
        return new SqlFieldValue();
    }

    /**
     * Create an instance of {@link Ship }
     */
    public Ship createShip() {
        return new Ship();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShipsResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.itmo/", name = "getShipsResponse")
    public JAXBElement<GetShipsResponse> createGetShipsResponse(GetShipsResponse value) {
        return new JAXBElement<GetShipsResponse>(_GetShipsResponse_QNAME, GetShipsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShips }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.itmo/", name = "getShips")
    public JAXBElement<GetShips> createGetShips(GetShips value) {
        return new JAXBElement<GetShips>(_GetShips_QNAME, GetShips.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShipsByFields }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.itmo/", name = "getShipsByFields")
    public JAXBElement<GetShipsByFields> createGetShipsByFields(GetShipsByFields value) {
        return new JAXBElement<GetShipsByFields>(_GetShipsByFields_QNAME, GetShipsByFields.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetShipsByFieldsResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://webservice.itmo/", name = "getShipsByFieldsResponse")
    public JAXBElement<GetShipsByFieldsResponse> createGetShipsByFieldsResponse(GetShipsByFieldsResponse value) {
        return new JAXBElement<GetShipsByFieldsResponse>(_GetShipsByFieldsResponse_QNAME, GetShipsByFieldsResponse.class, null, value);
    }

}
