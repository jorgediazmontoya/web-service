/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Respuesta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class RespuestaComprobanteConsultado {

    private String estadoComprobante;
    private String claveAcceso;
    private String comprobante;
    private String fechaAutorizacion;
    private List<MensajeGenerado> mensajes;

    /**
     * @return the estadoComprobante
     */
    public String getEstadoComprobante() {
        return estadoComprobante;
    }

    /**
     * @param estadoComprobante the estadoComprobante to set
     */
    public void setEstadoComprobante(String estadoComprobante) {
        this.estadoComprobante = estadoComprobante;
    }

    /**
     * @return the claveAcceso
     */
    public String getClaveAcceso() {
        return claveAcceso;
    }

    /**
     * @param claveAcceso the claveAcceso to set
     */
    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    /**
     * @return the comprobante
     */
    public String getComprobante() {
        return comprobante;
    }

    /**
     * @param comprobante the comprobante to set
     */
    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }

    /**
     * @return the fechaAutorizacion
     */
    public String getFechaAutorizacion() {
        return fechaAutorizacion;
    }

    /**
     * @param fechaAutorizacion the fechaAutorizacion to set
     */
    public void setFechaAutorizacion(String fechaAutorizacion) {
        this.fechaAutorizacion = fechaAutorizacion;
    }

    /**
     * @return the mensajes
     */
    public List<MensajeGenerado> getMensajes() {
        return mensajes;
    }

    /**
     * @param mensajes the mensajes to set
     */
    public void setMensajes(List<MensajeGenerado> mensajes) {
        this.mensajes = mensajes;
    }


   
    
}
