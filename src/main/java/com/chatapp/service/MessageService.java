package com.chatapp.service;

import java.util.List;

import com.chatapp.entity.Message;
import com.chatapp.payloads.PrivateChatsOfTwoUsers;

public interface MessageService {
	public Message saveMessage(Message message);
//	public PrivateChatsOfTwoUsers getAllSentMessageFromSenderToReceiver(String senderEmail,String receiverEmail);
	public List<Message> getAllMessages(String senderEmail,String receiverEmail);
}
