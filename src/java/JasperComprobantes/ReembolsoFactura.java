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
public class ReembolsoFactura {

    private String identificacionProveedorReembolso;
    private String tipoDocumento;
    private String noDocumento;
    private String fechaEmisionDocReembolso;
    private String impuesto;
    private String porcentaje;
    private double baseImponible;
    private double valorImpuesto;
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
     * @return the impuesto
     */
    public String getImpuesto() {
        return impuesto;
    }

    /**
     * @param impuesto the impuesto to set
     */
    public void setImpuesto(String impuesto) {
        this.impuesto = impuesto;
    }

    /**
     * @return the porcentaje
     */
    public String getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
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

   
}
