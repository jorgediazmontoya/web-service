/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JasperRide;

import JasperComprobantes.Destinatario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author yoelvys
 */
public class JasperRide {

    public void CrearRide(org.w3c.dom.Document xmlComprobante, String numAutorizacion, String dirGuardarDoc, String dirLogo, String ireportDir) throws FileNotFoundException, JRException {
        dirGuardarDoc = dirGuardarDoc.replace(".xml", ".pdf");
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
        if (xmlComprobante.getElementsByTagName("nombreComercial").getLength() != 0) {
            nombreComercial = xmlComprobante.getElementsByTagName("nombreComercial").item(0).getTextContent();
        }
        String dirMatriz = xmlComprobante.getElementsByTagName("dirMatriz").item(0).getTextContent();
        String dirEstablecimiento = "";
        if (xmlComprobante.getElementsByTagName("dirEstablecimiento").getLength() != 0) {
            dirEstablecimiento = xmlComprobante.getElementsByTagName("dirEstablecimiento").item(0).getTextContent();
        }
        String regimenMicroempresas = "";
        if (xmlComprobante.getElementsByTagName("regimenMicroempresas").getLength() != 0) {
            regimenMicroempresas = xmlComprobante.getElementsByTagName("regimenMicroempresas").item(0).getTextContent();
        }
        String agenteRetencion = "";
        if (xmlComprobante.getElementsByTagName("agenteRetencion").getLength() != 0) {
            agenteRetencion = xmlComprobante.getElementsByTagName("agenteRetencion").item(0).getTextContent();
        }
        String contribuyenteEspecial = "";
        if (xmlComprobante.getElementsByTagName("contribuyenteEspecial").getLength() != 0) {
            contribuyenteEspecial = xmlComprobante.getElementsByTagName("contribuyenteEspecial").item(0).getTextContent();
        }
        String obligadoContabilidad = xmlComprobante.getElementsByTagName("obligadoContabilidad").item(0).getTextContent();
        NodeList campoAdicional = xmlComprobante.getElementsByTagName("campoAdicional");
        int cantidad = campoAdicional.getLength();

        List data = new ArrayList();
        String dirPlantillas = dirPlantillaJasper(ruc, ireportDir);
        String dirPlantilla = "";
        String subTotal12 = "0.00";
        String subTotal0 = "0.00";
        String subTotalNoObjetoIVA = "0.00";
        String subTotalExentoIVA = "0.00";
        String ICE = "0.00";
        String IRBPNR = "";
        String IVA12 = "0.00";
        String importeTotal = "0.00";

        if (!codDoc.equals("07")) {
            org.w3c.dom.NodeList totalImpuesto = xmlComprobante.getElementsByTagName("totalImpuesto");
            int cantidadImpuesto = totalImpuesto.getLength();
            boolean presentaDescuento = true;
            if (cantidadImpuesto == 0) {
                presentaDescuento = false;
                totalImpuesto = xmlComprobante.getElementsByTagName("impuesto");
                cantidadImpuesto = totalImpuesto.getLength();
            }

            for (int i = 0; i < cantidadImpuesto; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) totalImpuesto.item(i);
                String codigo = element.getElementsByTagName("codigo").item(0).getTextContent();
                String codigoPorcentaje = element.getElementsByTagName("codigoPorcentaje").item(0).getTextContent();
                String baseImponible = element.getElementsByTagName("baseImponible").item(0).getTextContent();
                String valor = element.getElementsByTagName("valor").item(0).getTextContent();
                if (codigo.equals("2")) {
                    if (codigoPorcentaje.equals("0")) {
                        subTotal0 = baseImponible;
                    } else if (codigoPorcentaje.equals("6")) {
                        subTotalNoObjetoIVA = baseImponible;
                    } else if (codigoPorcentaje.equals("7")) {
                        subTotalExentoIVA = baseImponible;
                    } else {
                        subTotal12 = baseImponible;
                        IVA12 = valor;
                    }
                } else if (codigo.equals("3")) {
                    ICE = valor;
                } else if (codigo.equals("5")) {
                    IRBPNR = valor;
                }
            }

            if (xmlComprobante.getElementsByTagName("importeTotal").getLength() != 0) {
                importeTotal = xmlComprobante.getElementsByTagName("importeTotal").item(0).getTextContent();
            } else if (xmlComprobante.getElementsByTagName("valorTotal").getLength() != 0) {
                importeTotal = xmlComprobante.getElementsByTagName("valorTotal").item(0).getTextContent();
            } else if (xmlComprobante.getElementsByTagName("valorModificacion").getLength() != 0) {
                importeTotal = xmlComprobante.getElementsByTagName("valorModificacion").item(0).getTextContent();
            }
        }
        if (codDoc.equals("01")) {
            dirPlantilla = dirPlantillas + "/factura.jrxml";
            JasperComprobantes.Factura factura = new JasperComprobantes.Factura();
            factura.setDirLogo(dirLogo);
            factura.setRazonSocial(razonSocial);
            factura.setNombreComercial(nombreComercial);
            factura.setDirMatriz(dirMatriz);
            factura.setDirEstablecimiento(dirEstablecimiento);
            factura.setContribuyenteEspecial(contribuyenteEspecial);
            factura.setObligadoContabilidad(obligadoContabilidad);
            factura.setRuc(ruc);
            factura.setNumDocumento(noComprobante);
            factura.setNumAutorizacion(numAutorizacion);
            factura.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            factura.setTipoEmision("NORMAL");
            factura.setClaveAcc(claveAcceso);

            String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
            String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
            if (xmlComprobante.getElementsByTagName("direccionComprador").getLength() != 0) {
                String direccionComprador = xmlComprobante.getElementsByTagName("direccionComprador").item(0).getTextContent();
                factura.setDireccionComprador(direccionComprador);
            }

            String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();
            String guiaRemision = "";
            if (xmlComprobante.getElementsByTagName("guiaRemision").getLength() != 0) {
                guiaRemision = xmlComprobante.getElementsByTagName("guiaRemision").item(0).getTextContent();
            }

            factura.setRazonSocialComprador(razonSocialComprador);
            factura.setIdentificacionComprador(identificacionComprador);
            factura.setFechaEmision(fechaEmision);
            factura.setGuiaRemision(guiaRemision);

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                factura.getInfoAdicional().add(nuevoCampoAdicional);
            }

