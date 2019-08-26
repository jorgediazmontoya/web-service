/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facturacion;

import SRI.Autorizacion.*;
import SRI.Recepcion.*;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import Respuesta.*;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;

import Util.Util;
import FirmaElectronica.FirmaElectronica;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Properties;
import Util.*;
import java.net.MalformedURLException;
import org.apache.tomcat.jni.Time;

/**
 *
 * @author Yoelvys
 */
public class Facturacion {

    private final FirmaElectronica firma;
    private String ambiente;

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

    enum TipoError {

        EXCEPCION, AUTORIZACION
    }
    private Util util;

    public Facturacion() {
        util = new Util();
        firma = new FirmaElectronica();
    }

    public RespuestaInterna ProcesarComprobante(Document xmlComprobante, ConfigAplicacion configAplicacion, boolean envioSRI) {

        EliminarCertificado();
        firma.setDireccionCertificado(configAplicacion.getDirFirma());
        firma.setPasswordCertificado(configAplicacion.getPassFirma());
        firma.setDirCarpetaAutorizados(configAplicacion.getDirAutorizados());
        RespuestaInterna respuestaFirma = firma.FirmarComprobante(xmlComprobante);

        if (respuestaFirma.getEstadoComprobante().equals("ERROR")) {

            return respuestaFirma;
        }
        if (!envioSRI) {
            return respuestaFirma;
        }
        RespuestaSolicitud respuestaRecepcion = null;
        try {
            this.ambiente = xmlComprobante.getElementsByTagName("ambiente").item(0).getTextContent();
            respuestaRecepcion = ValidarComprobante(util.convertirXMLToString(respuestaFirma.getComprobante()), ambiente);
        } catch (Exception e) {

            return CrearRespuestaException("PROBLEMAS EN LA COMUNICACION CON EL SERVICIO DE RECEPCION DEL SRI");
        }
        if (respuestaRecepcion.getEstado().equals("DEVUELTA") && !respuestaRecepcion.getComprobantes().getComprobante().isEmpty()) {
            try {
                return CrearRespuestaRecepcion(respuestaRecepcion, respuestaFirma.getComprobante());
            } catch (Exception e) {

                return CrearRespuestaException("PROBLEMAS CREANDO LA RESPUESTA DE RECEPCION DEL SRI");
            }

        } else if (respuestaRecepcion.getEstado().equals("DEVUELTA")) {

            return CrearRespuestaException("RESPUESTA DE RECEPCION CON ARREGLO DE COMPROBANTES VACIOS");

        } else {
            RespuestaComprobante respuestaAutorizacion = null;
            try {
                String claveAcceso = xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent();
                Thread.sleep(1500);
                respuestaAutorizacion = AutorizarComprobante(claveAcceso, ambiente);
            } catch (Exception e) {

                return CrearRespuestaException("PROBLEMAS EN LA COMUNICACION CON EL SERVICIO DE AUTORIZACION DEL SRI");
            }

            return CrearRespuestaAutorizacion(respuestaAutorizacion);
        }

    }

    public RespuestaInterna ProcesarComprobanteLote(Document xmlComprobante) {

        EliminarCertificado();
        RespuestaSolicitud respuestaRecepcion = null;
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        try {
            respuestaRecepcion = ValidarComprobante(util.convertirXMLToString(xmlComprobante), ambiente);
            respuestaInterna.setRespuestaRecepcion(respuestaRecepcion);
        } catch (Exception e) {

            return CrearRespuestaException("PROBLEMAS EN LA COMUNICACION CON EL SERVICIO DE RECEPCION DEL SRI");
        }
        if (respuestaRecepcion.getEstado().equals("DEVUELTA") && !respuestaRecepcion.getComprobantes().getComprobante().isEmpty()) {

            return respuestaInterna;

        } else if (respuestaRecepcion.getEstado().equals("DEVUELTA")) {

            return CrearRespuestaException("RESPUESTA DE RECEPCION CON ARREGLO DE COMPROBANTES VACIOS");

        } else {
            RespuestaLote respuestaAutorizacion = null;
            try {
                String claveAcceso = xmlComprobante.getElementsByTagName("claveAcceso").item(0).getTextContent();
                respuestaAutorizacion = AutorizarComprobanteLote(claveAcceso, ambiente);
            } catch (Exception e) {

                return CrearRespuestaException("PROBLEMAS EN LA COMUNINCACION CON EL SERVICIO DE AUTORIZACION DEL SRI");
            }
            respuestaInterna.setRespuestaLote(respuestaAutorizacion);
            return respuestaInterna;
        }

    }

