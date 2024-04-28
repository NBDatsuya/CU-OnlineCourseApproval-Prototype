package ags.edu.cu.oca.util;

import ags.edu.cu.oca.dao.*;
import ags.edu.cu.oca.service.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Global {
    public final static UserDao userDao = UserDaoImpl.getInstance();
    public final static CourseDao courseDao = CourseDaoImpl.getInstance();
    public final static EnrollDao enrollDao = EnrollDaoImpl.getInstance();


    public final static UserService userService = UserServiceImpl.getInstance();

    public final static CourseService courseService = CourseServiceImpl.getInstance();
    public final static EnrollService enrollService = EnrollServiceImpl.getInstance();



    private static Connection connection;
    public static Connection getConnection(){
        try{
            if (connection == null || connection.isClosed()) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                String url = "jdbc:mysql://localhost:3306/crtu_enroll_prototype?characterEncoding=utf-8&serverTimezone=GMT%2B8";
                connection = DriverManager.getConnection(
                        url,
                        "root",
                        "root");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
    public static void closeConnection(){
        try{
            if(!connection.isClosed()){
                connection.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
