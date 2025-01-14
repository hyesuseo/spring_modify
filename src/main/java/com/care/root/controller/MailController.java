package com.care.root.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.care.root.service.MailService;

@Controller
public class MailController {
	@Autowired MailService ms;
	
	@GetMapping("sendmail")
	public void sendMail(HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		ms.sendMail("hyesuc_seo@naver.com", "test제목", "mail보내기 테스트입니다");
		out.print("메일전송완료");
		//사용자가 샌드메일을 요청하면 사용자에게 샌드메일메세지를 보여준다
	}
	
	@GetMapping("sendmail02")
	public void sendMail02(HttpServletResponse res, HttpServletRequest req) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		//내용을 html 형식으로 보내기, 아래와 같이 해도 되고
//		String str = "<h1>안녕</h1>";
//		str += "<b>새로운 문자를 작성</b>"; //기존에 있던 공간을 삭제하고 새로운 공간을 만들어서 다시 넣는다 
		
		StringBuffer sb = new StringBuffer(); 
		//String보다 StringBuffer가 처리속도가 조금 빠르다
		//하나의 공간을 만들고 그 공간에 계속 이어써준다
		sb.append("<h1>안녕</h1>");
		sb.append("<b>새로운 문자를 작성</b>");
		
		String str = sb.toString();
		
		ms.sendMail02("hyesuc_seo@naver.com", "test제목", str);
		out.print("메일전송완료02");
		//사용자가 샌드메일을 요청하면 사용자에게 샌드메일메세지를 보여준다
	}
	@GetMapping("auth_form")
	public String authForm(HttpSession session) {
		//session.invalidate();
		return "auth_form";
	}
	
	@PostMapping("auth")
	public String auth(String email, HttpServletRequest req,
			HttpSession session) {
		//req를 사용하는 이유는 ContextPath를 사용하기 위함임
		ms.auth(email, req.getContextPath(), session);
		return "redirect:http://www.naver.com"; //이메일 확인하라고 리다이렉트 시켜줌
	}
	
	@GetMapping("auth_check")
	public String authCheck(String username, String userkey, HttpSession session) {
		String serverKey = (String)session.getAttribute("userKey"); //서버키를 가져와서 확인한다.
		System.out.println("서버키"+ serverKey);
		System.out.println("유저키"+ userkey);
		if(serverKey !=null && serverKey.equals(userkey)) {
			session.setAttribute("username", username); //서버키와 사용자가 가져온 키가 일치하면, 세션을 만들어준다
			System.out.println("실행");
		}
		
		
		System.out.println("--- 인증 확인 ---");
		
		return "redirect:auth_form";
	}

}
