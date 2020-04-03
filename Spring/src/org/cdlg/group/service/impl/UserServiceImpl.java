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
		// id指的是insert的返回值
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
		// TODO 自动生成的方法存根
		boolean isSuccess=false;
		Map<String, Object> map=new HashMap<>();
		map.put("username", userid);
		map.put("password", password);
		//需要到数据库查看用户名和密码是否存在
		List<User> lst  = this.userdao.LoginUser(map);
		 
//		根据用户user是否为空来判断真假
		if(lst!=null&&lst.size()>0){
			isSuccess=true;
		}
		return isSuccess;

	}

	@Override
	public User getUserByUserid(String userid) {
		// TODO 自动生成的方法存根
		User user=this.userdao.getByUserid(userid);
		return user;
	}
	
	@Transactional
	@Override
	public void update(User user) {
		
		// TODO 自动生成的方法存根
		this.userdao.UpdateUser(user);
		
		
	}

	@Override
	public List<User> findFriendUser(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		
		return this.userdao.FindNoFriendUser(map);
	}
	@Override
	public int findFriendPages(Map<String, Object> map) {
		// TODO 自动生成的方法存根
		int rocount = this.userdao.FindFriendcount(map);
		if(rocount % 3 == 0)
			return rocount /3;
		return rocount /3+1;
		
	}
	
	/**
	 * 添加好友信息
	 * @param mineid
	 * @param frinedid
	 * @return
	 */
	@Transactional
	@Override
	public int AddFriend(int mineid, int friendid) {
		// TODO 自动生成的方法存根
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
			
			
			message.setContent(mineUser.getNickname()+",加个好友");
			messageDao.addMessage(message);
			return 0;
		}
		return 1;
	}
	@Override
	public List<User> FindFriendUserById(Integer uid) {
		// TODO 自动生成的方法存根
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
		// TODO 自动生成的方法存根
		return userdao.findUserById(uid);
	}

}
