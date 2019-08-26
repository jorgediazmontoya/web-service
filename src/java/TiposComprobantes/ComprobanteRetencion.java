/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TiposComprobantes;

import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.ImpuestoComprobanteRetencion;
import Util.Util;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author Yoelvys
 */
public class ComprobanteRetencion extends ComprobanteGeneral {
    private String tipoIdentificacionSujetoRetenido;
    private String razonSocialSujetoRetenido;
    private String identificacionSujetoRetenido;
    private String periodoFiscal;
    private List<ImpuestoComprobanteRetencion> impuestos;
    private List<CampoAdicional> infoAdicional;

    @Override
    public Document crearXMLComprobante() throws ParserConfigurationException {
        Util util = new Util();
        setTipoDoc("comprobanteRetencion");
        Document xmlComprobante = super.crearXMLComprobante();
        Element tagComprobanteRetencion = xmlComprobante.getDocumentElement();
        tagComprobanteRetencion.setAttribute("version", "1.0.0");
        tagComprobanteRetencion.setAttribute("id", "comprobante");

        Element tagInfoCompRetencion = xmlComprobante.createElement("infoCompRetencion");

        Element tagFechaEmision = xmlComprobante.createElement("fechaEmision");
        tagFechaEmision.setTextContent(getFechaEmision());
        tagInfoCompRetencion.appendChild(tagFechaEmision);

        if (getDirEstablecimiento() != null && !getDirEstablecimiento().equals("")) {
            Element tagDirEstablecimiento = xmlComprobante.createElement("dirEstablecimiento");
            tagDirEstablecimiento.setTextContent(getDirEstablecimiento());
            tagInfoCompRetencion.appendChild(tagDirEstablecimiento);
        }

        if (getContribuyenteEspecial() != null && !getContribuyenteEspecial().equals("")) {
            Element tagContribuyenteEspecial = xmlComprobante.createElement("contribuyenteEspecial");
            tagContribuyenteEspecial.setTextContent(getContribuyenteEspecial());
            tagInfoCompRetencion.appendChild(tagContribuyenteEspecial);
        }

        Element tagObligadoContabilidad = xmlComprobante.createElement("obligadoContabilidad");
        tagObligadoContabilidad.setTextContent(getObligadoContabilidad());
        tagInfoCompRetencion.appendChild(tagObligadoContabilidad);

        Element tagTipoIdentificacionSujetoRetenido = xmlComprobante.createElement("tipoIdentificacionSujetoRetenido");
        tagTipoIdentificacionSujetoRetenido.setTextContent(tipoIdentificacionSujetoRetenido);
        tagInfoCompRetencion.appendChild(tagTipoIdentificacionSujetoRetenido);

        Element tagRazonSocialSujetoRetenido = xmlComprobante.createElement("razonSocialSujetoRetenido");
        tagRazonSocialSujetoRetenido.setTextContent(razonSocialSujetoRetenido);
        tagInfoCompRetencion.appendChild(tagRazonSocialSujetoRetenido);

        Element tagIdentificacionSujetoRetenido = xmlComprobante.createElement("identificacionSujetoRetenido");
        tagIdentificacionSujetoRetenido.setTextContent(identificacionSujetoRetenido);
        tagInfoCompRetencion.appendChild(tagIdentificacionSujetoRetenido);

        Element tagPeriodoFiscal = xmlComprobante.createElement("periodoFiscal");
        tagPeriodoFiscal.setTextContent(periodoFiscal);
        tagInfoCompRetencion.appendChild(tagPeriodoFiscal);

        tagComprobanteRetencion.appendChild(tagInfoCompRetencion);

        if (impuestos!= null && !impuestos.isEmpty()) {
            tagComprobanteRetencion.appendChild(util.construirXMLImpuestosComprobanteRetencion(impuestos, xmlComprobante));
        }

        if (infoAdicional!= null &&  !infoAdicional.isEmpty() && infoAdicional.get(0) != null) {
            tagComprobanteRetencion.appendChild(util.construirXMLInfoAdicional(infoAdicional, xmlComprobante));
        }
        
        return xmlComprobante;
    }
    /**
     * @return the tipoIdentificacionSujetoRetenido
     */
    public String getTipoIdentificacionSujetoRetenido() {
        return tipoIdentificacionSujetoRetenido;
    }

    /**
     * @param tipoIdentificacionSujetoRetenido the
     * tipoIdentificacionSujetoRetenido to set
     */
    public void setTipoIdentificacionSujetoRetenido(String tipoIdentificacionSujetoRetenido) {
        this.tipoIdentificacionSujetoRetenido = tipoIdentificacionSujetoRetenido;
    }

    /**
     * @return the razonSocialSujetoRetenido
     */
    public String getRazonSocialSujetoRetenido() {
        return razonSocialSujetoRetenido;
    }

    /**
     * @param razonSocialSujetoRetenido the razonSocialSujetoRetenido to set
     */
    public void setRazonSocialSujetoRetenido(String razonSocialSujetoRetenido) {
        this.razonSocialSujetoRetenido = razonSocialSujetoRetenido;
    }

    /**
     * @return the identificacionSujetoRetenido
     */
    public String getIdentificacionSujetoRetenido() {
        return identificacionSujetoRetenido;
    }

    /**
     * @param identificacionSujetoRetenido the identificacionSujetoRetenido to
     * set
     */
    public void setIdentificacionSujetoRetenido(String identificacionSujetoRetenido) {
        this.identificacionSujetoRetenido = identificacionSujetoRetenido;
    }

    /**
     * @return the periodoFiscal
     */
    public String getPeriodoFiscal() {
        return periodoFiscal;
    }

    /**
     * @param periodoFiscal the periodoFiscal to set
     */
    public void setPeriodoFiscal(String periodoFiscal) {
        this.periodoFiscal = periodoFiscal;
    }

    /**
     * @return the impuestos
     */
    public List<ImpuestoComprobanteRetencion> getImpuestos() {
        return impuestos;
    }

    /**
     * @param impuestos the impuestos to set
     */
    public void setImpuestos(List<ImpuestoComprobanteRetencion> impuestos) {
        this.impuestos = impuestos;
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
}
