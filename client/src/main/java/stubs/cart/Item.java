
package stubs.cart;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour item complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="item">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="cookie" type="{http://webservice.tcf.isa.polytech.unice.fr/}cookies" minOccurs="0"/>
 *         &lt;element name="quantity" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "item", propOrder = {
    "cookie",
    "quantity"
})
public class Item {

    @XmlSchemaType(name = "string")
    protected Cookies cookie;
    protected int quantity;

    /**
     * Obtient la valeur de la propri�t� cookie.
     * 
     * @return
     *     possible object is
     *     {@link Cookies }
     *     
     */
    public Cookies getCookie() {
        return cookie;
    }

    /**
     * D�finit la valeur de la propri�t� cookie.
     * 
     * @param value
     *     allowed object is
     *     {@link Cookies }
     *     
     */
    public void setCookie(Cookies value) {
        this.cookie = value;
    }

    /**
     * Obtient la valeur de la propri�t� quantity.
     * 
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * D�finit la valeur de la propri�t� quantity.
     * 
     */
    public void setQuantity(int value) {
        this.quantity = value;
    }

}