    public RespuestaInterna procesarComprobantesPendientesAutorizacion(String claveAcceso) {
        RespuestaComprobante respuestaAutorizacion = null;
        try {
            respuestaAutorizacion = AutorizarComprobante(claveAcceso, ambiente);
        } catch (Exception e) {
            return CrearRespuestaException("PROBLEMAS EN LA COMUNICACION CON EL SERVICIO DE AUTORIZACION DEL SRI");
        }

        return CrearRespuestaAutorizacion(respuestaAutorizacion);
    }

    private RespuestaSolicitud ValidarComprobante(String comprobante, String ambiente) {

        RecepcionComprobantesOfflineService service = new RecepcionComprobantesOfflineService(ambiente);
        RecepcionComprobantesOffline port = service.getRecepcionComprobantesOfflinePort();

        return port.validarComprobante(comprobante.getBytes(Charset.forName("UTF-8")));

    }

    private RespuestaComprobante AutorizarComprobante(String claveAcceso, String ambiente) {

        AutorizacionComprobantesOfflineService service = new AutorizacionComprobantesOfflineService(ambiente);
        AutorizacionComprobantesOffline port = service.getAutorizacionComprobantesOfflinePort();

        return port.autorizacionComprobante(claveAcceso);

    }

    private RespuestaLote AutorizarComprobanteLote(String claveAcceso, String ambiente) {

        AutorizacionComprobantesOfflineService service = new AutorizacionComprobantesOfflineService(ambiente);
        AutorizacionComprobantesOffline port = service.getAutorizacionComprobantesOfflinePort();

        return port.autorizacionComprobanteLote(claveAcceso);

    }

    private RespuestaInterna CrearRespuestaRecepcion(RespuestaSolicitud respuestaRecepcion, Document xmlComprobante) {

        RespuestaInterna respuestaInterna = new RespuestaInterna();
        respuestaInterna.setEstadoComprobante(respuestaRecepcion.getEstado());

        Comprobante comprobante = respuestaRecepcion.getComprobantes().getComprobante().get(0);

        Element comprobanteGeneral = xmlComprobante.getDocumentElement();

        Element tagRespuesta = xmlComprobante.createElement("respuestaSolicitud");
        Element tagEstado = xmlComprobante.createElement("estado");
        tagEstado.appendChild(xmlComprobante.createTextNode(respuestaRecepcion.getEstado()));
        tagRespuesta.appendChild(tagEstado);

        Element tagComprobantes = xmlComprobante.createElement("comprobantes");
        Element tagComprobante = xmlComprobante.createElement("comprobante");

        Element tagClaveAcceso = xmlComprobante.createElement("claveAcceso");
        tagClaveAcceso.appendChild(xmlComprobante.createTextNode(comprobante.getClaveAcceso()));
        tagComprobante.appendChild(tagClaveAcceso);

        Element tagMensajes = xmlComprobante.createElement("mensajes");

        List<SRI.Recepcion.Mensaje> mensajes = comprobante.getMensajes().getMensaje();
        for (Iterator<SRI.Recepcion.Mensaje> it = mensajes.iterator(); it.hasNext();) {
            SRI.Recepcion.Mensaje mensaje = it.next();
            Element tagMensaje = xmlComprobante.createElement("mensaje");

            Element tagIdentificador = xmlComprobante.createElement("identificador");
            tagIdentificador.appendChild(xmlComprobante.createTextNode(mensaje.getIdentificador()));
            tagMensaje.appendChild(tagIdentificador);

            Element tagMensajeDatos = xmlComprobante.createElement("mensaje");
            tagMensajeDatos.appendChild(xmlComprobante.createTextNode(mensaje.getMensaje()));
            tagMensaje.appendChild(tagMensajeDatos);

            if (mensaje.getInformacionAdicional() != null && !mensaje.getInformacionAdicional().equals("")) {
                Element tagInformacionAdicional = xmlComprobante.createElement("informacionAdicional");
                tagInformacionAdicional.appendChild(xmlComprobante.createTextNode(mensaje.getInformacionAdicional()));
                tagMensaje.appendChild(tagInformacionAdicional);
            }

            Element tagTipo = xmlComprobante.createElement("tipo");
            tagTipo.appendChild(xmlComprobante.createTextNode(mensaje.getTipo()));
            tagMensaje.appendChild(tagTipo);

            tagMensajes.appendChild(tagMensaje);
            if (mensaje.getIdentificador().equals("70")) {
                respuestaInterna.setEstadoComprobante("PROCESANDOSE");
                respuestaInterna.addMensaje(new MensajeGenerado("3000", "EL COMPROBANTE SE ENCUENTRA SIENDO PROCESADO POR EL SRI. ENVIAR NUEVAMENTE EN UNOS 2 MIN. RESPUESTA DEL SRI", null, "PROCESANDOSE"));
            } else {
                respuestaInterna.addMensaje(new MensajeGenerado(mensaje.getIdentificador(), mensaje.getMensaje(), mensaje.getInformacionAdicional(), mensaje.getTipo()));

            }
        }
        tagComprobante.appendChild(tagMensajes);
        tagComprobantes.appendChild(tagComprobante);
        tagRespuesta.appendChild(tagComprobantes);
        comprobanteGeneral.appendChild(tagRespuesta);

        respuestaInterna.setComprobante(xmlComprobante);

        return respuestaInterna;
    }

