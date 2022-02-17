package dao;

import java.util.List;

import entity.Teacher;

public interface TeacherDao {

    /**
     * 教务查询所有老师信息
     */
    public List<Teacher> getAllTeacher();

    /**
     * 根据查询条件查询老师信息
     */
    public List<Teacher> selectTeacher(String sql, String[] param);

    /**
     * 更新老师信息（用来添加项目编号）
     */
    public int updateTeacher(String sql, String[] param);

}
