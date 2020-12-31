package dao.impl;

import dao.AlbumDao
import entity.Album;
import entity.Music;

import java.sql.*;

public class AlbumDaoImpl implements AlbumDao{
    public Album getAlbumById (Integer id){
        Album album = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            String sql="";
            conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","123456");
            sql="select * from album where album_id=?";
            ps=conn.prepareStatement(sql);
            ps.setInt(1,id);
            rs=ps.executeQuery();

            while (rs.next()) {
                album=new Album();
                album.setAlbumId(rs.getInt("album_id"));
                album.setAlbumName(rs.getString("album_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if(rs!=null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(ps!=null)
            {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    return album;
    }
}
