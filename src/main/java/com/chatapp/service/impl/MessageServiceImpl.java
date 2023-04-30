package com.chatapp.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.chatapp.entity.Message;
import com.chatapp.payloads.PrivateChatsOfTwoUsers;
import com.chatapp.repository.MessageRepo;
import com.chatapp.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepo messageRepo;
	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public Message saveMessage(Message message) {
		Message savedMsg = this.messageRepo.save(message);
		return savedMsg;
	}

	/*@Override
	public PrivateChatsOfTwoUsers getAllSentMessageFromSenderToReceiver(String senderEmail, String receiverEmail) {
		Query query = new Query();
		Criteria criteria1 = Criteria.where("sender_id").is(senderEmail).and("reciver_id").is(receiverEmail);
		Criteria criteria2 = Criteria.where("sender_id").is(receiverEmail).and("reciver_id").is(senderEmail);

		query.addCriteria(new Criteria().orOperator(criteria1, criteria2))
				.with(Sort.by(Sort.Direction.ASC, "created_date"));
		System.out.println(query.toString());

		List<Message> messageList = mongoTemplate.find(query, Message.class);

		List<Message> senderToReceiverMessages = messageList.stream().filter(m -> m.getSenderId().equals(senderEmail))
				.collect(Collectors.toList());

		List<Message> receiverToSenderMessages = messageList.stream().filter(m -> m.getSenderId().equals(receiverEmail))
				.collect(Collectors.toList());

		PrivateChatsOfTwoUsers allMsgs = new PrivateChatsOfTwoUsers();
		allMsgs.setSenderToReciverMsgs(senderToReceiverMessages);
		allMsgs.setRecivedFromSenderMsgs(receiverToSenderMessages);
		return allMsgs;
	}*/
	
	@Override
	public List<Message> getAllMessages(String senderEmail, String receiverEmail) {
		Query query = new Query();
		Criteria criteria1 = Criteria.where("sender_id").is(senderEmail).and("reciver_id").is(receiverEmail);
		Criteria criteria2 = Criteria.where("sender_id").is(receiverEmail).and("reciver_id").is(senderEmail);

		query.addCriteria(new Criteria().orOperator(criteria1, criteria2))
				.with(Sort.by(Sort.Direction.ASC, "created_date"));
		System.out.println(query.toString());

		List<Message> messageList = mongoTemplate.find(query, Message.class);

		return messageList;
	}
}