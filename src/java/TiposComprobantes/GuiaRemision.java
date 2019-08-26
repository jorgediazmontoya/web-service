/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.Destinatario;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class GuiaRemision extends ComprobanteGeneral {

    private String dirPartida;
    private String razonSocialTransportista;
    private String tipoIdentificacionTransportista;
    private String rucTransportista;
    private String rise;
    private String fechaIniTransporte;
    private String fechaFinTransporte;
    private String placa;
    private List<Destinatario> destinatarios;
    private List<CampoAdicional> infoAdicional;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("guiaRemision");
        setFechaEmision(fechaIniTransporte);
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagGuiaRemision = xmlComprobante.getDocumentElement();
        tagGuiaRemision.setAttribute("id", "comprobante");
        tagGuiaRemision.setAttribute("version", "1.0.0");

        Element tagInfoCompRetencion = xmlComprobante.createElement("infoGuiaRemision");

        if (getDirEstablecimiento() != null && !getDirEstablecimiento().equals("")) {
            Element tagDirEstablecimiento = xmlComprobante.createElement("dirEstablecimiento");
            tagDirEstablecimiento.setTextContent(getDirEstablecimiento());
            tagInfoCompRetencion.appendChild(tagDirEstablecimiento);
        }

        Element tagDirPartida = xmlComprobante.createElement("dirPartida");
        tagDirPartida.setTextContent(dirPartida);
        tagInfoCompRetencion.appendChild(tagDirPartida);

        Element tagRazonSocialTransportista = xmlComprobante.createElement("razonSocialTransportista");
        tagRazonSocialTransportista.setTextContent(razonSocialTransportista);
        tagInfoCompRetencion.appendChild(tagRazonSocialTransportista);

        Element tagTipoIdentificacionTransportista = xmlComprobante.createElement("tipoIdentificacionTransportista");
        tagTipoIdentificacionTransportista.setTextContent(tipoIdentificacionTransportista);
        tagInfoCompRetencion.appendChild(tagTipoIdentificacionTransportista);

        Element tagRucTransportista = xmlComprobante.createElement("rucTransportista");
        tagRucTransportista.setTextContent(rucTransportista);
        tagInfoCompRetencion.appendChild(tagRucTransportista);

        if (rise != null && !rise.equals("")) {
            Element tagRise = xmlComprobante.createElement("rise");
            tagRise.setTextContent(rise);
            tagInfoCompRetencion.appendChild(tagRise);
        }
        Element tagObligadoContabilidad = xmlComprobante.createElement("obligadoContabilidad");
        tagObligadoContabilidad.setTextContent(getObligadoContabilidad());
        tagInfoCompRetencion.appendChild(tagObligadoContabilidad);

        if (getContribuyenteEspecial() != null && !getContribuyenteEspecial().equals("")) {
            Element tagContribuyenteEspecial = xmlComprobante.createElement("contribuyenteEspecial");
            tagContribuyenteEspecial.setTextContent(getContribuyenteEspecial());
            tagInfoCompRetencion.appendChild(tagContribuyenteEspecial);
        }

        Element tagFechaIniTransporte = xmlComprobante.createElement("fechaIniTransporte");
        tagFechaIniTransporte.setTextContent(fechaIniTransporte);
        tagInfoCompRetencion.appendChild(tagFechaIniTransporte);

        Element tagFechaFinTransporte = xmlComprobante.createElement("fechaFinTransporte");
        tagFechaFinTransporte.setTextContent(fechaFinTransporte);
        tagInfoCompRetencion.appendChild(tagFechaFinTransporte);

        Element tagPlaca = xmlComprobante.createElement("placa");
        tagPlaca.setTextContent(placa);
        tagInfoCompRetencion.appendChild(tagPlaca);
        tagGuiaRemision.appendChild(tagInfoCompRetencion);

        tagGuiaRemision.appendChild(util.construirXMLDestinatarios(destinatarios, xmlComprobante));

        if (infoAdicional != null && !infoAdicional.isEmpty() && infoAdicional.get(0) != null) {
            tagGuiaRemision.appendChild(util.construirXMLInfoAdicional(infoAdicional, xmlComprobante));
        }

        return xmlComprobante;
    }

    /**
     * @return the dirPartida
     */
    public String getDirPartida() {
        return dirPartida;
    }

    /**
     * @param dirPartida the dirPartida to set
     */
    public void setDirPartida(String dirPartida) {
        this.dirPartida = dirPartida;
    }

    /**
     * @return the razonSocialTransportista
     */
    public String getRazonSocialTransportista() {
        return razonSocialTransportista;
    }

    /**
     * @param razonSocialTransportista the razonSocialTransportista to set
     */
    public void setRazonSocialTransportista(String razonSocialTransportista) {
        this.razonSocialTransportista = razonSocialTransportista;
    }

    /**
     * @return the tipoIdentificacionTransportista
     */
    public String getTipoIdentificacionTransportista() {
        return tipoIdentificacionTransportista;
    }

    /**
     * @param tipoIdentificacionTransportista the
     * tipoIdentificacionTransportista to set
     */
    public void setTipoIdentificacionTransportista(String tipoIdentificacionTransportista) {
        this.tipoIdentificacionTransportista = tipoIdentificacionTransportista;
    }

    /**
     * @return the rucTransportista
     */
    public String getRucTransportista() {
        return rucTransportista;
    }

    /**
     * @param rucTransportista the rucTransportista to set
     */
    public void setRucTransportista(String rucTransportista) {
        this.rucTransportista = rucTransportista;
    }

    /**
     * @return the rise
     */
    public String getRise() {
        return rise;
    }

    /**
     * @param rise the rise to set
     */
    public void setRise(String rise) {
        this.rise = rise;
    }

    /**
     * @return the fechaIniTransporte
     */
    public String getFechaIniTransporte() {
        return fechaIniTransporte;
    }

    /**
     * @param fechaIniTransporte the fechaIniTransporte to set
     */
    public void setFechaIniTransporte(String fechaIniTransporte) {
        this.fechaIniTransporte = fechaIniTransporte;
    }

    /**
     * @return the fechaFinTransporte
     */
    public String getFechaFinTransporte() {
        return fechaFinTransporte;
    }

    /**
     * @param fechaFinTransporte the fechaFinTransporte to set
     */
    public void setFechaFinTransporte(String fechaFinTransporte) {
        this.fechaFinTransporte = fechaFinTransporte;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the destinatarios
     */
    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    /**
     * @param destinatarios the destinatarios to set
     */
    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }

    /**
     * @return the infoAdicional
     */
    public List<CampoAdicional> getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(List<CampoAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

}
