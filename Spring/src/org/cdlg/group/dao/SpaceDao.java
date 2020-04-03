package org.cdlg.group.dao;

import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.Space;

public interface SpaceDao {
/**
 * 增加
 */
	public void addSpace(Space space);



	//public Space findSpaceByUid();
	/**
	 * 查询自己的动态
	 */
	public List<Space> findSpace(Map<String, Object> map);
	/**
	 * 查询朋友的动态
	 */
	public List<Space> findfriendSpace(Map<String, Object> map);
	/**
	 * 页数
	 */
	public int spaceCount(Map<String, Object> map);
	/**
	 * 朋友页数
	 */
	public int spacefriendCount(Map<String, Object> map);
	/**
	 * 修改回复条数
	 */
	public void updateSpaceCount(Integer count);
}
