package entity;

public class MusicDownload {

	// ����
	private String downloadDate;
	// ��������
	private int downloadNum;
	
	// �û�����
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