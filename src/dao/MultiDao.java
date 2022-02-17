package dao;

import java.util.List;

import entity.Multi;

public interface MultiDao {

    public List<Multi> selectMulti(String sql, String[] param);

}
