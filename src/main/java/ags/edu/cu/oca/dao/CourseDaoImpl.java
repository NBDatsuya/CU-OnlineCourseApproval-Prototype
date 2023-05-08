package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.util.Global;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    private static CourseDao instance;

    public static CourseDao getInstance() {
        if (instance == null) instance = new CourseDaoImpl();
        return instance;
    }

    @Override
    public List<Course> getAll() {
        return query("select * from course");
    }

    @Override
    public List<Course> query(String sql) {
        ArrayList<Course> list = new ArrayList<>();
        try {
            Statement statement = Global.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            list = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));
                list.add(course);
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
    public Course justQuery(String sql) {
        Course course = null;
        try {
            Statement statement = Global.getConnection().createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                course = new Course(resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4));

            }

            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return course;
    }

    @Override
    public boolean add(Course course) {
        boolean flag = false;
        try {
            String sql = "insert into course (c_id, c_name, instructor, schedule) " +
                    "values (?,?,?,?)";

            PreparedStatement preparedStatement =
                    Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, course.getUuid());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getInstructor());
            preparedStatement.setString(4, course.getSchedule());

            flag = preparedStatement.execute();
            preparedStatement.close();
            Global.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean update(Course course) {
        boolean flag = false;
        try {

            String sql = "update course set " +
                    "c_name=?, instructor=?, schedule=? where c_id=?";
            PreparedStatement preparedStatement =
                    Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getInstructor());
            preparedStatement.setString(3, course.getSchedule());
            preparedStatement.setString(4, course.getUuid());

            flag = preparedStatement.execute();

            preparedStatement.close();
            Global.closeConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public boolean del(Course course) {
        boolean flag = false;
        try {
            String sql = "delete from course where c_id=?";
            PreparedStatement preparedStatement = Global.getConnection().prepareStatement(sql);

            preparedStatement.setString(1, course.getUuid());

            flag = preparedStatement.execute();

            preparedStatement.close();
            Global.closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}
