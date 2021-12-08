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
import JasperRide.JasperRideProforma;
import Respuesta.MensajeGenerado;
import Respuesta.Respuesta;
import Respuesta.RespuestaComprobanteConsultado;
import Respuesta.RespuestaInterna;

import TiposComprobantes.*;
import TiposComprobantes.Bloques.CampoAdicional;
import Util.Util;
import java.util.List;
import java.io.File;
import java.nio.file.Paths;

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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.xml.sax.InputSource;

/**
 *
 * @author Yoelvys
 */
@WebService(serviceName = "ProcesarComprobanteElectronico")
@XmlSeeAlso({Factura.class, LiquidacionCompra.class, GuiaRemision.class, ComprobanteRetencion.class, NotaDebito.class, NotaCredito.class, ComprobantePendiente.class, ConfigAplicacion.class, ConfigCorreo.class, Proforma.class})
public class ProcesarComprobanteElectronico {

    //private final String DIR_IREPORT = "/home/Facturacion/IReport";
    private final String DIR_IREPORT = "C:\\Desarrollos\\IReport";

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
                jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), DIR_IREPORT);
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

    @WebMethod(operationName = "generarXMLPDF")
    public Respuesta generarXMLPDF(@WebParam(name = "comprobante") ComprobanteGeneral comprobante, @WebParam(name = "envioEmail") boolean envioEmail) {
        ConfigAplicacion configAplicacion = comprobante.getConfigAplicacion();
        ConfigCorreo configCorreo = comprobante.getConfigCorreo();

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
            respuestaInterna = facturacion.ProcesarComprobante(xmlComprobante, configAplicacion, false);
        } catch (Exception e) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR PROCESANDO EL XML DEL COMPROBANTE ELECTRONICO", null, "EXCEPCION"));

            return respuesta;
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
                JasperRide jasperRide = new JasperRide();
                String numAutorizacion = respuestaInterna.getComprobante().getElementsByTagName("claveAcceso").item(0).getTextContent();
                jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), DIR_IREPORT);
            } catch (Exception e) {
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL PDF", null, "WARNING"));
            }
            if (envioEmail) {
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
        }

        return respuesta;
    }

    @WebMethod(operationName = "procesarXML")
    public Respuesta procesarXML(@WebParam(name = "xml") String xml, @WebParam(name = "configAplicacion") ConfigAplicacion configAplicacion, @WebParam(name = "configCorreo") ConfigCorreo configCorreo) {

        Util util = new Util();
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        Respuesta respuesta = new Respuesta();
        Facturacion facturacion = new Facturacion();

        Document xmlComprobante = null;

        try {
            xmlComprobante = util.convertirStringToXML(xml);
            respuesta.setClaveAcceso(xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent());
            facturacion.setAmbiente(xmlComprobante.getElementsByTagName("ambiente").item(0).getTextContent());
        } catch (Exception e) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CONVIRTIENDO EL STRING XML A Document", null, "EXCEPCION"));

            return respuesta;
        }

        try {
            respuestaInterna = facturacion.ProcesarComprobante(xmlComprobante, configAplicacion, true);
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
        if (respuestaInterna.getEstadoComprobante().equals("AUTORIZADO")) {
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
                JasperRide jasperRide = new JasperRide();
                String numAutorizacion = respuestaInterna.getComprobante().getElementsByTagName("claveAcceso").item(0).getTextContent();
                jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), DIR_IREPORT);
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
                    try {
                        JasperRide jasperRide = new JasperRide();
                        jasperRide.CrearRide(xmlComprobante, numAutorizacion, dirGuardarDoc, configAplicacion.getDirLogo(), DIR_IREPORT);
                    } catch (Exception e) {
                        respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL PDF AUTORIZADO. ERROR: " + e.getMessage(), null, "WARNING"));
                    }
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
                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CONVIRTIENDO XML DE RESPUESTA A TEXTO. ERROR: " + e.getMessage(), null, "EXCEPCION"));
            }

        } catch (Exception ex) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR CREANDO EL XML DEL DOCUMENTO RECIBIDO", null, "EXCEPCION"));
        }
        return respuesta;
    }

    @WebMethod(operationName = "reenviarEmail")
    public Respuesta reenviarEmail(@WebParam(name = "reenvioEmailParam") ReenvioEmailParam reenvioEmailParam) {
        Respuesta respuesta = new Respuesta();
        try {
            ConfigCorreo configCorreo = reenvioEmailParam.getConfigCorreo();
            JCMail jcmail = new JCMail();
            jcmail.setHost(configCorreo.getCorreoHost());
            jcmail.setPort(configCorreo.getCorreoPort());
            jcmail.setFrom(configCorreo.getCorreoRemitente());
            jcmail.setPassword(configCorreo.getCorreoPass().toCharArray());
            jcmail.setSubject(configCorreo.getCorreoAsunto());

            String dirXmlAutorizado = reenvioEmailParam.getDirDocAutorizados();
            dirXmlAutorizado = Paths.get(dirXmlAutorizado).resolve(reenvioEmailParam.getIdentificacionComprador()).resolve(reenvioEmailParam.getNombreArchivo()).toString() + ".xml";
            File xml = new File(dirXmlAutorizado);
            if (xml.exists()) {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document xmlComprobante = dBuilder.parse(new InputSource(dirXmlAutorizado));

                String destinatarios = obtenerDestinatarios(xmlComprobante);
                if (reenvioEmailParam.getOtrosDestinatarios() != null && !reenvioEmailParam.getOtrosDestinatarios().equals("")) {
                    if (!destinatarios.equals("")) {
                        destinatarios = destinatarios + "," + reenvioEmailParam.getOtrosDestinatarios();
                    } else {
                        destinatarios = reenvioEmailParam.getOtrosDestinatarios();
                    }
                }
                jcmail.setToBBC(configCorreo.getBBC());
                jcmail.setToCC(configCorreo.getCC());
                if (!destinatarios.equals("")) {
                    jcmail.setMessage(crearMensajeCorreo(xmlComprobante));
                    jcmail.setTo(destinatarios);
                    jcmail.SEND(reenvioEmailParam.getNombreArchivo() + ".xml", dirXmlAutorizado, configCorreo.isSslHabilitado());
                } else {
                    respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", "NO EXISTEN DESTINATARIOS", "WARNING"));
                }
            } else {
                respuesta.addMensaje(new MensajeGenerado("1000", "EL XML NO EXISTE", "El xml en la ruta: " + dirXmlAutorizado + " NO EXISTE", "WARNING"));
            }
        } catch (Exception e) {
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", e.getMessage(), "WARNING"));
        }

        return respuesta;
    }

    @WebMethod(operationName = "obtenerComprobante")
    public RespuestaComprobanteConsultado obtenerComprobante(@WebParam(name = "claveAcceso") String claveAcceso, @WebParam(name = "ambiente") String ambiente) {
        Facturacion facturacion = new Facturacion();
        RespuestaComprobanteConsultado respuesta = new RespuestaComprobanteConsultado();
        facturacion.setAmbiente(ambiente);
        RespuestaInterna respuestaInterna = facturacion.procesarComprobantesPendientesAutorizacion(claveAcceso);
        respuesta.setEstadoComprobante(respuestaInterna.getEstadoComprobante());
        respuesta.setFechaAutorizacion(respuestaInterna.getFechaAutorizacion());
        respuesta.setClaveAcceso(claveAcceso);
        respuesta.setMensajes(respuestaInterna.getMensajes());
        respuesta.setComprobante(respuestaInterna.getDocAutorizado());
        return respuesta;

    }

    @WebMethod(operationName = "procesarProforma")
    public Respuesta procesarProforma(@WebParam(name = "proforma") Proforma proforma) {
        Respuesta respuesta = new Respuesta();
        String directorioRaiz = proforma.getDirProformas();
        directorioRaiz = Paths.get(directorioRaiz).resolve(proforma.getIdentificacionComprador()).toString();
        File directorio = new File(directorioRaiz);
        if (!directorio.exists()) {
            directorio.mkdir();
        }
        String nombreFichero = "PROFORMA-" + proforma.getNumero() + ".pdf";
        String dirGuardarDoc = Paths.get(directorioRaiz).resolve(nombreFichero).toString();
        try {
            //Ride ride = new Ride();
            JasperRideProforma jasperRideProforma = new JasperRideProforma();
            jasperRideProforma.CrearRide(proforma, dirGuardarDoc, DIR_IREPORT);

        } catch (Exception e) {
            respuesta.setEstadoComprobante("ERROR");
            respuesta.addMensaje(new MensajeGenerado("1000", "ERROR GUARDANDO EL PDF PROFORMA", e.getMessage(), "WARNING"));
            return respuesta;
        }
        ConfigCorreo configCorreo = proforma.getConfigCorreo();
        if (configCorreo != null && configCorreo.getCorreoHost() != null && !configCorreo.getCorreoHost().equals("")) {
            try {
                JCMail jcmail = new JCMail();
                jcmail.setHost(configCorreo.getCorreoHost());
                jcmail.setPort(configCorreo.getCorreoPort());
                jcmail.setFrom(configCorreo.getCorreoRemitente());
                jcmail.setPassword(configCorreo.getCorreoPass().toCharArray());
                jcmail.setSubject(configCorreo.getCorreoAsunto());

                String destinatarios = "";
                List<CampoAdicional> campoAdicional = proforma.getInfoAdicional();
                for (int i = 0; i < campoAdicional.size(); i++) {
                    if (campoAdicional.get(i).getNombre().equals("Email")) {
                        destinatarios = campoAdicional.get(i).getValor();
                    }
                }
                jcmail.setToBBC(configCorreo.getBBC());
                jcmail.setToCC(configCorreo.getCC());
                if (!destinatarios.equals("")) {
                    jcmail.setMessage(crearMensajeCorreoProforma(proforma.getRazonSocialComprador(), proforma.getRazonSocial(), proforma.getNumero(), proforma.getImporteTotal()));
                    jcmail.setTo(destinatarios);
                    jcmail.SEND(nombreFichero, dirGuardarDoc, configCorreo.isSslHabilitado());
                    respuesta.setEstadoComprobante("ENVIADA");
                }
            } catch (Exception e) {
                respuesta.setEstadoComprobante("ERROR EMAIL");

                respuesta.addMensaje(new MensajeGenerado("1000", "ERROR ENVIANDO EL CORREO AL CLIENTE", e.getMessage(), "WARNING"));
            }
        }
        return respuesta;
    }

    private static String crearMensajeCorreoProforma(String cliente, String razonSocialEmisor, String noProforma, String importeTotal) {
        String mensaje = "Estimado(a),<br /><br /><strong>" + cliente + "</strong>";
        mensaje += "<br /><br />Esta es una notificacion automática de una proforma emitida por <strong>" + razonSocialEmisor + "</strong><br /><br /> ";

        mensaje += "<strong>Proforma No. </strong>" + noProforma + "<br />";
        mensaje += "<strong>Valor Total: </strong>" + importeTotal + "<br /><br />";
        mensaje += "Los detalles generales de la proforma pueden ser consultados en el archivo pdf adjunto en este correo.<br /><br />"
                + "<strong>Atentamente,</strong><br /><br />"
                + "         <strong>" + razonSocialEmisor + "</strong>";
        return mensaje;
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
            if (nombre.startsWith("Email")) {
                if (node.getTextContent() == null || node.getTextContent().isEmpty()) {
                    break;
                }
                destinatarios = node.getTextContent();
            }
        }
        return destinatarios;
    }
}
