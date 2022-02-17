package service;

import java.util.List;
import entity.*;

public interface TeacherService {
    /**
     * 老师登录
     */
    public Teacher login();

    public List<Project> getAllProject();

    public List<Project> searchMyProject(String t_id);

    public List<Project> searchAppliedProject(String t_id);

    public List<Project> searchFinishedProject(String t_id);

    public int agreeAppliedProject(String p_id);

    public int gradeForProject(String p_id, String grade);

}
