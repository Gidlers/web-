package org.cdlg.group.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.aspectj.util.FileUtil;
import org.cdlg.group.entity.Message;
import org.cdlg.group.entity.Reply;
import org.cdlg.group.entity.Space;
import org.cdlg.group.entity.User;
import org.cdlg.group.service.MessageService;
import org.cdlg.group.service.SpaceService;
import org.cdlg.group.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private SpaceService spaceService;
	public SpaceService getSpaceService() {
		return spaceService;
	}

	public void setSpaceService(SpaceService spaceService) {
		this.spaceService = spaceService;
	}

	public MessageService getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * �������Ƿ���String
	 * @return
	 */
	/**
	 * ��¼
	 * @return
	 */
	@RequestMapping("/dologin")
	public String login(String userid,String password,HttpSession session){
		//�жϸ��û��Ƿ����
		boolean s = this.userService.login(userid, password);
		if(s){
			//��½�ɹ�
			session.setAttribute("username", userid);
			
			User user = this.userService.getUserByUserid(userid);
			session.setAttribute("user", user);
			return  "redirect:/doReadCheakMessage";
		}else{
			return  "redirect:/login.html";
		}
		
	}
	
	/**
	 * ע��
	 * @param userid
	 * @param password
	 * @return
	 */
	@RequestMapping("/doRegister")
	public String register(String userid,String password){
		User user = new User();
		user.setUserid(userid);
		user.setPassword(password);
		boolean b = this.userService.register(user);
		if(b){
			return "register_success";
		}else {
			return "redirect:/register.html";
		}

		
	}
	
	/**
	 * �鿴������Ϣ
	 * @param session
	 * @param request
	 * @return
	 */ 
	
	@RequestMapping("/getUserInfo")
	public String getUserInfo(HttpSession session,HttpServletRequest request){
		//��ȡ
		String userid = (String)session.getAttribute("username");
		User user=this.userService.getUserByUserid(userid);
		
		
		request.setAttribute("user", user);
		return "information";
	}
	
	/**
	 * ��ѯ��ǰ��¼�ĸ�����Ϣ����
	 * @param session
	 * @param request
	 * @return
	 */
	@RequestMapping("/infoSetting")
	public String infoSetting(HttpSession session,HttpServletRequest request){
		
		String userid =(String) session.getAttribute("username");
		//����id��ȡ�û�����Ϣ
		User user = this.userService.getUserByUserid(userid);
		//��user���ݴ���infoSetting.jsp
		request.setAttribute("user", user);
		
		//ͷ��
		String filename = user.getProfilehead();
		
		request.setAttribute("profilehead", filename);
		return "info-setting";
		
	}
	
	/**
	 * �����ļ��ϴ�
	 */
	@RequestMapping("/upload")
	public String upload(@RequestParam("file")MultipartFile file){		
		//��֤���ؼ���name���Ժ��β�һ�¡�����@RequestParam("username") String uu
		//�Ȼ�ȡ�ļ�����
		String filename = file.getOriginalFilename();
		//InputSream input=null;
		InputStream input = null;
		FileOutputStream output=null;
		//ͨ��ioд���ļ�
		try{
		input = file.getInputStream();
		output = new FileOutputStream("D:\\tomcat8\\webapps\\headimages\\"+filename);
		IOUtils.copy(input, output);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				input.close();
				output.close();
			} catch (IOException e) {
				// TODO �Զ����ɵ� catch ��
				e.printStackTrace();
			}
			
		}	
		//���ϴ��ļ�������filename��ֵ���������ݿ���
		return "info-setting";
	}
	
	/**
	 * �޸ĸ�����Ϣ
	 */
	
	@RequestMapping("/update")
	public String update(HttpSession session,User user,HttpServletRequest request){
		
		String userid =(String) session.getAttribute("username");
		
		String nickname = request.getParameter("nickname");
		String sex = request.getParameter("sex");
		String age = request.getParameter("age");
		String checktype = request.getParameter("checktype");
		String profile = request.getParameter("profile");
			
		user.setUserid(userid);
		user.setNickname(nickname);		
		user.setSex(sex);
		user.setAge(Integer.parseInt(age));
		user.setChecktype(Integer.parseInt(checktype));
		user.setProfile(profile);		
		//request.setAttribute("user", user);
		 this.userService.update(user);
		 
		// System.out.println(user.getNickname() );
//			
		return "index";	
	}
	/**
	 * ��ѯ������ӵĺ����б�
	 * ��ҳ
	 */
	@RequestMapping("/dofriend")
	public String findnoFriend(HttpSession session,HttpServletRequest request,
			String searchid,String searchnickname,Integer nowpage,Integer nowpage2){
		//�ж�ҳ��
		if(nowpage == null ||nowpage == 0){
			nowpage=1;
		}
		User user=(User)session.getAttribute("user");
		if(user==null){
			return "redirect:login.html";
		}
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userid",user.getUserid());//��ǰ��¼��id
		map.put("mineid",user.getId());//��½�ߵ�id
		//nowpage = 1;
		map.put("startindex", (nowpage-1)*3);
		//������������������
		map.put("searchid", searchid);
		map.put("searchnickname", searchnickname);
		
		List<User> userList = userService.findFriendUser(map);
		int pagecount = userService.findFriendPages(map);
		
		int backpage = nowpage-1;
		if(backpage<1)
			backpage = 1;
		int nextpage = nowpage+1;
		if(nextpage>pagecount)
			nextpage = pagecount;
///////////////////////////////////////////
		//����i
		
		if(nowpage2 == null ||nowpage2 == 0){
			nowpage2=1;
		}
		Map<String, Object> pmap = new HashMap<String,Object>();
		pmap.put("userid",user.getId());//��ǰ��¼��id

		pmap.put("startindex", (nowpage2-1)*3);

		List<Space> spacelist = spaceService.findfriendSpace(pmap);
		//List<Space> spaceList = spaceService.findSpaceByUid(user.getId());
		int pagecounts = spaceService.spacefriendPages(map);
		//������һҳ����һҳ
		int backpage2 = nowpage2-1;
		if(backpage2<1)
			backpage2 = 1;
		int nextpage2 = nowpage2+1;
		if(nextpage2>pagecounts)
			nextpage2 = pagecounts;
		//��������
		request.setAttribute("searchid", searchid);//������������
		request.setAttribute("searchnickname", searchnickname);
		
		request.setAttribute("spacelist", spacelist);
		request.setAttribute("userList", userList);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("backpage", backpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("nowpage", nowpage);
		
		request.setAttribute("pagecounts", pagecounts);
		request.setAttribute("backpage2", backpage2);
		request.setAttribute("nextpage2", nextpage2);
		request.setAttribute("nowpage2", nowpage2);
		return "friend";

	}
	
	@RequestMapping("/doAddFriend")
	public String addFriend(HttpSession session,
			HttpServletRequest request,Integer friendid){
		
		User user=(User)session.getAttribute("user");
		if(user==null){
			return "redirect:/login.html";
		}
		int num =  userService.AddFriend(user.getId(), friendid);
		if(num == 0){
			session.setAttribute("msg", "�Ѿ�������֤��Ϣ");
			//session.setAttribute(arg0, arg1);
		}else{
			session.setAttribute("msg", "��Ӻ��ѳɹ�����ˢ�º����б�");
		}
		return "redirect:/dofriend";
		
	}
	
	/**
	 * ��ȡ��֤��Ϣ�ķ���
	 * @param session
	 * @return
	 */
	@RequestMapping("/doReadCheakMessage")
	public String addFriend(HttpSession session,HttpServletRequest request){
		User user = (User)session.getAttribute("user");
		 Message message = new Message();
		 message.setToid(user.getId());
		 message.setType(1);
		 message.setStatus(0);
		 
		 List<Message> messagelist = messageService.readMessageByParam(message);
		 request.setAttribute("messagelist", messagelist);//δ����Ϣ�б�
		 request.setAttribute("messageCount", messagelist.size());//δ����Ϣ����
		 return "index";
	}
		/**
		 * ������֤��Ϣ
		 * @param session
		 * @param friendid
		 * @param isAgree
		 * @return
		 */
	@RequestMapping("/doCheakMessage")
	public String doCheakMessage(HttpSession session,Integer friendid,Integer isAgree){
		User user = (User)session.getAttribute("user");
		 messageService.updateCheckMessage(user.getId(), friendid, isAgree);
		
		return "redirect:/doReadCheakMessage";
		
	}
	/**
	 * ��ѯ�����б���Ϣ
	 * @param session
	 * @param request
	 * @param searchid
	 * @param searchnickname
	 * @param nowpage
	 * @return
	 */
	@RequestMapping("/doFriendList")
	@ResponseBody
	public Map<String,Object> findFriend(HttpSession session){
		User user = (User)session.getAttribute("user");
		if(user == null)
			return null;
		List<User> friendList = this.userService.FindFriendUserById(user.getId());
		//����ҵ�񷽷�
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("friendList", friendList);
		map.put("friendCount", friendList.size());

		return map;
		 
		
	}
	/**
	 * ��ȡ������Ϣ
	 * @param session
	 * @param friendid
	 * @param request
	 * @return
	 */
	@RequestMapping("/doReadChatMessage")
	@ResponseBody
	public Map<String,Object> readChatMessage(HttpSession session,Integer friendid){
		
		User user = (User)session.getAttribute("user");
		Message message = new Message();
		message.setFromid(friendid);
		message.setToid(user.getId());
		message.setType(0);
		
		List<Message> chatMessageList = messageService.readChatMessage(message);
		User friend = userService.findUserById(friendid);
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("chatMessageList", chatMessageList);
		map.put("friend", friend);
		map.put("user", user);
		return map;
	}
	/**
	 * ����
	 * @param session
	 * @param friendid
	 * @param content
	 * @return
	 */
	@RequestMapping("/dochatCheakMessage")
	@ResponseBody
	public String Addcheakmessage(HttpSession session,Integer friendid,String content){
		
		User user = (User)session.getAttribute("user");
		Message message = new Message();
		message.setFromid(user.getId());
		message.setToid(friendid);
		message.setType(0);
		message.setContent(content);
		messageService.addChatMessage(message);
		
		return "success";
	}
	@RequestMapping("/upspaceimage")
	@ResponseBody
	public Object upfile(@RequestParam("file") MultipartFile file,HttpServletRequest request){
		Map<String, Object> map = new HashMap<String,Object>();
		String path = request.getServletContext().getRealPath("/space_images/");
		try {
			File serviceFile = new File(path + file.getOriginalFilename());
			file.transferTo(serviceFile);
			map.put("imgurl", file.getOriginalFilename());
			map.put("msg", "�ϴ��ɹ���");
			
			File localFile=new File("D:/ASpring/workspace/Spring/WebContent/space_images/"+file.getOriginalFilename());
			FileUtil.copyFile(serviceFile, localFile);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return map;
	}
	@RequestMapping("/addspace")
	public String addspace(HttpSession session,Space space){
		User user = (User)session.getAttribute("user");
		space.setNickname(user.getNickname());
		space.setUserid(user.getId());
		
		spaceService.addSpace(space);

		return "redirect:/index.jsp";
	}

	@RequestMapping("/doReadSpaceList")
	public String doReadSpaceList(HttpSession session,HttpServletRequest request,Integer nowpage){
		//�ж�ҳ��
		if(nowpage == null ||nowpage == 0){
			nowpage=1;
		}
		User user=(User)session.getAttribute("user");
		if(user == null)
			return null;
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userid",user.getId());//��ǰ��¼��id

		map.put("startindex", (nowpage-1)*3);

		List<Space> spacelist = spaceService.findSpace(map);
		//List<Space> spaceList = spaceService.findSpaceByUid(user.getId());
		int pagecount = spaceService.spacePages(map);
		//������һҳ����һҳ
		int backpage = nowpage-1;
		if(backpage<1)
			backpage = 1;
		int nextpage = nowpage+1;
		if(nextpage>pagecount)
			nextpage = pagecount;
		//��������		
		//request.setAttribute("spaceList", spaceList);
		request.setAttribute("spacelist", spacelist);
		request.setAttribute("pagecount", pagecount);
		request.setAttribute("backpage", backpage);
		request.setAttribute("nextpage", nextpage);
		request.setAttribute("nowpage", nowpage);
		return "user_space";

	}
	@RequestMapping("/addreply")
	public String addReply(HttpSession session,Reply reply,Integer sid,String content){
		
		User user = (User)session.getAttribute("user");
		if(user == null)
			return null;
		
		reply.setNickname(user.getNickname());
		reply.setUserid(user.getId());
		reply.setSid(sid);
	
		reply.setContent(content);
		int count = spaceService.findReplyCount(reply.getSid());
		System.out.println(count);
		
		spaceService.updateSpaceCount(count);
		spaceService.addReply(reply);

		return "redirect:/friend.jsp";
	}
}
