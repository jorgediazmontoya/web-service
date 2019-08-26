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
public class GuiaRemision extends ComprobanteGeneral {

    private String dirPartida;
    private String razonSocialTransportista;
    private String rucTransportista;
    private String fechaIniTransporte;
    private String fechaFinTransporte;
    private String placa;
    private String identificacionDestinatario;
    private String razonSocialDestinatario;
    private String dirDestinatario;
    private String motivoTraslado;
    private String docAduaneroUnico;
    private String codEstabDestino;
    private String ruta;
    private String docSustento;
    private String numDocSustento;
    private String numAutDocSustento;
    private String fechaEmisionDocSustento;
    private List<DetalleGuiaRemision> detalles;
    private List<CampoAdicional> infoAdicional;

    public GuiaRemision() {
        detalles = new ArrayList();
        infoAdicional = new ArrayList();
    }

    /**
     * @return the dirPartida
     */
    public String getDirPartida() {
        return dirPartida;
    }

    /**
     * @param dirPartida the dirPartida to set
     */
    public void setDirPartida(String dirPartida) {
        this.dirPartida = dirPartida;
    }

    /**
     * @return the razonSocialTransportista
     */
    public String getRazonSocialTransportista() {
        return razonSocialTransportista;
    }

    /**
     * @param razonSocialTransportista the razonSocialTransportista to set
     */
    public void setRazonSocialTransportista(String razonSocialTransportista) {
        this.razonSocialTransportista = razonSocialTransportista;
    }

    /**
     * @return the rucTransportista
     */
    public String getRucTransportista() {
        return rucTransportista;
    }

    /**
     * @param rucTransportista the rucTransportista to set
     */
    public void setRucTransportista(String rucTransportista) {
        this.rucTransportista = rucTransportista;
    }

    /**
     * @return the fechaIniTransporte
     */
    public String getFechaIniTransporte() {
        return fechaIniTransporte;
    }

    /**
     * @param fechaIniTransporte the fechaIniTransporte to set
     */
    public void setFechaIniTransporte(String fechaIniTransporte) {
        this.fechaIniTransporte = fechaIniTransporte;
    }

    /**
     * @return the fechaFinTransporte
     */
    public String getFechaFinTransporte() {
        return fechaFinTransporte;
    }

    /**
     * @param fechaFinTransporte the fechaFinTransporte to set
     */
    public void setFechaFinTransporte(String fechaFinTransporte) {
        this.fechaFinTransporte = fechaFinTransporte;
    }

    /**
     * @return the placa
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * @param placa the placa to set
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * @return the infoAdicional
     */
    public List<CampoAdicional> getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(List<CampoAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

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
    public String getDocSustento() {
        return docSustento;
    }

    /**
     * @param codDocSustento the codDocSustento to set
     */
    public void setDocSustento(String docSustento) {
        this.docSustento = docSustento;
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

}
