package com.virtusa.demo.controllers;

import java.util.ArrayList;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleEmailController {
	
	@Autowired
	public JavaMailSender javaMailSender;
	
	//sending mail
	public void sendNewMail(ArrayList<String[]> list){
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message,true);
			helper.setTo(list.get(0)[0]);
			System.out.println(list.get(0)[0]);
			helper.setSubject("EMart Inventories");
			String text = emailText(list);
			helper.setText(text, true);
			javaMailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//converting all the content to be sent into html format
	public String emailText(ArrayList<String[]> list) {
		String details[] = list.get(0);
		String message="<html><body>"+
					   "<h3>Total Items : "+details[1]+"</h3>"+
					   "<h3>Amount : "+details[2]+"</h3>"+
				       "<table>"+
				       "<tr>"+
				       "<th>Product Id</th>"+
				       "<th>Product Name</th>"+
				       "<th>Vendor Code</th>"+
				       "<th>Count</th>"+
				       "<th>Cost</th>";
				       
	for(int i=1; i<list.size(); i++) {
		
		details = list.get(i); 
		message += "<tr>"+
				   "<td>"+details[0]+"</td>"+
				   "<td>"+details[1]+"</td>"+
				   "<td>"+details[2]+"</td>"+
				   "<td>"+details[5]+"</td>"+
				   "<td>"+details[6]+"</td>"+
				   "<tr>";
				   	
	}
	message += "</table>"+"</br></br>"+"<h6>-- Emart</h6>"+"</body></html>";
	return message;
	}
}