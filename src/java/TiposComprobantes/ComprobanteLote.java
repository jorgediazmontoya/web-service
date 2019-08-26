/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */
package TiposComprobantes;

import FirmaElectronica.FirmaElectronica;
import Respuesta.MensajeGenerado;
import Respuesta.RespuestaInterna;
import Util.ConfigAplicacion;
import Util.ConfigCorreo;
import Util.Util;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/**
 *
 * @author Yoelvys
 */
public class ComprobanteLote {

    private ConfigAplicacion configAplicacion;
    private ConfigCorreo configCorreo;
    private String idUnico;
    private String claveAcceso;
    private String ruc;
    private String fechaEmision;
    private String ambiente;
    private String tipoEmision;
    private String secuencial;
    private String codDoc;
    private String establecimiento;
    private String ptoEmision;
    private List<ComprobanteGeneral> comprobantes;

    /**
     * @return the claveAcceso
     */
    public String getClaveAcceso() {
        return claveAcceso;
    }

    /**
     * @param claveAcceso the claveAcceso to set
     */
    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the comprobantes
     */
    public List<ComprobanteGeneral> getComprobantes() {
        return comprobantes;
    }

    /**
     * @param comprobantes the comprobantes to set
     */
    public void setComprobantes(List<ComprobanteGeneral> comprobantes) {
        this.comprobantes = comprobantes;
    }

    /**
     * @return the idUnico
     */
    public String getIdUnico() {
        return idUnico;
    }

    /**
     * @param idUnico the idUnico to set
     */
    public void setIdUnico(String idUnico) {
        this.idUnico = idUnico;
    }

    public RespuestaInterna crearXMLComprobante() throws ParserConfigurationException, TransformerConfigurationException, TransformerException, SAXException, IOException {
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        FirmaElectronica firmar = new FirmaElectronica();
        Util util = new Util();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document xmlComprobante = implementation.createDocument(null, "lote", null);
        xmlComprobante.setXmlVersion("1.0");

        Element tagRoot = xmlComprobante.getDocumentElement();
        tagRoot.setAttribute("version", "1.0.0");

        Element tagClaveAcceso = xmlComprobante.createElement("claveAcceso");
        tagClaveAcceso.setTextContent(claveAcceso());
        tagRoot.appendChild(tagClaveAcceso);

        Element tagRuc = xmlComprobante.createElement("ruc");
        tagRuc.setTextContent(getRuc());
        tagRoot.appendChild(tagRuc);

        Element tagComprobantes = xmlComprobante.createElement("comprobantes");
        String stringComprobantes = "";
        for (Iterator<ComprobanteGeneral> it = comprobantes.iterator(); it.hasNext();) {

            ComprobanteGeneral comprobanteGeneral = it.next();
            Document xmlComprobanteHijo = null;
            try {
                xmlComprobanteHijo = comprobanteGeneral.crearXMLComprobante();
            } catch (Exception e) {
                String serie = getEstablecimiento() + "-" + getPtoEmision() + "-" + getSecuencial();
                respuestaInterna.setEstadoComprobante("ERROR");
                respuestaInterna.addMensaje(new MensajeGenerado("1000", "ERROR CREANDO EL XML DEL COMPROBANTE " + serie, null, "EXCEPCION"));

                return respuestaInterna;
            }
            respuestaInterna = firmar.FirmarComprobante(xmlComprobanteHijo);
            if (respuestaInterna.getEstadoComprobante().equals("ERROR")) {
                return respuestaInterna;
            }
            try {
                stringComprobantes += "<comprobante>" + "<![CDATA[" + util.convertirXMLToString(respuestaInterna.getComprobante()) + "]]>" + "</comprobante>";

            } catch (Exception e) {
                String serie = getEstablecimiento() + "-" + getPtoEmision() + "-" + getSecuencial();
                respuestaInterna.setEstadoComprobante("ERROR");
                respuestaInterna.addMensaje(new MensajeGenerado("1000", "ERROR CONVIRTIENDO A STRING EL XML DEL COMPROBANTE " + serie, null, "EXCEPCION"));

                return respuestaInterna;
            }

            tagComprobantes.setTextContent("insertar");
        }

        tagRoot.appendChild(tagComprobantes);

        xmlComprobante = util.convertirStringToXML(util.convertirXMLToString(xmlComprobante).replaceAll("insertar", stringComprobantes));
        respuestaInterna.setEstadoComprobante("CREADO");
        respuestaInterna.setComprobante(xmlComprobante);

        return respuestaInterna;
    }

    private String claveAcceso() {
        String claveAcceso = getFechaEmision().replaceAll("/", "");
        claveAcceso += getCodDoc();
        claveAcceso += getRuc();
        claveAcceso += getAmbiente();
        String serie = getEstablecimiento() + getPtoEmision();
        claveAcceso += serie;
        claveAcceso += getSecuencial();
        claveAcceso += "12345678";
        claveAcceso += getTipoEmision();
        claveAcceso += modulo11(claveAcceso);

        return claveAcceso;
    }

    private String modulo11(String claveAcceso) {
        int[] multiplos = {2, 3, 4, 5, 6, 7};
        int i = 0;
        int cantidad = claveAcceso.length();
        int total = 0;
        while (cantidad > 0) {
            total += Integer.parseInt(claveAcceso.substring(cantidad - 1, cantidad)) * multiplos[i];
            i++;
            i = i % 6;
            cantidad--;
        }
        int modulo11 = 11 - total % 11;
        if (modulo11 == 11) {
            modulo11 = 0;
        } else if (modulo11 == 10) {
            modulo11 = 1;
        }

        return Integer.toString(modulo11);
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the ambiente
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * @param ambiente the ambiente to set
     */
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * @return the tipoEmision
     */
    public String getTipoEmision() {
        return tipoEmision;
    }

    /**
     * @param tipoEmision the tipoEmision to set
     */
    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    /**
     * @return the secuencial
     */
    public String getSecuencial() {
        return secuencial;
    }

    /**
     * @param secuencial the secuencial to set
     */
    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
    }

    /**
     * @return the codDoc
     */
    public String getCodDoc() {
        return codDoc;
    }

    /**
     * @param codDoc the codDoc to set
     */
    public void setCodDoc(String codDoc) {
        this.codDoc = codDoc;
    }

    /**
     * @return the establecimiento
     */
    public String getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the ptoEmision
     */
    public String getPtoEmision() {
        return ptoEmision;
    }

    /**
     * @param ptoEmision the ptoEmision to set
     */
    public void setPtoEmision(String ptoEmision) {
        this.ptoEmision = ptoEmision;
    }

    /**
     * @return the configAplicacion
     */
    public ConfigAplicacion getConfigAplicacion() {
        return configAplicacion;
    }

    /**
     * @param configAplicacion the configAplicacion to set
     */
    public void setConfigAplicacion(ConfigAplicacion configAplicacion) {
        this.configAplicacion = configAplicacion;
    }

    /**
     * @return the configCorreo
     */
    public ConfigCorreo getConfigCorreo() {
        return configCorreo;
    }

    /**
     * @param configCorreo the configCorreo to set
     */
    public void setConfigCorreo(ConfigCorreo configCorreo) {
        this.configCorreo = configCorreo;
    }

}
