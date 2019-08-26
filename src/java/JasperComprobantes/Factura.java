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
    private List<DetalleFactura> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;

    public Factura() {
        detalles = new ArrayList();
        infoAdicional = new ArrayList();
        pagos = new ArrayList();
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

}
