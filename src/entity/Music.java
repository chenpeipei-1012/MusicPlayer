package entity;

import java.sql.Timestamp;

public class Music {

	private int musicId;
	private String musicName;
	private String musicAuthor ;
	private int musicAlbumId;
	private String musicAlbum;
	private String musicPath;
	private Timestamp musicCreatedTime;
	private String musicLyricPath;
	private String musicPic;
	private int musicTypeId;
	
	public int getMusicId() {
		return musicId;
	}
	
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}
	
	public String getMusicName() {
		return musicName;
	}
	
	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}
	
	public String getMusicAuthor() {
		return musicAuthor;
	}
	
	public void setMusicAuthor(String musicAuthor) {
		this.musicAuthor = musicAuthor;
	}
	
	public int getMusicAlbumId() {
		return musicAlbumId;
	}
	
	public void setMusicAlbumId(int musicAlbumId) {
		this.musicAlbumId = musicAlbumId;
	}
	
	public String getMusicPath() {
		return musicPath;
	}
	
	public void setMusicPath(String musicPath) {
		this.musicPath = musicPath;
	}
	
	public Timestamp getMusicCreatedTime() {
		return musicCreatedTime;
	}
	
	public void setMusicCreatedTime(Timestamp musicCreatedTime) {
		this.musicCreatedTime = musicCreatedTime;
	}

	public String getMusicAlbum() {
		return musicAlbum;
	}

	public void setMusicAlbum(String musicAlbum) {
		this.musicAlbum = musicAlbum;
	}

	public String getMusicLyricPath() {
		return musicLyricPath;
	}

	public void setMusicLyricPath(String musicLyricPath) {
		this.musicLyricPath = musicLyricPath;
	}

	public String getMusicPic() {
		return musicPic;
	}

	public void setMusicPic(String musicPic) {
		this.musicPic = musicPic;
	}

	public int getMusicTypeId() {
		return musicTypeId;
	}

	public void setMusicTypeId(int musicTypeId) {
		this.musicTypeId = musicTypeId;
	}
}