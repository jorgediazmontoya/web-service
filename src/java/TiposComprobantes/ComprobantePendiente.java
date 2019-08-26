/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */
package TiposComprobantes;

import Util.ConfigAplicacion;
import Util.ConfigCorreo;

/**
 *
 * @author Yoelvys
 */
public class ComprobantePendiente {

    private ConfigAplicacion configAplicacion;
    private ConfigCorreo configCorreo;
    private String clavAcc;
    private String codDoc;
    private String ruc;
    private String ambiente;
    private String establecimiento;
    private String ptoEmision;
    private String secuencial;
    private String tipoEmision;
    private String fechaEmision;
    private boolean enviarEmail;
    private String otrosDestinatarios;

    public String claveAcceso() {
        String claveAcceso = getFechaEmision().replaceAll("/", "");
        claveAcceso += getCodDoc();
        claveAcceso += getRuc();
        claveAcceso += getAmbiente();
        String serie = getEstablecimiento() + getPtoEmision();
        claveAcceso += serie;
        claveAcceso += getSecuencial();
        claveAcceso += "12345678";
        claveAcceso += getTipoEmision();
        claveAcceso += modulo11(claveAcceso);

        return claveAcceso;
    }

    private String modulo11(String claveAcceso) {
        int[] multiplos = {2, 3, 4, 5, 6, 7};
        int i = 0;
        int cantidad = claveAcceso.length();
        int total = 0;
        while (cantidad > 0) {
            total += Integer.parseInt(claveAcceso.substring(cantidad - 1, cantidad)) * multiplos[i];
            i++;
            i = i % 6;
            cantidad--;
        }
        int modulo11 = 11 - total % 11;
        if (modulo11 == 11) {
            modulo11 = 0;
        } else if (modulo11 == 10) {
            modulo11 = 1;
        }

        return Integer.toString(modulo11);
    }

    /**
     * @return the codDoc
     */
    public String getCodDoc() {
        return codDoc;
    }

    /**
     * @param codDoc the codDoc to set
     */
    public void setCodDoc(String codDoc) {
        this.codDoc = codDoc;
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
     * @return the establecimiento
     */
    public String getEstablecimiento() {
        return establecimiento;
    }

    /**
     * @param establecimiento the establecimiento to set
     */
    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    /**
     * @return the ptoEmision
     */
    public String getPtoEmision() {
        return ptoEmision;
    }

    /**
     * @param ptoEmision the ptoEmision to set
     */
    public void setPtoEmision(String ptoEmision) {
        this.ptoEmision = ptoEmision;
    }

    /**
     * @return the secuencial
     */
    public String getSecuencial() {
        return secuencial;
    }

    /**
     * @param secuencial the secuencial to set
     */
    public void setSecuencial(String secuencial) {
        this.secuencial = secuencial;
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
     * @return the configAplicacion
     */
    public ConfigAplicacion getConfigAplicacion() {
        return configAplicacion;
    }

    /**
     * @param configAplicacion the configAplicacion to set
     */
    public void setConfigAplicacion(ConfigAplicacion configAplicacion) {
        this.configAplicacion = configAplicacion;
    }

    /**
     * @return the configCorreo
     */
    public ConfigCorreo getConfigCorreo() {
        return configCorreo;
    }

    /**
     * @param configCorreo the configCorreo to set
     */
    public void setConfigCorreo(ConfigCorreo configCorreo) {
        this.configCorreo = configCorreo;
    }

    /**
     * @return the clavAcc
     */
    public String getClavAcc() {
        return clavAcc;
    }

    /**
     * @param clavAcc the clavAcc to set
     */
    public void setClavAcc(String clavAcc) {
        this.clavAcc = clavAcc;
    }



    /**
     * @return the otrosDestinatarios
     */
    public String getOtrosDestinatarios() {
        return otrosDestinatarios;
    }

    /**
     * @param otrosDestinatarios the otrosDestinatarios to set
     */
    public void setOtrosDestinatarios(String otrosDestinatarios) {
        this.otrosDestinatarios = otrosDestinatarios;
    }

    /**
     * @return the enviarEmail
     */
    public boolean isEnviarEmail() {
        return enviarEmail;
    }

    /**
     * @param enviarEmail the enviarEmail to set
     */
    public void setEnviarEmail(boolean enviarEmail) {
        this.enviarEmail = enviarEmail;
    }
}
