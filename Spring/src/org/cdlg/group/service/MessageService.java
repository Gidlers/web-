package org.cdlg.group.service;

import java.util.List;

import org.cdlg.group.entity.Message;

public interface MessageService {
	public List<Message> readMessageByParam(Message message);
	//处理验证消息的方法
	public int updateCheckMessage(Integer mineid,Integer friendid,Integer isAgree);
//处理消息
	public List<Message> readChatMessage(Message message);
	//发消息
	public void addChatMessage(Message message);
}
