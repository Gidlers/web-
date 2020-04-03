package org.cdlg.group.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.cdlg.group.entity.Message;
import org.cdlg.group.entity.Space;
import org.cdlg.group.entity.User;
import org.cdlg.group.service.MessageService;
import org.cdlg.group.service.SpaceService;
import org.cdlg.group.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestRegister {
	static ApplicationContext ac=null;
	static{
		//加载Spring容器，创造关联对象
		 ac= new ClassPathXmlApplicationContext("spring-mvc.xml","spring-mybatis.xml");
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		testRand();
	}
	
	public static void testRand(){
		Random random = new Random();
		for(int i=0;i<20;i++)
		{
			System.out.println(random.nextInt(10)%4);
		}
	}
	
	
	
	public static void testMess4(){
		SpaceService SpaceService = (SpaceService)ac.getBean("spaceService");
		int count = SpaceService.findReplyCount(8);
		System.out.println(count);
	}
	public static void testMess3(){
		SpaceService SpaceService = (SpaceService)ac.getBean("spaceService");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userid", "1");//当前登录者id
		int nowpage = 1;
		map.put("startindex", (nowpage-1)*3);
		//以下是两个搜索条件
		//map.put("searchid, "admin2");
		//map.put("searchnickname", "昵称");
		List<Space> list = SpaceService.findfriendSpace(map);
		System.out.println(list);
		int pages = SpaceService.spacefriendPages(map);
		
//		
		System.out.println("符合条件的总页数："+pages);
		for(Space space : list){
			System.out.println(space.getId()+"："+space.getNickname());
		}
	}
	
	
	public static void testMess2(){
		SpaceService SpaceService = (SpaceService)ac.getBean("spaceService");
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("userid", "1");//当前登录者id
		int nowpage = 2;
		map.put("startindex", (nowpage-1)*3);
		//以下是两个搜索条件
		//map.put("searchid, "admin2");
		//map.put("searchnickname", "昵称");
		List<Space> list = SpaceService.findSpace(map);
		System.out.println(list);
		int pages = SpaceService.spacePages(map);
		System.out.println("符合条件的总页数："+pages);
		for(Space space : list){
			System.out.println(space.getId()+"："+space.getNickname());
		}
	}

	public static void testMess(){
		MessageService messageService=(MessageService)ac.getBean("messageServiceImpl");
		Message message = new Message();
		//message.setFromid(1);
		message.setToid(5);
		message.setType(1);
		message.setStatus(0);
		List<Message> list = messageService.readMessageByParam(message);
	System.out.println(list.size());
	
	}
	public static void testAddfriend(){
		//
		UserService userService = (UserService)ac.getBean("userService");
		int num = userService.AddFriend(2, 4);
		System.out.println("num:"+num);
	}
	
	
	public static void testNotfriend(){
		//利用spring容器 获取指定的对象
		UserService userService = (UserService)ac.getBean("userService");
		
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("userid", "admin");//当前登录者id
		map.put("mineid", "1");//登陆者的id
		int nowpage = 2;
		map.put("startindex", (nowpage-1)*3);
		//以下是两个搜索条件
		//map.put("searchid, "admin2");
		map.put("searchnickname", "昵称");
		List<User> userList = userService.findFriendUser(map);
		int pages = userService.findFriendPages(map);
		System.out.println("符合条件的总页数："+pages);
		for(User user : userList){
			System.out.println(user.getId()+"："+user.getNickname());
		}
	}
	
	
	
}
