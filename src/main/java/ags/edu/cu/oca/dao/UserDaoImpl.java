package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.util.Global;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private static UserDao instance;

    public static UserDao getInstance() {
        if (instance == null) instance = new UserDaoImpl();
        return instance;
    }

    public List<User> getAll() {
        String sql = "select * from users";
        return query(sql);
    }

    @Override
    public List<User> query(String sql) {
        ArrayList<User> list = new ArrayList<>();
        try {
            Statement statement = Global.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            list = new ArrayList<>();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
                list.add(user);
            }

            resultSet.close();
            statement.close();
            Global.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public User justQuery(String sql) {
        User user = null;
        try {
            Statement statement = Global.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                user = new User(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                );
            }

            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
