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
public class LiquidacionCompra extends ComprobanteGeneral {

    private String razonSocialProveedor;
    private String identificacionProveedor;
    private String importeTotal;
    private String direccionProveedor;
    private List<DetalleLiquidacionCompra> detalles;
    private List<CampoAdicional> infoAdicional;
    private List<Pago> pagos;

    public LiquidacionCompra() {
        detalles = new ArrayList();
        infoAdicional = new ArrayList();
        pagos = new ArrayList();
    }

    /**
     * @return the razonSocialProveedor
     */
    public String getRazonSocialProveedor() {
        return razonSocialProveedor;
    }

    /**
     * @param razonSocialProveedor the razonSocialProveedor to set
     */
    public void setRazonSocialProveedor(String razonSocialProveedor) {
        this.razonSocialProveedor = razonSocialProveedor;
    }

    /**
     * @return the identificacionProveedor
     */
    public String getIdentificacionProveedor() {
        return identificacionProveedor;
    }

    /**
     * @param identificacionProveedor the identificacionProveedor to set
     */
    public void setIdentificacionProveedor(String identificacionProveedor) {
        this.identificacionProveedor = identificacionProveedor;
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
    public List<DetalleLiquidacionCompra> getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(List<DetalleLiquidacionCompra> detalles) {
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
     * @return the direccionProveedor
     */
    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    /**
     * @param direccionProveedor the direccionProveedor to set
     */
    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

   

}
