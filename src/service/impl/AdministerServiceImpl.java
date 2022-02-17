package service.impl;

import java.sql.*;
import java.util.*;

import entity.*;

import service.*;
import dao.*;
import dao.impl.*;

public class AdministerServiceImpl implements AdministerService {
    Scanner input = new Scanner(System.in);

    /**
     * 学生登录 根据id和密码查询，成功的话只会返回一行数据，所以取返回的第0个，即stu.get(0)
     */
    @Override
    public Administer login() {

        System.out.println("请先登录，请您输入id：");
        String stuId = input.nextLine().trim();
        System.out.println("请您输入密码：");
        String stuPasswd = input.nextLine().trim();
        AdministerDao admDao = new AdministerDaoImpl();
        String sql = "select * from administer where id=? and passwd=?";
        String[] param = { stuId, stuPasswd };
        Administer adm = admDao.selectAdminister(sql, param);
        if (adm != null) {
            System.out.println("-------恭喜您成功登录-------");
            System.out.println("-------您的基本信息：-------");

            System.out.println("id：" + adm.getId());
            return adm;
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

    @Override
    public List<Project> selectAgreedProject() {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;
        String[] param = { "同意申请" };
        projectList = pdi.selectProject("select * from project where schedule=?", param);
        for (int i = 0; i < projectList.size(); i++) {

            System.out.println((i + 1) + "\t" + projectList.get(i).getId() + "\t" + projectList.get(i).getName() + "\t"
                    + projectList.get(i).getT_id() + "\t" + projectList.get(i).getSchedule() + "\t"
                    + projectList.get(i).getH_id() + "\t" + projectList.get(i).getS_num() + "\t"
                    + projectList.get(i).getStep() + "\t" + projectList.get(i).getGrade());
        }

        return projectList;
    }

    @Override
    public int passProject(String p_id) {
        String[] param = { p_id };
        ProjectDaoImpl pdi = new ProjectDaoImpl();

        int count = pdi.updateProject("update project set schedule='申请通过' where id=? ", param);

        return count;
    }

    @Override
    public int addProject() {
        System.out.print("请输入项目id：");
        String id = input.next();
        System.out.print("请输入项目名称：");
        String name = input.next();
        System.out.print("请输入项目指导老师编号：");
        String t_id = input.next();
        String[] param = { id, name, t_id };
        int count = -1;
        try {
            ProjectDaoImpl pdi = new ProjectDaoImpl();

            count = pdi.updateProject("INSERT INTO project(id,name,t_id) VALUES(?,?,?)", param);

        } catch (Exception e) {

            e.printStackTrace();

        }

        return count;
    }

    @Override
    public int delProject(String p_id) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        String[] param = { p_id };

        int count = pdi.updateProject("delete from project where id=?", param);

        return count;

    }

}
