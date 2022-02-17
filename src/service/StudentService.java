package service;

import java.util.List;
import entity.*;

public interface StudentService {
    /**
     * 学生登录
     */
    public Student login();

    /**
     * 根据顾客标识符（id）获得到该顾客所有鲜花信息
     */
    public List<Project> getAllProject();

    public List<Project> searchAvailableProject();

    public Project selectMyProject(String s);// 查看自己的项目

    public int applyProject(String s, String id);

    public int pushProject(String p_id, String s_id);

    public boolean hasProject(String s);

    /**
     * 用于申报项目之后更新本地存储的学生信息（申报之前本地的stu.getP_id()为空，申报时只更改了数据库）
     * 
     * @return
     */
    public Student update(String s_id);

}
