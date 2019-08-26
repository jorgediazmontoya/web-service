/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import Util.ConfigAplicacion;
import Util.ConfigCorreo;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class ComprobanteGeneral {

    private ConfigAplicacion configAplicacion;
    private ConfigCorreo configCorreo;
    private String ambiente;
    private String tipoEmision;
    private String razonSocial;
    private String nombreComercial;
    private String ruc;
    private String codDoc;
    private String claveAcc;
    private String establecimiento;
    private String ptoEmision;
    private String secuencial;
    private String dirMatriz;
    private String dirEstablecimiento;
    private String contribuyenteEspecial;
    private String obligadoContabilidad;
    private String tipoDoc;
    private String fechaEmision;

    public Document crearXMLComprobante() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation implementation = builder.getDOMImplementation();
        Document xmlComprobante = implementation.createDocument(null, getTipoDoc(), null);
        xmlComprobante.setXmlVersion("1.0");
        Element tagRoot = xmlComprobante.getDocumentElement();

        Element tagInfoTributaria = xmlComprobante.createElement("infoTributaria");

        Element tagAmbiente = xmlComprobante.createElement("ambiente");
        tagAmbiente.setTextContent(getAmbiente());
        tagInfoTributaria.appendChild(tagAmbiente);

        Element tagTipoEmision = xmlComprobante.createElement("tipoEmision");
        tagTipoEmision.setTextContent(getTipoEmision());
        tagInfoTributaria.appendChild(tagTipoEmision);

        Element tagRazonSocial = xmlComprobante.createElement("razonSocial");
        tagRazonSocial.setTextContent(getRazonSocial());
        tagInfoTributaria.appendChild(tagRazonSocial);

        if (getNombreComercial() != null && !nombreComercial.equals("")) {
            Element tagNombreComercial = xmlComprobante.createElement("nombreComercial");
            tagNombreComercial.setTextContent(getNombreComercial());
            tagInfoTributaria.appendChild(tagNombreComercial);
        }
        Element tagRuc = xmlComprobante.createElement("ruc");
        tagRuc.setTextContent(getRuc());
        tagInfoTributaria.appendChild(tagRuc);

        Element tagClaveAcceso = xmlComprobante.createElement("claveAcceso");
        if (claveAcc == null || claveAcc.equals("")) {
            tagClaveAcceso.setTextContent(claveAcceso());
        } else {
            tagClaveAcceso.setTextContent(claveAcc);
        }
        tagInfoTributaria.appendChild(tagClaveAcceso);

        Element tagCodDoc = xmlComprobante.createElement("codDoc");
        tagCodDoc.setTextContent(getCodDoc());
        tagInfoTributaria.appendChild(tagCodDoc);

        Element tagEstab = xmlComprobante.createElement("estab");
        tagEstab.setTextContent(getEstablecimiento());
        tagInfoTributaria.appendChild(tagEstab);

        Element tagPtoEmi = xmlComprobante.createElement("ptoEmi");
        tagPtoEmi.setTextContent(getPtoEmision());
        tagInfoTributaria.appendChild(tagPtoEmi);

        Element tagSecuencial = xmlComprobante.createElement("secuencial");
        tagSecuencial.setTextContent(getSecuencial());
        tagInfoTributaria.appendChild(tagSecuencial);

        Element tagDirMatriz = xmlComprobante.createElement("dirMatriz");
        tagDirMatriz.setTextContent(getDirMatriz());
        tagInfoTributaria.appendChild(tagDirMatriz);

        tagRoot.appendChild(tagInfoTributaria);
        return xmlComprobante;
    }

    private String claveAcceso() {
        String claveAcceso = fechaEmision.replaceAll("/", "");
        claveAcceso += codDoc;
        claveAcceso += ruc;
        claveAcceso += ambiente;
        String serie = establecimiento + ptoEmision;
        claveAcceso += serie;
        claveAcceso += secuencial;
        claveAcceso += "12345678";
        claveAcceso += tipoEmision;
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
     * @return the tipoDoc
     */
    public String getTipoDoc() {
        return tipoDoc;
    }

    /**
     * @param tipoDoc the tipoDoc to set
     */
    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
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

}
