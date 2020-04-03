package org.cdlg.group.dao;

import java.util.List;

import org.cdlg.group.entity.Message;


public interface MessageDao {
	//添加一个消息功能
	public void addMessage(Message message);
	//读取消息列表的方法
	
	public List<Message> readMessageByParam(Message message);
	//修改消息的方法(根据消息的发送Id,接收id的类型去更改消息的状态)
	public int updateMessageStatus(Message message);
	//显示未读消息
	public int findMessageCount(Message message);
	//读取互为聊天的消息内容
	public List<Message> readChatMessage(Message message);
	
}
