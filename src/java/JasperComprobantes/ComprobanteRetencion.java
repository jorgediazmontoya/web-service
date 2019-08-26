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
public class ComprobanteRetencion extends ComprobanteGeneral {
    private String tipoIdentificacionSujetoRetenido;
    private String razonSocialSujetoRetenido;
    private String identificacionSujetoRetenido;
    private String periodoFiscal;
    private List<ImpuestoComprobanteRetencion> impuestos;
    private List<CampoAdicional> infoAdicional;

    public ComprobanteRetencion() {
        impuestos = new ArrayList();
        infoAdicional = new ArrayList();
    }
    
    public String getTipoIdentificacionSujetoRetenido() {
        return tipoIdentificacionSujetoRetenido;
    }

    /**
     * @param tipoIdentificacionSujetoRetenido the
     * tipoIdentificacionSujetoRetenido to set
     */
    public void setTipoIdentificacionSujetoRetenido(String tipoIdentificacionSujetoRetenido) {
        this.tipoIdentificacionSujetoRetenido = tipoIdentificacionSujetoRetenido;
    }

    /**
     * @return the razonSocialSujetoRetenido
     */
    public String getRazonSocialSujetoRetenido() {
        return razonSocialSujetoRetenido;
    }

    /**
     * @param razonSocialSujetoRetenido the razonSocialSujetoRetenido to set
     */
    public void setRazonSocialSujetoRetenido(String razonSocialSujetoRetenido) {
        this.razonSocialSujetoRetenido = razonSocialSujetoRetenido;
    }

    /**
     * @return the identificacionSujetoRetenido
     */
    public String getIdentificacionSujetoRetenido() {
        return identificacionSujetoRetenido;
    }

    /**
     * @param identificacionSujetoRetenido the identificacionSujetoRetenido to
     * set
     */
    public void setIdentificacionSujetoRetenido(String identificacionSujetoRetenido) {
        this.identificacionSujetoRetenido = identificacionSujetoRetenido;
    }

    /**
     * @return the periodoFiscal
     */
    public String getPeriodoFiscal() {
        return periodoFiscal;
    }

    /**
     * @param periodoFiscal the periodoFiscal to set
     */
    public void setPeriodoFiscal(String periodoFiscal) {
        this.periodoFiscal = periodoFiscal;
    }

    /**
     * @return the impuestos
     */
    public List<ImpuestoComprobanteRetencion> getImpuestos() {
        return impuestos;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(List<ImpuestoComprobanteRetencion> impuestos) {
        this.impuestos = impuestos;
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
