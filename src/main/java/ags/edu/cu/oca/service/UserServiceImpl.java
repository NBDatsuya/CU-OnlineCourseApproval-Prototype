package ags.edu.cu.oca.service;

import ags.edu.cu.oca.dao.UserDao;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.util.Global;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDao dao = Global.userDao;

    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) instance = new UserServiceImpl();
        return instance;
    }

    @Override
    public List<User> queryAll() {
        List<User> users = dao.getAll();
        if (users.isEmpty()) users.add(new User());
        return users;
    }

    @Override
    public User queryByID(String userID) {
        User user = new User();
        user.setRealName("无");
        if (!userID.equals("0")) {
            List<User> users = dao.query(
                    "select * from users where u_id='" + userID + "'");

            if (users.size() != 0) user = users.get(0);
        }

        return user;
    }

    public User queryByID(String userID, boolean mulQuery) {
        if (!mulQuery)
            return queryByID(userID);
        else {
            User user = new User();
            user.setRealName("无");
            if (!userID.equals("0")) {
                user = dao.justQuery(
                        "select * from users where u_id='" + userID + "'");
            }
            return user;
        }
    }

    @Override
    public User verifyLogin(User req) {
        String sql = "select * from users where u_name='" +
                req.getUName() + "' and password='" +
                req.getPassword() + "'";

        List<User> users = dao.query(sql);

        return users.size() == 0 ? new User() : users.get(0);
    }
}
