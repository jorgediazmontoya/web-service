/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes.Bloques;

/**
 *
 * @author Yoelvys
 */
public class TotalImpuesto {

    private String codigo;
    private String codigoPorcentaje;
    private String descuentoAdicional;
    private String baseImponible;
    private String tarifa;
    private String valor;

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the codigoPorcentaje
     */
    public String getCodigoPorcentaje() {
        return codigoPorcentaje;
    }

    /**
     * @param codigoPorcentaje the codigoPorcentaje to set
     */
    public void setCodigoPorcentaje(String codigoPorcentaje) {
        this.codigoPorcentaje = codigoPorcentaje;
    }

    /**
     * @return the baseImponible
     */
    public String getBaseImponible() {
        return baseImponible;
    }

    /**
     * @param baseImponible the baseImponible to set
     */
    public void setBaseImponible(String baseImponible) {
        this.baseImponible = baseImponible;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the descuentoAdicional
     */
    public String getDescuentoAdicional() {
        return descuentoAdicional;
    }

    /**
     * @param descuentoAdicional the descuentoAdicional to set
     */
    public void setDescuentoAdicional(String descuentoAdicional) {
        this.descuentoAdicional = descuentoAdicional;
    }

    /**
     * @return the tarifa
     */
    public String getTarifa() {
        return tarifa;
    }

    /**
     * @param tarifa the tarifa to set
     */
    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }
}
