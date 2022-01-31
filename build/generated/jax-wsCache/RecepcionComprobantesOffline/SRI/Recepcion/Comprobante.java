
package SRI.Recepcion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para comprobante complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="comprobante"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="claveAcceso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="mensajes" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element ref="{http://ec.gob.sri.ws.recepcion}mensaje" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
 *                 &amp;lt;/sequence&amp;gt;
 *               &amp;lt;/restriction&amp;gt;
 *             &amp;lt;/complexContent&amp;gt;
 *           &amp;lt;/complexType&amp;gt;
 *         &amp;lt;/element&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "comprobante", propOrder = {
    "claveAcceso",
    "mensajes"
})
public class Comprobante {

    protected String claveAcceso;
    protected Comprobante.Mensajes mensajes;

    /**
     * Obtiene el valor de la propiedad claveAcceso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveAcceso() {
        return claveAcceso;
    }

    /**
     * Define el valor de la propiedad claveAcceso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveAcceso(String value) {
        this.claveAcceso = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link Comprobante.Mensajes }
     *     
     */
    public Comprobante.Mensajes getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link Comprobante.Mensajes }
     *     
     */
    public void setMensajes(Comprobante.Mensajes value) {
        this.mensajes = value;
    }


    /**
     * &lt;p&gt;Clase Java para anonymous complex type.
     * 
     * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * &lt;pre&gt;
     * &amp;lt;complexType&amp;gt;
     *   &amp;lt;complexContent&amp;gt;
     *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
     *       &amp;lt;sequence&amp;gt;
     *         &amp;lt;element ref="{http://ec.gob.sri.ws.recepcion}mensaje" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
     *       &amp;lt;/sequence&amp;gt;
     *     &amp;lt;/restriction&amp;gt;
     *   &amp;lt;/complexContent&amp;gt;
     * &amp;lt;/complexType&amp;gt;
     * &lt;/pre&gt;
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "mensaje"
    })
    public static class Mensajes {

        @XmlElement(namespace = "http://ec.gob.sri.ws.recepcion")
        protected List<Mensaje> mensaje;

        /**
         * Gets the value of the mensaje property.
         * 
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the mensaje property.
         * 
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         *    getMensaje().add(newItem);
         * &lt;/pre&gt;
         * 
         * 
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link Mensaje }
         * 
         * 
         */
        public List<Mensaje> getMensaje() {
            if (mensaje == null) {
                mensaje = new ArrayList<Mensaje>();
            }
            return this.mensaje;
        }

    }

}
