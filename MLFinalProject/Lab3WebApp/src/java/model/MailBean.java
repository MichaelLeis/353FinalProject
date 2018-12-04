/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Properties;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import model.SignUpBean;

/**
 *
 * @author meleis
 */
@Named(value = "mailBean")
@Dependent
public class MailBean {

    public static void main(String[] args) {

    }

    public void sendEmail(SignUpBean theModel) {

        // Recipient's email ID needs to be mentioned.
        String to = theModel.getEmail();

        // Sender's email ID needs to be mentioned
        String from = "meleis@ilstu.edu";

        // Assuming you are sending email from this host
        String host = "outlook.office365.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.port", "587");
        // Get the default Session object.
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("email@ilstu.edu", "password");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Sign Up Confirmation");

            // Send the actual HTML message, as big as you like
            message.setContent("<h2>Hi " + theModel.getFirstName() + " " + theModel.getLastName() + "!</h2>" +
                    "<p>You have succesfully signed up with ISU Student Network. <br/><br/>You have the following information: " +
                    "<br/> &nbsp &nbsp &nbsp User ID: " + theModel.getUserID() +
                    "<br/> &nbsp &nbsp &nbsp Password: " + theModel.getPassword() +
                    "<br/> &nbsp &nbsp &nbsp Email: " + theModel.getEmail() +
                    "<br/> &nbsp &nbsp &nbsp Security Question: " + theModel.getSecQuestion() +
                    "<br/> &nbsp &nbsp &nbsp Security Answer: " + theModel.getSecAnswer() +
                    "<br/><br/> Thank you and have a great day!" +
                    "<br/><br/> Regards," +
                    "<br/> The ISU Student Network" +
                    "<br/><br/><br/><img src='https://universitymarketing.illinoisstate.edu/images/logos/PRIMARY_horiz_3c.png'>"
                    ,
                    "text/html");

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}

