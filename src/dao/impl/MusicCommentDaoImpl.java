package dao.impl;

import entity.MusicComment;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MusicCommentDaoImpl {
    public List<MusicComment> getMusicCommentsByMusicId(Integer id) {
        List<MusicComment> list = new ArrayList<>();

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;//结果集对象，用于封装数据库的查询结果的
        try {

            String sql = "";
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");
            sql = "select * from stu where music_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();//执行查询语句并返回查询结果

            while (rs.next()) {
                MusicComment mc = new MusicComment();
                mc.setMc_id(rs.getInt("mc_id"));
                mc.setMusic_id(rs.getInt("music_id"));
                mc.setComment(rs.getString("comment"));
                mc.setUser_id(rs.getInt("user_id"));
                mc.setComment_date(rs.getTimestamp("comment_date"));
                list.add(mc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return list;
    }

    public void addMusicComment(Integer mc_id, Integer musicId, Integer user_id, String comment, Timestamp comment_date) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection conn = null;
        PreparedStatement ps = null;
        try {

            String Sql = "insert into stu values(?,?,?,?,?)";
            ps = conn.prepareStatement(Sql);
            ps.setInt(1, mc_id);
            ps.setInt(2, musicId);
            ps.setString(3, comment);
            ps.setInt(4, user_id);
            ps.setTimestamp(5, comment_date);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) {
                {
                    try {
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}
