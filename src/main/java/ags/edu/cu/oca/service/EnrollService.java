package ags.edu.cu.oca.service;

import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.bean.User;

import java.util.List;

public interface EnrollService {
    List<Enrollment> queryAll();

    List<Enrollment> queryLikeField(String field, String value);

    int queryNotDeletable(Course course);
    int queryNotUnenrollable(Course course, User user);
    Enrollment queryByID(String enrollID);
    List<Enrollment> queryUnExamined();
    List<Enrollment> queryByApplicant(User applicant);
    List<Enrollment> queryByExamineUser(User user);

    boolean submit(Enrollment enrollment);
    boolean statusChange(Enrollment enrollment);

    boolean withdraw(Enrollment enrollment);
}
