/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes.Bloques;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class DetalleFactura {

    private String codigoPrincipal;
    private String codigoAuxiliar;
    private String descripcion;
    private String cantidad;
    private String precioUnitario;
    private String descuento;
    private String precioTotalSinImpuesto;
    private List<DetalleAdicional> detalleAdicional;
    private List<Impuesto> impuestos;


    /**
     * @return the codigoPrincipal
     */
    public String getCodigoPrincipal() {
        return codigoPrincipal;
    }

    /**
     * @param codigoPrincipal the codigoPrincipal to set
     */
    public void setCodigoPrincipal(String codigoPrincipal) {
        this.codigoPrincipal = codigoPrincipal;
    }

    /**
     * @return the codigoAuxiliar
     */
    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    /**
     * @param codigoAuxiliar the codigoAuxiliar to set
     */
    public void setCodigoAuxiliar(String codigoAuxiliar) {
        this.codigoAuxiliar = codigoAuxiliar;
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
     * @return the detalleAdicional
     */
    public List<DetalleAdicional> getDetalleAdicional() {
        return detalleAdicional;
    }

    /**
     * @return the impuestos
     */
    public List<Impuesto> getImpuestos() {
        return impuestos;
    }

    /**
     * @param detalleAdicional the detalleAdicional to set
     */
    public void setDetalleAdicional(List<DetalleAdicional> detalleAdicional) {
        this.detalleAdicional = detalleAdicional;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(List<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

}
