/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.model.other;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Enkhbat
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CodeMessageList {
    
    @XmlElement(name = "codeMessage")
    private List<CodeMessage> codeMessages;

    public CodeMessageList() {
    }

    public CodeMessageList(List<CodeMessage> codeMessages) {
        this.codeMessages = codeMessages;
    }
    
    public List<CodeMessage> getCodeMessages() {
        return codeMessages;
    }

    public void setCodeMessages(List<CodeMessage> codeMessages) {
        this.codeMessages = codeMessages;
    }
    
    /**
     * If first time adding error message to list
     * and you know the number of errors use this.
     * For example if you throwing MultipleCommonFault
     * with two error messages:
     * <code>
     * throw new MultipleCommonFault(new CodeMessageList()
     * .add(new CodeMessage(1, "error!"), 2)
     * .add(new CodeMessage(2, "seconderror!")));
     * </code>
     * notice that second add is not this method
     * 
     * @param codeMessage
     * @param size
     * @return 
     */
    public CodeMessageList add(CodeMessage codeMessage, int size) {
        if (codeMessages == null) {
            codeMessages = new ArrayList<>(size);
        }

        codeMessages.add(codeMessage);
        return this;
    }

    public CodeMessageList add(CodeMessage codeMessage) {
        if (codeMessages == null) {
            codeMessages = new ArrayList<>();
        }

        codeMessages.add(codeMessage);
        return this;
    }
    
}
