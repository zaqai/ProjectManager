package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.TeacherDao;
import entity.Teacher;

public class TeacherDaoImpl extends BaseDao implements TeacherDao {

    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询结果集

    @Override
    public List<Teacher> getAllTeacher() {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            String preparedSql = "select * from  teacher ";
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            rs = pstmt.executeQuery(); // 执行SQL语句

            while (rs.next()) {

                Teacher teacher = new Teacher();
                teacher.setId(rs.getString(2));
                teacher.setName(rs.getString(1));
                teacher.setPasswd(rs.getString(3));

                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return teacherList;
    }

    @Override
    public List<Teacher> selectTeacher(String sql, String[] param) {
        List<Teacher> teacherList = new ArrayList<Teacher>();
        try {
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
            if (param != null) {
                for (int i = 0; i < param.length; i++) {
                    pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
                }
            }
            rs = pstmt.executeQuery(); // 执行SQL语句
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getString(2));
                teacher.setName(rs.getString(1));
                teacher.setPasswd(rs.getString(3));

                teacherList.add(teacher);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return teacherList;
    }

    @Override
    public int updateTeacher(String sql, String[] param) {
        int count = super.executeSQL(sql, param);
        return count;

    }

}
