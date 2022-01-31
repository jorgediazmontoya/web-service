
package SRI.Autorizacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para autorizacionComprobanteLoteResponse complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="autorizacionComprobanteLoteResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RespuestaAutorizacionLote" type="{http://ec.gob.sri.ws.autorizacion}respuestaLote" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizacionComprobanteLoteResponse", propOrder = {
    "respuestaAutorizacionLote"
})
public class AutorizacionComprobanteLoteResponse {

    @XmlElement(name = "RespuestaAutorizacionLote")
    protected RespuestaLote respuestaAutorizacionLote;

    /**
     * Obtiene el valor de la propiedad respuestaAutorizacionLote.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaLote }
     *     
     */
    public RespuestaLote getRespuestaAutorizacionLote() {
        return respuestaAutorizacionLote;
    }

    /**
     * Define el valor de la propiedad respuestaAutorizacionLote.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaLote }
     *     
     */
    public void setRespuestaAutorizacionLote(RespuestaLote value) {
        this.respuestaAutorizacionLote = value;
    }

}