    public RespuestaInterna CrearRespuestaAutorizacionLote(Autorizacion autorizacion) {
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            DOMImplementation implementation = builder.getDOMImplementation();
            Document xmlComprobante = implementation.createDocument(null, "autorizacion", null);
            xmlComprobante.setXmlVersion("1.0");
            Element tagAutorizacion = xmlComprobante.getDocumentElement();
            Element tagEstado = xmlComprobante.createElement("estado");
            tagEstado.appendChild(xmlComprobante.createTextNode(autorizacion.getEstado()));
            tagAutorizacion.appendChild(tagEstado);
            respuestaInterna.setEstadoComprobante(autorizacion.getEstado());
            if (autorizacion.getNumeroAutorizacion() != null && !autorizacion.getNumeroAutorizacion().equals("")) {
                Element tagNumeroAutoriacion = xmlComprobante.createElement("numeroAutorizacion");
                tagNumeroAutoriacion.appendChild(xmlComprobante.createTextNode(autorizacion.getNumeroAutorizacion()));
                tagAutorizacion.appendChild(tagNumeroAutoriacion);
                respuestaInterna.setNumeroAutorizacion(autorizacion.getNumeroAutorizacion());
            }
            // tener en cuanta el formato de la fecha
            Element tagFechaAutorizacion = xmlComprobante.createElement("fechaAutorizacion");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            GregorianCalendar gc = autorizacion.getFechaAutorizacion().toGregorianCalendar();
            String formatted_string = sdf.format(gc.getTime());
            tagFechaAutorizacion.appendChild(xmlComprobante.createTextNode(formatted_string));
            tagAutorizacion.appendChild(tagFechaAutorizacion);
            tagFechaAutorizacion.setAttribute("class", "fechaAutorizacion");
            Element tagComprobante = xmlComprobante.createElement("comprobante");
            tagComprobante.appendChild(xmlComprobante.createCDATASection(autorizacion.getComprobante()));
            tagAutorizacion.appendChild(tagComprobante);
            respuestaInterna.setDocAutorizado(autorizacion.getComprobante());
            List<SRI.Autorizacion.Mensaje> mensajes = autorizacion.getMensajes().getMensaje();
            if (mensajes != null) {
                Element tagMensajes = xmlComprobante.createElement("mensajes");
                for (Iterator<SRI.Autorizacion.Mensaje> it = mensajes.iterator(); it.hasNext();) {
                    SRI.Autorizacion.Mensaje mensaje = it.next();
                    Element tagMensaje = xmlComprobante.createElement("mensaje");

                    Element tagIdentificador = xmlComprobante.createElement("identificador");
                    tagIdentificador.appendChild(xmlComprobante.createTextNode(mensaje.getIdentificador()));
                    tagMensaje.appendChild(tagIdentificador);

                    Element tagMensajeDatos = xmlComprobante.createElement("mensaje");
                    tagMensajeDatos.appendChild(xmlComprobante.createTextNode(mensaje.getMensaje()));
                    tagMensaje.appendChild(tagMensajeDatos);

                    if (mensaje.getInformacionAdicional() != null && !mensaje.getInformacionAdicional().equals("")) {
                        Element tagInformacionAdicional = xmlComprobante.createElement("informacionAdicional");
                        tagInformacionAdicional.appendChild(xmlComprobante.createTextNode(mensaje.getInformacionAdicional()));
                        tagMensaje.appendChild(tagInformacionAdicional);
                    }

                    Element tagTipo = xmlComprobante.createElement("tipo");
                    tagTipo.appendChild(xmlComprobante.createTextNode(mensaje.getTipo()));
                    tagMensaje.appendChild(tagTipo);

                    tagMensajes.appendChild(tagMensaje);
                    respuestaInterna.addMensaje(new MensajeGenerado(mensaje.getIdentificador(), mensaje.getMensaje(), mensaje.getInformacionAdicional(), mensaje.getTipo()));
                }
                tagAutorizacion.appendChild(tagMensajes);
            }
            respuestaInterna.setComprobante(xmlComprobante);
        } catch (Exception e) {
            return CrearRespuestaException("ERROR CREANDO EL XML DE RESPUESTA DE AUTORIZACION");
        }

