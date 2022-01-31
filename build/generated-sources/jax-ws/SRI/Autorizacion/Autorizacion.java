
package SRI.Autorizacion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * &lt;p&gt;Clase Java para autorizacion complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="autorizacion"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroAutorizacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="fechaAutorizacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="ambiente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="comprobante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="mensajes" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element ref="{http://ec.gob.sri.ws.autorizacion}mensaje" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
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
@XmlType(name = "autorizacion", propOrder = {
    "estado",
    "numeroAutorizacion",
    "fechaAutorizacion",
    "ambiente",
    "comprobante",
    "mensajes"
})
public class Autorizacion {

    protected String estado;
    protected String numeroAutorizacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAutorizacion;
    protected String ambiente;
    protected String comprobante;
    protected Autorizacion.Mensajes mensajes;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroAutorizacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    /**
     * Define el valor de la propiedad numeroAutorizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroAutorizacion(String value) {
        this.numeroAutorizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAutorizacion.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    /**
     * Define el valor de la propiedad fechaAutorizacion.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAutorizacion(XMLGregorianCalendar value) {
        this.fechaAutorizacion = value;
    }

    /**
     * Obtiene el valor de la propiedad ambiente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * Define el valor de la propiedad ambiente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAmbiente(String value) {
        this.ambiente = value;
    }

    /**
     * Obtiene el valor de la propiedad comprobante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * Define el valor de la propiedad comprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComprobante(String value) {
        this.comprobante = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajes.
     * 
     * @return
     *     possible object is
     *     {@link Autorizacion.Mensajes }
     *     
     */
    public Autorizacion.Mensajes getMensajes() {
        return mensajes;
    }

    /**
     * Define el valor de la propiedad mensajes.
     * 
     * @param value
     *     allowed object is
     *     {@link Autorizacion.Mensajes }
     *     
     */
    public void setMensajes(Autorizacion.Mensajes value) {
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
     *         &amp;lt;element ref="{http://ec.gob.sri.ws.autorizacion}mensaje" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
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

        //@XmlElement(namespace = "http://ec.gob.sri.ws.autorizacion")
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
