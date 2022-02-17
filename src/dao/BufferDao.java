package dao;

import java.util.List;

import entity.Buffer;

public interface BufferDao {

    /**
     * 管理员查询所有申请信息
     */
    public List<Buffer> getAllBuffer();

    /**
     * 根据查询条件查询项目信息
     */
    public List<Buffer> selectBuffer(String sql, String[] param);

    /**
     * 更新项目信息
     */
    public int updateBuffer(String sql, String[] param);
}