            NodeList pago = xmlComprobante.getElementsByTagName("pago");
            cantidad = pago.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) pago.item(i);
                JasperComprobantes.Pago nuevoPago = new JasperComprobantes.Pago();
                nuevoPago.setFormaPago(formaDePago(element.getElementsByTagName("formaPago").item(0).getTextContent()));
                nuevoPago.setTotal(element.getElementsByTagName("total").item(0).getTextContent());
                if (element.getElementsByTagName("plazo").getLength() != 0) {
                    nuevoPago.setPlazo(element.getElementsByTagName("plazo").item(0).getTextContent());
                }
                if (element.getElementsByTagName("unidadTiempo").getLength() != 0) {
                    nuevoPago.setUnidadTiempo(element.getElementsByTagName("unidadTiempo").item(0).getTextContent());
                }
                factura.getPagos().add(nuevoPago);
            }
            NodeList detalles = xmlComprobante.getElementsByTagName("detalle");
            cantidad = detalles.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(i);
                JasperComprobantes.DetalleFactura nuevoDetalle = new JasperComprobantes.DetalleFactura();
                nuevoDetalle.setCodigoPrincipal(element.getElementsByTagName("codigoPrincipal").item(0).getTextContent());
                if (element.getElementsByTagName("codigoAuxiliar").getLength() != 0) {
                    nuevoDetalle.setCodigoAuxiliar(element.getElementsByTagName("codigoAuxiliar").item(0).getTextContent());
                }
                nuevoDetalle.setDescuento(element.getElementsByTagName("descuento").item(0).getTextContent());
                nuevoDetalle.setCantidad(element.getElementsByTagName("cantidad").item(0).getTextContent());
                nuevoDetalle.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
                nuevoDetalle.setPrecioUnitario(element.getElementsByTagName("precioUnitario").item(0).getTextContent());
                nuevoDetalle.setPrecioTotalSinImpuesto(element.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());
                factura.getDetalles().add(nuevoDetalle);
            }

            factura.setSubTotal0(subTotal0);
            factura.setSubTotal12(subTotal12);
            factura.setSubTotalExentoIVA(subTotalExentoIVA);
            factura.setSubTotalNoObjetoIVA(subTotalNoObjetoIVA);
            factura.setIVA12(IVA12);
            factura.setICE(ICE);
            factura.setImporteTotal(subTotal0);
            factura.setSubTotalSinImpuesto(xmlComprobante.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
            factura.setTotalDescuento(xmlComprobante.getElementsByTagName("totalDescuento").item(0).getTextContent());
            factura.setImporteTotal(importeTotal);
            if (xmlComprobante.getElementsByTagName("totalSubsidio").getLength() != 0) {
                String totalSubsidioSinIva = xmlComprobante.getElementsByTagName("totalSubsidio").item(0).getTextContent();
                double ahorroPorSubsidio = Double.parseDouble(totalSubsidioSinIva) * 0.12 + Double.parseDouble(totalSubsidioSinIva);
                double totalSinSubsidio = Double.parseDouble(importeTotal) + ahorroPorSubsidio;
                factura.setTotalSinSubsidio(formatDouble(totalSinSubsidio));
                factura.setAhorroSubsidio(formatDouble(ahorroPorSubsidio));
            }
            NodeList reembolsos = xmlComprobante.getElementsByTagName("reembolsoDetalle");
            if (reembolsos.getLength() > 0) {
                cantidad = reembolsos.getLength();
                for (int i = 0; i < cantidad; i++) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) reembolsos.item(i);
                    JasperComprobantes.ReembolsoFactura reembolsoFactura = new JasperComprobantes.ReembolsoFactura();
                    reembolsoFactura.setIdentificacionProveedorReembolso(element.getElementsByTagName("identificacionProveedorReembolso").item(0).getTextContent());
                    reembolsoFactura.setTipoDocumento("FACTURA");

                    String noDocumento = element.getElementsByTagName("estabDocReembolso").item(0).getTextContent()
                            + "-" + element.getElementsByTagName("ptoEmiDocReembolso").item(0).getTextContent()
                            + "-" + element.getElementsByTagName("secuencialDocReembolso").item(0).getTextContent();
                    reembolsoFactura.setNoDocumento(noDocumento);
                    reembolsoFactura.setFechaEmisionDocReembolso(element.getElementsByTagName("fechaEmisionDocReembolso").item(0).getTextContent());

                    NodeList impuestos = element.getElementsByTagName("detalleImpuesto");
                    double total = 0.00;
                    double baseImponible = 0.00;
                    double baseImponibleSinIva = 0.00;
                    double iva = 0.00;
                    for (int y = 0; y < impuestos.getLength(); y++) {
                        org.w3c.dom.Element impuesto = (org.w3c.dom.Element) impuestos.item(y);
                        if (impuesto.getElementsByTagName("codigoPorcentaje").item(0).getTextContent().equals("2")) {
                            baseImponible = Double.parseDouble(impuesto.getElementsByTagName("baseImponibleReembolso").item(0).getTextContent());
                            iva = Double.parseDouble(impuesto.getElementsByTagName("impuestoReembolso").item(0).getTextContent());
                        } else if (impuesto.getElementsByTagName("codigoPorcentaje").item(0).getTextContent().equals("0")) {
                            baseImponibleSinIva = Double.parseDouble(impuesto.getElementsByTagName("baseImponibleReembolso").item(0).getTextContent());
                        }
                    }
                    reembolsoFactura.setBaseImponible(baseImponible);
                    reembolsoFactura.setBaseImponibleSinIva(baseImponibleSinIva);
                    reembolsoFactura.setValorImpuesto(iva);
                    total = reembolsoFactura.getBaseImponible() + reembolsoFactura.getValorImpuesto() + reembolsoFactura.getBaseImponibleSinIva();
                    reembolsoFactura.setTotal(total);
                    factura.getReembolso().add(reembolsoFactura);
                }
            }

            data.add(factura);
        }
        if (codDoc.equals("03")) {
            dirPlantilla = dirPlantillas + "/liquidacionCompra.jrxml";
            JasperComprobantes.LiquidacionCompra liqCompra = new JasperComprobantes.LiquidacionCompra();
            liqCompra.setDirLogo(dirLogo);
            liqCompra.setRazonSocial(razonSocial);
            liqCompra.setNombreComercial(nombreComercial);
            liqCompra.setDirMatriz(dirMatriz);
            liqCompra.setDirEstablecimiento(dirEstablecimiento);
            liqCompra.setContribuyenteEspecial(contribuyenteEspecial);
            liqCompra.setObligadoContabilidad(obligadoContabilidad);
            liqCompra.setRuc(ruc);
            liqCompra.setNumDocumento(noComprobante);
            liqCompra.setNumAutorizacion(numAutorizacion);
            liqCompra.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            liqCompra.setTipoEmision("NORMAL");
            liqCompra.setClaveAcc(claveAcceso);

            String razonSocialProveedor = xmlComprobante.getElementsByTagName("razonSocialProveedor").item(0).getTextContent();
            String identificacionProveedor = xmlComprobante.getElementsByTagName("identificacionProveedor").item(0).getTextContent();
            String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();
            String direccionProveedor = "";
            if (xmlComprobante.getElementsByTagName("direccionProveedor").getLength() != 0) {
                direccionProveedor = xmlComprobante.getElementsByTagName("direccionProveedor").item(0).getTextContent();
            }

            liqCompra.setRazonSocialProveedor(razonSocialProveedor);
            liqCompra.setIdentificacionProveedor(identificacionProveedor);
            liqCompra.setFechaEmision(fechaEmision);
            liqCompra.setDireccionProveedor(direccionProveedor);

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                liqCompra.getInfoAdicional().add(nuevoCampoAdicional);
            }

            NodeList pago = xmlComprobante.getElementsByTagName("pago");
            cantidad = pago.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) pago.item(i);
                JasperComprobantes.Pago nuevoPago = new JasperComprobantes.Pago();
                nuevoPago.setFormaPago(formaDePago(element.getElementsByTagName("formaPago").item(0).getTextContent()));
                nuevoPago.setTotal(element.getElementsByTagName("total").item(0).getTextContent());
                if (element.getElementsByTagName("plazo").getLength() != 0) {
                    nuevoPago.setPlazo(element.getElementsByTagName("plazo").item(0).getTextContent());
                }
                if (element.getElementsByTagName("unidadTiempo").getLength() != 0) {
                    nuevoPago.setUnidadTiempo(element.getElementsByTagName("unidadTiempo").item(0).getTextContent());
                }
                liqCompra.getPagos().add(nuevoPago);
            }
            NodeList detalles = xmlComprobante.getElementsByTagName("detalle");
            cantidad = detalles.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(i);
                JasperComprobantes.DetalleLiquidacionCompra nuevoDetalle = new JasperComprobantes.DetalleLiquidacionCompra();
                nuevoDetalle.setCodigoPrincipal(element.getElementsByTagName("codigoPrincipal").item(0).getTextContent());
                if (element.getElementsByTagName("codigoAuxiliar").getLength() != 0) {
                    nuevoDetalle.setCodigoAuxiliar(element.getElementsByTagName("codigoAuxiliar").item(0).getTextContent());
                }
                nuevoDetalle.setDescuento(element.getElementsByTagName("descuento").item(0).getTextContent());
                nuevoDetalle.setCantidad(element.getElementsByTagName("cantidad").item(0).getTextContent());
                nuevoDetalle.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
                nuevoDetalle.setPrecioUnitario(element.getElementsByTagName("precioUnitario").item(0).getTextContent());
                nuevoDetalle.setPrecioTotalSinImpuesto(element.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());
                liqCompra.getDetalles().add(nuevoDetalle);
            }

            liqCompra.setSubTotal0(subTotal0);
            liqCompra.setSubTotal12(subTotal12);
            liqCompra.setSubTotalExentoIVA(subTotalExentoIVA);
            liqCompra.setSubTotalNoObjetoIVA(subTotalNoObjetoIVA);
            liqCompra.setIVA12(IVA12);
            liqCompra.setImporteTotal(subTotal0);
            liqCompra.setSubTotalSinImpuesto(xmlComprobante.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
            liqCompra.setTotalDescuento(xmlComprobante.getElementsByTagName("totalDescuento").item(0).getTextContent());
            liqCompra.setImporteTotal(importeTotal);

            NodeList reembolsos = xmlComprobante.getElementsByTagName("reembolsoDetalle");
            if (reembolsos.getLength() > 0) {
                cantidad = reembolsos.getLength();
                for (int i = 0; i < cantidad; i++) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) reembolsos.item(i);
                    JasperComprobantes.ReembolsoLiquidacionCompra reembolsoLiquidacionCompra = new JasperComprobantes.ReembolsoLiquidacionCompra();
                    reembolsoLiquidacionCompra.setIdentificacionProveedorReembolso(element.getElementsByTagName("identificacionProveedorReembolso").item(0).getTextContent());
                    reembolsoLiquidacionCompra.setTipoDocumento("FACTURA");

                    String noDocumento = element.getElementsByTagName("estabDocReembolso").item(0).getTextContent()
                            + "-" + element.getElementsByTagName("ptoEmiDocReembolso").item(0).getTextContent()
                            + "-" + element.getElementsByTagName("secuencialDocReembolso").item(0).getTextContent();
                    reembolsoLiquidacionCompra.setNoDocumento(noDocumento);
                    reembolsoLiquidacionCompra.setFechaEmisionDocReembolso(element.getElementsByTagName("fechaEmisionDocReembolso").item(0).getTextContent());

                    NodeList impuestos = element.getElementsByTagName("detalleImpuesto");
                    double total = 0.00;
                    double baseImponible = 0.00;
                    double baseImponibleSinIva = 0.00;
                    double iva = 0.00;
                    for (int y = 0; y < impuestos.getLength(); y++) {
                        org.w3c.dom.Element impuesto = (org.w3c.dom.Element) impuestos.item(y);
                        if (impuesto.getElementsByTagName("codigoPorcentaje").item(0).getTextContent().equals("2")) {
                            baseImponible = Double.parseDouble(impuesto.getElementsByTagName("baseImponibleReembolso").item(0).getTextContent());
                            iva = Double.parseDouble(impuesto.getElementsByTagName("impuestoReembolso").item(0).getTextContent());
                        } else if (impuesto.getElementsByTagName("codigoPorcentaje").item(0).getTextContent().equals("0")) {
                            baseImponibleSinIva = Double.parseDouble(impuesto.getElementsByTagName("baseImponibleReembolso").item(0).getTextContent());
                        }
                    }
                    reembolsoLiquidacionCompra.setBaseImponible(baseImponible);
                    reembolsoLiquidacionCompra.setBaseImponibleSinIva(baseImponibleSinIva);
                    reembolsoLiquidacionCompra.setValorImpuesto(iva);
                    total = reembolsoLiquidacionCompra.getBaseImponible() + reembolsoLiquidacionCompra.getValorImpuesto() + reembolsoLiquidacionCompra.getBaseImponibleSinIva();
                    reembolsoLiquidacionCompra.setTotal(total);
                    liqCompra.getReembolso().add(reembolsoLiquidacionCompra);
                }
            }

            data.add(liqCompra);
        }
        if (codDoc.equals("04")) {
            dirPlantilla = dirPlantillas + "/notaCredito.jrxml";
            JasperComprobantes.NotaCredito notaCredito = new JasperComprobantes.NotaCredito();
            notaCredito.setDirLogo(dirLogo);
            notaCredito.setRazonSocial(razonSocial);
            notaCredito.setNombreComercial(nombreComercial);
            notaCredito.setDirMatriz(dirMatriz);
            notaCredito.setDirEstablecimiento(dirEstablecimiento);
            notaCredito.setContribuyenteEspecial(contribuyenteEspecial);
            notaCredito.setObligadoContabilidad(obligadoContabilidad);
            notaCredito.setRuc(ruc);
            notaCredito.setNumDocumento(noComprobante);
            notaCredito.setNumAutorizacion(numAutorizacion);
            notaCredito.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            notaCredito.setTipoEmision("NORMAL");
            notaCredito.setClaveAcc(claveAcceso);

            String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
            String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
            String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();
            String comprobanteModificado = tipoComprobante(xmlComprobante.getElementsByTagName("codDocModificado").item(0).getTextContent());
            String numComprobanteModificado = xmlComprobante.getElementsByTagName("numDocModificado").item(0).getTextContent();
            String fechaEmisionComprobanteModificado = xmlComprobante.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent();
            String razonModificacion = xmlComprobante.getElementsByTagName("motivo").item(0).getTextContent();

            notaCredito.setRazonSocialComprador(razonSocialComprador);
            notaCredito.setIdentificacionComprador(identificacionComprador);
            notaCredito.setFechaEmision(fechaEmision);
            notaCredito.setComprobanteModificado(comprobanteModificado);
            notaCredito.setNumDocModificado(numComprobanteModificado);
            notaCredito.setFechaEmisionDocSustento(fechaEmisionComprobanteModificado);
            notaCredito.setMotivo(razonModificacion);

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                notaCredito.getInfoAdicional().add(nuevoCampoAdicional);
            }

            NodeList detalles = xmlComprobante.getElementsByTagName("detalle");
            cantidad = detalles.getLength();
            double descuento = 0.00;
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(i);
                JasperComprobantes.DetalleNotaCredito nuevoDetalle = new JasperComprobantes.DetalleNotaCredito();
                nuevoDetalle.setCodigoInterno(element.getElementsByTagName("codigoInterno").item(0).getTextContent());
                if (element.getElementsByTagName("codigoAdicional").getLength() != 0) {
                    nuevoDetalle.setCodigoAdicional(element.getElementsByTagName("codigoAdicional").item(0).getTextContent());
                }
                nuevoDetalle.setDescuento(element.getElementsByTagName("descuento").item(0).getTextContent());
                nuevoDetalle.setCantidad(element.getElementsByTagName("cantidad").item(0).getTextContent());
                nuevoDetalle.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
                nuevoDetalle.setPrecioUnitario(element.getElementsByTagName("precioUnitario").item(0).getTextContent());
                nuevoDetalle.setPrecioTotalSinImpuesto(element.getElementsByTagName("precioTotalSinImpuesto").item(0).getTextContent());
                notaCredito.getDetalles().add(nuevoDetalle);
                descuento += Double.parseDouble(nuevoDetalle.getDescuento());
            }

            notaCredito.setSubTotal0(subTotal0);
            notaCredito.setSubTotal12(subTotal12);
            notaCredito.setSubTotalExentoIVA(subTotalExentoIVA);
            notaCredito.setSubTotalNoObjetoIVA(subTotalNoObjetoIVA);
            notaCredito.setIVA12(IVA12);
            notaCredito.setImporteTotal(subTotal0);
            notaCredito.setSubTotalSinImpuesto(xmlComprobante.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
            notaCredito.setImporteTotal(importeTotal);
            notaCredito.setTotalDescuento(String.valueOf(descuento));

            data.add(notaCredito);
        }
        if (codDoc.equals("05")) {
            dirPlantilla = dirPlantillas + "/notaDebito.jrxml";
            JasperComprobantes.NotaDebito notaDebito = new JasperComprobantes.NotaDebito();
            notaDebito.setDirLogo(dirLogo);
            notaDebito.setRazonSocial(razonSocial);
            notaDebito.setNombreComercial(nombreComercial);
            notaDebito.setDirMatriz(dirMatriz);
            notaDebito.setDirEstablecimiento(dirEstablecimiento);
            notaDebito.setContribuyenteEspecial(contribuyenteEspecial);
            notaDebito.setObligadoContabilidad(obligadoContabilidad);
            notaDebito.setRuc(ruc);
            notaDebito.setNumDocumento(noComprobante);
            notaDebito.setNumAutorizacion(numAutorizacion);
            notaDebito.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            notaDebito.setTipoEmision("NORMAL");
            notaDebito.setClaveAcc(claveAcceso);

            String razonSocialComprador = xmlComprobante.getElementsByTagName("razonSocialComprador").item(0).getTextContent();
            String identificacionComprador = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
            String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();
            String comprobanteModificado = tipoComprobante(xmlComprobante.getElementsByTagName("codDocModificado").item(0).getTextContent());
            String numComprobanteModificado = xmlComprobante.getElementsByTagName("numDocModificado").item(0).getTextContent();
            String fechaEmisionComprobanteModificado = xmlComprobante.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent();

            notaDebito.setRazonSocialComprador(razonSocialComprador);
            notaDebito.setIdentificacionComprador(identificacionComprador);
            notaDebito.setFechaEmision(fechaEmision);
            notaDebito.setComprobanteModificado(comprobanteModificado);
            notaDebito.setNumDocModificado(numComprobanteModificado);
            notaDebito.setFechaEmisionDocSustento(fechaEmisionComprobanteModificado);

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                notaDebito.getInfoAdicional().add(nuevoCampoAdicional);
            }
            NodeList pago = xmlComprobante.getElementsByTagName("pago");
            cantidad = pago.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) pago.item(i);
                JasperComprobantes.Pago nuevoPago = new JasperComprobantes.Pago();
                nuevoPago.setFormaPago(formaDePago(element.getElementsByTagName("formaPago").item(0).getTextContent()));
                nuevoPago.setTotal(element.getElementsByTagName("total").item(0).getTextContent());
                if (element.getElementsByTagName("plazo").getLength() != 0) {
                    nuevoPago.setPlazo(element.getElementsByTagName("plazo").item(0).getTextContent());
                }
                if (element.getElementsByTagName("unidadTiempo").getLength() != 0) {
                    nuevoPago.setUnidadTiempo(element.getElementsByTagName("unidadTiempo").item(0).getTextContent());
                }
                notaDebito.getPagos().add(nuevoPago);
            }
            NodeList detalles = xmlComprobante.getElementsByTagName("motivo");
            cantidad = detalles.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(i);
                JasperComprobantes.Motivo nuevoMotivo = new JasperComprobantes.Motivo();
                nuevoMotivo.setRazon(element.getElementsByTagName("razon").item(0).getTextContent());
                nuevoMotivo.setValor(element.getElementsByTagName("valor").item(0).getTextContent());
                notaDebito.getMotivos().add(nuevoMotivo);
            }
            notaDebito.setSubTotal0(subTotal0);
            notaDebito.setSubTotal12(subTotal12);
            notaDebito.setSubTotalExentoIVA(subTotalExentoIVA);
            notaDebito.setSubTotalNoObjetoIVA(subTotalNoObjetoIVA);
            notaDebito.setIVA12(IVA12);
            notaDebito.setImporteTotal(subTotal0);
            notaDebito.setSubTotalSinImpuesto(xmlComprobante.getElementsByTagName("totalSinImpuestos").item(0).getTextContent());
            notaDebito.setImporteTotal(importeTotal);
            data.add(notaDebito);
        }
        if (codDoc.equals("07")) {
            dirPlantilla = dirPlantillas + "/retencion.jrxml";
            JasperComprobantes.ComprobanteRetencion retencion = new JasperComprobantes.ComprobanteRetencion();
            retencion.setDirLogo(dirLogo);
            retencion.setRazonSocial(razonSocial);
            retencion.setNombreComercial(nombreComercial);
            retencion.setDirMatriz(dirMatriz);
            retencion.setDirEstablecimiento(dirEstablecimiento);
            retencion.setContribuyenteEspecial(contribuyenteEspecial);
            retencion.setObligadoContabilidad(obligadoContabilidad);
            retencion.setRuc(ruc);
            retencion.setNumDocumento(noComprobante);
            retencion.setNumAutorizacion(numAutorizacion);
            retencion.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            retencion.setTipoEmision("NORMAL");
            retencion.setClaveAcc(claveAcceso);

            String razonSocialSujetoRetenido = xmlComprobante.getElementsByTagName("razonSocialSujetoRetenido").item(0).getTextContent();
            String identificacionSujetoRetenido = xmlComprobante.getElementsByTagName("identificacionSujetoRetenido").item(0).getTextContent();
            String fechaEmision = xmlComprobante.getElementsByTagName("fechaEmision").item(0).getTextContent();

            retencion.setRazonSocialSujetoRetenido(razonSocialSujetoRetenido);
            retencion.setIdentificacionSujetoRetenido(identificacionSujetoRetenido);
            retencion.setFechaEmision(fechaEmision);

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                retencion.getInfoAdicional().add(nuevoCampoAdicional);
            }

            NodeList impuestos = xmlComprobante.getElementsByTagName("impuesto");
            cantidad = impuestos.getLength();
            for (int i = 0; i < cantidad; i++) {
                org.w3c.dom.Element element = (org.w3c.dom.Element) impuestos.item(i);
                JasperComprobantes.ImpuestoComprobanteRetencion nuevoImpuestoComprobanteRetencion = new JasperComprobantes.ImpuestoComprobanteRetencion();
                nuevoImpuestoComprobanteRetencion.setTipoImpuesto(impuestoRetencion(element.getElementsByTagName("codigo").item(0).getTextContent()));
                nuevoImpuestoComprobanteRetencion.setBaseImponible(element.getElementsByTagName("baseImponible").item(0).getTextContent());
                nuevoImpuestoComprobanteRetencion.setPorcentajeRetener(element.getElementsByTagName("porcentajeRetener").item(0).getTextContent());
                nuevoImpuestoComprobanteRetencion.setValorRetenido(element.getElementsByTagName("valorRetenido").item(0).getTextContent());
                nuevoImpuestoComprobanteRetencion.setNumDocSustento(element.getElementsByTagName("numDocSustento").item(0).getTextContent());
                nuevoImpuestoComprobanteRetencion.setCodigoRetencion(element.getElementsByTagName("codigoRetencion").item(0).getTextContent());
                nuevoImpuestoComprobanteRetencion.setEjercicioFiscal(xmlComprobante.getElementsByTagName("periodoFiscal").item(0).getTextContent());

                retencion.getImpuestos().add(nuevoImpuestoComprobanteRetencion);
            }

            data.add(retencion);
        }
        if (codDoc.equals("06")) {
            dirPlantilla = dirPlantillas + "/guia.jrxml";
            JasperComprobantes.GuiaRemision guia = new JasperComprobantes.GuiaRemision();
            guia.setDirLogo(dirLogo);
            guia.setRazonSocial(razonSocial);
            guia.setNombreComercial(nombreComercial);
            guia.setDirMatriz(dirMatriz);
            guia.setDirEstablecimiento(dirEstablecimiento);
            guia.setContribuyenteEspecial(contribuyenteEspecial);
            guia.setObligadoContabilidad(obligadoContabilidad);
            guia.setRuc(ruc);
            guia.setNumDocumento(noComprobante);
            guia.setNumAutorizacion(numAutorizacion);
            guia.setAmbiente(ambiente.equals("1") ? "PRUEBA" : "PRODUCCION");
            guia.setTipoEmision("NORMAL");
            guia.setClaveAcc(claveAcceso);

            String razonSocialTransportista = xmlComprobante.getElementsByTagName("razonSocialTransportista").item(0).getTextContent();
            String rucTransportista = xmlComprobante.getElementsByTagName("rucTransportista").item(0).getTextContent();
            String placa = xmlComprobante.getElementsByTagName("placa").item(0).getTextContent();
            String dirPartida = xmlComprobante.getElementsByTagName("dirPartida").item(0).getTextContent();
            String fechaIniTransporte = xmlComprobante.getElementsByTagName("fechaIniTransporte").item(0).getTextContent();
            String fechaFinTransporte = xmlComprobante.getElementsByTagName("fechaFinTransporte").item(0).getTextContent();

            guia.setRazonSocialTransportista(razonSocialTransportista);
            guia.setRucTransportista(rucTransportista);
            guia.setPlaca(placa);
            guia.setDirPartida(dirPartida);
            guia.setFechaIniTransporte(fechaIniTransporte);
            guia.setFechaFinTransporte(fechaFinTransporte);

            NodeList destinatarios = xmlComprobante.getElementsByTagName("destinatario");
            int cantidadDestinatarios = destinatarios.getLength();
            for (int i = 0; i < cantidadDestinatarios; i++) {
                org.w3c.dom.Element destinatario = (org.w3c.dom.Element) destinatarios.item(i);
                String docSustento = "FACTURA";
                String numDocSustento = destinatario.getElementsByTagName("numDocSustento").getLength() > 0 ? destinatario.getElementsByTagName("numDocSustento").item(0).getTextContent() : null;
                String fechaEmisionDocSustento = destinatario.getElementsByTagName("fechaEmisionDocSustento").getLength() > 0 ? destinatario.getElementsByTagName("fechaEmisionDocSustento").item(0).getTextContent() : null;
                String numAutDocSustento = destinatario.getElementsByTagName("numAutDocSustento").getLength() > 0 ? destinatario.getElementsByTagName("numAutDocSustento").item(0).getTextContent() : null;
                String motivoTraslado = destinatario.getElementsByTagName("motivoTraslado").item(0).getTextContent();
                String dirDestinatario = destinatario.getElementsByTagName("dirDestinatario").item(0).getTextContent();
                String identificacionDestinatario = destinatario.getElementsByTagName("identificacionDestinatario").item(0).getTextContent();
                String razonSocialDestinatario = destinatario.getElementsByTagName("razonSocialDestinatario").item(0).getTextContent();
                String docAduaneroUnico = destinatario.getElementsByTagName("docAduaneroUnico").getLength() > 0 ? destinatario.getElementsByTagName("docAduaneroUnico").item(0).getTextContent() : null;
                String codEstabDestino = destinatario.getElementsByTagName("codEstabDestino").getLength() > 0 ? destinatario.getElementsByTagName("codEstabDestino").item(0).getTextContent() : null;
                String ruta = destinatario.getElementsByTagName("ruta").getLength() > 0 ? destinatario.getElementsByTagName("ruta").item(0).getTextContent() : null;

                Destinatario destinatarioReport = new Destinatario();
                destinatarioReport.setDocSustento(docSustento);
                destinatarioReport.setNumDocSustento(numDocSustento);
                destinatarioReport.setFechaEmisionDocSustento(fechaEmisionDocSustento);
                destinatarioReport.setNumAutDocSustento(numAutDocSustento);
                destinatarioReport.setMotivoTraslado(motivoTraslado);
                destinatarioReport.setDirDestinatario(dirDestinatario);
                destinatarioReport.setIdentificacionDestinatario(identificacionDestinatario);
                destinatarioReport.setRazonSocialDestinatario(razonSocialDestinatario);
                destinatarioReport.setDocAduaneroUnico(docAduaneroUnico);
                destinatarioReport.setCodEstabDestino(codEstabDestino);
                destinatarioReport.setRuta(ruta);

                NodeList detalles = destinatario.getElementsByTagName("detalle");
                int cantidadDetalles = detalles.getLength();
                for (int j = 0; j < cantidadDetalles; j++) {
                    org.w3c.dom.Element element = (org.w3c.dom.Element) detalles.item(j);
                    JasperComprobantes.DetalleGuiaRemision nuevoDetalle = new JasperComprobantes.DetalleGuiaRemision();
                    nuevoDetalle.setCodigoInterno(element.getElementsByTagName("codigoInterno").item(0).getTextContent());
                    if (element.getElementsByTagName("codigoAdicional").getLength() != 0) {
                        nuevoDetalle.setCodigoAdicional(element.getElementsByTagName("codigoAdicional").item(0).getTextContent());
                    }
                    nuevoDetalle.setCantidad(element.getElementsByTagName("cantidad").item(0).getTextContent());
                    nuevoDetalle.setDescripcion(element.getElementsByTagName("descripcion").item(0).getTextContent());
                    destinatarioReport.getDetalles().add(nuevoDetalle);
                }

                guia.getDestinatarios().add(destinatarioReport);
            }

            for (int i = 0; i < cantidad; i++) {
                Node node = campoAdicional.item(i);
                String nombre = node.getAttributes().item(0).getNodeValue();
                String valor = node.getTextContent();

                JasperComprobantes.CampoAdicional nuevoCampoAdicional = new JasperComprobantes.CampoAdicional();
                nuevoCampoAdicional.setNombre(nombre);
                nuevoCampoAdicional.setValor(valor);
                guia.getInfoAdicional().add(nuevoCampoAdicional);
            }

            data.add(guia);
        }
        HashMap parameters = new HashMap();

        if (!regimenMicroempresas.equals("")) {
            parameters.put("LEYENDA_REGIMEN", "CONTRIBUYENTE RÉGIMEN MICROEMPRESAS");
        }
        if (!agenteRetencion.equals("")) {
            parameters.put("LEYENDA_AGENTE_RETENCION", "Agente de Retención Resolucion No. " + agenteRetencion);
        }

        InputStream inputStream = new FileInputStream(new File(dirPlantilla));
        JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        parameters.put("DIR_PLANTILLAS", dirPlantillas);
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint, dirGuardarDoc);

    }

    private String dirPlantillaJasper(String ruc, String ireportDir) {
        File f = new File(ireportDir + "/" + ruc);
        if (f.exists()) {
            return f.getAbsolutePath();
        }
        f = new File(ireportDir + "/Default");
        return f.getAbsolutePath();
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
        } else if (formaPago.equals("19")) {
            formaDePago = "TARJETA DE CRÉDITO";
        } else if (formaPago.equals("20")) {
            formaDePago = "OTROS CON UTILIZACION DEL SISTEMA FINANCIERO";
        } else if (formaPago.equals("21")) {
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
        } else if (codDoc.equals("03")) {
            tipoComprobante = "LIQUIDACIÓN DE COMPRA DE BIENES Y PRESTACIÓN DE SERVICIOS";
        }

        return tipoComprobante;
    }

    private String impuestoRetencion(String codigo) {
        return codigo.equals("1") ? "RENTA" : (codigo.equals("2") ? "IVA" : "ISD");
    }

    private static String formatDouble(double num) {
        return String.format("%.2f", num).replace(",", ".");
    }
}
