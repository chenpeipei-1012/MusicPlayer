package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import entity.Music;
import entity.MusicDownload;

public interface MusicDao {

	// ��ҳ��ѯ����
	public List<Music> queryMusicByPaging(int offset,int pageSize) throws SQLException;

	// ��¼��������
	public void addMusicDownloadRecord(int musicId) throws SQLException;
	
	// ���ո������ء��ϴ������������û���Ϣ
	public Map<String,Integer> queryCurMusicAandUserNum() throws SQLException;
	
	// �õ���һ����������
	public List<MusicDownload> queryDownloadNum() throws SQLException;
}
