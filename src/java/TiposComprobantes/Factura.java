/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.TotalImpuesto;
import TiposComprobantes.Bloques.DetalleFactura;
import TiposComprobantes.Bloques.Pago;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class Factura extends ComprobanteGeneral {

    private String tipoIdentificacionComprador;
    private String guiaRemision;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String totalSinImpuestos;
    private String totalDescuento;
    private String propina;
    private List<TotalImpuesto> totalConImpuesto;

    private String importeTotal;
    private String moneda;
    private List<DetalleFactura> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("factura");
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagFactura = xmlComprobante.getDocumentElement();
        tagFactura.setAttribute("version", "1.0.0");
        tagFactura.setAttribute("id", "comprobante");

        Element tagInfoFactura = xmlComprobante.createElement("infoFactura");

        Element tagFechaEmision = xmlComprobante.createElement("fechaEmision");
        tagFechaEmision.setTextContent(getFechaEmision());
        tagInfoFactura.appendChild(tagFechaEmision);

        if (getDirEstablecimiento() != null && !getDirEstablecimiento().equals("")) {
            Element tagDirEstablecimiento = xmlComprobante.createElement("dirEstablecimiento");
            tagDirEstablecimiento.setTextContent(getDirEstablecimiento());
            tagInfoFactura.appendChild(tagDirEstablecimiento);
        }
        if (getContribuyenteEspecial() != null && !getContribuyenteEspecial().equals("")) {
            Element tagContribuyenteEspecial = xmlComprobante.createElement("contribuyenteEspecial");
            tagContribuyenteEspecial.setTextContent(getContribuyenteEspecial());
            tagInfoFactura.appendChild(tagContribuyenteEspecial);
        }

        Element tagObligadoContabilidad = xmlComprobante.createElement("obligadoContabilidad");
        tagObligadoContabilidad.setTextContent(getObligadoContabilidad());
        tagInfoFactura.appendChild(tagObligadoContabilidad);

        Element tagTipoIdentificacionComprador = xmlComprobante.createElement("tipoIdentificacionComprador");
        tagTipoIdentificacionComprador.setTextContent(getTipoIdentificacionComprador());
        tagInfoFactura.appendChild(tagTipoIdentificacionComprador);

        if (getGuiaRemision() != null && !guiaRemision.equals("")) {
            Element tagGuiaRemision = xmlComprobante.createElement("guiaRemision");
            tagGuiaRemision.setTextContent(getGuiaRemision());
            tagInfoFactura.appendChild(tagGuiaRemision);
        }

        Element tagRazonSocialComprador = xmlComprobante.createElement("razonSocialComprador");
        tagRazonSocialComprador.setTextContent(getRazonSocialComprador());
        tagInfoFactura.appendChild(tagRazonSocialComprador);

        if (identificacionComprador != null && !identificacionComprador.equals("")) {
            Element tagIdentificacionComprador = xmlComprobante.createElement("identificacionComprador");
            tagIdentificacionComprador.setTextContent(getIdentificacionComprador());
            tagInfoFactura.appendChild(tagIdentificacionComprador);
        }

        Element tagTotalSinImpuestos = xmlComprobante.createElement("totalSinImpuestos");
        tagTotalSinImpuestos.setTextContent(getTotalSinImpuestos());
        tagInfoFactura.appendChild(tagTotalSinImpuestos);

        if (getTotalDescuento() != null && !totalDescuento.equals("")) {
            Element tagTotalDescuento = xmlComprobante.createElement("totalDescuento");
            tagTotalDescuento.setTextContent(getTotalDescuento());
            tagInfoFactura.appendChild(tagTotalDescuento);
        }
        tagInfoFactura.appendChild(util.construirXMLTotalConImpuestos(getTotalConImpuesto(), xmlComprobante));

        if (propina != null && !propina.equals("")) {
            Element tagPropina = xmlComprobante.createElement("propina");
            tagPropina.setTextContent(propina);
            tagInfoFactura.appendChild(tagPropina);
        }

        Element tagImporteTotal = xmlComprobante.createElement("importeTotal");
        tagImporteTotal.setTextContent(importeTotal);
        tagInfoFactura.appendChild(tagImporteTotal);

        if (moneda != null && !moneda.equals("")) {
            Element tagMoneda = xmlComprobante.createElement("moneda");
            tagMoneda.setTextContent(moneda);
            tagInfoFactura.appendChild(tagMoneda);
        }
        tagInfoFactura.appendChild(util.construirXMLPagos(getPagos(), xmlComprobante));
        tagFactura.appendChild(tagInfoFactura);

        tagFactura.appendChild(util.construirXMLDetallesFactura(detalles, xmlComprobante));
        if (infoAdicional != null && !infoAdicional.isEmpty() && infoAdicional.get(0) != null) {
            tagFactura.appendChild(util.construirXMLInfoAdicional(infoAdicional, xmlComprobante));
        }

        return xmlComprobante;
    }

    
    /**
     * @return the guiaRemision
     */
    public String getGuiaRemision() {
        return guiaRemision;
    }

    /**
     * @param guiaRemision the guiaRemision to set
     */
    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
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
     * @return the totalDescuento
     */
    public String getTotalDescuento() {
        return totalDescuento;
    }

    /**
     * @param totalDescuento the totalDescuento to set
     */
    public void setTotalDescuento(String totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    /**
     * @return the totalConImpuesto
     */
    public List<TotalImpuesto> getTotalConImpuesto() {
        return totalConImpuesto;
    }

    /**
     * @return the propina
     */
    public String getPropina() {
        return propina;
    }

    /**
     * @param propina the propina to set
     */
    public void setPropina(String propina) {
        this.propina = propina;
    }

    /**
     * @return the importeTotal
     */
    public String getImporteTotal() {
        return importeTotal;
    }

    /**
     * @param importeTotal the importeTotal to set
     */
    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
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
     * @return the detalles
     */
    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    /**
     * @return the infoAdicional
     */
    public List<CampoAdicional> getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param totalConImpuesto the totalConImpuesto to set
     */
    public void setTotalConImpuesto(List<TotalImpuesto> totalConImpuesto) {
        this.totalConImpuesto = totalConImpuesto;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(List<CampoAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
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
     * @return the pagos
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

}
