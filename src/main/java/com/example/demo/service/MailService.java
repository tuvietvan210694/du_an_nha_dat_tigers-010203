//package com.example.demo.service;
//
//import java.nio.charset.StandardCharsets;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import com.example.demo.entity.Request;
//import com.sendgrid.Content;
//import com.sendgrid.Email;
//import com.sendgrid.Mail;
//import com.sendgrid.Method;
//import com.sendgrid.SendGrid;
//
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import com.sendgrid.*;
//import java.io.IOException;
//
//@Service
//public class MailService {
//
//	
//	Email from = new Email("usermanagementsummer2018@gmail.com");
//    String subject = "Sending with SendGrid is Fun";
//    Email to = new Email("tuvietvancntt@gmail.com");
//    Content content = new Content("text/plain", "and easy to do anywhere, even with Java");
//    Mail mail = new Mail(from, subject, to, content);
//
//    SendGrid sg = new SendGrid(System.getenv("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"));
//    //com.sendgrid.Request request = new com.sendgrid.Request();
////    Response response = sg.se
//    
//    
////    
////    try {
////      request.setMethod(Method.POST);
////      request.setEndpoint("mail/send");
////      request.setBody(mail.build());
////      Response response = sg.api(request);
////      System.out.println(response.getStatusCode());
////      System.out.println(response.getBody());
////      System.out.println(response.getHeaders());
////    } catch (IOException ex) {
////      throw ex;
////    }
//    
//}
//
//	
//	
//	
//	
//	
////	@Autowired
////	JavaMailSender mailSender;
////
////	@Autowired
////	private Configuration freemarkerConfigg;
////	public void sendMail(String title, String href, String userMail, String registCode, Date expireDate)
////			throws MessagingException {
////		MimeMessage message = mailSender.createMimeMessage();
////
////		MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
////				StandardCharsets.UTF_8.name());
////
////		try {
////			freemarkerConfigg.setClassForTemplateLoading(this.getClass(), "/templates");
////			Template t = freemarkerConfigg.getTemplate("email-template.ftl");
////			Map<String, Object> model = new HashMap<String, Object>();
////			model.put("expireDate", expireDate.toString());
////			model.put("href", href);
////			model.put("token", registCode);
////			String mailContent = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
////			helper.setSubject(title);
////			helper.setText(mailContent, true);
////			helper.setTo(userMail);
////			mailSender.send(message);
////		} catch (Exception e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////	}
//	
//
