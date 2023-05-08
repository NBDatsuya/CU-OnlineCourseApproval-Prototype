package ags.edu.cu.oca.service;


import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.bean.User;

import java.util.List;

public interface CourseService {
    List<Course> queryAll();
    List<Course> queryUnSelected(User user);
    List<Course> querySelected(User user);
    Course queryByID(String courseID);
    Course queryByID(String courseID, boolean mulQuery);

    List<Course> queryLikeField(String field, String value);

    public boolean add(Course course);
    public boolean edit(Course course);
    public boolean del(Course course);
}
