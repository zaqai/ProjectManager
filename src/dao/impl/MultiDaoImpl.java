package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.BaseDao;
import dao.MultiDao;
import entity.Multi;

/**
 * 鲜花数据库操作类
 */
public class MultiDaoImpl extends BaseDao implements MultiDao {

    private Connection conn = null; // 保存数据库连接

    private PreparedStatement pstmt = null; // 用于执行SQL语句

    private ResultSet rs = null; // 用户保存查询结果集

    @Override
    public List<Multi> selectMulti(String sql, String[] param) {
        List<Multi> multiList = new ArrayList<Multi>();
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
                Multi multi = new Multi();
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    multi.setS(rs.getString(i + 1));

                }
                multiList.add(multi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            super.closeAll(conn, pstmt, rs);
        }
        return multiList;
    }

}
