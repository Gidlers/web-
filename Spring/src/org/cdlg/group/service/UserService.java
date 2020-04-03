package org.cdlg.group.service;


import java.util.List;
import java.util.Map;

import org.cdlg.group.entity.User;

/**
 * �û���ҵ����
 * @author CLT-BACK
 *
 */
public interface UserService {

	/**
	 * ע��ҵ��
	 */
	public boolean  register(User user);
	/**
	 * ��¼ҵ��
	 */
	public boolean login(String userid,String password);
	
	

	/**
	 * ��ȡ������Ϣ
	 */
	public User getUserByUserid(String userid);
	/**
	 * �޸ĸ�����Ϣ
	 */
	public void update(User user);
	/**
	 * ��ҳ��ѯ�����б�
	 */
	public List<User> findFriendUser(Map<String, Object> map);
	/**
	 * ҳ
	 * @param map
	 * @return
	 */
	public int  findFriendPages(Map<String, Object> map);
	/**
	 * ��Ӻ���
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
