package entity;

import java.sql.Timestamp;

public class UserMusicList {

	private int listId;
	private String listName;
	private Timestamp listTime;
	private int listUid;
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
}