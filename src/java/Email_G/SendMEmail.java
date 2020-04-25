/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Email_G;

/**
 *
 * @author USER
 */
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMEmail { 

    SendMEmail(String[] rm)
    {
        for(int i=0;i<rm.length;i++)
        {
            SendEmail se =new SendEmail(rm[i],"Your Exam Result","congratulations you pass the competition");
        }
    }
}