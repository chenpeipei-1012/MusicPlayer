package dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.DBUtils;
import dao.MusicDao;
import entity.Music;
import entity.MusicDownload;

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

	@Override
    public Music getMusicById(int id) throws SQLException{
		Connection conn = DBUtils.getConnection();
        String sql="select * from music " + 
						"LEFT JOIN album " + 
						"ON music.music_album_id = album.album_id " + 
						"where music_id = ?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,id);
        
        ResultSet rs=ps.executeQuery();

        Music music = null;
        if (rs.next()) {
            music=new Music();
            music.setMusicId(rs.getInt("music_id"));
            music.setMusicName(rs.getString("music_name"));
            music.setMusicAuthor(rs.getString("music_author"));
            music.setMusicAlbum(rs.getString("album_name"));
            music.setMusicPath(rs.getString("music_path"));
            music.setMusicCreatedTime(rs.getTimestamp("music_created_time"));
            music.setMusicLyricPath(rs.getString("music_lyric_path"));
            music.setMusicPic(rs.getString("music_pic"));
            
            // 歌曲类型
            music.setMusicTypeId(rs.getInt("music_type_id"));
        }
        
        return music;
    }

    public List<Music> queryMusicRecomMost()throws SQLException {
        List<Music> list = new ArrayList<Music>();


        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = DBUtils.getConnection();
        String sql = "select * from music left join (select * from music_download order by music_id)where 1=1 limit 11 ";
        ps = conn.prepareStatement(sql);
        rs = ps.executeQuery();

        Music music = null;
        // 循环结果集
        while (rs.next()) {
            music = new Music();
            music.setMusicId(rs.getInt("music_id"));
            music.setMusicName(rs.getString("music_name"));
            music.setMusicAuthor(rs.getString("music_author"));
            music.setMusicAlbumId(rs.getInt("music_album_id"));
            music.setMusicAlbum(rs.getString("album_name"));
            music.setMusicPath(rs.getString("music_path"));
            music.setMusicCreatedTime(rs.getTimestamp("music_created_time"));
            music.setMusicLyricPath(rs.getString("music_lyric_path"));
            music.setMusicPic(rs.getString("music_pic"));
            list.add(music);
        }
        return list;
    }

	@Override
	public void addMusicDownloadRecord(int musicId) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Integer> queryCurMusicAandUserNum() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MusicDownload> queryDownloadNum() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Music> querySimilarSongs(int musicType) throws SQLException {
		List<Music> list = new ArrayList<Music>();
		
		Connection conn = DBUtils.getConnection();
        String sql = "select * from " +
						"(select * from music where music_type_id = ?) t1 " +
						"left join album " + 
						"on t1.music_album_id =  album.album_id " +
						"left join  " +
						"(select mid,count(mid) save_count from list_music GROUP BY mid) t2 " +
						"on t1.music_id=t2.mid " +
						"order by save_count desc  " +
						"limit 0,10";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,musicType);
        
        ResultSet rs=ps.executeQuery();

        Music music = null;
        while (rs.next()) {
            music = new Music();
            music.setMusicId(rs.getInt("music_id"));
            music.setMusicName(rs.getString("music_name"));
            music.setMusicAuthor(rs.getString("music_author"));
            music.setMusicAlbum(rs.getString("album_name"));
            music.setMusicPath(rs.getString("music_path"));
            music.setMusicCreatedTime(rs.getTimestamp("music_created_time"));
            music.setMusicLyricPath(rs.getString("music_lyric_path"));
            music.setMusicPic(rs.getString("music_pic"));
            
            list.add(music);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}
}