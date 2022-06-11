package com.bookstore.userportal.utility;

import com.bookstore.userportal.domain.Order;
import com.bookstore.userportal.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Locale;
import java.util.Properties;


@Component
public class MailConstructor {

    @Autowired
    private TemplateEngine templateEngine;

    private final String from = "bookstore64cs1@gmail.com";
    private final String passwordE = "joqakkvishlikflk";
    private Properties props;

    private void inti() {
        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        props.put("mail.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
    }

    public void sendResetTokenEmail(String contextPath, Locale locale,
                                    String token, User user, String password) throws MessagingException {
        inti();
        String url = contextPath + "/newUser?token=" + token;
        String text = "\nChọn vào đường dẫn trên để xác nhận gmail và thông tin người dùng của bạn.\n" +
                "Mật khẩu hiện tại của bạn là: " + password;
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, passwordE);
                    }
                });
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(from);

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject("E-BookShop - New User");
        message.setContent(url + text, "text/plain");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

        transport.connect();
        Transport.send(message);
        transport.close();
    }

    public void sendOrderConfirmationEmail(User user, Order order, Locale locale) throws MessagingException {
        inti();
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(from, passwordE);
                    }
                });
        Transport transport = session.getTransport();
        InternetAddress addressFrom = new InternetAddress(from);
        Context context = new Context();
        context.setVariable("order", order);
        context.setVariable("user", user);
        context.setVariable("cartItemList", order.getCartItemList());
        String text = templateEngine.process("orderConfirmationEmailTemplate", context);

        MimeMessage message = new MimeMessage(session);
        message.setSender(addressFrom);
        message.setSubject("Order Confirmation - " + order.getId());
        message.setContent(text, "text/html; charset=utf-8");
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));

        transport.connect();
        Transport.send(message);
        transport.close();
    }
}
