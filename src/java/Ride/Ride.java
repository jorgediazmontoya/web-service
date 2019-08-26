/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ride;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Yoelvys
 */
public class Ride {

    private final Font font7;
    private final Font font8;
    private final Font font9;
    private double totalDescuento;

    public Ride() {
        totalDescuento = 0;
        font7 = FontFactory.getFont(FontFactory.HELVETICA, 7, Font.NORMAL);
        font9 = FontFactory.getFont(FontFactory.HELVETICA, 9, Font.BOLD);
        font8 = FontFactory.getFont(FontFactory.HELVETICA, 8, Font.BOLD);
    }

    public void CrearRide(org.w3c.dom.Document xmlComprobante, String numAutorizacion, String dirGuardarDoc, String dirLogo) {

        try {
            String ruc = xmlComprobante.getElementsByTagName("ruc").item(0).getTextContent();
            String establecimiento = xmlComprobante.getElementsByTagName("estab").item(0).getTextContent();
            String ptoEmision = xmlComprobante.getElementsByTagName("ptoEmi").item(0).getTextContent();
            String secuencial = xmlComprobante.getElementsByTagName("secuencial").item(0).getTextContent();
            String noComprobante = establecimiento + "-" + ptoEmision + "-" + secuencial;
            String razonSocial = xmlComprobante.getElementsByTagName("razonSocial").item(0).getTextContent();
            String claveAcceso = xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent();
            String codDoc = xmlComprobante.getElementsByTagName("codDoc").item(0).getTextContent();
            String ambiente = xmlComprobante.getElementsByTagName("ambiente").item(0).getTextContent();
            String nombreComercial = "";
            String tipoCoprobante = tipoComprobante(codDoc);
            if (xmlComprobante.getElementsByTagName("nombreComercial").getLength() != 0) {
                nombreComercial = xmlComprobante.getElementsByTagName("nombreComercial").item(0).getTextContent();
            }
            String dirMatriz = xmlComprobante.getElementsByTagName("dirMatriz").item(0).getTextContent();
            String dirEstablecimiento = "";
            if (xmlComprobante.getElementsByTagName("dirEstablecimiento").getLength() != 0) {
                dirEstablecimiento = xmlComprobante.getElementsByTagName("dirEstablecimiento").item(0).getTextContent();
            }
            String contribuyenteEspecial = "";
            if (xmlComprobante.getElementsByTagName("contribuyenteEspecial").getLength() != 0) {
                contribuyenteEspecial = xmlComprobante.getElementsByTagName("contribuyenteEspecial").item(0).getTextContent();
            }
            String obligadoContabilidad = xmlComprobante.getElementsByTagName("obligadoContabilidad").item(0).getTextContent();
            // Se crea el documento
            Document documento = new Document();

            dirGuardarDoc = dirGuardarDoc.replace(".xml", ".pdf");
            FileOutputStream ficheroPdf = new FileOutputStream(dirGuardarDoc);

            PdfWriter pdfw = PdfWriter.getInstance(documento, ficheroPdf);
// Se abre el documento.
            documento.open();
//Add Image

            Image image1 = Image.getInstance(dirLogo);
            //Fixed Positioning
            //Scale to new height and new width of image
            image1.scaleAbsolute(100, 80);

            PdfPTable tabla = new PdfPTable(2);
            tabla.setWidthPercentage(90);
            tabla.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            PdfPCell cellLogo = new PdfPCell(image1);
            cellLogo.setPadding(10);
            cellLogo.setHorizontalAlignment(Element.ALIGN_CENTER);
            cellLogo.setBorder(Rectangle.NO_BORDER);
            tabla.addCell(cellLogo);

            PdfPTable tablDatosComprobante = new PdfPTable(1);

            PdfPCell datosComprobante = new PdfPCell(new Phrase("R.U.C.: " + ruc, font8));
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(10);
            datosComprobante.setBorder(0);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase(tipoCoprobante, font9));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(10);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase("No.  " + noComprobante, font7));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(15);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase("NUMERO DE AUTORIZACION", font8));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(10);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase(numAutorizacion, font7));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(15);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase("AMBIENTE: " + (ambiente.equals("1") ? "PRUEBA" : "PRODUCCION"), font8));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(10);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase("EMISION: NORMAL", font8));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(10);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(new Phrase("CLAVE DE ACCESO", font8));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(5);
            datosComprobante.setPaddingLeft(10);
            datosComprobante.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDatosComprobante.addCell(datosComprobante);

            datosComprobante = new PdfPCell(getBarcode(claveAcceso, pdfw));
            datosComprobante.setBorder(0);
            datosComprobante.setPadding(2);
            tablDatosComprobante.addCell(datosComprobante);

            PdfPCell cellDatosComprobante = new PdfPCell(tablDatosComprobante);
            cellDatosComprobante.setRowspan(2);
            cellDatosComprobante.setPadding(2);
            cellDatosComprobante.setBorder(Rectangle.NO_BORDER);
            cellDatosComprobante.setCellEvent(new SpecialRoundedCell());
            tabla.addCell(cellDatosComprobante);

            PdfPTable tablDatosEmisor = new PdfPTable(1);
            PdfPCell datosEmisor = new PdfPCell(new Phrase(razonSocial, font9));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            datosEmisor = new PdfPCell(new Phrase(nombreComercial, font8));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            datosEmisor = new PdfPCell(new Phrase("Dirección Matriz: " + dirMatriz, font7));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            datosEmisor = new PdfPCell(new Phrase("Dirección Establecimiento: " + dirEstablecimiento, font7));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            datosEmisor = new PdfPCell(new Phrase("Contribuyente Especial Nro: " + contribuyenteEspecial, font7));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            datosEmisor = new PdfPCell(new Phrase("OBLIGADO A LLEVAR CONTABILIDAD: " + obligadoContabilidad, font7));
            datosEmisor.setBorder(0);
            datosEmisor.setPadding(5);
            datosEmisor.setPaddingLeft(10);
            tablDatosEmisor.addCell(datosEmisor);

            PdfPCell cellDatosEmisor = new PdfPCell(tablDatosEmisor);
            cellDatosEmisor.setPadding(2);
            cellDatosEmisor.setBorder(Rectangle.NO_BORDER);
            cellDatosEmisor.setCellEvent(new SpecialRoundedCell());
            tabla.addCell(cellDatosEmisor);

            if (tipoCoprobante.equals("FACTURA")) {
                PdfPCell cellDatosComprobanteGeneral = new PdfPCell(crearTablaFactura(xmlComprobante));
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                cellDatosComprobanteGeneral.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(cellDatosComprobanteGeneral);
                tabla.addCell(crearTablaInformacionAdicional(xmlComprobante));
                tabla.addCell(crearTablaResumenValores(xmlComprobante));

                cellDatosComprobanteGeneral = new PdfPCell(crearTablaFormaPago(xmlComprobante));
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setColspan(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(cellDatosComprobanteGeneral);

            } else if (tipoCoprobante.equals("NOTA DE CRÉDITO")) {
                PdfPCell cellDatosComprobanteGeneral = new PdfPCell(crearTablaNotaCredito(xmlComprobante));
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                cellDatosComprobanteGeneral.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(cellDatosComprobanteGeneral);
                tabla.addCell(crearTablaInformacionAdicional(xmlComprobante));
                tabla.addCell(crearTablaResumenValores(xmlComprobante));
            } else if (tipoCoprobante.equals("NOTA DE DÉBITO")) {
                PdfPCell cellDatosComprobanteGeneral = new PdfPCell(crearTablaNotaDebito(xmlComprobante));
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                cellDatosComprobanteGeneral.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(cellDatosComprobanteGeneral);
                tabla.addCell(crearTablaInformacionAdicional(xmlComprobante));
                tabla.addCell(crearTablaResumenValores(xmlComprobante));
            } else if (tipoCoprobante.equals("GUÍA DE REMISIÓN")) {
                PdfPCell cellDatosComprobanteGeneral = new PdfPCell(crearTablaGuiaRemision(xmlComprobante));
                cellDatosComprobanteGeneral.setBorder(0);
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                cellDatosComprobanteGeneral.setBorder(Rectangle.NO_BORDER);
                tabla.addCell(cellDatosComprobanteGeneral);

                org.w3c.dom.NodeList destinatarios = xmlComprobante.getElementsByTagName("destinatario");
                int cantidad = destinatarios.getLength();
                for (int i = 0; i < cantidad; i++) {
                    cellDatosComprobanteGeneral = new PdfPCell(crearDestinatarioGuiaRemision((org.w3c.dom.Element) destinatarios.item(i)));
                    cellDatosComprobanteGeneral.setBorder(0);
                    cellDatosComprobanteGeneral.setPadding(2);
                    cellDatosComprobanteGeneral.setPaddingTop(10);
                    cellDatosComprobanteGeneral.setColspan(2);
                    tabla.addCell(cellDatosComprobanteGeneral);
                }

                PdfPCell cellInformacionAdicional = new PdfPCell(crearTablaInformacionAdicional(xmlComprobante));
                cellInformacionAdicional.setBorder(0);
                cellInformacionAdicional.setPaddingBottom(10);
                cellInformacionAdicional.setPaddingLeft(90);
                cellInformacionAdicional.setPaddingRight(90);
                cellInformacionAdicional.setColspan(2);
                tabla.addCell(cellInformacionAdicional);

                PdfPCell cellFirma = new PdfPCell(new Phrase("Recibido por: \n Solo firme si ha recibido TODOS los productos y en las cantidades especificadas"
                        + " en el documento.", font7));
                cellFirma.setPaddingBottom(10);
                cellFirma.setHorizontalAlignment(Element.ALIGN_CENTER);
                cellFirma.setColspan(2);
                tabla.addCell(cellFirma);

            } else if (tipoCoprobante.equals("COMPROBANTE DE RETENCIÓN")) {
                PdfPCell cellDatosComprobanteGeneral = new PdfPCell(crearTablaComprobanteRetencion(xmlComprobante));
                cellDatosComprobanteGeneral.setBorder(0);
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                tabla.addCell(cellDatosComprobanteGeneral);

                cellDatosComprobanteGeneral = new PdfPCell(crearTablaImpuestosComprobanteRetencion(xmlComprobante));
                cellDatosComprobanteGeneral.setBorder(0);
                cellDatosComprobanteGeneral.setPadding(2);
                cellDatosComprobanteGeneral.setPaddingTop(10);
                cellDatosComprobanteGeneral.setColspan(2);
                tabla.addCell(cellDatosComprobanteGeneral);

                PdfPCell cellInformacionAdicional = new PdfPCell(crearTablaInformacionAdicional(xmlComprobante));
                cellInformacionAdicional.setBorder(0);
                cellInformacionAdicional.setPaddingBottom(10);
                cellInformacionAdicional.setPaddingLeft(90);
                cellInformacionAdicional.setPaddingRight(90);
                cellInformacionAdicional.setColspan(2);
                tabla.addCell(cellInformacionAdicional);
            }
            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private PdfPTable crearTablaImpuestosComprobanteRetencion(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        PdfPTable tablImpuestosComprobanteRetencion = new PdfPTable(7);
        float[] columnWidths = new float[]{14f, 16f, 12f, 10f, 10f, 10f, 10f};
        tablImpuestosComprobanteRetencion.setWidths(columnWidths);

        PdfPCell datosImpuesto = new PdfPCell(new Phrase("Número Factura", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Ejercicio Fiscal", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Base Imponible para la Retención", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Impuesto", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Cod Retención", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Porcentaje Retención", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        datosImpuesto = new PdfPCell(new Phrase("Valor Retenido", font7));
        datosImpuesto.setPadding(1);
        datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

        org.w3c.dom.NodeList impuestos = xmlComprobante.getElementsByTagName("impuesto");
        int cantidad = impuestos.getLength();

        for (int i = 0; i < cantidad; i++) {

            org.w3c.dom.Element element = (org.w3c.dom.Element) impuestos.item(i);

            datosImpuesto = new PdfPCell(new Phrase(element.getElementsByTagName("numDocSustento").getLength() > 0 ? element.getElementsByTagName("numDocSustento").item(0).getTextContent() : "", font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            datosImpuesto = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("periodoFiscal").item(0).getTextContent(), font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            datosImpuesto = new PdfPCell(new Phrase(element.getElementsByTagName("baseImponible").item(0).getTextContent(), font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            String codigoImpuesto = element.getElementsByTagName("codigo").item(0).getTextContent();
            String impuestoRetener = "";
            if (codigoImpuesto.equals("1")) {
                impuestoRetener = "RENTA";
            } else if (codigoImpuesto.equals("2")) {
                impuestoRetener = "IVA";
            } else {
                impuestoRetener = "ISD";
            }
            datosImpuesto = new PdfPCell(new Phrase(impuestoRetener, font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            datosImpuesto = new PdfPCell(new Phrase(element.getElementsByTagName("codigoRetencion").item(0).getTextContent(), font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            datosImpuesto = new PdfPCell(new Phrase(element.getElementsByTagName("porcentajeRetener").item(0).getTextContent(), font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);

            datosImpuesto = new PdfPCell(new Phrase(element.getElementsByTagName("valorRetenido").item(0).getTextContent(), font7));
            datosImpuesto.setPadding(2);
            datosImpuesto.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablImpuestosComprobanteRetencion.addCell(datosImpuesto);
        }

        return tablImpuestosComprobanteRetencion;
    }

    private PdfPTable crearDestinatarioGuiaRemision(org.w3c.dom.Element element) {
        PdfPTable tablDestinatarioGuiaRemision = new PdfPTable(1);

        PdfPCell detalleDestinatario = new PdfPCell(new Phrase("Comprobante de Venta:   "
                + "                   " + (element.getElementsByTagName("codDocSustento").getLength() > 0 ? "FACTURA" : "") + ""
                + "              " + (element.getElementsByTagName("numDocSustento").getLength() > 0 ? element.getElementsByTagName("numDocSustento").item(0).getTextContent() : "") + ""
                + "              Fecha Emisión:           "
                + "" + (element.getElementsByTagName("fechaEmisionDocSustento").getLength() > 0 ? element.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent() : ""), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.TOP);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        detalleDestinatario = new PdfPCell(new Phrase("Número Autorización:                         " + (element.getElementsByTagName("numAutDocSustento").getLength() > 0 ? element.getElementsByTagName("numAutDocSustento").item(0).getTextContent() : ""), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        detalleDestinatario = new PdfPCell(new Phrase("Motivo Traslado:                                 " + element.getElementsByTagName("motivoTraslado").item(0).getTextContent(), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        detalleDestinatario = new PdfPCell(new Phrase("Destino(Punto de Llegada):                " + element.getElementsByTagName("dirDestinatario").item(0).getTextContent(), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        detalleDestinatario = new PdfPCell(new Phrase("Identificación(Destinatario):                " + element.getElementsByTagName("identificacionDestinatario").item(0).getTextContent(), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        detalleDestinatario = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + element.getElementsByTagName("razonSocialDestinatario").item(0).getTextContent(), font7));
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        detalleDestinatario.setPadding(5);
        detalleDestinatario.setPaddingLeft(10);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        if (element.getElementsByTagName("docAduaneroUnico").getLength() > 0) {
            detalleDestinatario = new PdfPCell(new Phrase("Documento Aduanero:                        " + element.getElementsByTagName("docAduaneroUnico").item(0).getTextContent(), font7));
            detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
            detalleDestinatario.setPadding(5);
            detalleDestinatario.setPaddingLeft(10);
            tablDestinatarioGuiaRemision.addCell(detalleDestinatario);
        }
        if (element.getElementsByTagName("codEstabDestino").getLength() > 0) {
            detalleDestinatario = new PdfPCell(new Phrase("Codigo Establecimiento Destino:        " + element.getElementsByTagName("codEstabDestino").item(0).getTextContent(), font7));
            detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
            detalleDestinatario.setPadding(5);
            detalleDestinatario.setPaddingLeft(10);
            tablDestinatarioGuiaRemision.addCell(detalleDestinatario);
        }
        if (element.getElementsByTagName("ruta").getLength() > 0) {
            detalleDestinatario = new PdfPCell(new Phrase("Ruta:                                                   " + element.getElementsByTagName("ruta").item(0).getTextContent(), font7));
            detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
            detalleDestinatario.setPadding(5);
            detalleDestinatario.setPaddingLeft(10);
            tablDestinatarioGuiaRemision.addCell(detalleDestinatario);
        }
        NodeList detAdicional = element.getElementsByTagName("detAdicional");
        int cantidad = detAdicional.getLength();

        PdfPTable tablDetalles = new PdfPTable(4 + cantidad);

        PdfPCell datosDetalles = new PdfPCell(new Phrase("Cantidad", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDetalles.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Descripción", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDetalles.addCell(datosDetalles);

        for (int i = 0; i < cantidad; i++) {
            Node node = detAdicional.item(i);
            String nombre = node.getAttributes().item(0).getNodeValue();
            datosDetalles = new PdfPCell(new Phrase(nombre, font7));
            datosDetalles.setPadding(1);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDetalles.addCell(datosDetalles);
        }
        datosDetalles = new PdfPCell(new Phrase("Código Principal", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDetalles.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Código Auxiliar", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDetalles.addCell(datosDetalles);

        org.w3c.dom.NodeList detalles = element.getElementsByTagName("detalle");
        cantidad = detalles.getLength();

        for (int i = 0; i < cantidad; i++) {
            org.w3c.dom.Element detalle = (org.w3c.dom.Element) detalles.item(i);

            datosDetalles = new PdfPCell(new Phrase(detalle.getElementsByTagName("cantidad").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDetalles.addCell(datosDetalles);

            datosDetalles = new PdfPCell(new Phrase(detalle.getElementsByTagName("descripcion").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDetalles.addCell(datosDetalles);
            int cantidadDetAdicional = detAdicional.getLength();
            for (int j = 0; j < cantidadDetAdicional; j++) {
                Node node = detAdicional.item(j);
                String nombre = node.getAttributes().item(1).getNodeValue();
                datosDetalles = new PdfPCell(new Phrase(nombre, font7));
                datosDetalles.setPadding(1);
                datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
                tablDetalles.addCell(datosDetalles);
            }

            datosDetalles = new PdfPCell(new Phrase(detalle.getElementsByTagName("codigoInterno").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDetalles.addCell(datosDetalles);

            if (element.getElementsByTagName("codigoAdicional").getLength() != 0) {
                datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("codigoAdicional").item(0).getTextContent(), font7));
            } else {
                datosDetalles = new PdfPCell(new Phrase("", font7));
            }
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDetalles.addCell(datosDetalles);
        }
        detalleDestinatario = new PdfPCell(tablDetalles);
        detalleDestinatario.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
        detalleDestinatario.setPadding(10);
        detalleDestinatario.setPaddingLeft(40);
        detalleDestinatario.setPaddingRight(40);
        tablDestinatarioGuiaRemision.addCell(detalleDestinatario);

        return tablDestinatarioGuiaRemision;
    }

    private PdfPTable crearTablaFormaPago(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        PdfPTable tablTablaFormaPago = new PdfPTable(2);
        tablTablaFormaPago.setWidthPercentage(40);
        tablTablaFormaPago.setHorizontalAlignment(Element.ALIGN_RIGHT);
        float[] columnWidths = new float[]{15f, 40f};
        tablTablaFormaPago.setWidths(columnWidths);
        NodeList pago = xmlComprobante.getElementsByTagName("pago");
        int cantidad = pago.getLength();
        PdfPCell formaPagoCell = new PdfPCell(new Phrase("", font8));
        if (cantidad > 0) {
            formaPagoCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
        } else {
            formaPagoCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
        }
        formaPagoCell.setPadding(1);
        formaPagoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        formaPagoCell.setColspan(2);
        tablTablaFormaPago.addCell(formaPagoCell);
        for (int i = 0; i < cantidad; i++) {
            org.w3c.dom.Element element = (org.w3c.dom.Element) pago.item(i);

            formaPagoCell = new PdfPCell(new Phrase("Forma Pago", font8));
            formaPagoCell.setBorder(Rectangle.LEFT);
            formaPagoCell.setPadding(1);
            tablTablaFormaPago.addCell(formaPagoCell);

            formaPagoCell = new PdfPCell(new Phrase(formaDePago(element.getElementsByTagName("formaPago").item(0).getTextContent()), font7));
            formaPagoCell.setBorder(Rectangle.RIGHT);
            formaPagoCell.setPadding(1);
            tablTablaFormaPago.addCell(formaPagoCell);

            formaPagoCell = new PdfPCell(new Phrase("Total", font8));
            formaPagoCell.setBorder(Rectangle.LEFT);
            formaPagoCell.setPadding(1);
            tablTablaFormaPago.addCell(formaPagoCell);

            formaPagoCell = new PdfPCell(new Phrase(element.getElementsByTagName("total").item(0).getTextContent(), font7));
            formaPagoCell.setBorder(Rectangle.RIGHT);
            formaPagoCell.setPadding(1);
            tablTablaFormaPago.addCell(formaPagoCell);

            if (element.getElementsByTagName("plazo").getLength() != 0) {
                formaPagoCell = new PdfPCell(new Phrase("Plazo", font8));
                formaPagoCell.setBorder(Rectangle.LEFT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);

                formaPagoCell = new PdfPCell(new Phrase(element.getElementsByTagName("plazo").item(0).getTextContent(), font7));
                formaPagoCell.setBorder(Rectangle.RIGHT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);
            }

            if (element.getElementsByTagName("unidadTiempo").getLength() != 0) {
                formaPagoCell = new PdfPCell(new Phrase("Unidad Tiempo", font8));
                formaPagoCell.setBorder(Rectangle.LEFT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);

                formaPagoCell = new PdfPCell(new Phrase(element.getElementsByTagName("unidadTiempo").item(0).getTextContent(), font7));
                formaPagoCell.setBorder(Rectangle.RIGHT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);
            }
            if (i >= cantidad - 1) {
                formaPagoCell = new PdfPCell(new Phrase("", font8));
                formaPagoCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);

                formaPagoCell = new PdfPCell(new Phrase("", font8));
                formaPagoCell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);
            } else {
                formaPagoCell = new PdfPCell(new Phrase("", font8));
                formaPagoCell.setBorder(Rectangle.LEFT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);

                formaPagoCell = new PdfPCell(new Phrase("", font8));
                formaPagoCell.setBorder(Rectangle.RIGHT);
                formaPagoCell.setPadding(1);
                tablTablaFormaPago.addCell(formaPagoCell);
            }

        }

        return tablTablaFormaPago;
    }

    private PdfPTable crearTablaSubsidio() throws DocumentException {
        PdfPTable tablTablaSubsidio = new PdfPTable(2);
        tablTablaSubsidio.setWidthPercentage(40);
        tablTablaSubsidio.setHorizontalAlignment(Element.ALIGN_RIGHT);
        float[] columnWidths = new float[]{50f, 10f};
        tablTablaSubsidio.setWidths(columnWidths);

        PdfPCell campoAdicionalCell = new PdfPCell(new Phrase("VALOR TOTAL SIN SUBSIDIO", font8));
        campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
        campoAdicionalCell.setPadding(1);
        campoAdicionalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablTablaSubsidio.addCell(campoAdicionalCell);

        campoAdicionalCell = new PdfPCell(new Phrase("0.00", font8));
        campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
        campoAdicionalCell.setPadding(1);
        campoAdicionalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablTablaSubsidio.addCell(campoAdicionalCell);

        campoAdicionalCell = new PdfPCell(new Phrase("AHORRO POR SUBSIDIO:", font8));
        campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
        campoAdicionalCell.setPadding(1);
        campoAdicionalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablTablaSubsidio.addCell(campoAdicionalCell);

        campoAdicionalCell = new PdfPCell(new Phrase("0.00", font8));
        campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
        campoAdicionalCell.setPadding(1);
        campoAdicionalCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablTablaSubsidio.addCell(campoAdicionalCell);

        return tablTablaSubsidio;
    }

    private PdfPTable crearTablaFactura(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
        String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();
        String guiaRemision = "";
        if (xmlComprobante.getElementsByTagName("guiaRemision").getLength() != 0) {
            guiaRemision = xmlComprobante.getElementsByTagName("guiaRemision").item(0).getTextContent();
        }

        PdfPTable tablDatosFactura = new PdfPTable(7);
        float[] columnWidths = new float[]{10f, 10f, 6f, 40f, 10f, 10f, 10f};
        tablDatosFactura.setWidths(columnWidths);
        PdfPCell datosFactura = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + razonSocialComprador, font7));
        datosFactura.setBorder(Rectangle.TOP | Rectangle.LEFT);
        datosFactura.setPadding(5);
        datosFactura.setPaddingLeft(10);
        datosFactura.setColspan(4);
        tablDatosFactura.addCell(datosFactura);

        datosFactura = new PdfPCell(new Phrase("Identificación:  " + identificacionComprador, font7));
        datosFactura.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        datosFactura.setPadding(5);
        datosFactura.setPaddingLeft(2);
        datosFactura.setColspan(3);
        tablDatosFactura.addCell(datosFactura);

        datosFactura = new PdfPCell(new Phrase("Fecha Emisión:   " + fechaEmision, font7));
        datosFactura.setBorder(Rectangle.BOTTOM | Rectangle.LEFT);
        datosFactura.setPadding(5);
        datosFactura.setPaddingLeft(10);
        datosFactura.setColspan(4);
        tablDatosFactura.addCell(datosFactura);

        datosFactura = new PdfPCell(new Phrase("Guía de Remisión:  " + guiaRemision, font7));
        datosFactura.setBorder(Rectangle.BOTTOM | Rectangle.RIGHT);
        datosFactura.setPadding(5);
        datosFactura.setPaddingLeft(2);
        datosFactura.setColspan(3);
        tablDatosFactura.addCell(datosFactura);
        crearDetalles(tablDatosFactura, xmlComprobante);

        return tablDatosFactura;
    }

    private PdfPTable crearTablaNotaCredito(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
        String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();

        PdfPTable tablDatosNotaCredito = new PdfPTable(7);
        float[] columnWidths = new float[]{10f, 10f, 6f, 40f, 10f, 10f, 10f};
        tablDatosNotaCredito.setWidths(columnWidths);
        PdfPCell datosNotaCredito = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + razonSocialComprador, font7));
        datosNotaCredito.setBorder(Rectangle.TOP | Rectangle.LEFT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(10);
        datosNotaCredito.setColspan(4);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        datosNotaCredito = new PdfPCell(new Phrase("Identificación:  " + identificacionComprador, font7));
        datosNotaCredito.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(2);
        datosNotaCredito.setColspan(3);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        datosNotaCredito = new PdfPCell(new Phrase("Fecha Emisión:   " + fechaEmision, font7));
        datosNotaCredito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(10);
        datosNotaCredito.setColspan(7);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        String comprobanteModificado = tipoComprobante(xmlComprobante.getElementsByTagName("codDocModificado").item(0).getTextContent());
        datosNotaCredito = new PdfPCell(new Phrase("Comprobante que se modifica:"
                + "                                                            " + comprobanteModificado + "             " + xmlComprobante.getElementsByTagName("numDocModificado").item(0).getTextContent(), font7));
        datosNotaCredito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(10);
        datosNotaCredito.setColspan(7);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        datosNotaCredito = new PdfPCell(new Phrase("Fecha Emisión:(Comprobante a modificar)"
                + "                                         " + xmlComprobante.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent(), font7));
        datosNotaCredito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(10);
        datosNotaCredito.setColspan(7);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        datosNotaCredito = new PdfPCell(new Phrase("Razón Modificación:"
                + "                                                                            " + xmlComprobante.getElementsByTagName("motivo").item(0).getTextContent(), font7));
        datosNotaCredito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaCredito.setPadding(5);
        datosNotaCredito.setPaddingLeft(10);
        datosNotaCredito.setColspan(7);
        tablDatosNotaCredito.addCell(datosNotaCredito);

        crearDetalles(tablDatosNotaCredito, xmlComprobante);

        return tablDatosNotaCredito;
    }

    private PdfPTable crearTablaNotaDebito(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
        String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();

        PdfPTable tablDatosNotaDebito = new PdfPTable(7);
        float[] columnWidths = new float[]{10f, 10f, 6f, 40f, 10f, 10f, 10f};
        tablDatosNotaDebito.setWidths(columnWidths);
        PdfPCell datosNotaDebito = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + razonSocialComprador, font7));
        datosNotaDebito.setBorder(Rectangle.TOP | Rectangle.LEFT);
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(4);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        datosNotaDebito = new PdfPCell(new Phrase("Identificación:  " + identificacionComprador, font7));
        datosNotaDebito.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(2);
        datosNotaDebito.setColspan(3);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        datosNotaDebito = new PdfPCell(new Phrase("Fecha Emisión:   " + fechaEmision, font7));
        datosNotaDebito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(7);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        String comprobanteModificado = tipoComprobante(xmlComprobante.getElementsByTagName("codDocModificado").item(0).getTextContent());
        datosNotaDebito = new PdfPCell(new Phrase("Comprobante que se modifica:"
                + "                                                            " + comprobanteModificado + "             " + xmlComprobante.getElementsByTagName("numDocModificado").item(0).getTextContent(), font7));
        datosNotaDebito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(7);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        datosNotaDebito = new PdfPCell(new Phrase("Fecha Emisión:(Comprobante a modificar)"
                + "                                         " + xmlComprobante.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent(), font7));
        datosNotaDebito.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(7);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        datosNotaDebito = new PdfPCell(new Phrase("RAZON MODIFICACION", font8));
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(4);
        datosNotaDebito.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        datosNotaDebito = new PdfPCell(new Phrase("VALOR MODIFICACION", font8));
        datosNotaDebito.setPadding(5);
        datosNotaDebito.setPaddingLeft(10);
        datosNotaDebito.setColspan(3);
        datosNotaDebito.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablDatosNotaDebito.addCell(datosNotaDebito);

        org.w3c.dom.NodeList motivos = xmlComprobante.getElementsByTagName("motivo");
        int cantidad = motivos.getLength();
        for (int i = 0; i < cantidad; i++) {

            org.w3c.dom.Element motivo = (org.w3c.dom.Element) motivos.item(i);
            datosNotaDebito = new PdfPCell(new Phrase(motivo.getElementsByTagName("razon").item(0).getTextContent(), font8));
            datosNotaDebito.setPadding(5);
            datosNotaDebito.setPaddingLeft(10);
            datosNotaDebito.setColspan(4);
            datosNotaDebito.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDatosNotaDebito.addCell(datosNotaDebito);

            datosNotaDebito = new PdfPCell(new Phrase(motivo.getElementsByTagName("valor").item(0).getTextContent(), font8));
            datosNotaDebito.setPadding(5);
            datosNotaDebito.setPaddingLeft(10);
            datosNotaDebito.setColspan(3);
            datosNotaDebito.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablDatosNotaDebito.addCell(datosNotaDebito);
        }
        return tablDatosNotaDebito;
    }

    private void crearDetalles(PdfPTable tablaComprobante, org.w3c.dom.Document xmlComprobante) {
        PdfPCell datosDetalles = new PdfPCell(new Phrase("Cod. Principal", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Cod. Auxiliar", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Cant", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Descripción", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Precio Unitario", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Descuento", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        datosDetalles = new PdfPCell(new Phrase("Total Sin Impuestos", font7));
        datosDetalles.setPadding(1);
        datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
        tablaComprobante.addCell(datosDetalles);

        org.w3c.dom.NodeList detalles = xmlComprobante.getElementsByTagName("detalle");
        int cantidad = detalles.getLength();

        for (int i = 0; i < cantidad; i++) {

            org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(i);
            if (element.getElementsByTagName("codigoPrincipal").getLength() != 0) {
                datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("codigoPrincipal").item(0).getTextContent(), font7));
            } else if (element.getElementsByTagName("codigoInterno").getLength() != 0) {
                datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("codigoInterno").item(0).getTextContent(), font7));
            }
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaComprobante.addCell(datosDetalles);

            if (element.getElementsByTagName("codigoAuxiliar").getLength() != 0) {
                datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("codigoAuxiliar").item(0).getTextContent(), font7));
            } else if (element.getElementsByTagName("codigoAdicional").getLength() != 0) {
                datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("codigoAdicional").item(0).getTextContent(), font7));
            } else {
                datosDetalles = new PdfPCell(new Phrase("", font7));
            }
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaComprobante.addCell(datosDetalles);

            datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("cantidad").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_CENTER);
            tablaComprobante.addCell(datosDetalles);

            datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("descripcion").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablaComprobante.addCell(datosDetalles);

            datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("precioUnitario").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaComprobante.addCell(datosDetalles);

            String descuento = element.getElementsByTagName("descuento").item(0).getTextContent();
            this.totalDescuento += Double.parseDouble(descuento);
            datosDetalles = new PdfPCell(new Phrase(descuento, font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaComprobante.addCell(datosDetalles);

            datosDetalles = new PdfPCell(new Phrase(element.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent(), font7));
            datosDetalles.setPadding(2);
            datosDetalles.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablaComprobante.addCell(datosDetalles);
        }

    }

    private PdfPTable crearTablaComprobanteRetencion(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialSujetoRetenido").item(0).getTextContent();
        String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionSujetoRetenido").item(0).getTextContent();
        String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();

        PdfPTable tablComprobanteRetencion = new PdfPTable(7);
        float[] columnWidths = new float[]{10f, 10f, 6f, 40f, 10f, 10f, 10f};
        tablComprobanteRetencion.setWidths(columnWidths);
        PdfPCell datosComprobanteRetencion = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + razonSocialComprador, font7));
        datosComprobanteRetencion.setBorder(Rectangle.LEFT | Rectangle.TOP);
        datosComprobanteRetencion.setPadding(5);
        datosComprobanteRetencion.setPaddingLeft(10);
        datosComprobanteRetencion.setColspan(4);
        tablComprobanteRetencion.addCell(datosComprobanteRetencion);

        datosComprobanteRetencion = new PdfPCell(new Phrase("Identificación:  " + identificacionComprador, font7));
        datosComprobanteRetencion.setBorder(Rectangle.RIGHT | Rectangle.TOP);
        datosComprobanteRetencion.setPadding(5);
        datosComprobanteRetencion.setPaddingLeft(2);
        datosComprobanteRetencion.setColspan(3);
        tablComprobanteRetencion.addCell(datosComprobanteRetencion);

        datosComprobanteRetencion = new PdfPCell(new Phrase("Fecha Emisión:   " + fechaEmision, font7));
        datosComprobanteRetencion.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM | Rectangle.LEFT);
        datosComprobanteRetencion.setPadding(5);
        datosComprobanteRetencion.setPaddingLeft(10);
        datosComprobanteRetencion.setColspan(7);
        tablComprobanteRetencion.addCell(datosComprobanteRetencion);

        return tablComprobanteRetencion;
    }

    private PdfPTable crearTablaGuiaRemision(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        String razonSocialTransportista = xmlComprobante.getElementsByTagName("razonSocialTransportista").item(0).getTextContent();
        String rucTransportista = xmlComprobante.getElementsByTagName("rucTransportista").item(0).getTextContent();
        String placa = xmlComprobante.getElementsByTagName("placa").item(0).getTextContent();
        String dirPartida = xmlComprobante.getElementsByTagName("dirPartida").item(0).getTextContent();
        String fechaIniTransporte = xmlComprobante.getElementsByTagName("fechaIniTransporte").item(0).getTextContent();
        String fechaFinTransporte = xmlComprobante.getElementsByTagName("fechaFinTransporte").item(0).getTextContent();

        PdfPTable tablGuiaRemision = new PdfPTable(7);
        float[] columnWidths = new float[]{10f, 10f, 6f, 40f, 10f, 10f, 10f};
        tablGuiaRemision.setWidths(columnWidths);
        PdfPCell datosGuiaRemision = new PdfPCell(new Phrase("Razón Social/Nombres y Apellidos:   " + razonSocialTransportista, font7));
        datosGuiaRemision.setBorder(Rectangle.TOP | Rectangle.LEFT);
        datosGuiaRemision.setPadding(5);
        datosGuiaRemision.setPaddingLeft(10);
        datosGuiaRemision.setColspan(4);
        tablGuiaRemision.addCell(datosGuiaRemision);

        datosGuiaRemision = new PdfPCell(new Phrase("Identificación(Transportista):  " + rucTransportista, font7));
        datosGuiaRemision.setBorder(Rectangle.TOP | Rectangle.RIGHT);
        datosGuiaRemision.setPadding(5);
        datosGuiaRemision.setPaddingLeft(2);
        datosGuiaRemision.setColspan(3);
        tablGuiaRemision.addCell(datosGuiaRemision);

        datosGuiaRemision = new PdfPCell(new Phrase("Placa:                                     " + placa, font7));
        datosGuiaRemision.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosGuiaRemision.setPadding(5);
        datosGuiaRemision.setPaddingLeft(10);
        datosGuiaRemision.setColspan(7);
        tablGuiaRemision.addCell(datosGuiaRemision);

        datosGuiaRemision = new PdfPCell(new Phrase("Punto Partida:                        " + dirPartida, font7));
        datosGuiaRemision.setBorder(Rectangle.RIGHT | Rectangle.LEFT);
        datosGuiaRemision.setPadding(5);
        datosGuiaRemision.setPaddingLeft(10);
        datosGuiaRemision.setColspan(7);
        tablGuiaRemision.addCell(datosGuiaRemision);

        datosGuiaRemision = new PdfPCell(new Phrase("Fecha Inicio Transporte:       " + fechaIniTransporte + "                                       Fecha Fin Transporte:  " + fechaFinTransporte, font7));
        datosGuiaRemision.setBorder(Rectangle.RIGHT | Rectangle.LEFT | Rectangle.BOTTOM);
        datosGuiaRemision.setPadding(5);
        datosGuiaRemision.setPaddingLeft(10);
        datosGuiaRemision.setColspan(7);
        tablGuiaRemision.addCell(datosGuiaRemision);

        return tablGuiaRemision;
    }

    private PdfPTable crearTablaResumenValores(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        PdfPTable tablResumenValores = new PdfPTable(2);
        tablResumenValores.setWidthPercentage(40);
        tablResumenValores.setHorizontalAlignment(Element.ALIGN_RIGHT);
        float[] columnWidths = new float[]{40f, 11f};
        tablResumenValores.setWidths(columnWidths);
        org.w3c.dom.NodeList totalImpuesto = xmlComprobante.getElementsByTagName("totalImpuesto");
        int cantidad = totalImpuesto.getLength();
        boolean presentaDescuento = true;
        if (cantidad == 0) {
            presentaDescuento = false;
            totalImpuesto = xmlComprobante.getElementsByTagName("impuesto");
            cantidad = totalImpuesto.getLength();
        }

        Properties configuracion = new Properties();
        try {
            configuracion.load(Ride.class.getResourceAsStream("/Util/configuracion.properties"));
        } catch (IOException ex) {
            Logger.getLogger(Ride.class.getName()).log(Level.SEVERE, null, ex);
        }
        String subTotal12 = "0.00";
        String subTotal0 = "0.00";
        String subTotalNoObjetoIVA = "0.00";
        String subTotalExentoIVA = "0.00";
        String ICE = "";
        String IRBPNR = "";
        String IVA12 = "0.00";
        double impuestoTotal = 0;

        String codigoPorcentual = configuracion.getProperty(new StringBuilder().append("codigoDefecto").toString());

        for (int i = 0; i < cantidad; i++) {
            org.w3c.dom.Element element = (org.w3c.dom.Element) totalImpuesto.item(i);
            String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
            String codigoPorcentaje = element.getElementsByTagName("codigoPorcentaje").item(0).getTextContent();
            String baseImponible = element.getElementsByTagName("baseImponible").item(0).getTextContent();
            String valor = element.getElementsByTagName("valor").item(0).getTextContent();
            impuestoTotal += Double.parseDouble(valor);
            if (codigo.equals("2")) {
                if (codigoPorcentaje.equals("0")) {
                    subTotal0 = baseImponible;
                } else if (codigoPorcentaje.equals("6")) {
                    subTotalNoObjetoIVA = baseImponible;
                } else if (codigoPorcentaje.equals("7")) {
                    subTotalExentoIVA = baseImponible;
                } else {
                    codigoPorcentual = codigoPorcentaje;
                    subTotal12 = baseImponible;
                    IVA12 = valor;
                }
            } else if (codigo.equals("3")) {
                ICE = valor;
            } else if (codigo.equals("5")) {
                IRBPNR = valor;
            }
        }
        String textoPorcentual = configuracion.getProperty(new StringBuilder().append("codigo").append(codigoPorcentual).toString());
        PdfPCell datosresumen = new PdfPCell(new Phrase("SUBTOTAL " + textoPorcentual, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(subTotal12, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase("SUBTOTAL 0%", font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(subTotal0, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase("SUBTOTAL no objeto de IVA", font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(subTotalNoObjetoIVA, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase("SUBTOTAL exento de IVA", font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(subTotalExentoIVA, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase("SUBTOTAL SIN IMPUESTOS", font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("totalSinImpuestos").item(0).getTextContent(), font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);
        if (presentaDescuento) {
            datosresumen = new PdfPCell(new Phrase("TOTAL Descuento", font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablResumenValores.addCell(datosresumen);
            if (xmlComprobante.getElementsByTagName("totalDescuento").getLength() != 0) {
                datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("totalDescuento").item(0).getTextContent(), font8));
            } else {
                datosresumen = new PdfPCell(new Phrase(String.valueOf(this.totalDescuento), font8));
            }
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablResumenValores.addCell(datosresumen);
        }
        if (!ICE.equals("")) {
            datosresumen = new PdfPCell(new Phrase("ICE", font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablResumenValores.addCell(datosresumen);

            datosresumen = new PdfPCell(new Phrase(ICE, font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablResumenValores.addCell(datosresumen);
        }
        datosresumen = new PdfPCell(new Phrase("IVA " + textoPorcentual, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);

        datosresumen = new PdfPCell(new Phrase(IVA12, font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);
        if (!IRBPNR.equals("")) {
            datosresumen = new PdfPCell(new Phrase("IRBPNR", font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablResumenValores.addCell(datosresumen);

            datosresumen = new PdfPCell(new Phrase(IRBPNR, font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablResumenValores.addCell(datosresumen);
        }
        if (xmlComprobante.getElementsByTagName("propina").getLength() != 0) {
            datosresumen = new PdfPCell(new Phrase("PROPINA", font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
            tablResumenValores.addCell(datosresumen);

            datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("propina").item(0).getTextContent(), font8));
            datosresumen.setPadding(1);
            datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
            tablResumenValores.addCell(datosresumen);
        }

        datosresumen = new PdfPCell(new Phrase("IMPORTE TOTAL", font8));
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_LEFT);
        tablResumenValores.addCell(datosresumen);
        if (xmlComprobante.getElementsByTagName("importeTotal").getLength() != 0) {
            datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("importeTotal").item(0).getTextContent(), font8));
        } else if (xmlComprobante.getElementsByTagName("valorTotal").getLength() != 0) {
            datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("valorTotal").item(0).getTextContent(), font8));
        } else if (xmlComprobante.getElementsByTagName("valorModificacion").getLength() != 0) {
            datosresumen = new PdfPCell(new Phrase(xmlComprobante.getElementsByTagName("valorModificacion").item(0).getTextContent(), font8));
        }
        datosresumen.setPadding(1);
        datosresumen.setHorizontalAlignment(Element.ALIGN_RIGHT);
        tablResumenValores.addCell(datosresumen);

        return tablResumenValores;
    }

    private PdfPTable crearTablaInformacionAdicional(org.w3c.dom.Document xmlComprobante) throws DocumentException {
        PdfPTable tablTablaInformacionAdicional = new PdfPTable(2);
        tablTablaInformacionAdicional.setWidthPercentage(40);
        tablTablaInformacionAdicional.setSpacingBefore(30);
        tablTablaInformacionAdicional.setHorizontalAlignment(Element.ALIGN_RIGHT);
        float[] columnWidths = new float[]{15f, 40f};
        tablTablaInformacionAdicional.setWidths(columnWidths);
        NodeList campoAdicional = xmlComprobante.getElementsByTagName("campoAdicional");
        int cantidad = campoAdicional.getLength();
        PdfPCell campoAdicionalCell = new PdfPCell(new Phrase("Información Adicional", font8));
        if (cantidad > 0) {
            campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT);
        } else {
            campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.TOP | Rectangle.LEFT | Rectangle.BOTTOM);
        }
        campoAdicionalCell.setPadding(1);
        campoAdicionalCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        campoAdicionalCell.setColspan(2);
        tablTablaInformacionAdicional.addCell(campoAdicionalCell);
        for (int i = 0; i < cantidad; i++) {
            Node node = campoAdicional.item(i);
            String nombre = node.getAttributes().item(0).getNodeValue();
            String valor = node.getTextContent();

            campoAdicionalCell = new PdfPCell(new Phrase(nombre, font8));
            if (i < cantidad - 1) {
                campoAdicionalCell.setBorder(Rectangle.LEFT);
            } else {
                campoAdicionalCell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM);
            }
            campoAdicionalCell.setPadding(1);
            tablTablaInformacionAdicional.addCell(campoAdicionalCell);

            campoAdicionalCell = new PdfPCell(new Phrase(valor, font8));
            if (i < cantidad - 1) {
                campoAdicionalCell.setBorder(Rectangle.RIGHT);
            } else {
                campoAdicionalCell.setBorder(Rectangle.RIGHT | Rectangle.BOTTOM);
            }
            campoAdicionalCell.setPadding(1);
            tablTablaInformacionAdicional.addCell(campoAdicionalCell);
        }

        return tablTablaInformacionAdicional;
    }

    private Image getBarcode(String claveAcceso, PdfWriter pdfw) throws DocumentException, IOException {
        StringBuffer data = new StringBuffer(claveAcceso);
        data.append(Barcode128.FNC1);

        Barcode128 shipBarCode = new Barcode128();
        shipBarCode.setCode(data.toString());
        shipBarCode.setCodeSet(Barcode128.Barcode128CodeSet.B);
        shipBarCode.setBarHeight(20);
        shipBarCode.setFont(BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1257, BaseFont.EMBEDDED));
        shipBarCode.setSize(10);
        Image img = shipBarCode.createImageWithBarcode(pdfw.getDirectContent(), null, null);
        img.scaleAbsoluteWidth(228);
        return img;
    }

    private String formaDePago(String formaPago) {
        String formaDePago = "";
        if (formaPago.equals("01")) {
            formaDePago = "SIN UTILIZACION DEL SISTEMA FINANCIERO";
        } else if (formaPago.equals("15")) {
            formaDePago = "COMPENSACIÓN DE DEUDAS";
        } else if (formaPago.equals("16")) {
            formaDePago = "TARJETA DE DÉBITO";
        } else if (formaPago.equals("17")) {
            formaDePago = "DINERO ELECTRÓNICO";
        } else if (formaPago.equals("18")) {
            formaDePago = "TARJETA PREPAGO";
        }else if (formaPago.equals("19")) {
            formaDePago = "TARJETA DE CRÉDITO";
        }else if (formaPago.equals("20")) {
            formaDePago = "OTROS CON UTILIZACION DEL SISTEMA FINANCIERO";
        }
        else if (formaPago.equals("21")) {
            formaDePago = "ENDOSO DE TÍTULOS";
        }
        return formaDePago;
    }

    private String tipoComprobante(String codDoc) {
        String tipoComprobante = "";
        if (codDoc.equals("01")) {
            tipoComprobante = "FACTURA";
        } else if (codDoc.equals("04")) {
            tipoComprobante = "NOTA DE CRÉDITO";
        } else if (codDoc.equals("05")) {
            tipoComprobante = "NOTA DE DÉBITO";
        } else if (codDoc.equals("06")) {
            tipoComprobante = "GUÍA DE REMISIÓN";
        } else if (codDoc.equals("07")) {
            tipoComprobante = "COMPROBANTE DE RETENCIÓN";
        }

        return tipoComprobante;
    }

    private static String formatDouble(double num) {
        return String.format("%.2f", num).replace(",", ".");
    }
}

class SpecialRoundedCell implements PdfPCellEvent {

    public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvas) {
        PdfContentByte cb = canvas[PdfPTable.BACKGROUNDCANVAS];
        cb.roundRectangle(
                rect.getLeft() + 1.5f,
                rect.getBottom() + 1.5f,
                rect.getWidth() - 3,
                rect.getHeight() - 3, 4
        );
        cb.stroke();
    }
}
