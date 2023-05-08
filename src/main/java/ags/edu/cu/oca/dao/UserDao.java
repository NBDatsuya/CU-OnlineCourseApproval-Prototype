package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.User;

import java.util.List;

public interface UserDao {
    List<User> getAll();

    List<User> query(String sql);
    User justQuery(String sql);
}
