<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
   let sock = null;
   window.onload = () => {
      let wsUri = "ws://localhost:8080/root/test/websocket"
      sock = new WebSocket(wsUri);
      sock.onmessage = onMessage; //서버로 받는 메세지 등록 함수
      sock.onclose = onClose; //종료시 실행되는 함수 등록
      
      $("#sendBtn").click( () => { 
         sendMessage(); 
         $("#send_msg").val("")
      } )
      $("#send_msg").keydown( key => {
         if( key.keyCode == 13 ){ //엔터 버튼 눌렸을 경우
            sendMessage();
            $("#send_msg").val("")
         }
      })
   }
   function sendMessage(){
      sock.send( $("#send_msg").val() )
   }
   function onMessage( msg ){
      $("#rec_data").append( msg.data + "<br>" )
   }
   function onClose( ){}
</script>

</head>
<body>
   <div id="rec_data" 
         style="height:150px; border : 1px solid"></div>
   <hr>
   <textarea id="send_msg"></textarea>
   <button type="button" id="sendBtn">전송</button>
</body>
</html>

