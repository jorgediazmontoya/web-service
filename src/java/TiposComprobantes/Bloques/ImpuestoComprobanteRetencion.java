/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package TiposComprobantes.Bloques;

/**
 *
 * @author Yoelvys
 */
public class ImpuestoComprobanteRetencion {
    private String codigo;
    private String codigoRetencion;
    private String baseImponible;
    private String porcentajeRetener;
    private String valorRetenido;
    private String codDocSustento;
    private String numDocSustento;
    private String fechaEmisionDocSustento;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoRetencion
     */
    public String getCodigoRetencion() {
        return codigoRetencion;
    }

    /**
     * @param codigoRetencion the codigoRetencion to set
     */
    public void setCodigoRetencion(String codigoRetencion) {
        this.codigoRetencion = codigoRetencion;
    }

    /**
     * @return the baseImponible
     */
    public String getBaseImponible() {
        return baseImponible;
    }

    /**
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(String baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * @return the porcentajeRetener
     */
    public String getPorcentajeRetener() {
        return porcentajeRetener;
    }

    /**
     * @param porcentajeRetener the porcentajeRetener to set
     */
    public void setPorcentajeRetener(String porcentajeRetener) {
        this.porcentajeRetener = porcentajeRetener;
    }

    /**
     * @return the valorRetenido
     */
    public String getValorRetenido() {
        return valorRetenido;
    }

    /**
     * @param valorRetenido the valorRetenido to set
     */
    public void setValorRetenido(String valorRetenido) {
        this.valorRetenido = valorRetenido;
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
    
}
