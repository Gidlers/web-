package org.cdlg.group.service;


import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.User;

/**
 * 用户的业务类
 * @author CLT-BACK
 *
 */
public interface UserService {

	/**
	 * 注册业务
	 */
	public boolean  register(User user);
	/**
	 * 登录业务
	 */
	public boolean login(String userid,String password);
	
	

	/**
	 * 获取个人信息
	 */
	public User getUserByUserid(String userid);
	/**
	 * 修改个人信息
	 */
	public void update(User user);
	/**
	 * 分页查询好友列表
	 */
	public List<User> findFriendUser(Map<String, Object> map);
	/**
	 * 页
	 * @param map
	 * @return
	 */
	public int  findFriendPages(Map<String, Object> map);
	/**
	 * 添加好友
	 */
	public int  AddFriend(int mineid,int friendid);
	/**
	 * 
	 * @param uid
	 * @return
	 */
	public List<User> FindFriendUserById(Integer uid);
/**
 * id
 */
	public User findUserById(Integer uid);
}
