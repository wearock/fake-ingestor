package com.wearock.fakeingestor.services;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;

@WebService(targetNamespace = "http://auth.sesamecommunications.com", name = "Authentication", serviceName = "Authentication")
public class Authentication {

    @WebMethod(operationName = "authenticateMember")
    @WebResult(name = "authToken")
    public String authenticateMember(@XmlElement(required = true) @WebParam(name = "partnerId") String partnerId,
                                     @XmlElement(required = true) @WebParam(name = "userName") String userName,
                                     @XmlElement(required = true) @WebParam(name = "password") String password) {
        return userName;
    }
}
