/*
package util.utils;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

*/
/**
 * 邮件工具
 *
 * @author wjq
 *//*

public class MailUtil {
    */
/**
     * 发送邮件的方法
     *
     * @param server   smtp服务器地址
     * @param port     smtp服务器端口
     * @param user     发送邮件的账号
     * @param password 发送邮件的密码
     * @param from     显示的发件人
     * @param email    接收的邮件地址
     * @param subject  主题
     * @param responseBody     内容
     * @throws UnsupportedEncodingException
     *//*

    public static void sendEmail(String server, int port, String user, String password, String from, String email, String subject, String responseBody) throws UnsupportedEncodingException {
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", server);
            props.put("mail.smtp.port", String.valueOf(port));
            props.put("mail.smtp.auth", "true");
            Transport transport = null;
            Session session = Session.getDefaultInstance(props, null);
            transport = session.getTransport("smtp");
            transport.connect(server, user, password);
            MimeMessage msg = new MimeMessage(session);
            msg.setSentDate(new Date());
            InternetAddress fromAddress = new InternetAddress(user, from, "UTF-8");
            msg.setFrom(fromAddress);
            InternetAddress[] toAddress = new InternetAddress[1];
            toAddress[0] = new InternetAddress(email);
            msg.setRecipients(Message.RecipientType.TO, toAddress);
            msg.setSubject(subject, "UTF-8");
            //msg.setText(responseBody, "UTF-8");
            msg.setContent(responseBody, "text/html");
            msg.saveChanges();
            transport.sendMessage(msg, msg.getAllRecipients());
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) throws UnsupportedEncodingException {

        sendEmail("smtp.228.com.cn", 25, "wenming@228.com.cn", "Qwe7758521", "闻铭", "vagile@foxmail.com", "邮件测试", "hello");// 收件人
        System.out.println("ok");
    }
}*/
