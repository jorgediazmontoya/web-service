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
public class Respuesta {

    private String estadoComprobante;
    private String numeroAutorizacion;
    private String claveAcceso;
    private List<MensajeGenerado> mensajes;
    private String comprobanteID;
    private String fechaAutorizacion;

    public Respuesta() {
        mensajes = new ArrayList<MensajeGenerado>();
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

    public void addMensaje(MensajeGenerado mensaje) {
        mensajes.add(mensaje);
    }

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
     * @return the numeroAutorizacion
     */
    public String getNumeroAutorizacion() {
        return numeroAutorizacion;
    }

    /**
     * @param numeroAutorizacion the numeroAutorizacion to set
     */
    public void setNumeroAutorizacion(String numeroAutorizacion) {
        this.numeroAutorizacion = numeroAutorizacion;
    }

    /**
     * @return the comprobanteID
     */
    public String getComprobanteID() {
        return comprobanteID;
    }

    /**
     * @param comprobanteID the comprobanteID to set
     */
    public void setComprobanteID(String comprobanteID) {
        this.comprobanteID = comprobanteID;
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

  
    
}
