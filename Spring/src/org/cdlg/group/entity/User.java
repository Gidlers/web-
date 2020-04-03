package org.cdlg.group.entity;

import java.util.Date;

public class User {
		private int id;
		private String userid;
		private String password;
		private String nickname;
		private String sex;
		private String profilehead;
		private String profile;
		private int checktype;
		private Date firsttime;
		private int status;
		private int age;
		private Date lasttime;
		private int messageCount;
		


		public int getMessageCount() {
			return messageCount;
		}
		public void setMessageCount(int messageCount) {
			this.messageCount = messageCount;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserid() {
			return userid;
		}
		public void setUserid(String userid) {
			this.userid = userid;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getNickname() {
			return nickname;
		}
		public void setNickname(String nickname) {
			this.nickname = nickname;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getProfilehead() {
			return profilehead;
		}
		public void setProfilehead(String profilehead) {
			this.profilehead = profilehead;
		}
		public String getProfile() {
			return profile;
		}
		public void setProfile(String profile) {
			this.profile = profile;
		}
		public int getChecktype() {
			return checktype;
		}
		public void setChecktype(int checktype) {
			this.checktype = checktype;
		}
		public Date getFirsttime() {
			return firsttime;
		}
		public void setFirsttime(Date firsttime) {
			this.firsttime = firsttime;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getLasttime() {
			return lasttime;
		}
		public void setLasttime(Date lasttime) {
			this.lasttime = lasttime;
		}
		public int getAge() {
			return age;
		}
		public void setAge(int age) {
			this.age = age;
		}
		

		
}
