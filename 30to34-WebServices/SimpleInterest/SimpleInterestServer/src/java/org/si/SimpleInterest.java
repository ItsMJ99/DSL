/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.si;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mj
 */
@WebService(serviceName = "SimpleInterest")
public class SimpleInterest {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "calculateSimpleInterest")
    public double calculateSimpleInterest(@WebParam(name = "p") double p, @WebParam(name = "r") double r, @WebParam(name = "t") double t) {
        //TODO write your implementation code here:
        return ((p*r*t)/100);
    }

    /**
     * This is a sample web service operation
     */
    
}
