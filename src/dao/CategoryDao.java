package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Category;

public interface CategoryDao {

	// ��ѯ�������
	public List<Category> queryAllCategory() throws SQLException;
}
