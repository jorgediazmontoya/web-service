/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Respuesta;

/**
 *
 * @author Yoelvys
 */
public class MensajeGenerado {
    private String identificador;
    private String mensaje;
    private String informacionAdicional;
    private String tipo;
    public MensajeGenerado(String identificador,String mensaje,String informacionAdicional,String tipo){
      this.identificador = identificador;
      this.mensaje = mensaje;
      this.informacionAdicional = informacionAdicional;
      this.tipo = tipo;
    }
     public MensajeGenerado(){
    }
    /**
     * @return the identificador
     */
    public String getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the informacionAdicional
     */
    public String getInformacionAdicional() {
        return informacionAdicional;
    }

    /**
     * @param informacionAdicional the informacionAdicional to set
     */
    public void setInformacionAdicional(String informacionAdicional) {
        this.informacionAdicional = informacionAdicional;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
