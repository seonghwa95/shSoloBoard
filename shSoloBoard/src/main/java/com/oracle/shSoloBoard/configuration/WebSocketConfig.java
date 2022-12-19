package com.oracle.shSoloBoard.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.oracle.shSoloBoard.handler.ChatHandler;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSocket	// 웹소켓 활성화
public class WebSocketConfig implements WebSocketConfigurer {
	
	private final ChatHandler chatHandler;

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		// 도메인이 다른 서버에서도 접속이 가능하도록 한다.
		
		registry.addHandler(chatHandler, "ws/chat").setAllowedOrigins("*");
	}
}
