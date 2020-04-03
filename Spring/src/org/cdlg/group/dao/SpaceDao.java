package org.cdlg.group.dao;

import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.Space;

public interface SpaceDao {
/**
 * ����
 */
	public void addSpace(Space space);



	//public Space findSpaceByUid();
	/**
	 * ��ѯ�Լ��Ķ�̬
	 */
	public List<Space> findSpace(Map<String, Object> map);
	/**
	 * ��ѯ���ѵĶ�̬
	 */
	public List<Space> findfriendSpace(Map<String, Object> map);
	/**
	 * ҳ��
	 */
	public int spaceCount(Map<String, Object> map);
	/**
	 * ����ҳ��
	 */
	public int spacefriendCount(Map<String, Object> map);
	/**
	 * �޸Ļظ�����
	 */
	public void updateSpaceCount(Integer count);
}
