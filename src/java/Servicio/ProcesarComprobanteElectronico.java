/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servicio;

import Email.JCMail;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import Facturacion.Facturacion;
import JasperRide.JasperRide;
import Respuesta.MensajeGenerado;
import Respuesta.Respuesta;
import Respuesta.RespuestaComprobanteLote;
import Respuesta.RespuestaInterna;
import Ride.Ride;
import SRI.Autorizacion.Autorizacion;
import SRI.Autorizacion.Mensaje;
import SRI.Recepcion.Comprobante;

import TiposComprobantes.*;
import Util.Util;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;
import java.util.Properties;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import Util.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Yoelvys
 */
@WebService(serviceName = "ProcesarComprobanteElectronico")
@XmlSeeAlso({Factura.class, LiquidacionCompra.class, GuiaRemision.class, ComprobanteRetencion.class, NotaDebito.class, NotaCredito.class, ComprobantePendiente.class, ConfigAplicacion.class, ConfigCorreo.class})
public class ProcesarComprobanteElectronico {

    @WebMethod(operationName = "procesarComprobante")
    public Respuesta procesarComprobante(@WebParam(name = "comprobante") ComprobanteGeneral comprobante, @WebParam(name = "envioSRI") boolean envioSRI) {
        ConfigAplicacion configAplicacion = comprobante.getConfigAplicacion();
        ConfigCorreo configCorreo = comprobante.getConfigCorreo();

        Util util = new Util();
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        Respuesta respuesta = new Respuesta();
        Facturacion facturacion = new Facturacion();
        facturacion.setAmbiente(comprobante.getAmbiente());
        Document xmlComprobante = null;

        try {
            xmlComprobante = comprobante.crearXMLComprobante();
            respuesta.setClaveAcceso(xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent());
        } catch (Exception e) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CREANDO EL XML DEL DOCUMENTO RECIBIDO", null, "EXCEPCION"));

            return respuesta;
        }
        try {
            respuestaInterna = facturacion.ProcesarComprobante(xmlComprobante, configAplicacion, envioSRI);
        } catch (Exception e) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR PROCESANDO EL XML DEL COMPROBANTE ELECTRONICO", null, "EXCEPCION"));

