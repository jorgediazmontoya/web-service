/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.TotalImpuesto;
import TiposComprobantes.Bloques.DetalleLiquidacionCompra;
import TiposComprobantes.Bloques.Pago;
import TiposComprobantes.Bloques.ReembolsoLiquidacionCompra;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class LiquidacionCompra extends ComprobanteGeneral {

    private String tipoIdentificacionProveedor;
    private String razonSocialProveedor;
    private String identificacionProveedor;
    private String direccionProveedor;
    private String totalSinImpuestos;
    private String totalDescuento;
    private List<TotalImpuesto> totalConImpuesto;
    private String importeTotal;
    private String moneda;
    private List<DetalleLiquidacionCompra> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;
    private String codDocReemb;
    private String totalComprobantesReembolso;
    private String totalBaseImponibleReembolso;
    private String totalImpuestoReembolso;
    private List<ReembolsoLiquidacionCompra> reembolsos;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("liquidacionCompra");
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagLiquidacionCompra = xmlComprobante.getDocumentElement();
        tagLiquidacionCompra.setAttribute("version", "1.0.0");
        tagLiquidacionCompra.setAttribute("id", "comprobante");

        Element tagInfoLiquidacionCompra = xmlComprobante.createElement("infoLiquidacionCompra");

        Element tagFechaEmision = xmlComprobante.createElement("fechaEmision");
        tagFechaEmision.setTextContent(getFechaEmision());
        tagInfoLiquidacionCompra.appendChild(tagFechaEmision);

        if (getDirEstablecimiento() != null && !getDirEstablecimiento().equals("")) {
            Element tagDirEstablecimiento = xmlComprobante.createElement("dirEstablecimiento");
            tagDirEstablecimiento.setTextContent(getDirEstablecimiento());
            tagInfoLiquidacionCompra.appendChild(tagDirEstablecimiento);
        }
        if (getContribuyenteEspecial() != null && !getContribuyenteEspecial().equals("")) {
            Element tagContribuyenteEspecial = xmlComprobante.createElement("contribuyenteEspecial");
            tagContribuyenteEspecial.setTextContent(getContribuyenteEspecial());
            tagInfoLiquidacionCompra.appendChild(tagContribuyenteEspecial);
        }

        Element tagObligadoContabilidad = xmlComprobante.createElement("obligadoContabilidad");
        tagObligadoContabilidad.setTextContent(getObligadoContabilidad());
        tagInfoLiquidacionCompra.appendChild(tagObligadoContabilidad);

        Element tagTipoIdentificacionProveedor = xmlComprobante.createElement("tipoIdentificacionProveedor");
        tagTipoIdentificacionProveedor.setTextContent(getTipoIdentificacionProveedor());
        tagInfoLiquidacionCompra.appendChild(tagTipoIdentificacionProveedor);

        Element tagRazonSocialProveedor = xmlComprobante.createElement("razonSocialProveedor");
        tagRazonSocialProveedor.setTextContent(getRazonSocialProveedor());
        tagInfoLiquidacionCompra.appendChild(tagRazonSocialProveedor);

        if (getIdentificacionProveedor() != null && !identificacionProveedor.equals("")) {
            Element tagIdentificacionProveedor = xmlComprobante.createElement("identificacionProveedor");
            tagIdentificacionProveedor.setTextContent(getIdentificacionProveedor());
            tagInfoLiquidacionCompra.appendChild(tagIdentificacionProveedor);
        }

        if (getDireccionProveedor() != null && !direccionProveedor.equals("")) {
            Element tagDireccionProveedor = xmlComprobante.createElement("direccionProveedor");
            tagDireccionProveedor.setTextContent(getDireccionProveedor());
            tagInfoLiquidacionCompra.appendChild(tagDireccionProveedor);
        }
        
        Element tagTotalSinImpuestos = xmlComprobante.createElement("totalSinImpuestos");
        tagTotalSinImpuestos.setTextContent(getTotalSinImpuestos());
        tagInfoLiquidacionCompra.appendChild(tagTotalSinImpuestos);

        if (getTotalDescuento() != null && !totalDescuento.equals("")) {
            Element tagTotalDescuento = xmlComprobante.createElement("totalDescuento");
            tagTotalDescuento.setTextContent(getTotalDescuento());
            tagInfoLiquidacionCompra.appendChild(tagTotalDescuento);
        }
        
        if (getCodDocReemb() != null && !codDocReemb.equals("")) {
            Element tagCodDocReemb = xmlComprobante.createElement("codDocReembolso");
            tagCodDocReemb.setTextContent(getCodDocReemb());
            tagInfoLiquidacionCompra.appendChild(tagCodDocReemb);

            Element tagTotalComprobantesReembolso = xmlComprobante.createElement("totalComprobantesReembolso");
            tagTotalComprobantesReembolso.setTextContent(getTotalComprobantesReembolso());
            tagInfoLiquidacionCompra.appendChild(tagTotalComprobantesReembolso);

            Element tagTotalBaseImponibleReembolso = xmlComprobante.createElement("totalBaseImponibleReembolso");
            tagTotalBaseImponibleReembolso.setTextContent(getTotalBaseImponibleReembolso());
            tagInfoLiquidacionCompra.appendChild(tagTotalBaseImponibleReembolso);

            Element tagTotalImpuestoReembolso = xmlComprobante.createElement("totalImpuestoReembolso");
            tagTotalImpuestoReembolso.setTextContent(getTotalImpuestoReembolso());
            tagInfoLiquidacionCompra.appendChild(tagTotalImpuestoReembolso);
        }
        
        tagInfoLiquidacionCompra.appendChild(util.construirXMLTotalConImpuestos(getTotalConImpuesto(), xmlComprobante));

        Element tagImporteTotal = xmlComprobante.createElement("importeTotal");
        tagImporteTotal.setTextContent(getImporteTotal());
        tagInfoLiquidacionCompra.appendChild(tagImporteTotal);

        if (getMoneda() != null && !moneda.equals("")) {
            Element tagMoneda = xmlComprobante.createElement("moneda");
            tagMoneda.setTextContent(getMoneda());
            tagInfoLiquidacionCompra.appendChild(tagMoneda);
        }
        tagInfoLiquidacionCompra.appendChild(util.construirXMLPagos(getPagos(), xmlComprobante));
        tagLiquidacionCompra.appendChild(tagInfoLiquidacionCompra);
        tagLiquidacionCompra.appendChild(util.construirXMLDetallesLiquidacionCompra(getDetalles(), xmlComprobante));
        
        if (getReembolsos() != null && !reembolsos.isEmpty() && getReembolsos().get(0) != null) {
            tagLiquidacionCompra.appendChild(util.construirXMLReembolsosLiquidacionCompra(reembolsos, xmlComprobante));
        }
        
        if (getInfoAdicional() != null && !infoAdicional.isEmpty() && getInfoAdicional().get(0) != null) {
            tagLiquidacionCompra.appendChild(util.construirXMLInfoAdicional(getInfoAdicional(), xmlComprobante));
        }
        return xmlComprobante;
    }

    /**
     * @return the tipoIdentificacionProveedor
     */
    public String getTipoIdentificacionProveedor() {
        return tipoIdentificacionProveedor;
    }

    /**
     * @param tipoIdentificacionProveedor the tipoIdentificacionProveedor to set
     */
    public void setTipoIdentificacionProveedor(String tipoIdentificacionProveedor) {
        this.tipoIdentificacionProveedor = tipoIdentificacionProveedor;
    }

    /**
     * @return the razonSocialProveedor
     */
    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    /**
     * @param razonSocialProveedor the razonSocialProveedor to set
     */
    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    /**
     * @return the identificacionProveedor
     */
    public String getIdentificacionProveedor() {
        return identificacionProveedor;
    }

    /**
     * @param identificacionProveedor the identificacionProveedor to set
     */
    public void setIdentificacionProveedor(String identificacionProveedor) {
        this.identificacionProveedor = identificacionProveedor;
    }

    /**
     * @return the direccionProveedor
     */
    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    /**
     * @param direccionProveedor the direccionProveedor to set
     */
    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
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
     * @param totalConImpuesto the totalConImpuesto to set
     */
    public void setTotalConImpuesto(List<TotalImpuesto> totalConImpuesto) {
        this.totalConImpuesto = totalConImpuesto;
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
    public List<DetalleLiquidacionCompra> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleLiquidacionCompra> detalles) {
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

    /**
     * @return the codDocReemb
     */
    public String getCodDocReemb() {
        return codDocReemb;
    }

    /**
     * @param codDocReemb the codDocReemb to set
     */
    public void setCodDocReemb(String codDocReemb) {
        this.codDocReemb = codDocReemb;
    }

    /**
     * @return the totalComprobantesReembolso
     */
    public String getTotalComprobantesReembolso() {
        return totalComprobantesReembolso;
    }

    /**
     * @param totalComprobantesReembolso the totalComprobantesReembolso to set
     */
    public void setTotalComprobantesReembolso(String totalComprobantesReembolso) {
        this.totalComprobantesReembolso = totalComprobantesReembolso;
    }

    /**
     * @return the totalBaseImponibleReembolso
     */
    public String getTotalBaseImponibleReembolso() {
        return totalBaseImponibleReembolso;
    }

    /**
     * @param totalBaseImponibleReembolso the totalBaseImponibleReembolso to set
     */
    public void setTotalBaseImponibleReembolso(String totalBaseImponibleReembolso) {
        this.totalBaseImponibleReembolso = totalBaseImponibleReembolso;
    }

    /**
     * @return the totalImpuestoReembolso
     */
    public String getTotalImpuestoReembolso() {
        return totalImpuestoReembolso;
    }

    /**
     * @param totalImpuestoReembolso the totalImpuestoReembolso to set
     */
    public void setTotalImpuestoReembolso(String totalImpuestoReembolso) {
        this.totalImpuestoReembolso = totalImpuestoReembolso;
    }

    /**
     * @return the reembolsos
     */
    public List<ReembolsoLiquidacionCompra> getReembolsos() {
        return reembolsos;
    }

    /**
     * @param reembolsos the reembolsos to set
     */
    public void setReembolsos(List<ReembolsoLiquidacionCompra> reembolsos) {
        this.reembolsos = reembolsos;
    }

   
}
