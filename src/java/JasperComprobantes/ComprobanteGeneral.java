/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperComprobantes;

/**
 *
 * @author Yoelvys
 */
public class ComprobanteGeneral {

    private String dirLogo;
    private String ambiente;
    private String tipoEmision;
    private String razonSocial;
    private String nombreComercial;
    private String ruc;
    private String claveAcc;
    private String dirMatriz;
    private String regimenMicroempresas;
    private String agenteRetencion;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String fechaEmision;
    private String numDocumento;
    private String numAutorizacion;
    private String subTotal12 = "0.00";
    private String subTotal0 = "0.00";
    private String subTotalNoObjetoIVA = "0.00";
    private String subTotalExentoIVA = "0.00";
    private String ICE = "";
    private String IRBPNR = "";
    private String IVA12 = "0.00";
    private String subTotalSinImpuesto = "0.00";
    private String totalDescuento = "0.00";
    private String importeTotal = "0.00";

    /**
     * @return the ambiente
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * @param ambiente the ambiente to set
     */
    public void setAmbiente(String ambiente) {
        this.ambiente = ambiente;
    }

    /**
     * @return the tipoEmision
     */
    public String getTipoEmision() {
        return tipoEmision;
    }

    /**
     * @param tipoEmision the tipoEmision to set
     */
    public void setTipoEmision(String tipoEmision) {
        this.tipoEmision = tipoEmision;
    }

    /**
     * @return the razonSocial
     */
    public String getRazonSocial() {
        return razonSocial;
    }

    /**
     * @param razonSocial the razonSocial to set
     */
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    /**
     * @return the nombreComercial
     */
    public String getNombreComercial() {
        return nombreComercial;
    }

    /**
     * @param nombreComercial the nombreComercial to set
     */
    public void setNombreComercial(String nombreComercial) {
        this.nombreComercial = nombreComercial;
    }

    /**
     * @return the ruc
     */
    public String getRuc() {
        return ruc;
    }

    /**
     * @param ruc the ruc to set
     */
    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    /**
     * @return the dirMatriz
     */
    public String getDirMatriz() {
        return dirMatriz;
    }

    /**
     * @param dirMatriz the dirMatriz to set
     */
    public void setDirMatriz(String dirMatriz) {
        this.dirMatriz = dirMatriz;
    }

    /**
     * @return the dirEstablecimiento
     */
    public String getDirEstablecimiento() {
        return dirEstablecimiento;
    }

    /**
     * @param dirEstablecimiento the dirEstablecimiento to set
     */
    public void setDirEstablecimiento(String dirEstablecimiento) {
        this.dirEstablecimiento = dirEstablecimiento;
    }

    /**
     * @return the contribuyenteEspecial
     */
    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    /**
     * @param contribuyenteEspecial the contribuyenteEspecial to set
     */
    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    /**
     * @return the obligadoContabilidad
     */
    public String getObligadoContabilidad() {
        return obligadoContabilidad;
    }

    /**
     * @param obligadoContabilidad the obligadoContabilidad to set
     */
    public void setObligadoContabilidad(String obligadoContabilidad) {
        this.obligadoContabilidad = obligadoContabilidad;
    }

    /**
     * @return the fechaEmision
     */
    public String getFechaEmision() {
        return fechaEmision;
    }

    /**
     * @param fechaEmision the fechaEmision to set
     */
    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    /**
     * @return the claveAcc
     */
    public String getClaveAcc() {
        return claveAcc;
    }

    /**
     * @param claveAcc the claveAcc to set
     */
    public void setClaveAcc(String claveAcc) {
        this.claveAcc = claveAcc;
    }

    /**
     * @return the numDocumento
     */
    public String getNumDocumento() {
        return numDocumento;
    }

    /**
     * @param numDocumento the numDocumento to set
     */
    public void setNumDocumento(String numDocumento) {
        this.numDocumento = numDocumento;
    }

