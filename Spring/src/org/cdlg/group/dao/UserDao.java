package org.cdlg.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cdlg.group.entity.User;

public interface UserDao {
		
	/**
	 * 查询
	 */
	public User FindUser();
	/**
	 * 添加
	 */
	public int AddUser(User user);
	
	/**
	 * 修改
	 * @param user
	 */
	public void UpdateUser(User user);
	/**
	 * 登录
	 */
	public List<User> LoginUser(Map<String, Object> map);
	/**
	 * 根据id获取信息
	 * @param userid
	 * @return
	 */
	public User getByUserid(String userid);
	/**
	 *查询运行添加的好友列表
	 */
	public List<User> FindNoFriendUser(Map<String, Object> map);

	/**
	 * 根据组建查询单个对象
	 * @param uid
	 * @return
	 */
	public	User findUserById(Integer uid);
	/**
	 * 查询符合条件的好友总页数
	 */
	public int  FindFriendcount(Map<String, Object> map);
	/**
	 * 增加好友的方法
	 * 在Mybaties中传递多个参数的方式一共有三种
	 * 下 ： 使用注解标识每一个参数@param
	 */
	public int  addFriend(@Param(value="mineid") int mineid,
			@Param(value="friendid") int friendid);
	/**
	 * 根据用户
	 * @param map
	 * @return
	 */
	public List<User> FindFriendUserById(Integer uid);
}
