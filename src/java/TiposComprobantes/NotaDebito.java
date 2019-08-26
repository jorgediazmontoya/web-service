/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.Impuesto;
import TiposComprobantes.Bloques.Motivo;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class NotaDebito extends ComprobanteGeneral {

    private String tipoIdentificacionComprador;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String rise;
    private String codDocModificado;
    private String numDocModificado;
    private String fechaEmisionDocSustento;
    private String totalSinImpuestos;
    private List<Impuesto> impuestos;
    private String valorTotal;
    private List<Motivo> motivos;
    private List<CampoAdicional> infoAdicional;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("notaDebito");
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagNotaDebito = xmlComprobante.getDocumentElement();
        tagNotaDebito.setAttribute("version", "1.0.0");
        tagNotaDebito.setAttribute("id", "comprobante");

        Element tagInfoNotaDebito = xmlComprobante.createElement("infoNotaDebito");

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
        tagRazonSocialComprador.setTextContent(razonSocialComprador);
        tagInfoNotaDebito.appendChild(tagRazonSocialComprador);

        Element tagIdentificacionComprador = xmlComprobante.createElement("identificacionComprador");
        tagIdentificacionComprador.setTextContent(identificacionComprador);
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
            tagRise.setTextContent(rise);
            tagInfoNotaDebito.appendChild(tagRise);
        }
        Element tagCodDocModificado = xmlComprobante.createElement("codDocModificado");
        tagCodDocModificado.setTextContent(codDocModificado);
        tagInfoNotaDebito.appendChild(tagCodDocModificado);

        Element tagNumDocModificado = xmlComprobante.createElement("numDocModificado");
        tagNumDocModificado.setTextContent(numDocModificado);
        tagInfoNotaDebito.appendChild(tagNumDocModificado);

        if (fechaEmisionDocSustento != null && !fechaEmisionDocSustento.equals("")) {
            Element tagFechaEmisionDocSustento = xmlComprobante.createElement("fechaEmisionDocSustento");
            tagFechaEmisionDocSustento.setTextContent(fechaEmisionDocSustento);
            tagInfoNotaDebito.appendChild(tagFechaEmisionDocSustento);
        }

        Element tagTotalSinImpuestos = xmlComprobante.createElement("totalSinImpuestos");
        tagTotalSinImpuestos.setTextContent(totalSinImpuestos);
        tagInfoNotaDebito.appendChild(tagTotalSinImpuestos);

        tagInfoNotaDebito.appendChild(util.construirXMLImpuestos(impuestos, xmlComprobante));

        Element tagValorTotal = xmlComprobante.createElement("valorTotal");
        tagValorTotal.setTextContent(valorTotal);
        tagInfoNotaDebito.appendChild(tagValorTotal);

        tagNotaDebito.appendChild(tagInfoNotaDebito);

        tagNotaDebito.appendChild(util.construirXMLMotivos(getMotivos(), xmlComprobante));

        if (getInfoAdicional() != null && !infoAdicional.isEmpty() && infoAdicional.get(0) != null) {
            tagNotaDebito.appendChild(util.construirXMLInfoAdicional(getInfoAdicional(), xmlComprobante));
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
     * @return the impuestos
     */
    public List<Impuesto> getImpuestos() {
        return impuestos;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(List<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

    /**
     * @return the motivos
     */
    public List<Motivo> getMotivos() {
        return motivos;
    }

    /**
     * @param motivos the motivos to set
     */
    public void setMotivos(List<Motivo> motivos) {
        this.motivos = motivos;
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

    /**
     * @return the valorTotal
     */
    public String getValorTotal() {
        return valorTotal;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(String valorTotal) {
        this.valorTotal = valorTotal;
    }

}
