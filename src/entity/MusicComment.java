package entity;

import java.sql.Timestamp;
import java.util.Date;

public class MusicComment {
    private Integer mcId;
    private Integer musicId;
    private String comment;
    private Integer userId;
    private Timestamp commentDate;


    public MusicComment(){
    	
    }
    
    
    
    public Integer getMc_id() {
        return mcId;
    }

    public void setMc_id(Integer mc_id) {
        this.mcId = mc_id;
    }

    public Integer getMusic_id() {
        return musicId;
    }

    public void setMusic_id(Integer music_id) {
        this.musicId = music_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUser_id() {
        return userId;
    }

    public void setUser_id(Integer user_id) {
        this.userId = user_id;
    }

    public Timestamp getComment_date() {
        return commentDate;
    }

    public void setComment_date(Timestamp comment_date) {
        this.commentDate = comment_date;
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
