/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web_Services;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Milind Ghiya
 */
@XmlRootElement
public class IsSuccess {

    private boolean value;
    private String message;

    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
