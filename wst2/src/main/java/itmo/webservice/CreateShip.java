
package itmo.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for createShip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="createShip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ship" type="{http://webservice.itmo/}ship" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "createShip", propOrder = {
    "ship"
})
public class CreateShip {

    protected Ship ship;

    /**
     * Gets the value of the ship property.
     * 
     * @return
     *     possible object is
     *     {@link Ship }
     *     
     */
    public Ship getShip() {
        return ship;
    }

    /**
     * Sets the value of the ship property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ship }
     *     
     */
    public void setShip(Ship value) {
        this.ship = value;
    }

}
