package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.ProjectDao;
import entity.Project;

public class ProjectDaoImpl extends BaseDao implements ProjectDao {

    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询结果集

    @Override
    public List<Project> getAllProject() {
        List<Project> projectList = new ArrayList<Project>();
        try {
            String preparedSql = "select * from  project ";
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            rs = pstmt.executeQuery(); // 执行SQL语句

            while (rs.next()) {

                Project project = new Project();
                project.setId(rs.getString(1));
                project.setName(rs.getString(2));
                project.setT_id(rs.getString(3));
                project.setSchedule(rs.getString(4));
                project.setH_id(rs.getString(5));
                project.setS_num(rs.getInt(6));
                project.setStep(rs.getString(7));
                project.setGrade(rs.getInt(8));

                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return projectList;
    }

    @Override
    public List<Project> selectProject(String sql, String[] param) {
        List<Project> projectList = new ArrayList<Project>();
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
                Project project = new Project();
                project.setId(rs.getString(1));
                project.setName(rs.getString(2));
                project.setT_id(rs.getString(3));
                project.setSchedule(rs.getString(4));
                project.setH_id(rs.getString(5));
                project.setS_num(rs.getInt(6));
                project.setStep(rs.getString(7));
                project.setGrade(rs.getInt(8));
                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return projectList;
    }

    @Override
    public int updateProject(String sql, String[] param) {
        int count = super.executeSQL(sql, param);
        return count;

    }

}
