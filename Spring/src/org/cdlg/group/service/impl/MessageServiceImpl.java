package org.cdlg.group.service.impl;

import java.util.List;

import org.cdlg.group.dao.MessageDao;
import org.cdlg.group.dao.UserDao;
import org.cdlg.group.entity.Message;
import org.cdlg.group.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service(value="messageServiceImpl")
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageDao messageDao;
	@Autowired
	private UserDao userDao;
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public MessageDao getMessageDao() {
		return messageDao;
	}

	public void setMessageDao(MessageDao messageDao) {
		this.messageDao = messageDao;
	}

	@Override
	public List<Message> readMessageByParam(Message message) {
		// TODO �Զ����ɵķ������
		return messageDao.readMessageByParam(message);
	}

	@Transactional
	@Override
	public int updateCheckMessage(Integer mineid, Integer friendid, Integer isAgree) {
		// TODO �Զ����ɵķ������
		Message message = new Message();
		message.setFromid(friendid);
		message.setToid(mineid);
		message.setType(1);//��֤
		messageDao.updateMessageStatus(message);
		if(isAgree == 1){
			userDao.addFriend(mineid, friendid);
			userDao.addFriend(friendid, mineid);
		}
		return 1;
	}

	@Override
	public List<Message> readChatMessage(Message message) {
		// ������Ϣ��״̬
		messageDao.updateMessageStatus(message);
		return messageDao.readChatMessage(message);
	}
/**
 * �����Ϣ
 */
	@Override
	public void addChatMessage(Message message) {
		// TODO �Զ����ɵķ������
		//�������
		messageDao.addMessage(message);
	}

}
