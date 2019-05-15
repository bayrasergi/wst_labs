
package itmo.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateShip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateShip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="shipEdits" type="{http://webservice.itmo/}ship" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateShip", propOrder = {
    "shipEdits"
})
public class UpdateShip {

    protected Ship shipEdits;

    /**
     * Gets the value of the shipEdits property.
     * 
     * @return
     *     possible object is
     *     {@link Ship }
     *     
     */
    public Ship getShipEdits() {
        return shipEdits;
    }

    /**
     * Sets the value of the shipEdits property.
     * 
     * @param value
     *     allowed object is
     *     {@link Ship }
     *     
     */
    public void setShipEdits(Ship value) {
        this.shipEdits = value;
    }

}
