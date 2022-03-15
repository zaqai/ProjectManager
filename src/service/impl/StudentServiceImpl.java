package service.impl;

import java.util.*;

import entity.Project;
import entity.Student;
import service.*;
import dao.*;
import dao.impl.*;

public class StudentServiceImpl implements StudentService {

    /**
     * 学生登录 根据id和密码查询，成功的话只会返回一行数据，所以取返回的第0个，即stu.get(0)
     */
    @Override
    public Student login() {
        Scanner input = new Scanner(System.in);

        System.out.println("请先登录，请您输入id：");
        String stuId = input.nextLine().trim();
        System.out.println("请您输入密码：");
        String stuPasswd = input.nextLine().trim();
        StudentDao stuDao = new StudentDaoImpl();
        String sql = "select * from student where id=? and passwd=?";
        String[] param = { stuId, stuPasswd };
        List<Student> stu = stuDao.selectStudent(sql, param);
        if (stu.size() != 0) {
            System.out.println("-------恭喜您成功登录-------");
            System.out.println("-------您的基本信息：-------");
            System.out.println("名字：" + stu.get(0).getName());
            System.out.println("id：" + stu.get(0).getId());
            return stu.get(0);
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
                    + "\t" + project.getSchedule() + "\t" + project.getStep() + "\t" + project.getGrade());
        }
        return null;
    }

    @Override
    public Project selectMyProject(String s) {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;
        String[] param = { s };
        projectList = pdi.selectProject("select * from project where id=?", param);
        if (projectList.size() == 0) {
            System.out.println("您还没有已选项目");
            return null;
        }
        Project project = projectList.get(0);
        System.out.println(project.getId() + "\t" + project.getName() + "\t" + project.getT_id() + "\t"
                + project.getSchedule() + "\t" + project.getH_id() + "\t" + project.getS_num() + "\t"
                + project.getStep() + "\t" + project.getGrade());
        return project;
    }

    @Override
    public int applyProject(String p_id, String id) {

        ProjectDaoImpl pdi = new ProjectDaoImpl();
        String[] param1 = { p_id };
        int num = pdi.selectProject("select * from project where id=?", param1).get(0).getS_num();

        String[] param = { id, p_id };

        if (num == 0) {

            pdi.updateProject("update project set schedule='申请中'  where id=? ", param1);
            pdi.updateProject("update project set h_id=?  where id=? ", param);
            pdi.updateProject("update project set s_num=\"" + ++num + "\"  where id=? ", param1);

        } else {
            pdi.updateProject("update project set schedule='申请中'     where id=? ", param1);
            pdi.updateProject("update project set s_num=\"" + ++num + "\"    where id=? ", param1);
        }

        StudentDaoImpl sdi = new StudentDaoImpl();
        String[] param2 = { p_id, id };
        sdi.updateStudent("update student set p_id=? where id=? ", param2);

        return 0;
    }

    @Override
    public List<Project> searchAvailableProject() {
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        List<Project> projectList;

        projectList = pdi.selectProject("select * from project where s_num<2", null);
        for (int i = 0; i < projectList.size(); i++) {

            System.out.println((i + 1) + "\t" + projectList.get(i).getId() + "\t" + projectList.get(i).getName() + "\t"
                    + projectList.get(i).getT_id() + "\t" + projectList.get(i).getSchedule() + "\t"
                    + projectList.get(i).getH_id() + "\t" + projectList.get(i).getS_num() + "\t"
                    + projectList.get(i).getStep() + "\t" + projectList.get(i).getGrade());
        }
        return projectList;
    }

    @Override
    public boolean hasProject(String s) {
        StudentDaoImpl sdi = new StudentDaoImpl();
        List<Student> studentList;
        String[] param = { s };
        studentList = sdi.selectStudent("select * from student where id=? and p_id is not null", param);
        if (studentList.size() == 0) {

            return false;
        }

        return true;
    }

    @Override
    public int pushProject(String p_id, String s_id) {

        StudentServiceImpl stuImpl = new StudentServiceImpl();
        Project myPro = stuImpl.selectMyProject(p_id);
        String schedule = myPro.getSchedule();
        String step = myPro.getStep();
        if (!schedule.equals("申请通过")) {
            System.out.println("管理员通过申请才能推进项目");
            return 0;
        }
        ProjectDaoImpl pdi = new ProjectDaoImpl();
        String[] param = { p_id };
        if (step == null) {
            pdi.updateProject("update project set step='开题'  where id=? ", param);

        } else if (step.equals("中期")) {
            pdi.updateProject("update project set step='结题'  where id=? ", param);

        } else if (step.equals("结题")) {
            System.out.println("当前已为结题状态，不能再推进项目");

        } else {
            pdi.updateProject("update project set step='中期'  where id=? ", param);

        }

        return 0;
    }

    @Override
    public Student update(String s_id) {

        StudentDao stuDao = new StudentDaoImpl();
        String sql = "select * from student where id=?";
        String[] param = { s_id };
        List<Student> stu = stuDao.selectStudent(sql, param);

        return stu.get(0);

    }

}
