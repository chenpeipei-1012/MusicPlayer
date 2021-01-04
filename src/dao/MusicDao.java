package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import entity.Music;
import entity.MusicDownload;
import entity.Pagination;

public interface MusicDao {

	// 分页查询歌曲
	public List<Music> queryMusicByPaging(Pagination page,String condition) throws SQLException;

	// 根据查询条件得到歌曲总数
	public int getMusicCountByCondition(String condition) throws SQLException;
	
	// 记录歌曲下载
	public void addMusicDownloadRecord(int musicId) throws SQLException;
	
	// 当日歌曲下载、上传数量、新增用户信息
	public Map<String,Integer> queryCurMusicAandUserNum() throws SQLException;
	
	// 得到近一周下载数量
	public List<MusicDownload> queryDownloadNum() throws SQLException;
	
	// 通过歌曲ID获得歌曲对象
	public Music getMusicById(int id) throws SQLException;
	
	// 得到与这首歌类似的歌曲列表
	public List<Music> querySimilarSongs(int musicType,int count) throws SQLException;
	
	// 得到收藏量排名前10歌曲
	public List<Music> queryTopSaveSongs() throws SQLException;
	
	// 得到下载量排名前10的歌曲
	public List<Music> queryTopDownloadSongs() throws SQLException;
	
	// 得到某种类型的收藏前8的歌曲
	public List<Object[]> queryTopSaveSongsBymusicType(int musicType) throws SQLException;
}
