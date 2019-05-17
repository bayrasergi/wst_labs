
package itmo.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ship complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ship">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="level" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rarity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ship", propOrder = {
        "id",
        "name",
        "nation",
        "rarity",
        "type",
        "level"
})
public class Ship {
    protected int id;
    protected String name;
    protected String nation;
    protected String rarity;
    protected String type;
    protected int level;

    /**
     * Gets the value of the id property.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the level property.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the value of the level property.
     */
    public void setLevel(int value) {
        this.level = value;
    }

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the nation property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getNation() {
        return nation;
    }

    /**
     * Sets the value of the nation property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setNation(String value) {
        this.nation = value;
    }

    /**
     * Gets the value of the rarity property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRarity() {
        return rarity;
    }

    /**
     * Sets the value of the rarity property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRarity(String value) {
        this.rarity = value;
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setType(String value) {
        this.type = value;
    }


    public Ship(int id, String name, String nation, String rarity, String type, int level) {
        this.id = id;
        this.name = name;
        this.nation = nation;
        this.rarity = rarity;
        this.type = type;
        this.level = level;
    }

    public Ship() {
    }

    @Override
    public String toString() {
        return String.format("id: %d, name: '%s', nation: '%s', rarity: '%s', type: '%s', level: %d",
                id, name, nation, rarity, type, level);
    }
}



