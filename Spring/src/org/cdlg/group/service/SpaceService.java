package org.cdlg.group.service;

import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.Reply;
import org.cdlg.group.entity.Space;

public interface SpaceService {
/**
 * ���Ӹ��˿ռ�
 * @param space
 */
	public void addSpace(Space space);
	
	//public Space findSpaceByUid();
	/**
	 * ҳ
	 */
	public List<Space> findSpace(Map<String, Object> map);
	/**
	 * ҳ
	 */
	public List<Space> findfriendSpace(Map<String, Object> map);
	/**
	 * ��ѯ��̬ҳ��
	 */
	public int spacePages(Map<String, Object> map) ;
	/**
	 * ����ҳ��
	 */
	public int spacefriendPages(Map<String, Object> map);
	/**
	 * ���ӻظ�
	 * @param reply
	 */
	public void addReply(Reply reply);
	/**
	 * ��ѯ�ظ�����
	 * @param uid
	 * @return
	 */
	public int findReplyCount(Integer id);
	/**
	 * �޸�
	 * @param count
	 * @param sid
	 */
	public void updateSpaceCount(Integer count);
}
