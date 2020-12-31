package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.DBUtils;
import dao.MusicDao;
import entity.Music;

public class MusicDaoImpl implements MusicDao{

	@Override
	public List<Music> queryMusicByPaging(int offset, int pageSize) throws SQLException{
		List<Music> list = new ArrayList<Music>();
		Connection conn = DBUtils.getConnection();
		
		String sql = "select * from music limit ?,?;";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		stmt.setInt(1, offset);
		stmt.setInt(2, pageSize);
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		Music music = null;
		// 循环结果集
		while(result.next()){
			music = new Music();
			music.setMusicId(result.getInt("music_id"));
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
	}

    public Music getMusicById(Integer id){
	    Music music = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
        String sql="";
        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
        sql="select * from music where username=?";
        ps=conn.prepareStatement(sql);
        ps.setInt(1,id);
        rs=ps.executeQuery();

        while (rs.next()) {
            music=new Music();
            music.setMusicId(rs.getInt("music_id"));
            music.setMusicName(rs.getString("music_name"));
            music.setMusicAuthor(rs.getString("music_author"));
            music.setMusicAlbumId(rs.getInt("music_album_id"));
            music.setMusicAlbum(rs.getString("album_name"));
            music.setMusicPath(rs.getString("music_path"));
            music.setMusicCreatedTime(rs.getTimestamp("music_created_time"));
            music.setMusicLyricPath(rs.getString("music_lyric_path"));
            music.setMusicPic(rs.getString("music_pic"));


        }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return music;
    }


}