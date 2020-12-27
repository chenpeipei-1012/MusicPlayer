package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Music;

public interface MusicDao {

	// ∑÷“≥≤È—Ø∏Ë«˙
	public List<Music> queryMusicByPaging(int offset,int pageSize) throws SQLException;
}
