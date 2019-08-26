/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperComprobantes;

/**
 *
 * @author Yoelvys
 */
public class ImpuestoComprobanteRetencion {

    private String codigoRetencion;
    private String baseImponible;
    private String porcentajeRetener;
    private String valorRetenido;
    private String numDocSustento;
    private String ejercicioFiscal;
    private String tipoImpuesto;

    /**
     * @return the codigoRetencion
     */
    public String getCodigoRetencion() {
        return codigoRetencion;
    }

    /**
     * @param codigoRetencion the codigoRetencion to set
     */
    public void setCodigoRetencion(String codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    /**
     * @return the baseImponible
     */
    public String getBaseImponible() {
        return baseImponible;
    }

    /**
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(String baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * @return the porcentajeRetener
     */
    public String getPorcentajeRetener() {
        return porcentajeRetener;
    }

    /**
     * @param porcentajeRetener the porcentajeRetener to set
     */
    public void setPorcentajeRetener(String porcentajeRetener) {
        this.porcentajeRetener = porcentajeRetener;
    }

    /**
     * @return the valorRetenido
     */
    public String getValorRetenido() {
        return valorRetenido;
    }

    /**
     * @param valorRetenido the valorRetenido to set
     */
    public void setValorRetenido(String valorRetenido) {
        this.valorRetenido = valorRetenido;
    }

    /**
     * @return the numDocSustento
     */
    public String getNumDocSustento() {
        return numDocSustento;
    }

    /**
     * @param numDocSustento the numDocSustento to set
     */
    public void setNumDocSustento(String numDocSustento) {
        this.numDocSustento = numDocSustento;
    }

    /**
     * @return the ejercicioFiscal
     */
    public String getEjercicioFiscal() {
        return ejercicioFiscal;
    }

    /**
     * @param ejercicioFiscal the ejercicioFiscal to set
     */
    public void setEjercicioFiscal(String ejercicioFiscal) {
        this.ejercicioFiscal = ejercicioFiscal;
    }

    /**
     * @return the tipoImpuesto
     */
    public String getTipoImpuesto() {
        return tipoImpuesto;
    }

    /**
     * @param tipoImpuesto the tipoImpuesto to set
     */
    public void setTipoImpuesto(String tipoImpuesto) {
        this.tipoImpuesto = tipoImpuesto;
    }

}
