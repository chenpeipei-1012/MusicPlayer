package entity;

import java.sql.Timestamp;

public class UserMusicList {

	private int listId;
	private String listName;
	private Timestamp listTime;
	private int listUid;
	private int listLove;
	
	// 歌单中拥有的歌曲数量
	private int musicNum;
	private String listPic;
	
	public UserMusicList(){
		this.musicNum = 0;
	}
	
	public UserMusicList(int listId,String listName,Timestamp listTime,int listUid,int listLove
			,int musicNum){
		this.listId = listId;
		this.listName = listName;
		this.listTime = listTime;
		this.listUid = listUid;
		this.listLove = listLove;
		this.musicNum = musicNum;
	}
	
	public int getListId() {
		return listId;
	}
	public void setListId(int listId) {
		this.listId = listId;
	}
	
	public String getListName() {
		return listName;
	}
	
	public void setListName(String listName) {
		this.listName = listName;
	}
	
	public Timestamp getListTime() {
		return listTime;
	}
	
	public void setListTime(Timestamp listTime) {
		this.listTime = listTime;
	}
	
	public int getListUid() {
		return listUid;
	}
	
	public void setListUid(int listUid) {
		this.listUid = listUid;
	}
	
	public int getListLove() {
		return listLove;
	}
	
	public void setListLove(int list_love) {
		this.listLove = list_love;
	}
	
	public int getMusicNum() {
		return musicNum;
	}
	
	public void setMusicNum(int musicNum) {
		this.musicNum = musicNum;
	}

	public String getListPic() {
		return listPic;
	}

	public void setListPic(String listPic) {
		this.listPic = listPic;
	}
}