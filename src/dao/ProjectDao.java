package dao;

import java.util.List;

import entity.Project;

public interface ProjectDao {

    /**
     * 查询所有项目信息
     */
    public List<Project> getAllProject();

    /**
     * 根据查询条件查询项目信息
     */
    public List<Project> selectProject(String sql, String[] param);

    /**
     * 更新项目信息
     */
    public int updateProject(String sql, String[] param);
}