    /**
     * @return the numAutorizacion
     */
    public String getNumAutorizacion() {
        return numAutorizacion;
    }

    /**
     * @param numAutorizacion the numAutorizacion to set
     */
    public void setNumAutorizacion(String numAutorizacion) {
        this.numAutorizacion = numAutorizacion;
    }

    /**
     * @return the dirLogo
     */
    public String getDirLogo() {
        return dirLogo;
    }

    /**
     * @param dirLogo the dirLogo to set
     */
    public void setDirLogo(String dirLogo) {
        this.dirLogo = dirLogo;
    }

    /**
     * @return the subTotal12
     */
    public String getSubTotal12() {
        return subTotal12;
    }

    /**
     * @param subTotal12 the subTotal12 to set
     */
    public void setSubTotal12(String subTotal12) {
        this.subTotal12 = subTotal12;
    }

    /**
     * @return the subTotal0
     */
    public String getSubTotal0() {
        return subTotal0;
    }

    /**
     * @param subTotal0 the subTotal0 to set
     */
    public void setSubTotal0(String subTotal0) {
        this.subTotal0 = subTotal0;
    }

    /**
     * @return the subTotalNoObjetoIVA
     */
    public String getSubTotalNoObjetoIVA() {
        return subTotalNoObjetoIVA;
    }

    /**
     * @param subTotalNoObjetoIVA the subTotalNoObjetoIVA to set
     */
    public void setSubTotalNoObjetoIVA(String subTotalNoObjetoIVA) {
        this.subTotalNoObjetoIVA = subTotalNoObjetoIVA;
    }

    /**
     * @return the subTotalExentoIVA
     */
    public String getSubTotalExentoIVA() {
        return subTotalExentoIVA;
    }

    /**
     * @param subTotalExentoIVA the subTotalExentoIVA to set
     */
    public void setSubTotalExentoIVA(String subTotalExentoIVA) {
        this.subTotalExentoIVA = subTotalExentoIVA;
    }

    /**
     * @return the ICE
     */
    public String getICE() {
        return ICE;
    }

    /**
     * @param ICE the ICE to set
     */
    public void setICE(String ICE) {
        this.ICE = ICE;
    }

    /**
     * @return the IRBPNR
     */
    public String getIRBPNR() {
        return IRBPNR;
    }

    /**
     * @param IRBPNR the IRBPNR to set
     */
    public void setIRBPNR(String IRBPNR) {
        this.IRBPNR = IRBPNR;
    }

    /**
     * @return the IVA12
     */
    public String getIVA12() {
        return IVA12;
    }

    /**
     * @param IVA12 the IVA12 to set
     */
    public void setIVA12(String IVA12) {
        this.IVA12 = IVA12;
    }

    /**
     * @return the subTotalSinImpuesto
     */
    public String getSubTotalSinImpuesto() {
        return subTotalSinImpuesto;
    }

    /**
     * @param subTotalSinImpuesto the subTotalSinImpuesto to set
     */
    public void setSubTotalSinImpuesto(String subTotalSinImpuesto) {
        this.subTotalSinImpuesto = subTotalSinImpuesto;
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
     * @return the totalDescuento
     */
    public String getTotalDescuento() {
        return totalDescuento;
    }

    /**
     * @param totalDescuento the totalDescuento to set
     */
    public void setTotalDescuento(String totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    /**
     * @return the regimenMicroempresas
     */
    public String getRegimenMicroempresas() {
        return regimenMicroempresas;
    }

    /**
     * @param regimenMicroempresas the regimenMicroempresas to set
     */
    public void setRegimenMicroempresas(String regimenMicroempresas) {
        this.regimenMicroempresas = regimenMicroempresas;
    }

    /**
     * @return the agenteRetencion
     */
    public String getAgenteRetencion() {
        return agenteRetencion;
    }

    /**
     * @param agenteRetencion the agenteRetencion to set
     */
    public void setAgenteRetencion(String agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

}
