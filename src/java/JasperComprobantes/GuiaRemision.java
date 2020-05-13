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

    private List<Destinatario> destinatarios;

    private List<CampoAdicional> infoAdicional;

    public GuiaRemision() {
        destinatarios = new ArrayList();
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
     * @return the destinatarios
     */
    public List<Destinatario> getDestinatarios() {
        return destinatarios;
    }

    /**
     * @param destinatarios the destinatarios to set
     */
    public void setDestinatarios(List<Destinatario> destinatarios) {
        this.destinatarios = destinatarios;
    }
}
