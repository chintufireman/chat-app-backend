package com.chatapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatapp.entity.Message;
import com.chatapp.entity.User;
import com.chatapp.payloads.LoginDetails;
import com.chatapp.payloads.MessageRequest;
import com.chatapp.payloads.PrivateChatsOfTwoUsers;
import com.chatapp.service.MessageService;
import com.chatapp.service.UserService;

@RestController
public class MessageController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@MessageMapping("/chatTo")
	@SendTo("/topic/return-to")
	@CrossOrigin("*")
	public ResponseEntity<MessageRequest> getContent(@RequestBody MessageRequest message) {
		try {
			// Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/javascript");

        return new ResponseEntity<>(message, headers, HttpStatus.OK);

	}

	@MessageMapping("/private/{from}/{to}")
	public MessageRequest getPrivateMessage(@RequestBody MessageRequest message,
			@DestinationVariable("from") String from, @DestinationVariable("to") String to) {
		String chatUrl;
		if (from.compareTo(to) < 0) {
			chatUrl = "/chatroom/" + from + "-" + to;
		} else {
			chatUrl = "/chatroom/" + to + "-" + from;
		}

		Message msg = getMessageEntity(message, from, to);
		Message savedMsg = this.messageService.saveMessage(msg);

		this.messagingTemplate.convertAndSend(chatUrl, message);
		
		
		return message;

	}

	Message getMessageEntity(MessageRequest msg, String from, String to) {

		Message messageEntity = new Message();
		messageEntity.setData(msg.getContent());
		messageEntity.setSenderId(from);
		messageEntity.setReciverId(to);

		return messageEntity;
	}

	@PostMapping("/sent-messages/{senderEmail}/{reciverEmail}")
	public ResponseEntity<List<Message>> sentMessages(@PathVariable String senderEmail,
			@PathVariable String reciverEmail) {
		List<Message> sentMessages = this.messageService.getAllMessages(senderEmail, reciverEmail);
		return new ResponseEntity<List<Message>>(sentMessages, HttpStatus.OK);
	}

}
