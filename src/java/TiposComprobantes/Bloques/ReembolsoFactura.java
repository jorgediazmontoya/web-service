/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes.Bloques;

import JasperComprobantes.*;
import TiposComprobantes.Bloques.Impuesto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class ReembolsoFactura {

    private String tipoIdentificacionProveedorReembolso;
    private String identificacionProveedorReembolso;
    private String codPaisPagoProveedorReembolso;
    private String tipoProveedorReembolso;
    private String codDocReembolso;
    private String estabDocReembolso;
    private String ptoEmiDocReembolso;
    private String secuencialDocReembolso;
    private String fechaEmisionDocReembolso;
    private String numeroautorizacionDocReemb;
    private List<Impuesto> impuestos;

    /**
     * @return the tipoIdentificacionProveedorReembolso
     */
    public String getTipoIdentificacionProveedorReembolso() {
        return tipoIdentificacionProveedorReembolso;
    }

    /**
     * @param tipoIdentificacionProveedorReembolso the tipoIdentificacionProveedorReembolso to set
     */
    public void setTipoIdentificacionProveedorReembolso(String tipoIdentificacionProveedorReembolso) {
        this.tipoIdentificacionProveedorReembolso = tipoIdentificacionProveedorReembolso;
    }

    /**
     * @return the identificacionProveedorReembolso
     */
    public String getIdentificacionProveedorReembolso() {
        return identificacionProveedorReembolso;
    }

    /**
     * @param identificacionProveedorReembolso the identificacionProveedorReembolso to set
     */
    public void setIdentificacionProveedorReembolso(String identificacionProveedorReembolso) {
        this.identificacionProveedorReembolso = identificacionProveedorReembolso;
    }

    /**
     * @return the codPaisPagoProveedorReembolso
     */
    public String getCodPaisPagoProveedorReembolso() {
        return codPaisPagoProveedorReembolso;
    }

    /**
     * @param codPaisPagoProveedorReembolso the codPaisPagoProveedorReembolso to set
     */
    public void setCodPaisPagoProveedorReembolso(String codPaisPagoProveedorReembolso) {
        this.codPaisPagoProveedorReembolso = codPaisPagoProveedorReembolso;
    }

    /**
     * @return the tipoProveedorReembolso
     */
    public String getTipoProveedorReembolso() {
        return tipoProveedorReembolso;
    }

    /**
     * @param tipoProveedorReembolso the tipoProveedorReembolso to set
     */
    public void setTipoProveedorReembolso(String tipoProveedorReembolso) {
        this.tipoProveedorReembolso = tipoProveedorReembolso;
    }

    /**
     * @return the codDocReembolso
     */
    public String getCodDocReembolso() {
        return codDocReembolso;
    }

    /**
     * @param codDocReembolso the codDocReembolso to set
     */
    public void setCodDocReembolso(String codDocReembolso) {
        this.codDocReembolso = codDocReembolso;
    }

    /**
     * @return the estabDocReembolso
     */
    public String getEstabDocReembolso() {
        return estabDocReembolso;
    }

    /**
     * @param estabDocReembolso the estabDocReembolso to set
     */
    public void setEstabDocReembolso(String estabDocReembolso) {
        this.estabDocReembolso = estabDocReembolso;
    }

    /**
     * @return the ptoEmiDocReembolso
     */
    public String getPtoEmiDocReembolso() {
        return ptoEmiDocReembolso;
    }

    /**
     * @param ptoEmiDocReembolso the ptoEmiDocReembolso to set
     */
    public void setPtoEmiDocReembolso(String ptoEmiDocReembolso) {
        this.ptoEmiDocReembolso = ptoEmiDocReembolso;
    }

    /**
     * @return the secuencialDocReembolso
     */
    public String getSecuencialDocReembolso() {
        return secuencialDocReembolso;
    }

    /**
     * @param secuencialDocReembolso the secuencialDocReembolso to set
     */
    public void setSecuencialDocReembolso(String secuencialDocReembolso) {
        this.secuencialDocReembolso = secuencialDocReembolso;
    }

    /**
     * @return the fechaEmisionDocReembolso
     */
    public String getFechaEmisionDocReembolso() {
        return fechaEmisionDocReembolso;
    }

    /**
     * @param fechaEmisionDocReembolso the fechaEmisionDocReembolso to set
     */
    public void setFechaEmisionDocReembolso(String fechaEmisionDocReembolso) {
        this.fechaEmisionDocReembolso = fechaEmisionDocReembolso;
    }

    /**
     * @return the numeroautorizacionDocReemb
     */
    public String getNumeroautorizacionDocReemb() {
        return numeroautorizacionDocReemb;
    }

    /**
     * @param numeroautorizacionDocReemb the numeroautorizacionDocReemb to set
     */
    public void setNumeroautorizacionDocReemb(String numeroautorizacionDocReemb) {
        this.numeroautorizacionDocReemb = numeroautorizacionDocReemb;
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

}
