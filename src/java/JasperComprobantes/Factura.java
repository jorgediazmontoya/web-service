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
public class Factura extends ComprobanteGeneral {

    private String guiaRemision;
    private String razonSocialComprador;
    private String identificacionComprador;
    private String propina;
    private String importeTotal;
    private String direccionComprador;
    private List<DetalleFactura> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;
    private List<ReembolsoFactura> reembolso;
    private String totalSinSubsidio;
    private String ahorroSubsidio;

    public Factura() {
        detalles = new ArrayList();
        infoAdicional = new ArrayList();
        pagos = new ArrayList();
        reembolso = new ArrayList();
    }

    /**
     * @return the guiaRemision
     */
    public String getGuiaRemision() {
        return guiaRemision;
    }

    /**
     * @param guiaRemision the guiaRemision to set
     */
    public void setGuiaRemision(String guiaRemision) {
        this.guiaRemision = guiaRemision;
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
     * @return the propina
     */
    public String getPropina() {
        return propina;
    }

    /**
     * @param propina the propina to set
     */
    public void setPropina(String propina) {
        this.propina = propina;
    }

    /**
     * @return the importeTotal
     */
    public String getImporteTotal() {
        return importeTotal;
    }

    /**
     * @param importeTotal the importeTotal to set
     */
    public void setImporteTotal(String importeTotal) {
        this.importeTotal = importeTotal;
    }

    /**
     * @return the detalles
     */
    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    /**
     * @return the infoAdicional
     */
    public List<CampoAdicional> getInfoAdicional() {
        return infoAdicional;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }

    /**
     * @param infoAdicional the infoAdicional to set
     */
    public void setInfoAdicional(List<CampoAdicional> infoAdicional) {
        this.infoAdicional = infoAdicional;
    }

    /**
     * @return the pagos
     */
    public List<Pago> getPagos() {
        return pagos;
    }

    /**
     * @param pagos the pagos to set
     */
    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }

    /**
     * @return the reembolso
     */
    public List<ReembolsoFactura> getReembolso() {
        return reembolso;
    }

    /**
     * @param reembolso the reembolso to set
     */
    public void setReembolso(List<ReembolsoFactura> reembolso) {
        this.reembolso = reembolso;
    }

    /**
     * @return the direccionComprador
     */
    public String getDireccionComprador() {
        return direccionComprador;
    }

    /**
     * @param direccionComprador the direccionComprador to set
     */
    public void setDireccionComprador(String direccionComprador) {
        this.direccionComprador = direccionComprador;
    }

    /**
     * @return the totalSinSubsidio
     */
    public String getTotalSinSubsidio() {
        return totalSinSubsidio;
    }

    /**
     * @param totalSinSubsidio the totalSinSubsidio to set
     */
    public void setTotalSinSubsidio(String totalSinSubsidio) {
        this.totalSinSubsidio = totalSinSubsidio;
    }

    /**
     * @return the ahorroSubsidio
     */
    public String getAhorroSubsidio() {
        return ahorroSubsidio;
    }

    /**
     * @param ahorroSubsidio the ahorroSubsidio to set
     */
    public void setAhorroSubsidio(String ahorroSubsidio) {
        this.ahorroSubsidio = ahorroSubsidio;
    }

}
