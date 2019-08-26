/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */

package Util;

/**
 *
 * @author Yoelvys
 */
public class ConfigAplicacion {
    private String dirFirma;
    private String passFirma;
    private String dirAutorizados;
    private String dirLogo;

    /**
     * @return the dirFirma
     */
    public String getDirFirma() {
        return dirFirma;
    }

    /**
     * @param dirFirma the dirFirma to set
     */
    public void setDirFirma(String dirFirma) {
        this.dirFirma = dirFirma;
    }

    /**
     * @return the passFirma
     */
    public String getPassFirma() {
        return passFirma;
    }

    /**
     * @param passFirma the passFirma to set
     */
    public void setPassFirma(String passFirma) {
        this.passFirma = passFirma;
    }

    /**
     * @return the dirAutorizados
     */
    public String getDirAutorizados() {
        return dirAutorizados;
    }

    /**
     * @param dirAutorizados the dirAutorizados to set
     */
    public void setDirAutorizados(String dirAutorizados) {
        this.dirAutorizados = dirAutorizados;
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
}
