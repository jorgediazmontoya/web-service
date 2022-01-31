
package SRI.Recepcion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * &lt;p&gt;Clase Java para validarComprobanteResponse complex type.
 * 
 * &lt;p&gt;El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * &lt;pre&gt;
 * &amp;lt;complexType name="validarComprobanteResponse"&amp;gt;
 *   &amp;lt;complexContent&amp;gt;
 *     &amp;lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&amp;gt;
 *       &amp;lt;sequence&amp;gt;
 *         &amp;lt;element name="RespuestaRecepcionComprobante" type="{http://ec.gob.sri.ws.recepcion}respuestaSolicitud" minOccurs="0"/&amp;gt;
 *       &amp;lt;/sequence&amp;gt;
 *     &amp;lt;/restriction&amp;gt;
 *   &amp;lt;/complexContent&amp;gt;
 * &amp;lt;/complexType&amp;gt;
 * &lt;/pre&gt;
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validarComprobanteResponse", propOrder = {
    "respuestaRecepcionComprobante"
})
public class ValidarComprobanteResponse {

    @XmlElement(name = "RespuestaRecepcionComprobante")
    protected RespuestaSolicitud respuestaRecepcionComprobante;

    /**
     * Obtiene el valor de la propiedad respuestaRecepcionComprobante.
     * 
     * @return
     *     possible object is
     *     {@link RespuestaSolicitud }
     *     
     */
    public RespuestaSolicitud getRespuestaRecepcionComprobante() {
        return respuestaRecepcionComprobante;
    }

    /**
     * Define el valor de la propiedad respuestaRecepcionComprobante.
     * 
     * @param value
     *     allowed object is
     *     {@link RespuestaSolicitud }
     *     
     */
    public void setRespuestaRecepcionComprobante(RespuestaSolicitud value) {
        this.respuestaRecepcionComprobante = value;
    }

}
