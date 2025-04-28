/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.calculator;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author mj
 */
@WebService(serviceName = "Calculator")
public class Calculator {

    /**
     * Web service operation
     */
    @WebMethod(operationName = "addition")
    public double addition(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1+num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "subtraction")
    public double subtraction(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1-num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "multiplication")
    public double multiplication(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
        //TODO write your implementation code here:
        return num1*num2;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "division")
public double division(@WebParam(name = "num1") double num1, @WebParam(name = "num2") double num2) {
    // Check if second number is zero
    if (num2 == 0) {
        throw new IllegalArgumentException("Cannot divide by zero.");
    }

    // Check if first number is greater than second number
    if (num1 > num2) {
        throw new IllegalArgumentException("First number cannot be greater than second number.");
    }

    // If checks pass, perform division
    return num1 / num2;
}


    /**
     * This is a sample web service operation
     */
    
}
