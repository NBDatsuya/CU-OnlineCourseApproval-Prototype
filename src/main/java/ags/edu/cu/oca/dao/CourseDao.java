package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.Course;

import java.util.List;

public interface CourseDao {
    List<Course> getAll();
    List<Course> query(String sql);

    Course justQuery(String sql);

    boolean add (Course course);
    boolean update (Course course);
    boolean del (Course course);
}
