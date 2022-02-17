package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.plaf.synth.SynthUI;
import java.io.*;
import dao.*;
import dao.impl.*;
import entity.*;
import service.*;
import service.impl.*;

/**
 * 入口类
 * 
 */
public class Main {
	/**
	 * 系统入口方法
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main.startFlowerShop();
	}

	private static void startFlowerShop() {
		Scanner input = new Scanner(System.in);
		System.out.println("----------大学生创新项目管理系统-----------");
		System.out.println("请选择登录角色  1.学生  2.老师  3.管理员");
		int role = input.nextInt();
		switch (role) {
			case 1:
				stuLogin();
				break;

			case 2:
				teaLogin();
				break;

			case 3:
				admLogin();
				break;

			default:
				break;
		}
		// admLogin();
		// stuLogin();
	}

	private static Student stuLogin() {
		Scanner input = new Scanner(System.in);
		StudentServiceImpl stuImpl = new StudentServiceImpl();
		Student stu = stuImpl.login();
		boolean reg = true;
		while (reg) {
			if (null == stu) {
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				stu = stuImpl.login();
				reg = true;
			} else {
				boolean exit = false;
				while (!exit) {
					reg = false;
					System.out.println("------您可以输入序号以选择功能---------");
					System.out.println("1. 查询所有项目");
					System.out.println("2. 查询本人所选项目信息");
					System.out.println("3. 申报项目");
					System.out.println("4. 推进项目");

					System.out.println("0. 退出系统");
					System.out.println("-----------------------------------------");

					boolean type = true;

					exit = StudentCirculate(exit, type, input, stu);
				}

			}
		}
		return stu;
	}

	private static boolean StudentCirculate(boolean exit, boolean type, Scanner input, Student stu) {

		while (type) {
			int num = input.nextInt();
			StudentServiceImpl stuImpl = new StudentServiceImpl();

			if (1 == num) {
				stuImpl.getAllProject();
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (2 == num) {
				stu = stuImpl.update(stu.getId());

				stuImpl.selectMyProject(stu.getP_id());
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (3 == num) {
				if (stuImpl.hasProject(stu.getId())) {
					System.out.println("您已有申报项目，不能再次申报项目");
					return false;
				}
				System.out.println("请输入项目id以申报项目");
				stuImpl.searchAvailableProject();
				String p_id = input.next();
				stuImpl.applyProject(p_id, stu.getId());

				System.out.println("申报成功");

				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (4 == num) {
				stu = stuImpl.update(stu.getId());

				if (!stuImpl.hasProject(stu.getId())) {
					System.out.println("请先申报项目");
					type = false;
				} else {
					// String p_id = stuImpl.selectMyProject(stu.getId()).getId();

					stuImpl.pushProject(stu.getP_id(), stu.getId());
					type = false;
				}
				System.out.println("按回车继续...");

				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (0 == num) {
				exit = true;
				type = false;
			} else {
				System.out.println("输入有误,请重新输入");
				type = true;
			}
		}
		return exit;
	}

	/**
	 * teacher
	 */

	private static Teacher teaLogin() {
		Scanner input = new Scanner(System.in);
		TeacherServiceImpl teaImpl = new TeacherServiceImpl();
		Teacher tea = teaImpl.login();
		boolean reg = true;

		while (reg) {
			if (null == tea) {
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				tea = teaImpl.login();
				reg = true;
			} else {
				boolean exit = false;
				while (!exit) {
					reg = false;
					System.out.println("------您可以输入序号以选择功能---------");
					System.out.println("1. 查询所有项目");
					System.out.println("2. 查询您所指导的项目信息");
					System.out.println("3. 同意项目申请");
					System.out.println("4. 为结题项目打分");
					System.out.println("0. 退出系统");
					System.out.println("-----------------------------------------");

					boolean type = true;

					exit = TeacherCirculate(exit, type, input, tea);
				}

			}
		}
		return tea;
	}

	private static boolean TeacherCirculate(boolean exit, boolean type, Scanner input, Teacher tea) {
		while (type) {
			int num = input.nextInt();
			TeacherServiceImpl teaImpl = new TeacherServiceImpl();

			if (1 == num) {
				teaImpl.getAllProject();
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (2 == num) {

				teaImpl.searchMyProject(tea.getId());
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (3 == num) {
				teaImpl.searchAppliedProject(tea.getId());
				System.out.println("请输入项目id以同意项目");

				String p_id = input.next();
				int count = teaImpl.agreeAppliedProject(p_id);

				if (count == 1) {
					System.out.println("同意成功");
				}

				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (4 == num) {
				if (teaImpl.searchFinishedProject(tea.getId()).size() == 0) {
					System.out.println("您指导的项目还没有已结题的");
					type = false;
					return false;
				}
				System.out.println("请输入要打分的项目id");

				String p_id = input.next();
				System.out.println("请输入分数");

				String grade = input.next();

				int count = teaImpl.gradeForProject(p_id, grade);

				if (count == 1) {
					System.out.println("打分成功");
				}

				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (0 == num) {
				exit = true;
				type = false;
			} else {
				System.out.println("输入有误,请重新输入");
				type = true;
			}
		}
		return exit;
	}

	private static Administer admLogin() {
		Scanner input = new Scanner(System.in);
		AdministerServiceImpl admImpl = new AdministerServiceImpl();
		Administer adm = admImpl.login();
		boolean reg = true;
		while (reg) {
			if (null == adm) {
				System.out.println("登录失败，请确认您的用户名和密码后重新输入");
				adm = admImpl.login();
				reg = true;
			} else {

				boolean exit = false;
				while (!exit) {
					reg = false;
					System.out.println("------您可以输入序号以选择功能---------");
					System.out.println("1. 查询所有项目");
					System.out.println("2. 查询已同意申请的项目信息");
					System.out.println("3. 通过项目申请");
					System.out.println("4. 删除项目");
					System.out.println("5. 新增项目");
					System.out.println("0. 退出系统");
					System.out.println("-----------------------------------------");

					boolean type = true;

					exit = AdministerCirculate(exit, type, input, adm);
				}
			}
		}
		return adm;
	}

	private static boolean AdministerCirculate(boolean exit, boolean type, Scanner input, Administer adm) {
		while (type) {
			int num = input.nextInt();
			AdministerServiceImpl admImpl = new AdministerServiceImpl();

			if (1 == num) {
				admImpl.getAllProject();
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (2 == num) {

				admImpl.selectAgreedProject();
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (3 == num) {
				if (admImpl.selectAgreedProject().size() == 0) {
					System.out.println("没有老师已同意的项目");
				} else {
					System.out.println("请输入项目id以通过项目");

					String p_id = input.next();
					int count = admImpl.passProject(p_id);

					if (count == 1) {
						System.out.println("通过项目成功");
					}

				}

				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else if (4 == num) {
				admImpl.getAllProject();
				System.out.println("请输入项目id以删除项目");
				String p_id = input.next();
				int count = admImpl.delProject(p_id);

				if (count == 1) {
					System.out.println("删除项目成功");
				}
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (5 == num) {
				int count = admImpl.addProject();
				if (count == 1) {
					System.out.println("新增项目成功");

				}
				type = false;
				System.out.println("按回车继续...");
				try {
					new BufferedReader(new InputStreamReader(System.in)).readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} else if (0 == num) {
				exit = true;
				type = false;
			} else {
				System.out.println("输入有误,请重新输入");
				type = true;
			}
		}
		return exit;
	}

}