package entity;

import java.sql.Timestamp;

public class MusicComment {
    private int mcId;
    private int musicId;
    private String comment;
    private int userId;
    private Timestamp commentDate;

    public MusicComment(){
    	
    }
    
    public MusicComment(Integer mcId, Integer musicId, String comment,
			Integer userId, Timestamp commentDate) {
		super();
		this.mcId = mcId;
		this.musicId = musicId;
		this.comment = comment;
		this.userId = userId;
		this.commentDate = commentDate;
	}

    public int getMcId() {
		return mcId;
	}

	public void setMcId(int mcId) {
		this.mcId = mcId;
	}

	public int getMusicId() {
		return musicId;
	}

	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	}

	@Override
    public String toString() {
        return "MusicComment{" +
                "mc_id=" + mcId +
                ", music_id=" + musicId +
                ", comment='" + comment + '\'' +
                ", user_id=" + userId +
                ", comment_date=" + commentDate
                ;
    }
}
