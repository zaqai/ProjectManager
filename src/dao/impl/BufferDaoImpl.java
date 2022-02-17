package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.BufferDao;
import entity.Buffer;

/**
 * 鲜花数据库操作类
 */
public class BufferDaoImpl extends BaseDao implements BufferDao {

    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询结果集

    /**
     * 查询所有鲜花
     */
    @Override
    public List<Buffer> getAllBuffer() {
        List<Buffer> bufferList = new ArrayList<Buffer>();
        try {
            String preparedSql = "select * from  buffer ";
            conn = getConn(); // 得到数据库连接
            pstmt = conn.prepareStatement(preparedSql); // 得到PreparedStatement对象
            rs = pstmt.executeQuery(); // 执行SQL语句

            while (rs.next()) {

                Buffer buffer = new Buffer();
                buffer.setS_id(rs.getString(1));
                buffer.setP_id(rs.getString(2));
                buffer.setSchedule(rs.getString(3));

                bufferList.add(buffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return bufferList;
    }

    /**
     * 根据参数查询相应符合条件鲜花
     */
    @Override
    public List<Buffer> selectBuffer(String sql, String[] param) {
        List<Buffer> bufferList = new ArrayList<Buffer>();
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
                Buffer buffer = new Buffer();
                buffer.setS_id(rs.getString(1));
                buffer.setP_id(rs.getString(2));
                buffer.setSchedule(rs.getString(3));

                bufferList.add(buffer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return bufferList;
    }

    @Override
    public int updateBuffer(String sql, String[] param) {
        int count = super.executeSQL(sql, param);
        return count;

    }

}
