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
public class Destinatario {
    private String identificacionDestinatario;
    private String razonSocialDestinatario;
    private String dirDestinatario;
    private String motivoTraslado;
    private String docAduaneroUnico;
    private String codEstabDestino;
    private String ruta;
    private String codDocSustento;
    private String numDocSustento;
    private String numAutDocSustento;
    private String fechaEmisionDocSustento;
    private List<DetalleGuiaRemision> detalles;

    /**
     * @return the identificacionDestinatario
     */
    public String getIdentificacionDestinatario() {
        return identificacionDestinatario;
    }

    /**
     * @param identificacionDestinatario the identificacionDestinatario to set
     */
    public void setIdentificacionDestinatario(String identificacionDestinatario) {
        this.identificacionDestinatario = identificacionDestinatario;
    }

    /**
     * @return the dirDestinatario
     */
    public String getDirDestinatario() {
        return dirDestinatario;
    }

    /**
     * @param dirDestinatario the dirDestinatario to set
     */
    public void setDirDestinatario(String dirDestinatario) {
        this.dirDestinatario = dirDestinatario;
    }

    /**
     * @return the motivoTraslado
     */
    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    /**
     * @param motivoTraslado the motivoTraslado to set
     */
    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    /**
     * @return the docAduaneroUnico
     */
    public String getDocAduaneroUnico() {
        return docAduaneroUnico;
    }

    /**
     * @param docAduaneroUnico the docAduaneroUnico to set
     */
    public void setDocAduaneroUnico(String docAduaneroUnico) {
        this.docAduaneroUnico = docAduaneroUnico;
    }

    /**
     * @return the codEstabDestino
     */
    public String getCodEstabDestino() {
        return codEstabDestino;
    }

    /**
     * @param codEstabDestino the codEstabDestino to set
     */
    public void setCodEstabDestino(String codEstabDestino) {
        this.codEstabDestino = codEstabDestino;
    }

    /**
     * @return the ruta
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * @param ruta the ruta to set
     */
    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    /**
     * @return the codDocSustento
     */
    public String getCodDocSustento() {
        return codDocSustento;
    }

    /**
     * @param codDocSustento the codDocSustento to set
     */
    public void setCodDocSustento(String codDocSustento) {
        this.codDocSustento = codDocSustento;
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
     * @return the numAutDocSustento
     */
    public String getNumAutDocSustento() {
        return numAutDocSustento;
    }

    /**
     * @param numAutDocSustento the numAutDocSustento to set
     */
    public void setNumAutDocSustento(String numAutDocSustento) {
        this.numAutDocSustento = numAutDocSustento;
    }

    /**
     * @return the fechaEmisionDocSustento
     */
    public String getFechaEmisionDocSustento() {
        return fechaEmisionDocSustento;
    }

    /**
     * @param fechaEmisionDocSustento the fechaEmisionDocSustento to set
     */
    public void setFechaEmisionDocSustento(String fechaEmisionDocSustento) {
        this.fechaEmisionDocSustento = fechaEmisionDocSustento;
    }

    /**
     * @return the detalles
     */
    public List<DetalleGuiaRemision> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleGuiaRemision> detalles) {
        this.detalles = detalles;
    }

    /**
     * @return the razonSocialDestinatario
     */
    public String getRazonSocialDestinatario() {
        return razonSocialDestinatario;
    }

    /**
     * @param razonSocialDestinatario the razonSocialDestinatario to set
     */
    public void setRazonSocialDestinatario(String razonSocialDestinatario) {
        this.razonSocialDestinatario = razonSocialDestinatario;
    }
}
