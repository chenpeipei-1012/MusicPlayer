package entity;

import java.util.Date;

public class User {

	private int userId;
	private String userName;
	private String userPwd;
	private String userGender;
	private String userDesc;
	private String userPic;
	private String userNick;
	private String userIddr;
	private Date userBirthday;
	
	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getUserGender() {
		return userGender;
	}
	
	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}
	
	public String getUserDesc() {
		return userDesc;
	}
	
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	
	public String getUserPic() {
		return userPic;
	}
	
	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}
	
	public String getUserNick() {
		return userNick;
	}
	
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	
	public String getUserIddr() {
		return userIddr;
	}
	
	public void setUserIddr(String userIddr) {
		this.userIddr = userIddr;
	}
	
	public Date getUserBirthday() {
		return userBirthday;
	}
	
	public void setUserBirthday(Date userBirthday) {
		this.userBirthday = userBirthday;
	}
}