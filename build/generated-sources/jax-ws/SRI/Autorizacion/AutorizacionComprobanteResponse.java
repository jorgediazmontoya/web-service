
package SRI.Autorizacion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para autorizacionComprobanteResponse complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="autorizacionComprobanteResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RespuestaAutorizacionComprobante" type="{http://ec.gob.sri.ws.autorizacion}respuestaComprobante" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autorizacionComprobanteResponse", propOrder = {
    "respuestaAutorizacionComprobante"
})
public class AutorizacionComprobanteResponse {

    @XmlElement(name = "RespuestaAutorizacionComprobante")
    protected RespuestaComprobante respuestaAutorizacionComprobante;

    /**
     * Obtiene el valor de la propiedad respuestaAutorizacionComprobante.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaComprobante }
     *     
     */
    public RespuestaComprobante getRespuestaAutorizacionComprobante() {
        return respuestaAutorizacionComprobante;
    }

    /**
     * Define el valor de la propiedad respuestaAutorizacionComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaComprobante }
     *     
     */
    public void setRespuestaAutorizacionComprobante(RespuestaComprobante value) {
        this.respuestaAutorizacionComprobante = value;
    }

}
