package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CategoryDao;
import util.DBUtils;
import entity.Category;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> queryAllCategory() throws SQLException {
		List<Category> list = new ArrayList<Category>();
		
		Connection conn = DBUtils.getConnection();
        String sql = "select * from category limit 0,6";
        
        PreparedStatement ps = conn.prepareStatement(sql);
        
        ResultSet rs=ps.executeQuery();

        Category category = null;
        while (rs.next()) {
        	category = new Category();
        	category.setCategory_id(rs.getInt("category_id"));
        	category.setCategory_type(rs.getString("category_type"));

            list.add(category);
        }
        
        DBUtils.closeConnection(conn);
        
        return list;
	}

}
