
package SRI.Autorizacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para autorizacionComprobanteLote complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="autorizacionComprobanteLote"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="claveAccesoLote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizacionComprobanteLote", propOrder = {
    "claveAccesoLote"
})
public class AutorizacionComprobanteLote {

    protected String claveAccesoLote;

    /**
     * Obtiene el valor de la propiedad claveAccesoLote.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClaveAccesoLote() {
        return claveAccesoLote;
    }

    /**
     * Define el valor de la propiedad claveAccesoLote.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClaveAccesoLote(String value) {
        this.claveAccesoLote = value;
    }

}
