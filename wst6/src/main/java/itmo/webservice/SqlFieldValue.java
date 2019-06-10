
package itmo.webservice;

public class SqlFieldValue {

    protected String field;
    protected String value;

    /**
     * Gets the value of the field property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the value of the field property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setField(String value) {
        this.field = value;
    }

    /**
     * Gets the value of the value property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValue(String value) {
        this.value = value;
    }

    public SqlFieldValue(String field, String value) {
        this.field = field;
        this.value = value;
    }

    public SqlFieldValue() {
    }

    @Override
    public String toString() {
        return String.format("field '%s': '%s'", field, value);
    }
}
