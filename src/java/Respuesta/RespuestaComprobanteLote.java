/*
 * Facturacion Electronica (FACTEL) es propiedad intelectual de Ing. Yoelvys Martinez Hidalgo.
 * Cualquier distribución, comercialización o modificacion sin previo consentimiento del autor, puede ser penado por la ley.
 */
package Respuesta;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yoelvys
 */
public class RespuestaComprobanteLote {

    private boolean error;
    private String claveAccesoConsultada;
    private List<Respuesta> respuestas;
    private MensajeGenerado mensajeGeneral;

    public RespuestaComprobanteLote() {
        respuestas = new ArrayList<Respuesta>();
    }

    /**
     * @return the respuestas
     */
    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    /**
     * @param respuestas the respuestas to set
     */
    public void setRespuestas(List<Respuesta> respuestas) {
        this.respuestas = respuestas;
    }

    /**
     * @return the mensajeGeneral
     */
    public MensajeGenerado getMensajeGeneral() {
        return mensajeGeneral;
    }

    /**
     * @param mensajeGeneral the mensajeGeneral to set
     */
    public void setMensajeGeneral(MensajeGenerado mensajeGeneral) {
        this.mensajeGeneral = mensajeGeneral;
    }

    /**
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * @param error the error to set
     */
    public void setError(boolean error) {
        this.error = error;
    }

    /**
     * @return the claveAccesoConsultada
     */
    public String getClaveAccesoConsultada() {
        return claveAccesoConsultada;
    }

    /**
     * @param claveAccesoConsultada the claveAccesoConsultada to set
     */
    public void setClaveAccesoConsultada(String claveAccesoConsultada) {
        this.claveAccesoConsultada = claveAccesoConsultada;
    }

}
