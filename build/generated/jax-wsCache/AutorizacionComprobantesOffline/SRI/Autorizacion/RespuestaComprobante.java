
package SRI.Autorizacion;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para respuestaComprobante complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="respuestaComprobante"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="claveAccesoConsultada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="numeroComprobantes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *         &amp;lt;element name="autorizaciones" minOccurs="0"&amp;gt;
 *           &amp;lt;complexType&amp;gt;
 *             &amp;lt;complexContent&amp;gt;
 *               &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *                 &amp;lt;sequence&amp;gt;
 *                   &amp;lt;element ref="{http://ec.gob.sri.ws.autorizacion}autorizacion" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
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
@XmlType(name = "respuestaComprobante", propOrder = {
    "claveAccesoConsultada",
    "numeroComprobantes",
    "autorizaciones"
})
public class RespuestaComprobante {

    protected String claveAccesoConsultada;
    protected String numeroComprobantes;
    protected RespuestaComprobante.Autorizaciones autorizaciones;

    /**
     * Obtiene el valor de la propiedad claveAccesoConsultada.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveAccesoConsultada() {
        return claveAccesoConsultada;
    }

    /**
     * Define el valor de la propiedad claveAccesoConsultada.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveAccesoConsultada(String value) {
        this.claveAccesoConsultada = value;
    }

    /**
     * Obtiene el valor de la propiedad numeroComprobantes.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroComprobantes() {
        return numeroComprobantes;
    }

    /**
     * Define el valor de la propiedad numeroComprobantes.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroComprobantes(String value) {
        this.numeroComprobantes = value;
    }

    /**
     * Obtiene el valor de la propiedad autorizaciones.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaComprobante.Autorizaciones }
     *     
     */
    public RespuestaComprobante.Autorizaciones getAutorizaciones() {
        return autorizaciones;
    }

    /**
     * Define el valor de la propiedad autorizaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaComprobante.Autorizaciones }
     *     
     */
    public void setAutorizaciones(RespuestaComprobante.Autorizaciones value) {
        this.autorizaciones = value;
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
     *         &amp;lt;element ref="{http://ec.gob.sri.ws.autorizacion}autorizacion" maxOccurs="unbounded" minOccurs="0"/&amp;gt;
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
        "autorizacion"
    })
    public static class Autorizaciones {

        @XmlElement(namespace = "http://ec.gob.sri.ws.autorizacion")
        protected List<Autorizacion> autorizacion;

        /**
         * Gets the value of the autorizacion property.
         * 
         * &lt;p&gt;
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a &lt;CODE&gt;set&lt;/CODE&gt; method for the autorizacion property.
         * 
         * &lt;p&gt;
         * For example, to add a new item, do as follows:
         * &lt;pre&gt;
         *    getAutorizacion().add(newItem);
         * &lt;/pre&gt;
         * 
         * 
         * &lt;p&gt;
         * Objects of the following type(s) are allowed in the list
         * {@link Autorizacion }
         * 
         * 
         */
        public List<Autorizacion> getAutorizacion() {
            if (autorizacion == null) {
                autorizacion = new ArrayList<Autorizacion>();
            }
            return this.autorizacion;
        }

    }

}
