/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model.exception;



import com.model.other.CodeMessage;

import javax.xml.ws.WebFault;

/**
 *
 * @author Enkhbat
 */
@WebFault
public class CommonFault extends Exception {
    
    private CodeMessage faultBean;

    public CommonFault(CodeMessage faultBean) {
        super();
        this.faultBean = faultBean;
    }
    
    public CommonFault(String message, CodeMessage faultInfo) {
        super(message);
        this.faultBean = faultInfo;
    }

    public CommonFault(String message, CodeMessage faultInfo, Throwable cause) {
        super(message, cause);
    }

    public CodeMessage getFaultInfo() {
        return faultBean;
    }
    
}
