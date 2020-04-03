package org.cdlg.group.service;

import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.Reply;
import org.cdlg.group.entity.Space;

public interface SpaceService {
/**
 * 增加个人空间
 * @param space
 */
	public void addSpace(Space space);
	
	//public Space findSpaceByUid();
	/**
	 * 页
	 */
	public List<Space> findSpace(Map<String, Object> map);
	/**
	 * 页
	 */
	public List<Space> findfriendSpace(Map<String, Object> map);
	/**
	 * 查询动态页数
	 */
	public int spacePages(Map<String, Object> map) ;
	/**
	 * 朋友页数
	 */
	public int spacefriendPages(Map<String, Object> map);
	/**
	 * 增加回复
	 * @param reply
	 */
	public void addReply(Reply reply);
	/**
	 * 查询回复条数
	 * @param uid
	 * @return
	 */
	public int findReplyCount(Integer id);
	/**
	 * 修改
	 * @param count
	 * @param sid
	 */
	public void updateSpaceCount(Integer count);
}
