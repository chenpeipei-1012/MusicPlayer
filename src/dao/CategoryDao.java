package dao;

import java.sql.SQLException;
import java.util.List;

import entity.Category;

public interface CategoryDao {

	// 查询所有类别
	public List<Category> queryAllCategory() throws SQLException;
}
