package dao.impl;

import entity.Music;
import entity.MusicComment;
import entity.Pagination;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
import dao.MusicCommentDao;

public class MusicCommentDaoImpl implements MusicCommentDao{
    public List<MusicComment> getMusicCommentsByMusicId(Integer id) {
        List<MusicComment> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//������������ڷ�װ���ݿ�Ĳ�ѯ�����
        try {

            String sql = "";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            sql = "select * from music_comment  where music_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();//ִ�в�ѯ��䲢���ز�ѯ���

            while (rs.next()) {
                MusicComment mc = new MusicComment();
                mc.setMc_id(rs.getInt("mc_id"));
                mc.setMusic_id(rs.getInt("music_id"));
                mc.setComment(rs.getString("comment"));
                mc.setUser_id(rs.getInt("user_id"));
                mc.setComment_date(rs.getTimestamp("comment_date"));
                list.add(mc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

  
	@Override
	public void addMusicComment(int musicId, int userId, String comment) throws SQLException{
		Connection conn = DBUtils.getConnection();
		String sql = "insert into music_comment(music_id,user_id,comment) values(?,?,?);";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		stmt.setInt(2, userId);
		stmt.setString(3, comment);
		
		// ִ��SQL
		int result = stmt.executeUpdate();
		System.out.println("result:" + result);
		// �ر�����
		DBUtils.closeConnection(conn);
	}


	@Override
	public int getCommentCountByMusicId(int musicId) throws SQLException {
		int commentCount = -1;
		Connection conn = DBUtils.getConnection();
		
		String sql = "select count(*) from music_comment where music_id = ?";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();

		Music music = null;
		// ѭ�������
		if(result.next()){
			commentCount = result.getInt(1);
		}
		
		return commentCount;
	}


	@Override
	public List<MusicComment> getMusicCommentsByMusicId(int musicId,
			Pagination page) throws SQLException {
		List<MusicComment> list = new ArrayList<MusicComment>();
		Connection conn = DBUtils.getConnection();
		
		String sql = "select * from music_comment " +
						"where music_id = ? " +
						"ORDER BY comment_date desc " +
						"limit  ?,? ";
		
		// Ԥ׼��Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// ��������ֵ
		stmt.setInt(1, musicId);
		stmt.setInt(2, page.getCurStartRow());
		stmt.setInt(3, page.getPageSize());
		
		// ִ��SQL
		ResultSet result = stmt.executeQuery();

		MusicComment mc = null;
		// ѭ�������
		while(result.next()){
			mc = new MusicComment();
			mc.setComment(comment);
			mc.set
			mc.setMusicId(result.getInt("music_id"));
			music.setMusicName(result.getString("music_name"));
			music.setMusicAuthor(result.getString("music_author"));
			music.setMusicAlbumId(result.getInt("music_album_id"));
			music.setMusicAlbum(result.getString("album_name"));
			music.setMusicPath(result.getString("music_path"));
			music.setMusicCreatedTime(result.getTimestamp("music_created_time"));
			music.setMusicLyricPath(result.getString("music_lyric_path"));
			music.setMusicPic(result.getString("music_pic"));
			
			list.add(music);
		}
		
		return list;
		return null;
	}
}