package service;

import java.util.List;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import entity.*;

public interface AdministerService {
    /**
     * 管理员登录
     */
    public Administer login();

    public List<Project> getAllProject();

    /**
     * 选择老师已经同意的项目
     * 
     * @param sql
     * @param param
     * @return
     */
    public List<Project> selectAgreedProject();

    /**
     * 通过老师已经同意的项目
     * 
     * @param p_id
     * @return
     */
    public int passProject(String p_id);

    public int addProject() throws MySQLIntegrityConstraintViolationException;

    public int delProject(String p_id);

}
