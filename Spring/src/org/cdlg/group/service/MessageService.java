package org.cdlg.group.service;

import java.util.List;

import org.cdlg.group.entity.Message;

public interface MessageService {
	public List<Message> readMessageByParam(Message message);
	//������֤��Ϣ�ķ���
	public int updateCheckMessage(Integer mineid,Integer friendid,Integer isAgree);
//������Ϣ
	public List<Message> readChatMessage(Message message);
	//����Ϣ
	public void addChatMessage(Message message);
}
