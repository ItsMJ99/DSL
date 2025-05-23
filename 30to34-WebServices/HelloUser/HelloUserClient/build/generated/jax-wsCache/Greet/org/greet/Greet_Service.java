
package org.greet;

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
 * JAX-WS RI 2.2.11-b150120.1832
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "Greet", targetNamespace = "http://greet.org/", wsdlLocation = "http://localhost:8080/HelloUserServer/Greet?wsdl")
public class Greet_Service
    extends Service
{

    private final static URL GREET_WSDL_LOCATION;
    private final static WebServiceException GREET_EXCEPTION;
    private final static QName GREET_QNAME = new QName("http://greet.org/", "Greet");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/HelloUserServer/Greet?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        GREET_WSDL_LOCATION = url;
        GREET_EXCEPTION = e;
    }

    public Greet_Service() {
        super(__getWsdlLocation(), GREET_QNAME);
    }

    public Greet_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), GREET_QNAME, features);
    }

    public Greet_Service(URL wsdlLocation) {
        super(wsdlLocation, GREET_QNAME);
    }

    public Greet_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, GREET_QNAME, features);
    }

    public Greet_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Greet_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Greet
     */
    @WebEndpoint(name = "GreetPort")
    public Greet getGreetPort() {
        return super.getPort(new QName("http://greet.org/", "GreetPort"), Greet.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Greet
     */
    @WebEndpoint(name = "GreetPort")
    public Greet getGreetPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://greet.org/", "GreetPort"), Greet.class, features);
    }

    private static URL __getWsdlLocation() {
        if (GREET_EXCEPTION!= null) {
            throw GREET_EXCEPTION;
        }
        return GREET_WSDL_LOCATION;
    }

}
