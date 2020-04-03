package org.cdlg.group.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Space {
	private Integer id;
	private Integer userid;
	private String nickname;
	private String content;
	private String pics;//图片之间使用逗号分开
	private Date createdate;
	private Integer replynum;
	private List<String> picList = new ArrayList<String>();//集合保存图片 的路径
	private List<Reply> replyList = new ArrayList<Reply>();
	
	public List<Reply> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<Reply> replyList) {
		this.replyList = replyList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPics() {
		return pics;
	}
	public void setPics(String pics) {
		this.pics = pics;
	}
	
	public Integer getReplynum() {
		return replynum;
	}
	public void setReplynum(Integer replynum) {
		this.replynum = replynum;
	}
	public List<String> getPicList() {
		if( pics!=null && pics.indexOf(",")!=-1){
			String[] array = pics.split(",");
			for(int i=0;i<array.length;i++){
				if(array[i]!=null && !array[i].equals("")){
					picList.add(array[i]);
				}
			}
		}else{
			picList.add(pics);
		}
		return picList;
	}
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}
	
}
