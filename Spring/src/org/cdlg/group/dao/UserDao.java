package org.cdlg.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.cdlg.group.entity.User;

public interface UserDao {
		
	/**
	 * ��ѯ
	 */
	public User FindUser();
	/**
	 * ���
	 */
	public int AddUser(User user);
	
	/**
	 * �޸�
	 * @param user
	 */
	public void UpdateUser(User user);
	/**
	 * ��¼
	 */
	public List<User> LoginUser(Map<String, Object> map);
	/**
	 * ����id��ȡ��Ϣ
	 * @param userid
	 * @return
	 */
	public User getByUserid(String userid);
	/**
	 *��ѯ������ӵĺ����б�
	 */
	public List<User> FindNoFriendUser(Map<String, Object> map);

	/**
	 * �����齨��ѯ��������
	 * @param uid
	 * @return
	 */
	public	User findUserById(Integer uid);
	/**
	 * ��ѯ���������ĺ�����ҳ��
	 */
	public int  FindFriendcount(Map<String, Object> map);
	/**
	 * ���Ӻ��ѵķ���
	 * ��Mybaties�д��ݶ�������ķ�ʽһ��������
	 * �� �� ʹ��ע���ʶÿһ������@param
	 */
	public int  addFriend(@Param(value="mineid") int mineid,
			@Param(value="friendid") int friendid);
	/**
	 * �����û�
	 * @param map
	 * @return
	 */
	public List<User> FindFriendUserById(Integer uid);
}
