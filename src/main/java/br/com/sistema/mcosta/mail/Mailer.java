package br.com.sistema.mcosta.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.sistema.mcosta.util.Util;

public class Mailer {  
  
    public void enviar(Mensagem mensagem) {  
        
    	Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.connectiontimeout", 10000);
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", true);
          
        Session session = Session.getDefaultInstance(props,  new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("pedrojr9119@gmail.com", "senha");
            }
        });
          
        session.setDebug(true);
          
        InternetAddress enderecoOrigem = Util.toAddress(mensagem.getRemetente());
        InternetAddress[] enderecoDestino = Util.toArrayAddress(mensagem.getDestinatarios());
          
        Message message = new MimeMessage(session);
        try {
        	message.setFrom(enderecoOrigem);
        	message.setRecipients(Message.RecipientType.TO, enderecoDestino);        
        	message.setSubject(mensagem.getAssunto());
        	message.setContent(mensagem.getCorpo(), "text/html; charset=\"iso-8859-1\"");
        	Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Email enviado com sucesso!");
    }
    
}