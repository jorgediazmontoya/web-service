
package SRI.Autorizacion;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.3
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AutorizacionComprobantesOfflineService", targetNamespace = "http://ec.gob.sri.ws.autorizacion", wsdlLocation = "https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl")
public class AutorizacionComprobantesOfflineService
    extends Service
{

    private final static URL AUTORIZACIONCOMPROBANTESOFFLINESERVICE_WSDL_LOCATION;
    private final static WebServiceException AUTORIZACIONCOMPROBANTESOFFLINESERVICE_EXCEPTION;
    private final static QName AUTORIZACIONCOMPROBANTESOFFLINESERVICE_QNAME = new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesOfflineService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("https://celcer.sri.gob.ec/comprobantes-electronicos-ws/AutorizacionComprobantesOffline?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AUTORIZACIONCOMPROBANTESOFFLINESERVICE_WSDL_LOCATION = url;
        AUTORIZACIONCOMPROBANTESOFFLINESERVICE_EXCEPTION = e;
    }

    public AutorizacionComprobantesOfflineService() {
        super(__getWsdlLocation(), AUTORIZACIONCOMPROBANTESOFFLINESERVICE_QNAME);
    }

    public AutorizacionComprobantesOfflineService(WebServiceFeature... features) {
        super(__getWsdlLocation(), AUTORIZACIONCOMPROBANTESOFFLINESERVICE_QNAME, features);
    }

    public AutorizacionComprobantesOfflineService(URL wsdlLocation) {
        super(wsdlLocation, AUTORIZACIONCOMPROBANTESOFFLINESERVICE_QNAME);
    }

    public AutorizacionComprobantesOfflineService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AUTORIZACIONCOMPROBANTESOFFLINESERVICE_QNAME, features);
    }

    public AutorizacionComprobantesOfflineService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AutorizacionComprobantesOfflineService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AutorizacionComprobantesOffline
     */
    @WebEndpoint(name = "AutorizacionComprobantesOfflinePort")
    public AutorizacionComprobantesOffline getAutorizacionComprobantesOfflinePort() {
        return super.getPort(new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesOfflinePort"), AutorizacionComprobantesOffline.class);
    }

    /**
     * 
     * @param features
     *     A list of {&#064;link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the &lt;code&gt;features&lt;/code&gt; parameter will have their default values.
     * @return
     *     returns AutorizacionComprobantesOffline
     */
    @WebEndpoint(name = "AutorizacionComprobantesOfflinePort")
    public AutorizacionComprobantesOffline getAutorizacionComprobantesOfflinePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ec.gob.sri.ws.autorizacion", "AutorizacionComprobantesOfflinePort"), AutorizacionComprobantesOffline.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AUTORIZACIONCOMPROBANTESOFFLINESERVICE_EXCEPTION!= null) {
            throw AUTORIZACIONCOMPROBANTESOFFLINESERVICE_EXCEPTION;
        }
        return AUTORIZACIONCOMPROBANTESOFFLINESERVICE_WSDL_LOCATION;
    }

}
