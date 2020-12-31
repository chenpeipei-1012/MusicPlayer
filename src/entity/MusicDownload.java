package entity;

public class MusicDownload {

	// 日期
	private String downloadDate;
	// 下载数量
	private int downloadNum;
	
	// 用户数量
	private int userNum;
	
	public MusicDownload(){
		
	}
	
	public String getDownloadDate() {
		return downloadDate;
	}
	
	public void setDownloadDate(String downloadDate) {
		this.downloadDate = downloadDate;
	}
	
	public int getDownloadNum() {
		return downloadNum;
	}
	
	public void setDownloadNum(int downloadNum) {
		this.downloadNum = downloadNum;
	}

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}
	
	
}