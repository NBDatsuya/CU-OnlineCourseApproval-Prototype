package ags.edu.cu.oca.service;

import ags.edu.cu.oca.dao.CourseDao;
import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.bean.User;
import ags.edu.cu.oca.util.Global;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private static CourseService instance;
    CourseDao courseDao = Global.courseDao;

    public static CourseService getInstance() {
        if (instance == null) instance = new CourseServiceImpl();
        return instance;
    }

    @Override
    public List<Course> queryAll() {
        List<Course> courses = courseDao.getAll();
        if (courses == null) courses = new ArrayList<>();
        return courses;
    }

    @Override
    public List<Course> queryUnSelected(User user) {
        String sql = "select * from course where " +
                "course.c_id not in " +
                "(select enroll.c_id from enroll where u_applicant='" + user.getUuid() + "')";

        return courseDao.query(sql);
    }

    @Override
    public List<Course> querySelected(User user) {
        String sql = "select * from course where " +
                "course.c_id in " +
                "(select enroll.c_id from enroll where u_applicant='" + user.getUuid() + "')";

        return courseDao.query(sql);
    }

    @Override
    public Course queryByID(String courseID) {
        List<Course> courses =
                courseDao.query(
                        "select * from course where c_id='" + courseID + "'");
        return courses.size() == 0 ? new Course() : courses.get(0);
    }

    @Override
    public Course queryByID(String courseID, boolean mulQuery) {
        if (mulQuery) {
            return courseDao.justQuery(
                    "select * from course where c_id='" + courseID + "'");
        } else
            return queryByID(courseID);
    }

    @Override
    public List<Course> queryLikeField(String field, String value) {
        return courseDao.query(
                "select * from course " +
                        "where " + field + " like '%" + value + "%'");
    }

    @Override
    public boolean add(Course course) {
        return courseDao.add(course);
    }

    @Override
    public boolean edit(Course course) {
        return courseDao.update(course);
    }

    @Override
    public boolean del(Course course) {
        return courseDao.del(course);
    }
}
