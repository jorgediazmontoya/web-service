/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */
package Util;

/**
 *
 * @author Yoelvys
 */
public class ConfigCorreo {

    private String correoHost;
    private String correoPort;
    private boolean sslHabilitado;
    private String correoRemitente;
    private String correoPass;
    private String correoAsunto;
    private String BBC;
    private String CC;

    /**
     * @return the correoHost
     */
    public String getCorreoHost() {
        return correoHost;
    }

    /**
     * @param correoHost the correoHost to set
     */
    public void setCorreoHost(String correoHost) {
        this.correoHost = correoHost;
    }

    /**
     * @return the correoPort
     */
    public String getCorreoPort() {
        return correoPort;
    }

    /**
     * @param correoPort the correoPort to set
     */
    public void setCorreoPort(String correoPort) {
        this.correoPort = correoPort;
    }

    /**
     * @return the correoRemitente
     */
    public String getCorreoRemitente() {
        return correoRemitente;
    }

    /**
     * @param correoRemitente the correoRemitente to set
     */
    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }

    /**
     * @return the correoPass
     */
    public String getCorreoPass() {
        return correoPass;
    }

    /**
     * @param correoPass the correoPass to set
     */
    public void setCorreoPass(String correoPass) {
        this.correoPass = correoPass;
    }

    /**
     * @return the correoAsunto
     */
    public String getCorreoAsunto() {
        return correoAsunto;
    }

    /**
     * @param correoAsunto the correoAsunto to set
     */
    public void setCorreoAsunto(String correoAsunto) {
        this.correoAsunto = correoAsunto;
    }

    /**
     * @return the sslHabilitado
     */
    public boolean isSslHabilitado() {
        return sslHabilitado;
    }

    /**
     * @param sslHabilitado the sslHabilitado to set
     */
    public void setSslHabilitado(boolean sslHabilitado) {
        this.sslHabilitado = sslHabilitado;
    }

    /**
     * @return the BBC
     */
    public String getBBC() {
        return BBC;
    }

    /**
     * @param BBC the BBC to set
     */
    public void setBBC(String BBC) {
        this.BBC = BBC;
    }

    /**
     * @return the CC
     */
    public String getCC() {
        return CC;
    }

    /**
     * @param CC the CC to set
     */
    public void setCC(String CC) {
        this.CC = CC;
    }

}
