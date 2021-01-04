package dao;

import java.sql.SQLException;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import entity.Music;

public interface BandOrderDao {

    //新歌榜 按上传时间
	List<Music> orderByUpdata() throws SQLException;
	
	//收藏榜 按收藏数量
	//public List<Music> orderByCollect() throws SQLException;
	
	//下载榜 按下载次数
	//public List<Music> orderByDownlod() throws SQLException;
	
}
