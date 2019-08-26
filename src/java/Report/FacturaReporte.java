/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Report;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.DetalleFactura;
import TiposComprobantes.Bloques.Pago;
import java.util.List;

/**
 *
 * @author yoelvys
 */
public class FacturaReporte {
    private String ambiente;
    private String tipoEmision;
    private String razonSocial;
    private String nombreComercial;
    private String ruc;
    private String claveAcc;
    private String establecimiento;
    private String ptoEmision;
    private String secuencial;
    private String dirMatriz;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String fechaEmision;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String totalSinImpuestos;
    private String totalDescuento;
    private String propina;
    private String importeTotal;
    private List<DetalleFactura> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;

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
     * @return the claveAcc
     */
    public String getClaveAcc() {
        return claveAcc;
    }

    /**
     * @param claveAcc the claveAcc to set
     */
    public void setClaveAcc(String claveAcc) {
        this.claveAcc = claveAcc;
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
     * @return the contribuyenteEspecial
     */
    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    /**
     * @param contribuyenteEspecial the contribuyenteEspecial to set
     */
    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    /**
     * @return the obligadoContabilidad
     */
    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    /**
     * @param obligadoContabilidad the obligadoContabilidad to set
     */
    public void setObligadoContabilidad(String obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
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
     * @return the detalles
     */
    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleFactura> detalles) {
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
}
