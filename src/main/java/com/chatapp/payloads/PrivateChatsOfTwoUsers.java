package com.chatapp.payloads;

import java.util.List;

import com.chatapp.entity.Message;

public class PrivateChatsOfTwoUsers {
	List<Message> senderToReciverMsgs;
	List<Message> recivedFromSenderMsgs;
	public List<Message> getSenderToReciverMsgs() {
		return senderToReciverMsgs;
	}
	public void setSenderToReciverMsgs(List<Message> senderToReciverMsgs) {
		this.senderToReciverMsgs = senderToReciverMsgs;
	}
	public List<Message> getRecivedFromSenderMsgs() {
		return recivedFromSenderMsgs;
	}
	public void setRecivedFromSenderMsgs(List<Message> recivedFromSenderMsgs) {
		this.recivedFromSenderMsgs = recivedFromSenderMsgs;
	}
}
