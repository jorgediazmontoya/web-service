/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.DetalleNotaCredito;
import TiposComprobantes.Bloques.TotalImpuesto;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class NotaCredito extends ComprobanteGeneral {

    private String tipoIdentificacionComprador;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String rise;
    private String codDocModificado;
    private String numDocModificado;
    private String fechaEmisionDocSustento;
    private String totalSinImpuestos;
    private String valorModificacion;
    private String moneda;
    private List<TotalImpuesto> totalConImpuesto;
    private String motivo;
    private List<DetalleNotaCredito> detalles;
    private List<CampoAdicional> infoAdicional;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("notaCredito");
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagNotaCredito = xmlComprobante.getDocumentElement();
        tagNotaCredito.setAttribute("version", "1.1.0");
        tagNotaCredito.setAttribute("id", "comprobante");

        Element tagInfoNotaDebito = xmlComprobante.createElement("infoNotaCredito");

        Element tagFechaEmision = xmlComprobante.createElement("fechaEmision");
        tagFechaEmision.setTextContent(getFechaEmision());
        tagInfoNotaDebito.appendChild(tagFechaEmision);

        if (getDirEstablecimiento() != null && !getDirEstablecimiento().equals("")) {
            Element tagDirEstablecimiento = xmlComprobante.createElement("dirEstablecimiento");
            tagDirEstablecimiento.setTextContent(getDirEstablecimiento());
            tagInfoNotaDebito.appendChild(tagDirEstablecimiento);
        }
        Element tagTipoIdentificacionComprador = xmlComprobante.createElement("tipoIdentificacionComprador");
        tagTipoIdentificacionComprador.setTextContent(getTipoIdentificacionComprador());
        tagInfoNotaDebito.appendChild(tagTipoIdentificacionComprador);

        Element tagRazonSocialComprador = xmlComprobante.createElement("razonSocialComprador");
        tagRazonSocialComprador.setTextContent(getRazonSocialComprador());
        tagInfoNotaDebito.appendChild(tagRazonSocialComprador);

        Element tagIdentificacionComprador = xmlComprobante.createElement("identificacionComprador");
        tagIdentificacionComprador.setTextContent(getIdentificacionComprador());
        tagInfoNotaDebito.appendChild(tagIdentificacionComprador);

        if (getContribuyenteEspecial() != null && !getContribuyenteEspecial().equals("")) {
            Element tagContribuyenteEspecial = xmlComprobante.createElement("contribuyenteEspecial");
            tagContribuyenteEspecial.setTextContent(getContribuyenteEspecial());
            tagInfoNotaDebito.appendChild(tagContribuyenteEspecial);
        }

        Element tagObligadoContabilidad = xmlComprobante.createElement("obligadoContabilidad");
        tagObligadoContabilidad.setTextContent(getObligadoContabilidad());
        tagInfoNotaDebito.appendChild(tagObligadoContabilidad);

        if (getRise() != null && !getRise().equals("")) {
            Element tagRise = xmlComprobante.createElement("rise");
            tagRise.setTextContent(getRise());
            tagInfoNotaDebito.appendChild(tagRise);
        }
        Element tagCodDocModificado = xmlComprobante.createElement("codDocModificado");
        tagCodDocModificado.setTextContent(getCodDocModificado());
        tagInfoNotaDebito.appendChild(tagCodDocModificado);

        Element tagNumDocModificado = xmlComprobante.createElement("numDocModificado");
        tagNumDocModificado.setTextContent(getNumDocModificado());
        tagInfoNotaDebito.appendChild(tagNumDocModificado);

        if (getFechaEmisionDocSustento() != null && !fechaEmisionDocSustento.equals("")) {
            Element tagFechaEmisionDocSustento = xmlComprobante.createElement("fechaEmisionDocSustento");
            tagFechaEmisionDocSustento.setTextContent(getFechaEmisionDocSustento());
            tagInfoNotaDebito.appendChild(tagFechaEmisionDocSustento);
        }

        Element tagTotalSinImpuestos = xmlComprobante.createElement("totalSinImpuestos");
        tagTotalSinImpuestos.setTextContent(getTotalSinImpuestos());
        tagInfoNotaDebito.appendChild(tagTotalSinImpuestos);

        Element tagValorModificacion = xmlComprobante.createElement("valorModificacion");
        tagValorModificacion.setTextContent(getValorModificacion());
        tagInfoNotaDebito.appendChild(tagValorModificacion);

        if (getMoneda() != null && !moneda.equals("")) {
            Element tagMoneda = xmlComprobante.createElement("moneda");
            tagMoneda.setTextContent(getMoneda());
            tagInfoNotaDebito.appendChild(tagMoneda);
        }

        tagInfoNotaDebito.appendChild(util.construirXMLTotalConImpuestos(getTotalConImpuesto(), xmlComprobante));

        Element tagMotivo = xmlComprobante.createElement("motivo");
        tagMotivo.setTextContent(getMotivo());
        tagInfoNotaDebito.appendChild(tagMotivo);

        tagNotaCredito.appendChild(tagInfoNotaDebito);

        tagNotaCredito.appendChild(util.construirXMLDetallesNotaCredito(getDetalles(), xmlComprobante));

        if (getInfoAdicional() != null && !infoAdicional.isEmpty() && infoAdicional.get(0) != null) {
            tagNotaCredito.appendChild(util.construirXMLInfoAdicional(getInfoAdicional(), xmlComprobante));
        }

        return xmlComprobante;
    }

    /**
     * @return the tipoIdentificacionComprador
     */
    public String getTipoIdentificacionComprador() {
        return tipoIdentificacionComprador;
    }

    /**
     * @param tipoIdentificacionComprador the tipoIdentificacionComprador to set
     */
    public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
        this.tipoIdentificacionComprador = tipoIdentificacionComprador;
    }

    /**
     * @return the razonSocialComprador
     */
    public String getRazonSocialComprador() {
        return razonSocialComprador;
    }

    /**
     * @param razonSocialComprador the razonSocialComprador to set
     */
    public void setRazonSocialComprador(String razonSocialComprador) {
        this.razonSocialComprador = razonSocialComprador;
    }

    /**
     * @return the identificacionComprador
     */
    public String getIdentificacionComprador() {
        return identificacionComprador;
    }

    /**
     * @param identificacionComprador the identificacionComprador to set
     */
    public void setIdentificacionComprador(String identificacionComprador) {
        this.identificacionComprador = identificacionComprador;
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
     * @return the codDocModificado
     */
    public String getCodDocModificado() {
        return codDocModificado;
    }

    /**
     * @param codDocModificado the codDocModificado to set
     */
    public void setCodDocModificado(String codDocModificado) {
        this.codDocModificado = codDocModificado;
    }

    /**
     * @return the numDocModificado
     */
    public String getNumDocModificado() {
        return numDocModificado;
    }

    /**
     * @param numDocModificado the numDocModificado to set
     */
    public void setNumDocModificado(String numDocModificado) {
        this.numDocModificado = numDocModificado;
    }

    /**
     * @return the fechaEmisionDocSustento
     */
    public String getFechaEmisionDocSustento() {
        return fechaEmisionDocSustento;
    }

    /**
     * @param fechaEmisionDocSustento the fechaEmisionDocSustento to set
     */
    public void setFechaEmisionDocSustento(String fechaEmisionDocSustento) {
        this.fechaEmisionDocSustento = fechaEmisionDocSustento;
    }

    /**
     * @return the totalSinImpuestos
     */
    public String getTotalSinImpuestos() {
        return totalSinImpuestos;
    }

    /**
     * @param totalSinImpuestos the totalSinImpuestos to set
     */
    public void setTotalSinImpuestos(String totalSinImpuestos) {
        this.totalSinImpuestos = totalSinImpuestos;
    }

    /**
     * @return the valorModificacion
     */
    public String getValorModificacion() {
        return valorModificacion;
    }

    /**
     * @param valorModificacion the valorModificacion to set
     */
    public void setValorModificacion(String valorModificacion) {
        this.valorModificacion = valorModificacion;
    }

    /**
     * @return the moneda
     */
    public String getMoneda() {
        return moneda;
    }

    /**
     * @param moneda the moneda to set
     */
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    /**
     * @return the totalConImpuesto
     */
    public List<TotalImpuesto> getTotalConImpuesto() {
        return totalConImpuesto;
    }

    /**
     * @param totalConImpuesto the totalConImpuesto to set
     */
    public void setTotalConImpuesto(List<TotalImpuesto> totalConImpuesto) {
        this.totalConImpuesto = totalConImpuesto;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the detalles
     */
    public List<DetalleNotaCredito> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleNotaCredito> detalles) {
        this.detalles = detalles;
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
