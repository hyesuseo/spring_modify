package com.care.root.service;

import java.util.Random;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailService {
	@Autowired JavaMailSender mailSender; //메일을 전송하는 객체를 하나 얻어온다
	public void sendMail(String to, String subject, String body) {
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body);
			
			mailSender.send( msg );
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	public void sendMail02(String to, String subject, String body) {
		MimeMessage msg = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper helper = new MimeMessageHelper(msg, true, "UTF-8");
			helper.setSubject(subject);
			helper.setTo(to);
			helper.setText(body, true); //html형식으로 보내려고 하면, 뒤에 true를 넣어주면 된다.
			
			mailSender.send( msg );
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void auth(String email, String contextPath, HttpSession session) {
		//session.invalidate(); //이미 만들어진 세션이 있다면 종료하고 진행!
		
		String userName = "hyesu";
		String userKey = rand(); //"유저키"; //서버에서 만든 유저키 -> 사용자에게 보내준 것
//		session.setAttribute("userName", userName); //서버에서 가지고 있는 것
		session.setAttribute("userKey", userKey); //이 키를 사용자에게 보내준다
		
		String body = "<h3>인증하기</h3>";
		body += "<a href='http://localhost:8080"+ contextPath + 
				"/auth_check?username="+userName+"&userkey="+userKey+"'>";
		body += "인증하기</a>"; //이 때 값을 하나 보내줌: 서버가 가진 값과 사용자가 가진 값이 일치하는지 아닌지 여부를 따진다
		sendMail02(email, "인증", body);
	}
	   private String rand() { //랜덤한 값을 만들어주는 메소드
		      Random ran = new Random();
		      String str="";
		      int num;
		      while(str.length() != 20) {
		         num = ran.nextInt(75)+48;//0~74 + 48 (숫자,소문자, 대문자)
		         if((num>=48 && num<=57)||(num>=65 && num<=90)||(num>=97 && num<=122)) {
		            str+=(char)num;
		         }else {
		            continue;
		         }
		      }
		      return str;
		   }

}
