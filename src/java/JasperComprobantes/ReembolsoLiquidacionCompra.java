/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperComprobantes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class ReembolsoLiquidacionCompra {

    private String identificacionProveedorReembolso;
    private String tipoDocumento;
    private String noDocumento;
    private String fechaEmisionDocReembolso;
    private double baseImponible;
    private double valorImpuesto;
    private double baseImponibleSinIva;
    private double total;  

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
     * @return the tipoDocumento
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * @param tipoDocumento the tipoDocumento to set
     */
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    /**
     * @return the noDocumento
     */
    public String getNoDocumento() {
        return noDocumento;
    }

    /**
     * @param noDocumento the noDocumento to set
     */
    public void setNoDocumento(String noDocumento) {
        this.noDocumento = noDocumento;
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
     * @return the baseImponible
     */
    public double getBaseImponible() {
        return baseImponible;
    }

    /**
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(double baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * @return the valorImpuesto
     */
    public double getValorImpuesto() {
        return valorImpuesto;
    }

    /**
     * @param valorImpuesto the valorImpuesto to set
     */
    public void setValorImpuesto(double valorImpuesto) {
        this.valorImpuesto = valorImpuesto;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(double total) {
        this.total = total;
    }

    /**
     * @return the baseImponibleSinIva
     */
    public double getBaseImponibleSinIva() {
        return baseImponibleSinIva;
    }

    /**
     * @param baseImponibleSinIva the baseImponibleSinIva to set
     */
    public void setBaseImponibleSinIva(double baseImponibleSinIva) {
        this.baseImponibleSinIva = baseImponibleSinIva;
    }

   
}
