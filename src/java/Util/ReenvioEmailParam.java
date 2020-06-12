/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */
package Util;

/**
 *
 * @author Yoelvys
 */
public class ReenvioEmailParam {

   private ConfigCorreo configCorreo;
   private String dirDocAutorizados;
   private String identificacionComprador;
   private String nombreArchivo;
   private String otrosDestinatarios;

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
     * @return the dirDocAutorizados
     */
    public String getDirDocAutorizados() {
        return dirDocAutorizados;
    }

    /**
     * @param dirDocAutorizados the dirDocAutorizados to set
     */
    public void setDirDocAutorizados(String dirDocAutorizados) {
        this.dirDocAutorizados = dirDocAutorizados;
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
     * @return the nombreArchivo
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * @param nombreArchivo the nombreArchivo to set
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
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

}
