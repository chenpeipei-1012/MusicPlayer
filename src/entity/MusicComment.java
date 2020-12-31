package entity;

import java.sql.Timestamp;
import java.util.Date;

public class MusicComment {
    private Integer mc_id;
    private Integer music_id;
    private String comment;
    private Integer user_id;
    private Timestamp comment_date;


    public Integer getMc_id() {
        return mc_id;
    }

    public void setMc_id(Integer mc_id) {
        this.mc_id = mc_id;
    }

    public Integer getMusic_id() {
        return music_id;
    }

    public void setMusic_id(Integer music_id) {
        this.music_id = music_id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Timestamp getComment_date() {
        return comment_date;
    }

    public void setComment_date(Timestamp comment_date) {
        this.comment_date = comment_date;
    }
}
