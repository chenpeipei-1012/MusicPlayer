package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;

import util.DBUtils;
import dao.MusicDao;
import entity.Music;
import entity.MusicDownload;
import entity.Pagination;

public class MusicDaoImpl implements MusicDao{

	// 测试
	public static void main(String args []){
		MusicDao impl = new MusicDaoImpl();

		try {
			//impl.modifyUserInfo(1, "佩佩", "女", "这是我的描述", "c://", new Date(), "湖南省 株洲市");
			Pagination page = new Pagination();
			page.setCurPage(1);
			page.setPageSize(10);
			impl.queryMusicByPaging(page, "走着");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Music> queryMusicByPaging(Pagination page,String condition) throws SQLException{
		List<Music> list = new ArrayList<Music>();
		Connection conn = DBUtils.getConnection();
		PreparedStatement stmt = null;

		String sql = "select * from  " +
						 "(select * from music ";
		
		if(!"".equals(condition)){
			sql += " where music_name like '%" + condition + "%' or music_author like '%" + condition + "%'";
		}
		
		sql += " limit ?,? ) t1 " +
					"LEFT JOIN album " +
					"on t1.music_album_id = album.album_id";
		stmt = conn.prepareStatement(sql);
		
		// 给参数赋值
		stmt.setInt(1, page.getCurStartRow());
		stmt.setInt(2, page.getPageSize());
		
		
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
			
			// 歌曲类型
            music.setMusicTypeId(result.getInt("music_type_id"));
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

	@Override
	public List<Music> querySimilarSongs(int musicType, int count) throws SQLException {
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
						"limit 0,?";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,musicType);
        ps.setInt(2,count);
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
            
            // 歌曲类型
            music.setMusicTypeId(rs.getInt("music_type_id"));
            
            list.add(music);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}

	@Override
	public int getMusicCountByCondition(String condition) throws SQLException {
		int musicCount = -1;
		Connection conn = DBUtils.getConnection();
		
		String sql = "select count(*) from music ";
		if(!"".equals(condition)){
			sql += " where music_name like '%" + condition + "%' or music_author like '%" + condition + "%'";
		}
		
		// 预准备Statement
		PreparedStatement stmt = conn.prepareStatement(sql);
		
		// 执行SQL
		ResultSet result = stmt.executeQuery();

		// 循环结果集
		if(result.next()){
			musicCount = result.getInt(1);
		}
		
		return musicCount;
	}

	@Override
	public List<Music> queryTopSaveSongs() throws SQLException {
		List<Music> list = new ArrayList<Music>();
		
		Connection conn = DBUtils.getConnection();
        String sql = "select * from " +
						"(select * from music) t1 " +
						"left join album " + 
						"on t1.music_album_id =  album.album_id " +
						"left join  " +
						"(select mid,count(mid) save_count from list_music GROUP BY mid) t2 " +
						"on t1.music_id=t2.mid " +
						"order by save_count desc  " +
						"limit 0,10";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
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
            
            // 歌曲类型
            music.setMusicTypeId(rs.getInt("music_type_id"));
            
            list.add(music);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}

	@Override
	public List<Music> queryTopDownloadSongs() throws SQLException {
		List<Music> list = new ArrayList<Music>();
		
		Connection conn = DBUtils.getConnection();
        String sql = "select * from  " +
						"( " +
						"select music_id,count(*) download_count from music_download  " +
						"GROUP BY music_id " +
						") t1 " +
						"left join music " +
						"on t1.music_id = music.music_id " +
						"left join album " +
						"on music.music_album_id = album.album_id " +
						"limit 0,10";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
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
            
            // 歌曲类型
            music.setMusicTypeId(rs.getInt("music_type_id"));
            list.add(music);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}

	@Override
	public List<Object[]> queryTopSaveSongsBymusicType(int musicType)
			throws SQLException {
		List<Object[]> list = new ArrayList<Object[]>();
		
		Connection conn = DBUtils.getConnection();
        String sql = "select * from " +
						"(select * from music where music_type_id = ?) t1 " +
						"left join album " + 
						"on t1.music_album_id =  album.album_id " +
						"left join  " +
						"(select mid,count(mid) save_count from list_music GROUP BY mid) t2 " +
						"on t1.music_id=t2.mid " +
						"order by save_count desc  " +
						"limit 0,8";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1,musicType);
        ResultSet rs=ps.executeQuery();

        Music music = null;
        Object[] object = null;
        int saveCount = -1;
        while (rs.next()) {
            music = new Music();
            object = new Object[2];
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
            
            // 歌曲收藏次数
            saveCount = rs.getInt("save_count");
            
            object[0] = music;
            object[1] = saveCount;
            
            list.add(object);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}
}