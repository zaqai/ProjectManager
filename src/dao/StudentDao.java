package dao;

import java.util.List;

import entity.Student;

public interface StudentDao {

    /**
     * 查询所有学生信息
     */
    public List<Student> getAllStudent();

    /**
     * 根据查询条件查询学生信息
     */
    public List<Student> selectStudent(String sql, String[] param);

    /**
     * 更新学生信息（用来添加项目编号）
     */
    public int updateStudent(String sql, String[] param);

}
