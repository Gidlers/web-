package org.cdlg.group.dao;

import java.util.List;

import org.cdlg.group.entity.Reply;

public interface ReplyDao {
	/**
	 * 查询空间ID
	 * 
	 * @param uid
	 * @return
	 */
	public List<Reply> findReplyBySid(Integer sid);

	/**
	 * 添加回复
	 * 
	 * @param reply
	 */
	public void addReply(Reply reply);

	/**
	 * 查询回复条数
	 * 
	 * @param uid
	 * @return
	 */
	public int findReplyCount(Integer id);

}
