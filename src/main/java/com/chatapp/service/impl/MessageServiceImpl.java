package com.chatapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatapp.entity.Message;
import com.chatapp.repository.MessageRepo;
import com.chatapp.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {
	
	@Autowired
	private MessageRepo messageRepo;
	
	@Override
	public Message saveMessage(Message message) {
		Message savedMsg = this.messageRepo.save(message);
		return savedMsg;
	}

}
