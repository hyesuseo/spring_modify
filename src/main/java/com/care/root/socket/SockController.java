package com.care.root.socket;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SockController {

	@GetMapping("index")
	public String index() {
		return "sock/sock_index";
	}
	int i =0; //세션 번호를 저장하기 위한 변수 선언
	@GetMapping("chat")
	public String chat(HttpSession session) { //사용자가 채팅을 요청하는 경우 세션 만들기
		i++; //채팅을 요청할때마다 번호 값이 달라진다.
		session.setAttribute("username", "홍길동-"+i);
		return "sock/chat";
	}
}
