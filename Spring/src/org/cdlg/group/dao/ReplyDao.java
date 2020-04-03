package org.cdlg.group.dao;

import java.util.List;

import org.cdlg.group.entity.Reply;

public interface ReplyDao {
	/**
	 * ��ѯ�ռ�ID
	 * 
	 * @param uid
	 * @return
	 */
	public List<Reply> findReplyBySid(Integer sid);

	/**
	 * ��ӻظ�
	 * 
	 * @param reply
	 */
	public void addReply(Reply reply);

	/**
	 * ��ѯ�ظ�����
	 * 
	 * @param uid
	 * @return
	 */
	public int findReplyCount(Integer id);

}
