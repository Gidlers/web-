package org.cdlg.group.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.cdlg.group.dao.MessageDao;
import org.cdlg.group.dao.UserDao;
import org.cdlg.group.entity.Message;
import org.cdlg.group.entity.User;
import org.cdlg.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service(value="userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userdao;
	@Autowired
	private MessageDao messageDao;
	
	public UserDao getUserdao() {
		return userdao;
	}
	public void setUserdao(UserDao userdao) {
		this.userdao = userdao;
	}
	@Transactional
	@Override	
	public boolean register(User user) {
		// idָ����insert�ķ���ֵ
		int id = this.userdao.AddUser(user);
		if(id>0){
			return true;
		}
		else{
			return false;
		}

	}
	
	@Override
	public boolean login(String userid, String password) {
		// TODO �Զ����ɵķ������
		boolean isSuccess=false;
		Map<String, Object> map=new HashMap<>();
		map.put("username", userid);
		map.put("password", password);
		//��Ҫ�����ݿ�鿴�û����������Ƿ����
		List<User> lst  = this.userdao.LoginUser(map);
		 
//		�����û�user�Ƿ�Ϊ�����ж����
		if(lst!=null&&lst.size()>0){
			isSuccess=true;
		}
		return isSuccess;

	}

	@Override
	public User getUserByUserid(String userid) {
		// TODO �Զ����ɵķ������
		User user=this.userdao.getByUserid(userid);
		return user;
	}
	
	@Transactional
	@Override
	public void update(User user) {
		
		// TODO �Զ����ɵķ������
		this.userdao.UpdateUser(user);
		
		
	}

	@Override
	public List<User> findFriendUser(Map<String, Object> map) {
		// TODO �Զ����ɵķ������
		
		return this.userdao.FindNoFriendUser(map);
	}
	@Override
	public int findFriendPages(Map<String, Object> map) {
		// TODO �Զ����ɵķ������
		int rocount = this.userdao.FindFriendcount(map);
		if(rocount % 3 == 0)
			return rocount /3;
		return rocount /3+1;
		
	}
	
	/**
	 * ��Ӻ�����Ϣ
	 * @param mineid
	 * @param frinedid
	 * @return
	 */
	@Transactional
	@Override
	public int AddFriend(int mineid, int friendid) {
		// TODO �Զ����ɵķ������
		User frinedUser = userdao.findUserById(friendid);
		if(frinedUser.getChecktype() == 1){
			userdao.addFriend(mineid, friendid);
			userdao.addFriend(friendid, mineid);
		}else if(frinedUser.getChecktype() == 2){
			User mineUser = userdao.findUserById(mineid);
			Message message = new Message();
			message.setFromid(mineid);
			message.setToid(friendid);
			message.setType(1);
			
			
			message.setContent(mineUser.getNickname()+",�Ӹ�����");
			messageDao.addMessage(message);
			return 0;
		}
		return 1;
	}
	@Override
	public List<User> FindFriendUserById(Integer uid) {
		// TODO �Զ����ɵķ������
			List<User> friendList = userdao.FindFriendUserById(uid);
			for(User user : friendList){
				Message message = new Message();
				message.setToid(uid);
				message.setFromid(user.getId());
				int count = messageDao.findMessageCount(message);
				//
				user.setMessageCount(count);		
			}			
			return friendList;
	}
	@Override
	public User findUserById(Integer uid) {
		// TODO �Զ����ɵķ������
		return userdao.findUserById(uid);
	}

}
