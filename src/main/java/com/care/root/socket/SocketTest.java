package com.care.root.socket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketTest extends TextWebSocketHandler {
	List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();
	
	@Override //연결 후 실행되는 메소드
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessionList.add(session);
		// TODO Auto-generated method stub
		System.out.println(session.getId() +"부터 연결됨"); //여기서의 session은 웹소켓에서 만든 세션임. 우리가 쓰는 세션 아님
	}
	@Override //메세지 받는 메소드
	protected void handleTextMessage(WebSocketSession session, 
										TextMessage message) throws Exception {
		String msg = session.getId()+"로부터";
		msg += message.getPayload()+"내용 받음"; //사용자가 보낸 메시지
		System.out.println(msg);
		Map<String , Object> map = session.getAttributes(); //현재 접속한 세션에서 만든 것이 입력된다.
		System.out.println(map);
		
		TextMessage m = new TextMessage(map.get("username")+"님:"+message.getPayload()); 
		//키를 이용하면, 해당 사용자명에 해당하는 이름을 가져올 수 있다
		//session.sendMessage(message);
		for(WebSocketSession s : sessionList) {
			s.sendMessage( m ); //TextMessage 형식으로 지정된 message임
			//새롭게 만들어진 메세지를 전송해주려고 한다.
			
		}
		
		
	}
	@Override //연결 종료시 실행
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		System.out.println(session.getId()+"종료");
		
	}

}
