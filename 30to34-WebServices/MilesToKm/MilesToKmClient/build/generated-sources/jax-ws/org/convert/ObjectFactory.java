
package org.convert;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.convert package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CalculateMilesToKm_QNAME = new QName("http://convert.org/", "CalculateMilesToKm");
    private final static QName _CalculateMilesToKmResponse_QNAME = new QName("http://convert.org/", "CalculateMilesToKmResponse");
    private final static QName _Hello_QNAME = new QName("http://convert.org/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://convert.org/", "helloResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.convert
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateMilesToKm }
     * 
     */
    public CalculateMilesToKm createCalculateMilesToKm() {
        return new CalculateMilesToKm();
    }

    /**
     * Create an instance of {@link CalculateMilesToKmResponse }
     * 
     */
    public CalculateMilesToKmResponse createCalculateMilesToKmResponse() {
        return new CalculateMilesToKmResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateMilesToKm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://convert.org/", name = "CalculateMilesToKm")
    public JAXBElement<CalculateMilesToKm> createCalculateMilesToKm(CalculateMilesToKm value) {
        return new JAXBElement<CalculateMilesToKm>(_CalculateMilesToKm_QNAME, CalculateMilesToKm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateMilesToKmResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://convert.org/", name = "CalculateMilesToKmResponse")
    public JAXBElement<CalculateMilesToKmResponse> createCalculateMilesToKmResponse(CalculateMilesToKmResponse value) {
        return new JAXBElement<CalculateMilesToKmResponse>(_CalculateMilesToKmResponse_QNAME, CalculateMilesToKmResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://convert.org/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://convert.org/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

}
