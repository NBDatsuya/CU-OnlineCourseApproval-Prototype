package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.Enrollment;

import ags.edu.cu.oca.service.CourseService;

import ags.edu.cu.oca.service.UserService;
import ags.edu.cu.oca.util.Global;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnrollDaoImpl implements EnrollDao {
    private static EnrollDao instance;

    public static EnrollDao getInstance() {
        if (instance == null) instance = new EnrollDaoImpl();
        return instance;
    }

    private final UserService userService = Global.userService;
    private final CourseService courseService = Global.courseService;

    @Override
    public List<Enrollment> getAll() {
        String sql = "select * from enroll";
        return query(sql);
    }

    @Override
    public boolean add(Enrollment enrollment) {
        boolean flag = false;
        try {
            String sql = "insert into enroll (e_id, c_id, u_applicant," +
                    " u_examine, e_status, a_date, e_date) " +
                    "values (?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement =
                    Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, enrollment.getUuid());
            preparedStatement.setString(2, enrollment.getCourse().getUuid());
            preparedStatement.setString(3, enrollment.getApplicant().getUuid());
            preparedStatement.setString(4, enrollment.getExamineUser() == null ?
                    "0" : enrollment.getExamineUser().getUuid());

            preparedStatement.setString(5, enrollment.getStatus());
            preparedStatement.setString(6, enrollment.getADate());
            preparedStatement.setString(7, enrollment.getEDate() == null ? "" :
                    enrollment.getEDate());

            flag = preparedStatement.execute();
            System.out.println(flag);
            preparedStatement.close();
            Global.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(Enrollment enrollment) {
        boolean flag = false;
        try {
            String sql = "update enroll set " +
                    "u_examine=?, e_status=?, e_date=? where e_id=?";
            PreparedStatement preparedStatement =
                    Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, enrollment.getExamineUser().getUuid());
            preparedStatement.setString(2, enrollment.getStatus());
            preparedStatement.setString(3, enrollment.getEDate());
            preparedStatement.setString(4, enrollment.getUuid());

            flag = preparedStatement.execute();

            // 7. 释放资源
            preparedStatement.close();
            Global.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean delete(Enrollment enrollment) {
        boolean flag = false;
        try {
            String sql = "delete from enroll where e_id=?";
            PreparedStatement preparedStatement = Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, enrollment.getUuid());

            flag = preparedStatement.execute();

            preparedStatement.close();
            Global.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ArrayList<Enrollment> query(String sql) {

        ArrayList<Enrollment> list = new ArrayList<>();
        try {
            Statement statement = Global.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Enrollment enrollment = new Enrollment(
                        resultSet.getString("e_id"),
                        Global.courseService.queryByID(resultSet.getString("c_id"),true),
                        Global.userService.queryByID(resultSet.getString("u_applicant"), true),
                        Global.userService.queryByID(resultSet.getString("u_examine"),true),
                        resultSet.getString("e_status"),
                        resultSet.getString("a_date"),
                        resultSet.getString("e_date")
                );
                list.add(enrollment);
            }

            resultSet.close();
            statement.close();
            Global.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