            return respuesta;
        }
        if (respuestaInterna.getEstadoComprobante().equals("ERROR")) {

        }
        respuesta.setEstadoComprobante(respuestaInterna.getEstadoComprobante());
        respuesta.setNumeroAutorizacion(respuestaInterna.getNumeroAutorizacion());
        respuesta.setFechaAutorizacion(respuestaInterna.getFechaAutorizacion());
        respuesta.setMensajes(respuestaInterna.getMensajes());
        if (respuestaInterna.getEstadoComprobante().equals("FIRMADO")) {
            String directorioRaiz = configAplicacion.getDirAutorizados();
            String nombreFichero = "", dirGuardarDoc = "";
            try {
                xmlComprobante = respuestaInterna.getComprobante();
                directorioRaiz = Paths.get(directorioRaiz).resolve(crearNombreCarpeta(xmlComprobante)).toString();
                File directorio = new File(directorioRaiz);
                if (!directorio.exists()) {
                    directorio.mkdir();
                }
                nombreFichero = crearNombreFichero(xmlComprobante);
                dirGuardarDoc = Paths.get(directorioRaiz).resolve(nombreFichero).toString();

                Source source = new DOMSource(respuestaInterna.getComprobante());
                Result result = new StreamResult(new File(dirGuardarDoc));
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformer.transform(source, result);
            } catch (Exception e) {
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL XML AUTORIZADO", null, "WARNING"));
            }
            try {
                //Ride ride = new Ride();
                JasperRide jasperRide = new JasperRide();
                String numAutorizacion = respuestaInterna.getComprobante().getElementsByTagName("claveAcceso").item(0).getTextContent();
                //ride.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo());
                jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), "D:\\Desarrollos\\IReport");
            } catch (Exception e) {
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL PDF AUTORIZADO", null, "WARNING"));
            }
            try {
                JCMail jcmail = new JCMail();
                jcmail.setHost(configCorreo.getCorreoHost());
                jcmail.setPort(configCorreo.getCorreoPort());
                jcmail.setFrom(configCorreo.getCorreoRemitente());
                jcmail.setPassword(configCorreo.getCorreoPass().toCharArray());
                jcmail.setSubject(configCorreo.getCorreoAsunto());

                String destinatarios = obtenerDestinatarios(xmlComprobante);
                jcmail.setToBBC(configCorreo.getBBC());
                jcmail.setToCC(configCorreo.getCC());
                if (!destinatarios.equals("")) {
                    jcmail.setMessage(crearMensajeCorreo(xmlComprobante));
                    jcmail.setTo(destinatarios);
                    jcmail.SEND(nombreFichero, dirGuardarDoc, configCorreo.isSslHabilitado());
                }
            } catch (Exception e) {
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", e.getMessage(), "WARNING"));
            }
        }

        return respuesta;
    }

    @WebMethod(operationName = "procesarComprobanteLote")
    public RespuestaComprobanteLote procesarComprobanteLotes(@WebParam(name = "comprobanteLote") ComprobanteLote comprobanteLote) {
        Util util = new Util();
        ConfigAplicacion configAplicacion = comprobanteLote.getConfigAplicacion();
        ConfigCorreo configCorreo = comprobanteLote.getConfigCorreo();

        RespuestaInterna respuestaInterna = new RespuestaInterna();
        RespuestaComprobanteLote respuestaLote = new RespuestaComprobanteLote();

        Facturacion facturacion = new Facturacion();
        facturacion.setAmbiente(comprobanteLote.getAmbiente());
        try {
            respuestaInterna = comprobanteLote.crearXMLComprobante();
        } catch (Exception e) {
            respuestaLote.setError(true);
            respuestaLote.setMensajeGeneral(new MensajeGenerado("1000", "ERROR CREANDO EL XML DEL DOCUMENTO RECIBIDO", null, "EXCEPCION"));

            return respuestaLote;
        }
        if (respuestaInterna.getEstadoComprobante().equals("ERROR")) {
            respuestaLote.setError(true);
            respuestaLote.setMensajeGeneral(respuestaInterna.getMensajes().get(0));
            return respuestaLote;
        }
        try {
            respuestaInterna = facturacion.ProcesarComprobanteLote(respuestaInterna.getComprobante());
        } catch (Exception e) {
            respuestaLote.setError(true);
            respuestaLote.setMensajeGeneral(new MensajeGenerado("1000", "ERROR PROCESANDO XML LOTE", null, "EXCEPCION"));

            return respuestaLote;
        }

        if (respuestaInterna.getEstadoComprobante() != null && respuestaInterna.getEstadoComprobante().equals("ERROR")) {
            respuestaLote.setError(true);
            respuestaLote.setMensajeGeneral(respuestaInterna.getMensajes().get(0));
            return respuestaLote;
        }

        respuestaLote.setError(false);
        if (respuestaInterna.getRespuestaRecepcion() != null) {
            List<Comprobante> comprobantesDevueltos = respuestaInterna.getRespuestaRecepcion().getComprobantes().getComprobante();
            int numeroComprobantesDevueltos = comprobantesDevueltos.size();
            for (int i = 0; i < numeroComprobantesDevueltos; i++) {
                Respuesta respuesta = new Respuesta();

                respuesta.setEstadoComprobante("DEVUELTA");

                List<SRI.Recepcion.Mensaje> mensajes = comprobantesDevueltos.get(i).getMensajes().getMensaje();
                int cantMensaje = mensajes.size();
                for (int j = 0; j < cantMensaje; j++) {
                    MensajeGenerado mensajeGenerado = new MensajeGenerado();
                    mensajeGenerado.setIdentificador(mensajes.get(j).getIdentificador());
                    mensajeGenerado.setMensaje(mensajes.get(j).getMensaje());
                    mensajeGenerado.setInformacionAdicional(mensajes.get(j).getInformacionAdicional());
                    mensajeGenerado.setTipo(mensajes.get(j).getTipo());
                    respuesta.getMensajes().add(mensajeGenerado);
                }

                respuesta.setClaveAcceso(comprobantesDevueltos.get(i).getClaveAcceso());
                respuesta.setComprobanteID(comprobantesDevueltos.get(i).getClaveAcceso().substring(24, 39));
                respuestaLote.getRespuestas().add(respuesta);
            }
        }
        if (respuestaInterna.getRespuestaLote() != null) {
            respuestaLote.setClaveAccesoConsultada(respuestaInterna.getRespuestaLote().getClaveAccesoLoteConsultada());
            int numeroComprobantesLote = Integer.parseInt(respuestaInterna.getRespuestaLote().getNumeroComprobantesLote());
            List<Autorizacion> autorizaciones = respuestaInterna.getRespuestaLote().getAutorizaciones().getAutorizacion();
            for (int i = 0; i < numeroComprobantesLote; i++) {
                Respuesta respuesta = new Respuesta();

                respuesta.setEstadoComprobante(autorizaciones.get(i).getEstado());
                respuesta.setNumeroAutorizacion(autorizaciones.get(i).getNumeroAutorizacion());
                respuesta.setClaveAcceso(autorizaciones.get(i).getNumeroAutorizacion());
                List<Mensaje> mensajes = autorizaciones.get(i).getMensajes().getMensaje();
                int cantMensaje = mensajes.size();
                for (int j = 0; j < cantMensaje; j++) {
                    MensajeGenerado mensajeGenerado = new MensajeGenerado();
                    mensajeGenerado.setIdentificador(mensajes.get(j).getIdentificador());
                    mensajeGenerado.setMensaje(mensajes.get(j).getMensaje());
                    mensajeGenerado.setInformacionAdicional(mensajes.get(j).getInformacionAdicional());
                    mensajeGenerado.setTipo(mensajes.get(j).getTipo());
                    respuesta.getMensajes().add(mensajeGenerado);
                }
                Document xmlComprobante = null;
                try {
                    xmlComprobante = util.convertirStringToXML(autorizaciones.get(i).getComprobante());

                    respuesta.setComprobanteID(xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent().substring(24, 39));

                    if (respuesta.getEstadoComprobante().equals("AUTORIZADO")) {
                        RespuestaInterna respuestaAutorizacionLote = null;
                        try {
                            respuestaAutorizacionLote = facturacion.CrearRespuestaAutorizacionLote(autorizaciones.get(i));

                            String ruc = xmlComprobante.getElementsByTagName("ruc").item(0).getTextContent();
                            String directorioRaiz = configAplicacion.getDirAutorizados();
                            String nombreFichero = "", dirGuardarDoc = "";
                            try {
                                directorioRaiz = Paths.get(directorioRaiz).resolve(crearNombreCarpeta(xmlComprobante)).toString();
                                File directorio = new File(directorioRaiz);
                                if (!directorio.exists()) {
                                    directorio.mkdir();
                                }
                                nombreFichero = crearNombreFichero(xmlComprobante);
                                dirGuardarDoc = Paths.get(directorioRaiz).resolve(crearNombreFichero(xmlComprobante)).toString();

                                Source source = new DOMSource(respuestaAutorizacionLote.getComprobante());
                                Result result = new StreamResult(new File(dirGuardarDoc));
                                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                                transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                                transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                                transformer.transform(source, result);
                            } catch (Exception e) {
                                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL XML AUTORIZADO", null, "WARNING"));
                            }
                            try {
                                //Ride ride = new Ride();
                                String numAutorizacion = respuestaAutorizacionLote.getComprobante().getElementsByTagName("numeroAutorizacion").item(0).getTextContent();
                                //ride.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo());
                                JasperRide jasperRide = new JasperRide();
                                jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), "D:\\Desarrollos\\IReport");
                            } catch (Exception e) {
                                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL PDF AUTORIZADO", null, "WARNING"));
                            }
                            try {
                                JCMail jcmail = new JCMail();
                                jcmail.setHost(configCorreo.getCorreoHost());
                                jcmail.setPort(configCorreo.getCorreoPort());
                                jcmail.setFrom(configCorreo.getCorreoRemitente());
                                jcmail.setPassword(configCorreo.getCorreoPass().toCharArray());
                                jcmail.setSubject(configCorreo.getCorreoAsunto());

                                String destinatarios = obtenerDestinatarios(xmlComprobante);
                                jcmail.setToBBC(configCorreo.getBBC());
                                jcmail.setToCC(configCorreo.getCC());
                                if (!destinatarios.equals("")) {
                                    jcmail.setMessage(crearMensajeCorreo(xmlComprobante));
                                    jcmail.setTo(destinatarios);
                                    jcmail.SEND(nombreFichero, dirGuardarDoc, configCorreo.isSslHabilitado());
                                }
                            } catch (Exception e) {
                                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", null, "WARNING"));
                            }
                        } catch (Exception e) {
                            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CREANDO RESPUESTA DE AUTORIZACION", null, "EXCEPTION"));
                        }

                    }
                } catch (Exception e) {
                    respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CONVIRTIENDO A STRING EL XML PROCESADO POR EL SRI", null, "EXCEPTION"));
                }
                respuestaLote.getRespuestas().add(respuesta);
            }
        }

        return respuestaLote;

    }

    @WebMethod(operationName = "procesarComprobantePendiente")
    public Respuesta procesarComprobantePendiente(@WebParam(name = "comprobantePendiente") ComprobantePendiente comprobantePendiente) {
        Util util = new Util();
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        Respuesta respuesta = new Respuesta();

        ConfigAplicacion configAplicacion = comprobantePendiente.getConfigAplicacion();
        ConfigCorreo configCorreo = comprobantePendiente.getConfigCorreo();

        try {

            Facturacion facturacion = new Facturacion();
            facturacion.setAmbiente(comprobantePendiente.getAmbiente());
            String claveAcceso = "";
            if (comprobantePendiente.getClavAcc() != null && !comprobantePendiente.getClavAcc().equals("")) {
                claveAcceso = comprobantePendiente.getClavAcc();
            } else {
                claveAcceso = comprobantePendiente.claveAcceso();
            }

            respuestaInterna = facturacion.procesarComprobantesPendientesAutorizacion(claveAcceso);
            try {
                respuesta.setEstadoComprobante(respuestaInterna.getEstadoComprobante());
                respuesta.setNumeroAutorizacion(respuestaInterna.getNumeroAutorizacion());
                respuesta.setFechaAutorizacion(respuestaInterna.getFechaAutorizacion());
                respuesta.setClaveAcceso(claveAcceso);
                respuesta.setMensajes(respuestaInterna.getMensajes());
                if (respuestaInterna.getEstadoComprobante().equals("AUTORIZADO")) {
                    String directorioRaiz = configAplicacion.getDirAutorizados();
                    Document xmlComprobante = util.convertirStringToXML(respuestaInterna.getDocAutorizado());
                    directorioRaiz = Paths.get(directorioRaiz).resolve(crearNombreCarpeta(xmlComprobante)).toString();
                    File directorio = new File(directorioRaiz);
                    if (!directorio.exists()) {
                        directorio.mkdir();
                    }
                    String nombreFichero = crearNombreFichero(xmlComprobante);
                    String dirGuardarDoc = Paths.get(directorioRaiz).resolve(nombreFichero).toString();

                    Source source = new DOMSource(xmlComprobante);
                    Result result = new StreamResult(new File(dirGuardarDoc));
                    Transformer transformer = TransformerFactory.newInstance().newTransformer();
                    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
                    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                    transformer.transform(source, result);

                    //Ride ride = new Ride();
                    String numAutorizacion = respuestaInterna.getComprobante().getElementsByTagName("claveAcceso").item(0).getTextContent();
                    //ride.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo());
                    JasperRide jasperRide = new JasperRide();
                    jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), "D:\\Desarrollos\\IReport");
                    if (comprobantePendiente.isEnviarEmail()) {
                        try {
                            JCMail jcmail = new JCMail();
                            jcmail.setHost(configCorreo.getCorreoHost());
                            jcmail.setPort(configCorreo.getCorreoPort());
                            jcmail.setFrom(configCorreo.getCorreoRemitente());
                            jcmail.setPassword(configCorreo.getCorreoPass().toCharArray());
                            jcmail.setSubject(configCorreo.getCorreoAsunto());

                            String destinatarios = obtenerDestinatarios(xmlComprobante);
                            if (comprobantePendiente.getOtrosDestinatarios() != null && !comprobantePendiente.getOtrosDestinatarios().equals("")) {
                                if (!destinatarios.equals("")) {
                                    destinatarios = destinatarios + "," + comprobantePendiente.getOtrosDestinatarios();
                                } else {
                                    destinatarios = comprobantePendiente.getOtrosDestinatarios();
                                }
                            }
                            jcmail.setToBBC(configCorreo.getBBC());
                            jcmail.setToCC(configCorreo.getCC());
                            if (!destinatarios.equals("")) {
                                jcmail.setMessage(crearMensajeCorreo(xmlComprobante));
                                jcmail.setTo(destinatarios);
                                jcmail.SEND(nombreFichero, dirGuardarDoc, configCorreo.isSslHabilitado());
                            } else {
                                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", "NO EXISTEN DESTINATARIOS", "WARNING"));
                            }
                        } catch (Exception e) {
                            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", e.getMessage(), "WARNING"));
                        }
                    }
                }
            } catch (Exception e) {
                respuesta.setEstadoComprobante("ERROR");
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CONVIRTIENDO XML DE RESPUESTA A TEXTO", null, "EXCEPCION"));
            }

        } catch (Exception ex) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CREANDO EL XML DEL DOCUMENTO RECIBIDO", null, "EXCEPCION"));
        }
        return respuesta;
    }

    private static String crearMensajeCorreo(Document xmlComprobante) {
        String codDoc = xmlComprobante.getElementsByTagName("codDoc").item(0).getTextContent();
        String tipoComprobante = "";
        String dirigido = "";
        String datos = "";
        if (codDoc.equals("01")) {
            tipoComprobante = "FACTURA";
            dirigido = "razonSocialComprador";
            String importeTotal = xmlComprobante.getElementsByTagName("importeTotal").item(0).getTextContent();
            datos = "<strong>Valor Total: </strong>" + importeTotal + "<br /><br />";
        } else if (codDoc.equals("03")) {
            tipoComprobante = "LIQUIDACIÓN DE COMPRA DE BIENES Y PRESTACIÓN DE SERVICIOS";
            dirigido = "razonSocialProveedor";
            String importeTotal = xmlComprobante.getElementsByTagName("importeTotal").item(0).getTextContent();
            datos = "<strong>Valor Total: </strong>" + importeTotal + "<br /><br />";
        } else if (codDoc.equals("04")) {
            tipoComprobante = "NOTA DE CRÉDITO";
            dirigido = "razonSocialComprador";
        } else if (codDoc.equals("05")) {
            tipoComprobante = "NOTA DE DÉBITO";
            dirigido = "razonSocialComprador";
        } else if (codDoc.equals("06")) {
            tipoComprobante = "GUÍA DE REMISIÓN";
            dirigido = "razonSocialDestinatario";
        } else if (codDoc.equals("07")) {
            tipoComprobante = "COMPROBANTE DE RETENCIÓN";
            dirigido = "razonSocialSujetoRetenido";
        }
        String razonSocial = xmlComprobante.getElementsByTagName(dirigido).item(0).getTextContent();
        String razonSocialEmisor = xmlComprobante.getElementsByTagName("razonSocial").item(0).getTextContent();
        String mensaje = "Estimado(a),<br /><br /><strong>" + razonSocial + "</strong>";
        mensaje += "<br /><br />Esta es una notificacion automatica de un documento tributario electronico emitido por <strong>" + razonSocialEmisor + "</strong><br /><br /> ";
        String establecimiento = xmlComprobante.getElementsByTagName("estab").item(0).getTextContent();
        String ptoEmision = xmlComprobante.getElementsByTagName("ptoEmi").item(0).getTextContent();
        String secuencial = xmlComprobante.getElementsByTagName("secuencial").item(0).getTextContent();

        mensaje += "<strong>Tipo de Comprobante: </strong>" + tipoComprobante + "<br /><br />";
        mensaje += "<strong>Nro de Comprobante: </strong>" + establecimiento + "-" + ptoEmision + "-" + secuencial + "<br /><br />";
        mensaje += datos;
        mensaje += "Los detalles generales del comprobante pueden ser consultados en el archivo pdf adjunto en este correo.<br /><br />"
                + "<strong>Atentamente,</strong><br /><br />"
                + "         <strong>" + razonSocialEmisor + "</strong>";
        return mensaje;
    }

    private String crearNombreFichero(Document xmlComprobante) {
        String nombre = "";
        String codDoc = xmlComprobante.getElementsByTagName("codDoc").item(0).getTextContent();
        String estab = xmlComprobante.getElementsByTagName("estab").item(0).getTextContent();
        String ptoEmi = xmlComprobante.getElementsByTagName("ptoEmi").item(0).getTextContent();
        String secuencial = xmlComprobante.getElementsByTagName("secuencial").item(0).getTextContent();
        if (codDoc.equals("01")) {
            nombre = "FAC";
        } else if (codDoc.equals("04")) {
            nombre = "NC";
        } else if (codDoc.equals("05")) {
            nombre = "ND";
        } else if (codDoc.equals("06")) {
            nombre = "GR";
        } else if (codDoc.equals("07")) {
            nombre = "CR";
        } else if (codDoc.equals("03")) {
            nombre = "LIQ";
        }
        return nombre + estab + "-" + ptoEmi + "-" + secuencial + ".xml";
    }

    private String crearNombreCarpeta(Document xmlComprobante) {
        String nombre = "";
        String codDoc = xmlComprobante.getElementsByTagName("codDoc").item(0).getTextContent();
        if (codDoc.equals("01")) {
            nombre = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        } else if (codDoc.equals("04")) {
            nombre = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        } else if (codDoc.equals("05")) {
            nombre = xmlComprobante.getElementsByTagName("identificacionComprador").item(0).getTextContent();
        } else if (codDoc.equals("06")) {
            nombre = xmlComprobante.getElementsByTagName("rucTransportista").item(0).getTextContent();
        } else if (codDoc.equals("07")) {
            nombre = xmlComprobante.getElementsByTagName("identificacionSujetoRetenido").item(0).getTextContent();
        } else if (codDoc.equals("03")) {
            nombre = xmlComprobante.getElementsByTagName("identificacionProveedor").item(0).getTextContent();
        }
        return nombre;
    }

    private static String obtenerDestinatarios(Document xmlComprobante) {
        NodeList campoAdicional = xmlComprobante.getElementsByTagName("campoAdicional");
        int cantidad = campoAdicional.getLength();
        String destinatarios = "";
        for (int i = 0; i < cantidad; i++) {
            Node node = campoAdicional.item(i);
            String nombre = node.getAttributes().item(0).getNodeValue();
            if (nombre.equals("Email")) {
                if (node.getTextContent() == null || node.getTextContent().isEmpty()) {
                    break;
                }
                destinatarios = node.getTextContent();
            }
        }
        return destinatarios;
    }
}
