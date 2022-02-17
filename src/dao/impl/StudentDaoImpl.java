package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.StudentDao;
import entity.Student;

/**
 * 鲜花数据库操作类
 */
public class StudentDaoImpl extends BaseDao implements StudentDao {

	private Connection conn = null; // 保存数据库连接

	private PreparedStatement pstmt = null; // 用于执行SQL语句

	private ResultSet rs = null; // 用户保存查询结果集

	/**
	 * 查询所有鲜花
	 */
	@Override
	public List<Student> getAllStudent() {
		List<Student> studentList = new ArrayList<Student>();
		try {
			String preparedSql = "select * from  student ";
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
			rs = pstmt.executeQuery(); // 执行SQL语句

			while (rs.next()) {

				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPasswd(rs.getString(3));
				student.setP_id(rs.getString(4));

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return studentList;
	}

	/**
	 * 根据参数查询相应符合条件鲜花
	 */
	@Override
	public List<Student> selectStudent(String sql, String[] param) {
		List<Student> studentList = new ArrayList<Student>();
		try {
			conn = getConn(); // 得到数据库连接
			pstmt = conn.prepareStatement(sql); // 得到PreparedStatement对象
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pstmt.setString(i + 1, param[i]); // 为预编译sql设置参数
				}
			}
			rs = pstmt.executeQuery(); // 执行SQL语句
			while (rs.next()) {
				Student student = new Student();
				student.setId(rs.getString(1));
				student.setName(rs.getString(2));
				student.setPasswd(rs.getString(3));
				student.setP_id(rs.getString(4));

				studentList.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			super.closeAll(conn, pstmt, rs);
		}
		return studentList;
	}

	@Override
	public int updateStudent(String sql, String[] param) {
		int count = super.executeSQL(sql, param);
		return count;

	}

}
