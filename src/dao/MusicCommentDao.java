package dao;

import entity.Music;
import entity.MusicComment;

import java.util.Date;
import java.util.List;

public interface MusicCommentDao {
    public List<MusicComment> getMusicCommentsByMusicId (Integer id);
    public void addMusicComment(Integer mc_id, Integer musicId, Integer user_id, String comment, Date comment_date);
}
