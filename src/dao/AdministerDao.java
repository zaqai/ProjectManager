package dao;

import java.util.List;

import entity.Administer;
import entity.Project;

public interface AdministerDao {

    /**
     * 根据查询条件查询管理员信息，用于登录
     */
    public Administer selectAdminister(String sql, String[] param);

}
