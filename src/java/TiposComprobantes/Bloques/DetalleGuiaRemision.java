/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TiposComprobantes.Bloques;

import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class DetalleGuiaRemision {
    private String codigoInterno;
    private String codigoAdicional;
    private String descripcion;
    private String cantidad;
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
