package FirmaElectronica;

import Respuesta.*;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import org.w3c.dom.Document;
import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.issues.PassStoreKS;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
import es.mityc.javasign.pkstore.keystore.KeyTool;

import Util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Random;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;

/**
 *
 * @author Yoelvys
 */
public class FirmaElectronica {

    private String direccionCertificado;
    private String passwordCertificado;
    private String dirCarpetaAutorizados;

    /**
     * @return the direccionCertificado
     */
    public String getDireccionCertificado() {
        return direccionCertificado;
    }

    /**
     * @param direccionCertificado the direccionCertificado to set
     */
    public void setDireccionCertificado(String direccionCertificado) {
        this.direccionCertificado = direccionCertificado;
    }

    /**
     * @return the passwordCertificado
     */
    public String getPasswordCertificado() {
        return passwordCertificado;
    }

    /**
     * @param passwordCertificado the passwordCertificado to set
     */
    public void setPasswordCertificado(String passwordCertificado) {
        this.passwordCertificado = passwordCertificado;
    }

    /**
     * @return the dirCarpetaAutorizados
     */
    public String getDirCarpetaAutorizados() {
        return dirCarpetaAutorizados;
    }

    /**
     * @param dirCarpetaAutorizados the dirCarpetaAutorizados to set
     */
    public void setDirCarpetaAutorizados(String dirCarpetaAutorizados) {
        this.dirCarpetaAutorizados = dirCarpetaAutorizados;
    }

    enum TipoError {

        EXCEPCION
    }

    public RespuestaInterna FirmarComprobante(Document xmlComprobante) {
        Properties propiedades = new Properties();
        Util util = new Util();
        RespuestaInterna respuestaInterna = new RespuestaInterna();
        respuestaInterna.setEstadoComprobante("ERROR");
        IPKStoreManager storeManager = null;
        X509Certificate certificate = null;
        try {
            storeManager = getPKStoreManager();
        } catch (Exception e) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "EL GESTOR DE CLAVES NO SE HA OBTENIDO CORRECTAMENTE", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }
        if (storeManager == null) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "FALLO OBTENIENDO EL LISTADO DE CERTIFICADOS", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }
        try {
            certificate = getFirstCertificate(storeManager);
        } catch (Exception e) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "FALLO OBTENIENDO EL LISTADO DE CERTIFICADOS", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }

        if (certificate == null) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "NO EXISTE NINGUN CERTIFICADO PARA FIRMAR", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }

        PrivateKey privateKey = null;
        try {
            privateKey = storeManager.getPrivateKey(certificate);
        } catch (CertStoreException e) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "ERROR AL ACCEDER AL ALMACEN", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }

        Provider provider = storeManager.getProvider(certificate);
        DataToSign dataToSign = null;
        try {
            dataToSign = createDataToSign(xmlComprobante);
        } catch (Exception e) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "ERROR PROCESANDO EL XML A FIRMAR" +e.getMessage(), null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }

        FirmaXML firma = new FirmaXML();
        Document xmlFirmado = null;
        try {
            Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
            xmlFirmado = (Document) res[0];
        } catch (Exception ex) {
            respuestaInterna.addMensaje(new MensajeGenerado("1000", "ERROR REALIZANDO LA FIRMA DEL COMPROBANTE", null, TipoError.EXCEPCION.toString()));

            return respuestaInterna;
        }

        respuestaInterna.setComprobante(xmlFirmado);
        respuestaInterna.setEstadoComprobante("FIRMADO");

        return respuestaInterna;
    }

    private IPKStoreManager getPKStoreManager() throws KeyStoreException, IOException, NoSuchAlgorithmException, CertificateException {
        KeyStore ks = KeyStore.getInstance("PKCS12");
        FileInputStream fis = new java.io.FileInputStream(getDireccionCertificado());
        ks.load(fis, getPasswordCertificado().toCharArray());
        IPKStoreManager storeManager = new KSStore(ks, new PassStoreKS(getPasswordCertificado()));
        return storeManager;
    }

    private X509Certificate getFirstCertificate(final IPKStoreManager storeManager) throws CertStoreException {

        List<X509Certificate> certs = storeManager.getSignCertificates();
        if ((certs == null) || (certs.isEmpty())) {
            return null;
        }

        return certs.get(0);
    }

    protected DataToSign createDataToSign(Document xmlComprobante) throws TransformerException, TransformerConfigurationException, IOException, ParserConfigurationException, SAXException {

        DataToSign datosAFirmar = new DataToSign();
        datosAFirmar.setXadesFormat(es.mityc.javasign.EnumFormatoFirma.XAdES_BES);
        datosAFirmar.setEsquema(XAdESSchemas.XAdES_132);
        datosAFirmar.setXMLEncoding("UTF-8");
        datosAFirmar.setEnveloped(true);
        datosAFirmar.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "contenido comprobante", null, "text/xml", null));
        datosAFirmar.setParentSignNode("comprobante");
        datosAFirmar.setDocument(getDocument(xmlComprobante));

        return datosAFirmar;
    }

    protected Document getDocument(Document xmlComprobante) throws TransformerConfigurationException, TransformerException, IOException, ParserConfigurationException, SAXException {

        Source source = new DOMSource(xmlComprobante);

        Random rnd = new Random();
        int sufijo = (int) (rnd.nextDouble() * 100.0D);
        String nombreArchivoTemp = "temp" + Integer.toString(sufijo) + ".xml";
        Path dir = new File(this.dirCarpetaAutorizados).toPath().resolve(nombreArchivoTemp);
        File temp = new File(dir.toString());
        Result result = new StreamResult(temp);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty("indent", "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        transformer.transform(source, result);
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        doc = db.parse(temp);

        temp.delete();
        return doc;
    }
}
