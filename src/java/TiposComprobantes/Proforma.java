/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.DetalleProforma;
import Util.ConfigCorreo;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class Proforma extends ComprobanteGeneral {

    private ConfigCorreo configCorreo;
    private String dirLogo;
    private String dirProformas;
    private String tipoEmision;
    private String razonSocial;
    private String nombreComercial;
    private String ruc;
    private String numero;
    private String dirMatriz;
    private String dirEstablecimiento;
    private String fechaEmision;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String direccionComprador;
    private String subTotal12;
    private String subTotal0;
    private String subTotalSinImpuesto;
    private String iva;
    private String totalDescuento;
    private String importeTotal;
    private List<DetalleProforma> detalles;
    private List<CampoAdicional> infoAdicional;

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
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the nombreComercial
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
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
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * @return the dirMatriz
     */
    public String getDirMatriz() {
        return dirMatriz;
    }

    /**
     * @param dirMatriz the dirMatriz to set
     */
    public void setDirMatriz(String dirMatriz) {
        this.dirMatriz = dirMatriz;
    }

    /**
     * @return the dirEstablecimiento
     */
    public String getDirEstablecimiento() {
        return dirEstablecimiento;
    }

    /**
     * @param dirEstablecimiento the dirEstablecimiento to set
     */
    public void setDirEstablecimiento(String dirEstablecimiento) {
        this.dirEstablecimiento = dirEstablecimiento;
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
     * @return the direccionComprador
     */
    public String getDireccionComprador() {
        return direccionComprador;
    }

    /**
     * @param direccionComprador the direccionComprador to set
     */
    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    /**
     * @return the subTotal12
     */
    public String getSubTotal12() {
        return subTotal12;
    }

    /**
     * @param subTotal12 the subTotal12 to set
     */
    public void setSubTotal12(String subTotal12) {
        this.subTotal12 = subTotal12;
    }

    /**
     * @return the subTotal0
     */
    public String getSubTotal0() {
        return subTotal0;
    }

    /**
     * @param subTotal0 the subTotal0 to set
     */
    public void setSubTotal0(String subTotal0) {
        this.subTotal0 = subTotal0;
    }

    /**
     * @return the iva
     */
    public String getIva() {
        return iva;
    }

    /**
     * @param iva the iva to set
     */
    public void setIva(String iva) {
        this.iva = iva;
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
     * @return the detalles
     */
    public List<DetalleProforma> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleProforma> detalles) {
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
     * @return the dirLogo
     */
    public String getDirLogo() {
        return dirLogo;
    }

    /**
     * @param dirLogo the dirLogo to set
     */
    public void setDirLogo(String dirLogo) {
        this.dirLogo = dirLogo;
    }

    /**
     * @return the dirProformas
     */
    public String getDirProformas() {
        return dirProformas;
    }

    /**
     * @param dirProformas the dirProformas to set
     */
    public void setDirProformas(String dirProformas) {
        this.dirProformas = dirProformas;
    }

    /**
     * @return the subTotalSinImpuesto
     */
    public String getSubTotalSinImpuesto() {
        return subTotalSinImpuesto;
    }

    /**
     * @param subTotalSinImpuesto the subTotalSinImpuesto to set
     */
    public void setSubTotalSinImpuesto(String subTotalSinImpuesto) {
        this.subTotalSinImpuesto = subTotalSinImpuesto;
    }

}
