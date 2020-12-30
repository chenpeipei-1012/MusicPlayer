package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import util.DBUtils;
import dao.MusicDao;
import entity.Music;
import entity.MusicDownload;

public class MusicDaoImpl implements MusicDao{

	@Override
	public List<Music> queryMusicByPaging(int offset, int pageSize) throws SQLException{
		List<Music> list = new ArrayList<Music>();
		Connection conn = DBUtils.getConnection();
		
		String sql = "select * from (select * from music limit ?,?) m " +
						"left join album " + 
						"on m.music_album_id = album_id";
		
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
	public void addMusicDownloadRecord(int musicId) throws SQLException {
		Connection conn = DBUtils.getConnection();
		String sql = "insert into music_download(music_id,download_date) values(?,?)";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		Date curDate = new Date();
		// 给参数赋值
		stmt.setInt(1, musicId);
		stmt.setDate(2, new java.sql.Date(curDate.getTime()));
		
		// 执行SQL
		int result = stmt.executeUpdate();
		System.out.println("result:" + result);
		// 关闭连接
		DBUtils.closeConnection(conn);
		
	}

	@Override
	public Map<String,Integer> queryCurMusicAandUserNum() throws SQLException {
		Map<String,Integer> numMap = new HashMap<String,Integer>();
		
		Connection conn = DBUtils.getConnection();
		
		String sql = "select count(*) from music where music_created_time = ? " +
						"UNION ALL " +
						"select count(*) from music_download where download_date = ? " +
						"UNION ALL " +
						"select count(*) from user where user_created_date = ? ";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		// 给参数赋值
		Date curDate = new Date();
		java.sql.Date sqlCurDate = new java.sql.Date(curDate.getTime());
		
		stmt.setDate(1, sqlCurDate);
		stmt.setDate(2, sqlCurDate);
		stmt.setDate(3, new java.sql.Date(DateUtils.addDays(curDate, -1).getTime()));
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();
		System.out.println(result.getRow());
		// 循环结果集
		while(result.next()){
			int row = result.getRow();
			if(row == 1){
				numMap.put("curNewMusicNum", result.getInt(1));
			}else if(row == 2){
				numMap.put("curMusicDownloadNum", result.getInt(1));
			}else if(row == 3){
				numMap.put("yestodayNewUserNum", result.getInt(1));
			}
		}
		
		return numMap;
	}
	
	// 测试
	public static void main(String args []){
		MusicDao impl = new MusicDaoImpl();
		
		try {
			//impl.modifyUserInfo(1, "佩佩", "女", "这是我的描述", "c://", new Date(), "湖南省 株洲市");
			impl.queryDownloadNum();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<MusicDownload> queryDownloadNum() throws SQLException {
		List<MusicDownload> list = new ArrayList<MusicDownload>();
		Connection conn = DBUtils.getConnection();
		
		String sql = "select curr_date,download_num,user_num from " +
						"( " +
							"select date_format(date_add(?,interval -t.help_topic_id day),'%Y-%m-%d') as 'curr_date' " + 
							"from mysql.help_topic t where t.help_topic_id <= 6 " +
						") d " +
						"LEFT JOIN " +
						"( " +
							"select user_created_date,count(user_created_date) user_num from user " +
							"where user_type = 1  " +
							"GROUP BY user_created_date " +
						") n1 " +
						"ON d.curr_date = n1.user_created_date " +
						"LEFT JOIN  " +
						"( " +
							"select download_date,count(download_date) download_num from music_download " +
							"GROUP BY download_date " +
						") n2 " +
						"ON d.curr_date = n2.download_date ";
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		Date curDate = new Date();
		
		stmt.setDate(1, new java.sql.Date(DateUtils.addDays(curDate, -1).getTime()));
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		MusicDownload musicDownload = null;
		
		// 循环结果集
		while(result.next()){
			musicDownload = new MusicDownload();

			musicDownload.setDownloadDate(result.getString("curr_date"));
			musicDownload.setDownloadNum(result.getInt("download_num"));
			musicDownload.setUserNum(result.getInt("user_num"));
			
			list.add(musicDownload);
		}
		
		return list;
	}

}