        return respuestaInterna;
    }

    private RespuestaInterna CrearRespuestaAutorizacion(RespuestaComprobante respuestaAutorizacion) {
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        try {
            List<Autorizacion> autorizaciones = null;
            Autorizacion autorizacion = null;
            try {
                autorizaciones = respuestaAutorizacion.getAutorizaciones().getAutorizacion();
                autorizacion = autorizaciones.get(0);
            } catch (Exception e) {
                respuestaInterna.setEstadoComprobante("PROCESANDOSE");
                MensajeGenerado mensaje = new MensajeGenerado();
                mensaje.setTipo("PROCESANDOSE");
                mensaje.setIdentificador("3000");
                mensaje.setMensaje("EL COMPROBANTE SE ENCUENTRA SIENDO PROCESADO POR EL SRI. ENVIAR NUEVAMENTE EN UNOS 2 MIN. RESPUESTA DEL SISTEMA");
                respuestaInterna.getMensajes().add(mensaje);
                return respuestaInterna;
            }

            if (autorizacion.getEstado().equals("EN PROCESO")) {
                respuestaInterna.setEstadoComprobante("PROCESANDOSE");
                MensajeGenerado mensaje = new MensajeGenerado();
                mensaje.setTipo("PROCESANDOSE");
                mensaje.setIdentificador("3000");
                mensaje.setMensaje("EL COMPROBANTE SE ENCUENTRA SIENDO PROCESADO POR EL SRI. ENVIAR NUEVAMENTE EN UNOS 2 MIN. RESPUESTA DEL SISTEMA");
                respuestaInterna.getMensajes().add(mensaje);
                return respuestaInterna;
            }
            if (!autorizacion.getEstado().equals("AUTORIZADO")) {
                int cantidad = autorizaciones.size();
                for (int i = 0; i < cantidad; i++) {
                    if (autorizaciones.get(i).getEstado().equals("AUTORIZADO")) {
                        autorizacion = autorizaciones.get(i);
                        break;
                    }
                }
            }

            respuestaInterna.setEstadoComprobante(autorizacion.getEstado());
            if (autorizacion.getNumeroAutorizacion() != null && !autorizacion.getNumeroAutorizacion().equals("")) {
                respuestaInterna.setNumeroAutorizacion(autorizacion.getNumeroAutorizacion());
            }
            // tener en cuanta el formato de la fecha

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
            GregorianCalendar gc = autorizacion.getFechaAutorizacion().toGregorianCalendar();
            String formatted_string = sdf.format(gc.getTime());
            respuestaInterna.setFechaAutorizacion(formatted_string);
            respuestaInterna.setDocAutorizado(autorizacion.getComprobante());
            List<SRI.Autorizacion.Mensaje> mensajes = autorizacion.getMensajes().getMensaje();
            if (mensajes != null) {

                for (Iterator<SRI.Autorizacion.Mensaje> it = mensajes.iterator(); it.hasNext();) {
                    SRI.Autorizacion.Mensaje mensaje = it.next();
                    respuestaInterna.addMensaje(new MensajeGenerado(mensaje.getIdentificador(), mensaje.getMensaje(), mensaje.getInformacionAdicional(), mensaje.getTipo()));
                }
            }
            respuestaInterna.setComprobante(util.convertirStringToXML(autorizacion.getComprobante()));
        } catch (Exception e) {
            return CrearRespuestaException("ERROR CREANDO EL XML DE RESPUESTA DE AUTORIZACION");
        }

        return respuestaInterna;
    }

    private RespuestaInterna CrearRespuestaException(String mensaje) {
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        respuestaInterna.setEstadoComprobante("ERROR");
        respuestaInterna.addMensaje(new MensajeGenerado("1000", mensaje, null, TipoError.EXCEPCION.toString()));

        return respuestaInterna;
    }

    private void EliminarCertificado() {
        TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }
        };
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return true;
            }
        };

        try {
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(hv);
        } catch (Exception e) {
        }
    }

}
