/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.convert;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mj
 */
@WebService(serviceName = "MilesToKm")
public class MilesToKm {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CalculateMilesToKm")
    public double CalculateMilesToKm(@WebParam(name = "miles") double miles) {
        //TODO write your implementation code here:
        return miles * 1.609;
    }
}
