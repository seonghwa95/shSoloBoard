package com.oracle.shSoloBoard.handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.log4j.Log4j2;

// 다수의 클라이언트가 보낸 메세지를 처리할 핸들러
@Component
@Log4j2
public class ChatHandler extends TextWebSocketHandler {
	
	private static List<WebSocketSession> list = new ArrayList<>();
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// payLoad는 전송되는 데이터만을 의미
		// 예를들어 JSON에서 상태, 수신자, 송신자, 메서드(GET, POST) 그리고 데이터(메세지) 등의 정보를 전달하는데
		// payLoad는 그 중에서 데이터(메세지)만을 의미한다.
		String payLoad = message.getPayload();
		log.info("payLoad = " + payLoad);
		
		for (WebSocketSession webSocketSession : list) {
			session.sendMessage(message);
		}
	}
	
	// Client가 접속 시 호출되는 메서드
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		list.add(session);
		
		log.info(session + "클라이언트 접속");
	}
	
	// Client가 접속 해제 시 호출되는 메서드
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.info(session + "클라이언트 접속 종료");
		
		list.remove(session);
	}
}
