package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import entity.Music;
import entity.MusicDownload;
import entity.Pagination;

public interface MusicDao {

	// ��ҳ��ѯ����
	public List<Music> queryMusicByPaging(Pagination page,String condition) throws SQLException;

	// ���ݲ�ѯ�����õ���������
	public int getMusicCountByCondition(String condition) throws SQLException;
	
	// ��¼��������
	public void addMusicDownloadRecord(int musicId) throws SQLException;
	
	// ���ո������ء��ϴ������������û���Ϣ
	public Map<String,Integer> queryCurMusicAandUserNum() throws SQLException;
	
	// �õ���һ����������
	public List<MusicDownload> queryDownloadNum() throws SQLException;
	
	// ͨ������ID��ø�������
	public Music getMusicById(int id) throws SQLException;
	
	// �õ������׸����Ƶĸ����б�
	public List<Music> querySimilarSongs(int musicType,int count) throws SQLException;
	
	// �õ��ղ�������ǰ10����
	public List<Music> queryTopSaveSongs() throws SQLException;
	
	// �õ�����������ǰ10�ĸ���
	public List<Music> queryTopDownloadSongs() throws SQLException;
	
	// �õ�ĳ�����͵��ղ�ǰ8�ĸ���
	public List<Object[]> queryTopSaveSongsBymusicType(int musicType) throws SQLException;
}
