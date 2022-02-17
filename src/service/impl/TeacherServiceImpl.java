package service.impl;

import java.util.*;

import entity.*;

import service.*;
import dao.*;
import dao.impl.*;

public class TeacherServiceImpl implements TeacherService {

    /**
     * 学生登录 根据id和密码查询，成功的话只会返回一行数据，所以取返回的第0个，即stu.get(0)
     */
    @Override
    public Teacher login() {
        Scanner input = new Scanner(System.in);

        System.out.println("请先登录，请您输入id：");
        String stuId = input.nextLine().trim();
        System.out.println("请您输入密码：");
        String stuPasswd = input.nextLine().trim();
        TeacherDao stuDao = new TeacherDaoImpl();
        String sql = "select * from teacher where id=? and passwd=?";
        String[] param = { stuId, stuPasswd };
        List<Teacher> tea = stuDao.selectTeacher(sql, param);
        if (tea.size() != 0) {
            System.out.println("-------恭喜您成功登录-------");
            System.out.println("-------您的基本信息：-------");
            System.out.println("名字：" + tea.get(0).getName());
            System.out.println("id：" + tea.get(0).getId());
            return tea.get(0);
        }
        return null;

    }

    @Override
    public List<Project> getAllProject() {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        Project project;

        List<Project> projectlist = pdi.getAllProject();
        for (int i = 0; i < projectlist.size(); i++) {
            project = projectlist.get(i);
            System.out.println((i + 1) + "\t" + project.getId() + "\t" + project.getName() + "\t" + project.getT_id()
                    + "\t" + project.getSchedule() + "\t" + project.getH_id() + "\t" + project.getS_num() + "\t"
                    + project.getStep() + "\t" + project.getGrade());
        }
        return projectlist;
    }

    /**
     * 同意该老师指导的项目的申请信息
     * 
     * @param p_id
     * @param id
     * @return
     */
    @Override
    public int agreeAppliedProject(String p_id) {

        ProjectDaoImpl pdi = new ProjectDaoImpl();
        String[] param1 = { p_id };

        int count = pdi.updateProject("update project set schedule='同意申请'  where id=? ", param1);

        return count;
    }

    /**
     * 查询该老师指导的项目
     * 
     * @return
     */
    @Override
    public List<Project> searchMyProject(String t_id) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;
        String[] param = { t_id };
        projectList = pdi.selectProject("select * from project where t_id=?", param);
        for (int i = 0; i < projectList.size(); i++) {

            System.out.println((i + 1) + "\t" + projectList.get(i).getId() + "\t" + projectList.get(i).getName() + "\t"
                    + projectList.get(i).getT_id() + "\t" + projectList.get(i).getSchedule() + "\t"
                    + projectList.get(i).getH_id() + "\t" + projectList.get(i).getS_num() + "\t"
                    + projectList.get(i).getStep() + "\t" + projectList.get(i).getGrade());
        }

        return projectList;
    }

    @Override
    public List<Project> searchAppliedProject(String t_id) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;
        String[] param = { t_id };
        projectList = pdi.selectProject("select * from project where t_id=? and schedule='申请中'", param);
        for (int i = 0; i < projectList.size(); i++) {

            System.out.println((i + 1) + "\t" + projectList.get(i).getId() + "\t" + projectList.get(i).getName() + "\t"
                    + projectList.get(i).getT_id() + "\t" + projectList.get(i).getSchedule() + "\t"
                    + projectList.get(i).getH_id() + "\t" + projectList.get(i).getS_num() + "\t"
                    + projectList.get(i).getStep() + "\t" + projectList.get(i).getGrade());
        }

        return projectList;
    }

    @Override
    public List<Project> searchFinishedProject(String t_id) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;
        String[] param = { t_id };
        projectList = pdi.selectProject("select * from project where t_id=? and step='结题'", param);
        for (int i = 0; i < projectList.size(); i++) {

            System.out.println((i + 1) + "\t" + projectList.get(i).getId() + "\t" + projectList.get(i).getName() + "\t"
                    + projectList.get(i).getT_id() + "\t" + projectList.get(i).getSchedule() + "\t"
                    + projectList.get(i).getH_id() + "\t" + projectList.get(i).getS_num() + "\t"
                    + projectList.get(i).getStep() + "\t" + projectList.get(i).getGrade());
        }

        return projectList;
    }

    @Override
    public int gradeForProject(String p_id, String grade) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        String[] param = { grade, p_id };

        int count = pdi.updateProject("update project set grade=?  where id=? ", param);

        return count;

    }

}
