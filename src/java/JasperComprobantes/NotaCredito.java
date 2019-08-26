/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperComprobantes;

import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class NotaCredito extends ComprobanteGeneral {

    private String tipoIdentificacionComprador;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String rise;
    private String comprobanteModificado;
    private String numDocModificado;
    private String fechaEmisionDocSustento;
    private String valorModificacion;
    private String motivo;
    private List<DetalleNotaCredito> detalles;
    private List<CampoAdicional> infoAdicional;

    
    public NotaCredito(){
        detalles = new ArrayList();
        infoAdicional = new ArrayList();
    }
    /**
     * @return the tipoIdentificacionComprador
     */
    public String getTipoIdentificacionComprador() {
        return tipoIdentificacionComprador;
    }

    /**
     * @param tipoIdentificacionComprador the tipoIdentificacionComprador to set
     */
    public void setTipoIdentificacionComprador(String tipoIdentificacionComprador) {
        this.tipoIdentificacionComprador = tipoIdentificacionComprador;
    }

    /**
     * @return the razonSocialComprador
     */
    public String getRazonSocialComprador() {
        return razonSocialComprador;
    }

    /**
     * @param razonSocialComprador the razonSocialComprador to set
     */
    public void setRazonSocialComprador(String razonSocialComprador) {
        this.razonSocialComprador = razonSocialComprador;
    }

    /**
     * @return the identificacionComprador
     */
    public String getIdentificacionComprador() {
        return identificacionComprador;
    }

    /**
     * @param identificacionComprador the identificacionComprador to set
     */
    public void setIdentificacionComprador(String identificacionComprador) {
        this.identificacionComprador = identificacionComprador;
    }

    /**
     * @return the rise
     */
    public String getRise() {
        return rise;
    }

    /**
     * @param rise the rise to set
     */
    public void setRise(String rise) {
        this.rise = rise;
    }

    /**
     * @return the codDocModificado
     */
    public String getComprobanteModificado() {
        return comprobanteModificado;
    }

    /**
     * @param codDocModificado the codDocModificado to set
     */
    public void setComprobanteModificado(String comprobanteModificado) {
        this.comprobanteModificado = comprobanteModificado;
    }

    /**
     * @return the numDocModificado
     */
    public String getNumDocModificado() {
        return numDocModificado;
    }

    /**
     * @param numDocModificado the numDocModificado to set
     */
    public void setNumDocModificado(String numDocModificado) {
        this.numDocModificado = numDocModificado;
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
     * @return the valorModificacion
     */
    public String getValorModificacion() {
        return valorModificacion;
    }

    /**
     * @param valorModificacion the valorModificacion to set
     */
    public void setValorModificacion(String valorModificacion) {
        this.valorModificacion = valorModificacion;
    }

    /**
     * @return the motivo
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * @param motivo the motivo to set
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * @return the detalles
     */
    public List<DetalleNotaCredito> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleNotaCredito> detalles) {
        this.detalles = detalles;
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

}
