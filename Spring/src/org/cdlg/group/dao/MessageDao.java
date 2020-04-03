package org.cdlg.group.dao;

import java.util.List;

import org.cdlg.group.entity.Message;


public interface MessageDao {
	//���һ����Ϣ����
	public void addMessage(Message message);
	//��ȡ��Ϣ�б�ķ���
	
	public List<Message> readMessageByParam(Message message);
	//�޸���Ϣ�ķ���(������Ϣ�ķ���Id,����id������ȥ������Ϣ��״̬)
	public int updateMessageStatus(Message message);
	//��ʾδ����Ϣ
	public int findMessageCount(Message message);
	//��ȡ��Ϊ�������Ϣ����
	public List<Message> readChatMessage(Message message);
	
}
