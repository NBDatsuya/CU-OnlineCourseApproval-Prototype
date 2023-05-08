package ags.edu.cu.oca.service;

import ags.edu.cu.oca.bean.User;

import java.util.List;

public interface UserService {
    List<User> queryAll();
    User verifyLogin(User req);
    User queryByID(String userID);
    User queryByID(String userID, boolean mulQuery);
}
