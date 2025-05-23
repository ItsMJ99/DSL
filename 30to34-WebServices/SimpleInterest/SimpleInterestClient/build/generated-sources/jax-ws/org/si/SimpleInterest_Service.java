
package org.si;

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
@WebServiceClient(name = "SimpleInterest", targetNamespace = "http://si.org/", wsdlLocation = "http://localhost:8080/SimpleInterestServer/SimpleInterest?wsdl")
public class SimpleInterest_Service
    extends Service
{

    private final static URL SIMPLEINTEREST_WSDL_LOCATION;
    private final static WebServiceException SIMPLEINTEREST_EXCEPTION;
    private final static QName SIMPLEINTEREST_QNAME = new QName("http://si.org/", "SimpleInterest");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/SimpleInterestServer/SimpleInterest?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        SIMPLEINTEREST_WSDL_LOCATION = url;
        SIMPLEINTEREST_EXCEPTION = e;
    }

    public SimpleInterest_Service() {
        super(__getWsdlLocation(), SIMPLEINTEREST_QNAME);
    }

    public SimpleInterest_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), SIMPLEINTEREST_QNAME, features);
    }

    public SimpleInterest_Service(URL wsdlLocation) {
        super(wsdlLocation, SIMPLEINTEREST_QNAME);
    }

    public SimpleInterest_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, SIMPLEINTEREST_QNAME, features);
    }

    public SimpleInterest_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SimpleInterest_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns SimpleInterest
     */
    @WebEndpoint(name = "SimpleInterestPort")
    public SimpleInterest getSimpleInterestPort() {
        return super.getPort(new QName("http://si.org/", "SimpleInterestPort"), SimpleInterest.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns SimpleInterest
     */
    @WebEndpoint(name = "SimpleInterestPort")
    public SimpleInterest getSimpleInterestPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://si.org/", "SimpleInterestPort"), SimpleInterest.class, features);
    }

    private static URL __getWsdlLocation() {
        if (SIMPLEINTEREST_EXCEPTION!= null) {
            throw SIMPLEINTEREST_EXCEPTION;
        }
        return SIMPLEINTEREST_WSDL_LOCATION;
    }

}
