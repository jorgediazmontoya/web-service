/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package JasperComprobantes;

import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class DetalleNotaCredito {
    private String codigoInterno;
    private String codigoAdicional;
    private String descripcion;
    private String cantidad;
     private String precioUnitario;
     private String descuento;
     private String precioTotalSinImpuesto;
     private List<DetalleAdicional> detallesAdicionales;

    /**
     * @return the codigoInterno
     */
    public String getCodigoInterno() {
        return codigoInterno;
    }

    /**
     * @param codigoInterno the codigoInterno to set
     */
    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    /**
     * @return the codigoAdicional
     */
    public String getCodigoAdicional() {
        return codigoAdicional;
    }

    /**
     * @param codigoAdicional the codigoAdicional to set
     */
    public void setCodigoAdicional(String codigoAdicional) {
        this.codigoAdicional = codigoAdicional;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cantidad
     */
    public String getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the precioUnitario
     */
    public String getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * @param precioUnitario the precioUnitario to set
     */
    public void setPrecioUnitario(String precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    /**
     * @return the descuento
     */
    public String getDescuento() {
        return descuento;
    }

    /**
     * @param descuento the descuento to set
     */
    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    /**
     * @return the precioTotalSinImpuesto
     */
    public String getPrecioTotalSinImpuesto() {
        return precioTotalSinImpuesto;
    }

    /**
     * @param precioTotalSinImpuesto the precioTotalSinImpuesto to set
     */
    public void setPrecioTotalSinImpuesto(String precioTotalSinImpuesto) {
        this.precioTotalSinImpuesto = precioTotalSinImpuesto;
    }

    /**
     * @return the detallesAdicionales
     */
    public List<DetalleAdicional> getDetallesAdicionales() {
        return detallesAdicionales;
    }

    /**
     * @param detallesAdicionales the detallesAdicionales to set
     */
    public void setDetallesAdicionales(List<DetalleAdicional> detallesAdicionales) {
        this.detallesAdicionales = detallesAdicionales;
    }


}
