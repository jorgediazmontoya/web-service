/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import TiposComprobantes.Bloques.ReembolsoFactura;
import TiposComprobantes.Bloques.ReembolsoLiquidacionCompra;
import TiposComprobantes.Bloques.CampoAdicional;
import TiposComprobantes.Bloques.Destinatario;
import TiposComprobantes.Bloques.DetalleAdicional;
import TiposComprobantes.Bloques.DetalleFactura;
import TiposComprobantes.Bloques.DetalleGuiaRemision;
import TiposComprobantes.Bloques.DetalleLiquidacionCompra;
import TiposComprobantes.Bloques.DetalleNotaCredito;
import TiposComprobantes.Bloques.Impuesto;
import TiposComprobantes.Bloques.ImpuestoComprobanteRetencion;
import TiposComprobantes.Bloques.Motivo;
import TiposComprobantes.Bloques.Pago;
import TiposComprobantes.Bloques.TotalImpuesto;
import TiposComprobantes.ComprobanteGeneral;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Yoelvys
 */
public class Util {

    public Document convertirStringToXML(String comprobante) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        InputSource is = new InputSource();
        is.setCharacterStream(new StringReader(comprobante));

        Document doc = db.parse(is);
        return doc;
    }

    public String convertirXMLToString(Document xmlComprobante) throws TransformerConfigurationException, TransformerException {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();

        StringWriter writer = new StringWriter();
        transformer.transform(new DOMSource(xmlComprobante), new StreamResult(writer));
        String xmlString = writer.getBuffer().toString();
        return xmlString;
    }

    public Element construirXMLTotalConImpuestos(List<TotalImpuesto> totalConImpuestos, Document object) {
        Element tagTotalConImpuestos = object.createElement("totalConImpuestos");
        for (Iterator<TotalImpuesto> it = totalConImpuestos.iterator(); it.hasNext();) {
            Element tagTotalImpuesto = object.createElement("totalImpuesto");
            TotalImpuesto totalImpuesto = it.next();

            Element tagCodigo = object.createElement("codigo");
            tagCodigo.setTextContent(totalImpuesto.getCodigo());
            tagTotalImpuesto.appendChild(tagCodigo);

            Element tagCodigoPorcentaje = object.createElement("codigoPorcentaje");
            tagCodigoPorcentaje.setTextContent(totalImpuesto.getCodigoPorcentaje());
            tagTotalImpuesto.appendChild(tagCodigoPorcentaje);

            if (totalImpuesto.getDescuentoAdicional() != null && !totalImpuesto.getDescuentoAdicional().equals("")) {
                Element tagDescuentoAdicional = object.createElement("descuentoAdicional");
                tagDescuentoAdicional.setTextContent(totalImpuesto.getDescuentoAdicional());
                tagTotalImpuesto.appendChild(tagDescuentoAdicional);
            }

            Element tagBaseImponible = object.createElement("baseImponible");
            tagBaseImponible.setTextContent(totalImpuesto.getBaseImponible());
            tagTotalImpuesto.appendChild(tagBaseImponible);

            if (totalImpuesto.getTarifa() != null && !totalImpuesto.getTarifa().equals("")) {
                Element tagTarifa = object.createElement("tarifa");
                tagTarifa.setTextContent(totalImpuesto.getTarifa());
                tagTotalImpuesto.appendChild(tagTarifa);
            }

            Element tagValor = object.createElement("valor");
            tagValor.setTextContent(totalImpuesto.getValor());
            tagTotalImpuesto.appendChild(tagValor);

            tagTotalConImpuestos.appendChild(tagTotalImpuesto);
        }
        return tagTotalConImpuestos;

    }

    public Element construirXMLImpuestosReembolso(List<Impuesto> impuestos, Document object) {
        Element tagImpuestos = object.createElement("detalleImpuestos");
        for (Iterator<Impuesto> it = impuestos.iterator(); it.hasNext();) {
            Element tagImpuesto = object.createElement("detalleImpuesto");
            Impuesto impuesto = it.next();

            Element tagCodigo = object.createElement("codigo");
            tagCodigo.setTextContent(impuesto.getCodigo());
            tagImpuesto.appendChild(tagCodigo);

            Element tagCodigoPorcentaje = object.createElement("codigoPorcentaje");
            tagCodigoPorcentaje.setTextContent(impuesto.getCodigoPorcentaje());
            tagImpuesto.appendChild(tagCodigoPorcentaje);

            Element tagTarifa = object.createElement("tarifa");
            tagTarifa.setTextContent(impuesto.getTarifa());
            tagImpuesto.appendChild(tagTarifa);

            Element tagBaseImponible = object.createElement("baseImponibleReembolso");
            tagBaseImponible.setTextContent(impuesto.getBaseImponible());
            tagImpuesto.appendChild(tagBaseImponible);

            Element tagValor = object.createElement("impuestoReembolso");
            tagValor.setTextContent(impuesto.getValor());
            tagImpuesto.appendChild(tagValor);

            tagImpuestos.appendChild(tagImpuesto);
        }
        return tagImpuestos;

    }

    public Element construirXMLImpuestos(List<Impuesto> impuestos, Document object) {
        Element tagImpuestos = object.createElement("impuestos");
        for (Iterator<Impuesto> it = impuestos.iterator(); it.hasNext();) {
            Element tagImpuesto = object.createElement("impuesto");
            Impuesto impuesto = it.next();

            Element tagCodigo = object.createElement("codigo");
            tagCodigo.setTextContent(impuesto.getCodigo());
            tagImpuesto.appendChild(tagCodigo);

            Element tagCodigoPorcentaje = object.createElement("codigoPorcentaje");
            tagCodigoPorcentaje.setTextContent(impuesto.getCodigoPorcentaje());
            tagImpuesto.appendChild(tagCodigoPorcentaje);

            Element tagTarifa = object.createElement("tarifa");
            tagTarifa.setTextContent(impuesto.getTarifa());
            tagImpuesto.appendChild(tagTarifa);

            Element tagBaseImponible = object.createElement("baseImponible");
            tagBaseImponible.setTextContent(impuesto.getBaseImponible());
            tagImpuesto.appendChild(tagBaseImponible);

            Element tagValor = object.createElement("valor");
            tagValor.setTextContent(impuesto.getValor());
            tagImpuesto.appendChild(tagValor);

            tagImpuestos.appendChild(tagImpuesto);
        }
        return tagImpuestos;

    }

    public Element construirXMLPagos(List<Pago> pagos, Document xmlComprobante) {
        Element tagPagos = xmlComprobante.createElement("pagos");
        for (Pago pago : pagos) {
            Element tagPago = xmlComprobante.createElement("pago");

            Element tagFormaPago = xmlComprobante.createElement("formaPago");
            tagFormaPago.appendChild(xmlComprobante.createTextNode(pago.getFormaPago()));
            tagPago.appendChild(tagFormaPago);

            Element tagTotal = xmlComprobante.createElement("total");
            tagTotal.appendChild(xmlComprobante.createTextNode(pago.getTotal()));
            tagPago.appendChild(tagTotal);

            if (pago.getPlazo() != null && !pago.getPlazo().equals("")) {
                Element tagPlazo = xmlComprobante.createElement("plazo");
                tagPlazo.appendChild(xmlComprobante.createTextNode(pago.getPlazo()));
                tagPago.appendChild(tagPlazo);
            }

            if (pago.getUnidadTiempo() != null && !pago.getUnidadTiempo().equals("")) {
                Element tagUnidadTiempo = xmlComprobante.createElement("unidadTiempo");
                tagUnidadTiempo.appendChild(xmlComprobante.createTextNode(pago.getUnidadTiempo()));
                tagPago.appendChild(tagUnidadTiempo);
            }

            tagPagos.appendChild(tagPago);
        }
        return tagPagos;

    }

    public Element construirXMLDetallesLiquidacionCompra(List<DetalleLiquidacionCompra> detalles, Document object) {
        Element tagDetalles = object.createElement("detalles");
        for (Iterator<DetalleLiquidacionCompra> it = detalles.iterator(); it.hasNext();) {
            Element tagDetalle = object.createElement("detalle");
            DetalleLiquidacionCompra detalle = it.next();

            Element tagCodigoPrincipal = object.createElement("codigoPrincipal");
            tagCodigoPrincipal.setTextContent(detalle.getCodigoPrincipal());
            tagDetalle.appendChild(tagCodigoPrincipal);

            if (detalle.getCodigoAuxiliar() != null && !detalle.getCodigoAuxiliar().equals("")) {
                Element tagCodigoAuxiliar = object.createElement("codigoAuxiliar");
                tagCodigoAuxiliar.setTextContent(detalle.getCodigoAuxiliar());
                tagDetalle.appendChild(tagCodigoAuxiliar);
            }

            Element tagDescripcion = object.createElement("descripcion");
            tagDescripcion.setTextContent(detalle.getDescripcion());
            tagDetalle.appendChild(tagDescripcion);

            Element tagCantidad = object.createElement("cantidad");
            tagCantidad.setTextContent(detalle.getCantidad());
            tagDetalle.appendChild(tagCantidad);

            Element tagPrecioUnitario = object.createElement("precioUnitario");
            tagPrecioUnitario.setTextContent(detalle.getPrecioUnitario());
            tagDetalle.appendChild(tagPrecioUnitario);

            if (detalle.getDescuento() != null && !detalle.getDescuento().equals("")) {
                Element tagDescuento = object.createElement("descuento");
                tagDescuento.setTextContent(detalle.getDescuento());
                tagDetalle.appendChild(tagDescuento);
            }

            Element tagPrecioTotalSinImpuesto = object.createElement("precioTotalSinImpuesto");
            tagPrecioTotalSinImpuesto.setTextContent(detalle.getPrecioTotalSinImpuesto());
            tagDetalle.appendChild(tagPrecioTotalSinImpuesto);

            if (detalle.getDetalleAdicional() != null && !detalle.getDetalleAdicional().isEmpty() && detalle.getDetalleAdicional().get(0) != null) {
                tagDetalle.appendChild(construirXMLDetallesAdicionales(detalle.getDetalleAdicional(), object));
            }

            tagDetalle.appendChild(construirXMLImpuestos(detalle.getImpuestos(), object));
            tagDetalles.appendChild(tagDetalle);
        }
        return tagDetalles;

    }

    public Element construirXMLReembolsosLiquidacionCompra(List<ReembolsoLiquidacionCompra> reembolsos, Document object) {
        Element tagReembolsos = object.createElement("reembolsos");
        for (Iterator<ReembolsoLiquidacionCompra> it = reembolsos.iterator(); it.hasNext();) {
            Element tagReembolso = object.createElement("reembolsoDetalle");
            ReembolsoLiquidacionCompra reembolso = it.next();

            Element tagTipoIdentificacionProveedorReembolso = object.createElement("tipoIdentificacionProveedorReembolso");
            tagTipoIdentificacionProveedorReembolso.setTextContent(reembolso.getTipoIdentificacionProveedorReembolso());
            tagReembolso.appendChild(tagTipoIdentificacionProveedorReembolso);

            Element tagIdentificacionProveedorReembolso = object.createElement("identificacionProveedorReembolso");
            tagIdentificacionProveedorReembolso.setTextContent(reembolso.getIdentificacionProveedorReembolso());
            tagReembolso.appendChild(tagIdentificacionProveedorReembolso);

            Element tagCodPaisPagoProveedorReembolso = object.createElement("codPaisPagoProveedorReembolso");
            tagCodPaisPagoProveedorReembolso.setTextContent(reembolso.getCodPaisPagoProveedorReembolso());
            tagReembolso.appendChild(tagCodPaisPagoProveedorReembolso);

            Element tagTipoProveedorReembolso = object.createElement("tipoProveedorReembolso");
            tagTipoProveedorReembolso.setTextContent(reembolso.getTipoProveedorReembolso());
            tagReembolso.appendChild(tagTipoProveedorReembolso);

            Element tagCodDocReembolso = object.createElement("codDocReembolso");
            tagCodDocReembolso.setTextContent(reembolso.getCodDocReembolso());
            tagReembolso.appendChild(tagCodDocReembolso);

            Element tagEstabDocReembolso = object.createElement("estabDocReembolso");
            tagEstabDocReembolso.setTextContent(reembolso.getEstabDocReembolso());
            tagReembolso.appendChild(tagEstabDocReembolso);

            Element tagPtoEmiDocReembolso = object.createElement("ptoEmiDocReembolso");
            tagPtoEmiDocReembolso.setTextContent(reembolso.getPtoEmiDocReembolso());
            tagReembolso.appendChild(tagPtoEmiDocReembolso);

            Element tagSecuencialDocReembolso = object.createElement("secuencialDocReembolso");
            tagSecuencialDocReembolso.setTextContent(reembolso.getSecuencialDocReembolso());
            tagReembolso.appendChild(tagSecuencialDocReembolso);

            Element tagFechaEmisionDocReembolso = object.createElement("fechaEmisionDocReembolso");
            tagFechaEmisionDocReembolso.setTextContent(reembolso.getFechaEmisionDocReembolso());
            tagReembolso.appendChild(tagFechaEmisionDocReembolso);

            String numAutorizacion = reembolso.getNumeroautorizacionDocReemb();

            Element tagNumeroautorizacionDocReemb = object.createElement("numeroautorizacionDocReemb");
            tagNumeroautorizacionDocReemb.setTextContent(numAutorizacion);
            tagReembolso.appendChild(tagNumeroautorizacionDocReemb);

            tagReembolso.appendChild(construirXMLImpuestosReembolso(reembolso.getImpuestos(), object));
            tagReembolsos.appendChild(tagReembolso);
        }

        return tagReembolsos;
    }

    
    public Element construirXMLReembolsosFactura(List<ReembolsoFactura> reembolsos, Document object) {
        Element tagReembolsos = object.createElement("reembolsos");
        for (Iterator<ReembolsoFactura> it = reembolsos.iterator(); it.hasNext();) {
            Element tagReembolso = object.createElement("reembolsoDetalle");
            ReembolsoFactura reembolso = it.next();

            Element tagTipoIdentificacionProveedorReembolso = object.createElement("tipoIdentificacionProveedorReembolso");
            tagTipoIdentificacionProveedorReembolso.setTextContent(reembolso.getTipoIdentificacionProveedorReembolso());
            tagReembolso.appendChild(tagTipoIdentificacionProveedorReembolso);

            Element tagIdentificacionProveedorReembolso = object.createElement("identificacionProveedorReembolso");
            tagIdentificacionProveedorReembolso.setTextContent(reembolso.getIdentificacionProveedorReembolso());
            tagReembolso.appendChild(tagIdentificacionProveedorReembolso);

            Element tagCodPaisPagoProveedorReembolso = object.createElement("codPaisPagoProveedorReembolso");
            tagCodPaisPagoProveedorReembolso.setTextContent(reembolso.getCodPaisPagoProveedorReembolso());
            tagReembolso.appendChild(tagCodPaisPagoProveedorReembolso);

            Element tagTipoProveedorReembolso = object.createElement("tipoProveedorReembolso");
            tagTipoProveedorReembolso.setTextContent(reembolso.getTipoProveedorReembolso());
            tagReembolso.appendChild(tagTipoProveedorReembolso);

            Element tagCodDocReembolso = object.createElement("codDocReembolso");
            tagCodDocReembolso.setTextContent(reembolso.getCodDocReembolso());
            tagReembolso.appendChild(tagCodDocReembolso);

            Element tagEstabDocReembolso = object.createElement("estabDocReembolso");
            tagEstabDocReembolso.setTextContent(reembolso.getEstabDocReembolso());
            tagReembolso.appendChild(tagEstabDocReembolso);

            Element tagPtoEmiDocReembolso = object.createElement("ptoEmiDocReembolso");
            tagPtoEmiDocReembolso.setTextContent(reembolso.getPtoEmiDocReembolso());
            tagReembolso.appendChild(tagPtoEmiDocReembolso);

            Element tagSecuencialDocReembolso = object.createElement("secuencialDocReembolso");
            tagSecuencialDocReembolso.setTextContent(reembolso.getSecuencialDocReembolso());
            tagReembolso.appendChild(tagSecuencialDocReembolso);

            Element tagFechaEmisionDocReembolso = object.createElement("fechaEmisionDocReembolso");
            tagFechaEmisionDocReembolso.setTextContent(reembolso.getFechaEmisionDocReembolso());
            tagReembolso.appendChild(tagFechaEmisionDocReembolso);

            String numAutorizacion = reembolso.getNumeroautorizacionDocReemb();

            Element tagNumeroautorizacionDocReemb = object.createElement("numeroautorizacionDocReemb");
            tagNumeroautorizacionDocReemb.setTextContent(numAutorizacion);
            tagReembolso.appendChild(tagNumeroautorizacionDocReemb);

            tagReembolso.appendChild(construirXMLImpuestosReembolso(reembolso.getImpuestos(), object));
            tagReembolsos.appendChild(tagReembolso);
        }

        return tagReembolsos;
    }

    public Element construirXMLDetallesFactura(List<DetalleFactura> detalles, Document object) {
        Element tagDetalles = object.createElement("detalles");
        for (Iterator<DetalleFactura> it = detalles.iterator(); it.hasNext();) {
            Element tagDetalle = object.createElement("detalle");
            DetalleFactura detalle = it.next();

            Element tagCodigoPrincipal = object.createElement("codigoPrincipal");
            tagCodigoPrincipal.setTextContent(detalle.getCodigoPrincipal());
            tagDetalle.appendChild(tagCodigoPrincipal);

            if (detalle.getCodigoAuxiliar() != null && !detalle.getCodigoAuxiliar().equals("")) {
                Element tagCodigoAuxiliar = object.createElement("codigoAuxiliar");
                tagCodigoAuxiliar.setTextContent(detalle.getCodigoAuxiliar());
                tagDetalle.appendChild(tagCodigoAuxiliar);
            }

            Element tagDescripcion = object.createElement("descripcion");
            tagDescripcion.setTextContent(detalle.getDescripcion());
            tagDetalle.appendChild(tagDescripcion);

            Element tagCantidad = object.createElement("cantidad");
            tagCantidad.setTextContent(detalle.getCantidad());
            tagDetalle.appendChild(tagCantidad);

            Element tagPrecioUnitario = object.createElement("precioUnitario");
            tagPrecioUnitario.setTextContent(detalle.getPrecioUnitario());
            tagDetalle.appendChild(tagPrecioUnitario);

            if (detalle.getPrecioSinSubsidio() != null && !detalle.getPrecioSinSubsidio().equals("")) {
                Element tagPrecioSinSubsidio = object.createElement("precioSinSubsidio");
                tagPrecioSinSubsidio.setTextContent(detalle.getPrecioSinSubsidio());
                tagDetalle.appendChild(tagPrecioSinSubsidio);
            }

            if (detalle.getDescuento() != null && !detalle.getDescuento().equals("")) {
                Element tagDescuento = object.createElement("descuento");
                tagDescuento.setTextContent(detalle.getDescuento());
                tagDetalle.appendChild(tagDescuento);
            }

            Element tagPrecioTotalSinImpuesto = object.createElement("precioTotalSinImpuesto");
            tagPrecioTotalSinImpuesto.setTextContent(detalle.getPrecioTotalSinImpuesto());
            tagDetalle.appendChild(tagPrecioTotalSinImpuesto);

            if (detalle.getDetalleAdicional() != null && !detalle.getDetalleAdicional().isEmpty() && detalle.getDetalleAdicional().get(0) != null) {
                tagDetalle.appendChild(construirXMLDetallesAdicionales(detalle.getDetalleAdicional(), object));
            }

            tagDetalle.appendChild(construirXMLImpuestos(detalle.getImpuestos(), object));
            tagDetalles.appendChild(tagDetalle);
        }
        return tagDetalles;

    }

    public Element construirXMLDetallesGuiaRemision(List<DetalleGuiaRemision> detalles, Document object) {
        Element tagDetalles = object.createElement("detalles");
        for (Iterator<DetalleGuiaRemision> it = detalles.iterator(); it.hasNext();) {
            Element tagDetalle = object.createElement("detalle");
            DetalleGuiaRemision detalle = it.next();

            Element tagCodigoInterno = object.createElement("codigoInterno");
            tagCodigoInterno.setTextContent(detalle.getCodigoInterno());
            tagDetalle.appendChild(tagCodigoInterno);

            if (detalle.getCodigoAdicional() != null && !detalle.getCodigoAdicional().equals("")) {
                Element tagCodigoAdicional = object.createElement("codigoAdicional");
                tagCodigoAdicional.setTextContent(detalle.getCodigoAdicional());
                tagDetalle.appendChild(tagCodigoAdicional);
            }

            Element tagDescripcion = object.createElement("descripcion");
            tagDescripcion.setTextContent(detalle.getDescripcion());
            tagDetalle.appendChild(tagDescripcion);

            Element tagCantidad = object.createElement("cantidad");
            tagCantidad.setTextContent(detalle.getCantidad());
            tagDetalle.appendChild(tagCantidad);

            if (detalle.getDetallesAdicionales() != null && !detalle.getDetallesAdicionales().isEmpty() && detalle.getDetallesAdicionales().get(0) != null) {
                tagDetalle.appendChild(construirXMLDetallesAdicionales(detalle.getDetallesAdicionales(), object));
            }

            tagDetalles.appendChild(tagDetalle);
        }
        return tagDetalles;

    }

    public Element construirXMLDetallesAdicionales(List<DetalleAdicional> detallesAdicional, Document object) {
        Element tagDetallesAdicionales = object.createElement("detallesAdicionales");
        for (Iterator<DetalleAdicional> it = detallesAdicional.iterator(); it.hasNext();) {
            Element tagDetAdicional = object.createElement("detAdicional");
            DetalleAdicional detalleAdicional = it.next();
            tagDetAdicional.setAttribute("nombre", detalleAdicional.getNombre());
            tagDetAdicional.setAttribute("valor", detalleAdicional.getValor());
            tagDetallesAdicionales.appendChild(tagDetAdicional);
        }
        return tagDetallesAdicionales;

    }

    public Element construirXMLInfoAdicional(List<CampoAdicional> infoAdicional, Document object) {
        Element tagInfoAdicional = object.createElement("infoAdicional");
        for (Iterator<CampoAdicional> it = infoAdicional.iterator(); it.hasNext();) {
            Element tagCampoAdicional = object.createElement("campoAdicional");
            CampoAdicional campoAdicional = it.next();
            tagCampoAdicional.setAttribute("nombre", campoAdicional.getNombre());
            tagCampoAdicional.setTextContent(campoAdicional.getValor());

            tagInfoAdicional.appendChild(tagCampoAdicional);
        }
        return tagInfoAdicional;

    }

    public Element construirXMLImpuestosComprobanteRetencion(List<ImpuestoComprobanteRetencion> impuestos, Document object) {
        Element tagImpuestos = object.createElement("impuestos");
        for (Iterator<ImpuestoComprobanteRetencion> it = impuestos.iterator(); it.hasNext();) {
            Element tagImpuesto = object.createElement("impuesto");
            ImpuestoComprobanteRetencion impuesto = it.next();

            Element tagCodigo = object.createElement("codigo");
            tagCodigo.setTextContent(impuesto.getCodigo());
            tagImpuesto.appendChild(tagCodigo);

            Element tagCodigoRetencion = object.createElement("codigoRetencion");
            tagCodigoRetencion.setTextContent(impuesto.getCodigoRetencion());
            tagImpuesto.appendChild(tagCodigoRetencion);

            Element tagBaseImponible = object.createElement("baseImponible");
            tagBaseImponible.setTextContent(impuesto.getBaseImponible());
            tagImpuesto.appendChild(tagBaseImponible);

            Element tagPorcentajeRetener = object.createElement("porcentajeRetener");
            tagPorcentajeRetener.setTextContent(impuesto.getPorcentajeRetener());
            tagImpuesto.appendChild(tagPorcentajeRetener);

            Element tagValorRetenido = object.createElement("valorRetenido");
            tagValorRetenido.setTextContent(impuesto.getValorRetenido());
            tagImpuesto.appendChild(tagValorRetenido);

            Element tagCodDocSustento = object.createElement("codDocSustento");
            tagCodDocSustento.setTextContent(impuesto.getCodDocSustento());
            tagImpuesto.appendChild(tagCodDocSustento);

            if (impuesto.getNumDocSustento() != null && !impuesto.getNumDocSustento().equals("")) {
                Element tagNumDocSustento = object.createElement("numDocSustento");
                tagNumDocSustento.setTextContent(impuesto.getNumDocSustento());
                tagImpuesto.appendChild(tagNumDocSustento);
            }
            if (impuesto.getFechaEmisionDocSustento() != null && !impuesto.getFechaEmisionDocSustento().equals("")) {
                Element tagFechaEmisionDocSustento = object.createElement("fechaEmisionDocSustento");
                tagFechaEmisionDocSustento.setTextContent(impuesto.getFechaEmisionDocSustento());
                tagImpuesto.appendChild(tagFechaEmisionDocSustento);
            }
            tagImpuestos.appendChild(tagImpuesto);
        }
        return tagImpuestos;

    }

    public Element construirXMLDestinatarios(List<Destinatario> destinatarios, Document object) {
        Element tagDestinatarios = object.createElement("destinatarios");
        for (Iterator<Destinatario> it = destinatarios.iterator(); it.hasNext();) {
            Element tagDestinatario = object.createElement("destinatario");
            Destinatario destinatario = it.next();

            Element tagIdentificacionDestinatario = object.createElement("identificacionDestinatario");
            tagIdentificacionDestinatario.setTextContent(destinatario.getIdentificacionDestinatario());
            tagDestinatario.appendChild(tagIdentificacionDestinatario);

            Element tagRazonSocialDestinatario = object.createElement("razonSocialDestinatario");
            tagRazonSocialDestinatario.setTextContent(destinatario.getRazonSocialDestinatario());
            tagDestinatario.appendChild(tagRazonSocialDestinatario);

            Element tagDirDestinatario = object.createElement("dirDestinatario");
            tagDirDestinatario.setTextContent(destinatario.getDirDestinatario());
            tagDestinatario.appendChild(tagDirDestinatario);

            Element tagMotivoTraslado = object.createElement("motivoTraslado");
            tagMotivoTraslado.setTextContent(destinatario.getMotivoTraslado());
            tagDestinatario.appendChild(tagMotivoTraslado);

            if (destinatario.getDocAduaneroUnico() != null && !destinatario.getDocAduaneroUnico().equals("")) {
                Element tagDocAduaneroUnico = object.createElement("docAduaneroUnico");
                tagDocAduaneroUnico.setTextContent(destinatario.getDocAduaneroUnico());
                tagDestinatario.appendChild(tagDocAduaneroUnico);
            }

            if (destinatario.getCodEstabDestino() != null && !destinatario.getCodEstabDestino().equals("")) {
                Element tagCodEstabDestino = object.createElement("codEstabDestino");
                tagCodEstabDestino.setTextContent(destinatario.getCodEstabDestino());
                tagDestinatario.appendChild(tagCodEstabDestino);
            }

            if (destinatario.getRuta() != null && !destinatario.getRuta().equals("")) {
                Element tagRuta = object.createElement("ruta");
                tagRuta.setTextContent(destinatario.getRuta());
                tagDestinatario.appendChild(tagRuta);
            }

            if (destinatario.getCodDocSustento() != null && !destinatario.getCodDocSustento().equals("")) {
                Element tagCodDocSustento = object.createElement("codDocSustento");
                tagCodDocSustento.setTextContent(destinatario.getCodDocSustento());
                tagDestinatario.appendChild(tagCodDocSustento);
            }

            if (destinatario.getNumDocSustento() != null && !destinatario.getNumDocSustento().equals("")) {
                Element tagNumDocSustento = object.createElement("numDocSustento");
                tagNumDocSustento.setTextContent(destinatario.getNumDocSustento());
                tagDestinatario.appendChild(tagNumDocSustento);
            }

            if (destinatario.getNumAutDocSustento() != null && !destinatario.getNumAutDocSustento().equals("")) {
                Element tagNumAutDocSustento = object.createElement("numAutDocSustento");
                tagNumAutDocSustento.setTextContent(destinatario.getNumAutDocSustento());
                tagDestinatario.appendChild(tagNumAutDocSustento);
            }

            if (destinatario.getFechaEmisionDocSustento() != null && !destinatario.getFechaEmisionDocSustento().equals("")) {
                Element tagFechaEmisionDocSustento = object.createElement("fechaEmisionDocSustento");
                tagFechaEmisionDocSustento.setTextContent(destinatario.getFechaEmisionDocSustento());
                tagDestinatario.appendChild(tagFechaEmisionDocSustento);
            }
            tagDestinatario.appendChild(construirXMLDetallesGuiaRemision(destinatario.getDetalles(), object));
            tagDestinatarios.appendChild(tagDestinatario);
        }
        return tagDestinatarios;

    }

    public Element construirXMLMotivos(List<Motivo> motivos, Document object) {
        Element tagMotivos = object.createElement("motivos");
        for (Iterator<Motivo> it = motivos.iterator(); it.hasNext();) {
            Element tagMotivo = object.createElement("motivo");
            Motivo motivo = it.next();

            Element tagRazon = object.createElement("razon");
            tagRazon.setTextContent(motivo.getRazon());
            tagMotivo.appendChild(tagRazon);

            Element tagValor = object.createElement("valor");
            tagValor.setTextContent(motivo.getValor());
            tagMotivo.appendChild(tagValor);

            tagMotivos.appendChild(tagMotivo);
        }
        return tagMotivos;

    }

    public Element construirXMLDetallesNotaCredito(List<DetalleNotaCredito> detalles, Document object) {
        Element tagDetalles = object.createElement("detalles");
        for (Iterator<DetalleNotaCredito> it = detalles.iterator(); it.hasNext();) {
            Element tagDetalle = object.createElement("detalle");
            DetalleNotaCredito detalle = it.next();

            Element tagCodigoInterno = object.createElement("codigoInterno");
            tagCodigoInterno.setTextContent(detalle.getCodigoInterno());
            tagDetalle.appendChild(tagCodigoInterno);

            if (detalle.getCodigoAdicional() != null && !detalle.getCodigoAdicional().equals("")) {
                Element tagCodigoAdicional = object.createElement("codigoAdicional");
                tagCodigoAdicional.setTextContent(detalle.getCodigoAdicional());
                tagDetalle.appendChild(tagCodigoAdicional);
            }

            Element tagDescripcion = object.createElement("descripcion");
            tagDescripcion.setTextContent(detalle.getDescripcion());
            tagDetalle.appendChild(tagDescripcion);

            Element tagCantidad = object.createElement("cantidad");
            tagCantidad.setTextContent(detalle.getCantidad());
            tagDetalle.appendChild(tagCantidad);

            Element tagPrecioUnitario = object.createElement("precioUnitario");
            tagPrecioUnitario.setTextContent(detalle.getPrecioUnitario());
            tagDetalle.appendChild(tagPrecioUnitario);

            if (detalle.getDescuento() != null && !detalle.getDescuento().equals("")) {
                Element tagDescuento = object.createElement("descuento");
                tagDescuento.setTextContent(detalle.getDescuento());
                tagDetalle.appendChild(tagDescuento);
            }

            Element tagPrecioTotalSinImpuesto = object.createElement("precioTotalSinImpuesto");
            tagPrecioTotalSinImpuesto.setTextContent(detalle.getPrecioTotalSinImpuesto());
            tagDetalle.appendChild(tagPrecioTotalSinImpuesto);

            if (detalle.getDetallesAdicionales() != null && !detalle.getDetallesAdicionales().isEmpty() && detalle.getDetallesAdicionales().get(0) != null) {
                tagDetalle.appendChild(construirXMLDetallesAdicionales(detalle.getDetallesAdicionales(), object));
            }
            tagDetalle.appendChild(construirXMLImpuestos(detalle.getImpuestos(), object));
            tagDetalles.appendChild(tagDetalle);
        }
        return tagDetalles;

    }

    public String claveAccesoContingencia(ComprobanteGeneral comprobante, String claveContingencia) {
        String fechaEmision = "";
        if (comprobante instanceof TiposComprobantes.GuiaRemision) {
            fechaEmision = ((TiposComprobantes.GuiaRemision) comprobante).getFechaIniTransporte();
        } else {
            fechaEmision = comprobante.getFechaEmision();
        }
        String claveAcceso = fechaEmision.replaceAll("/", "");
        claveAcceso += comprobante.getCodDoc();
        claveAcceso += claveContingencia;
        claveAcceso += comprobante.getTipoEmision();
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
}
