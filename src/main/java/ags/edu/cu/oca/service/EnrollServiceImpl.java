package ags.edu.cu.oca.service;

import ags.edu.cu.oca.dao.EnrollDao;
import ags.edu.cu.oca.bean.Course;
import ags.edu.cu.oca.bean.Enrollment;
import ags.edu.cu.oca.bean.User;

import java.util.List;

import static ags.edu.cu.oca.util.Global.enrollDao;

public class EnrollServiceImpl implements EnrollService {
    private static EnrollService instance;
    private static final EnrollDao dao = enrollDao;

    public static EnrollService getInstance() {
        if (instance == null) instance = new EnrollServiceImpl();
        return instance;
    }

    @Override
    public List<Enrollment> queryAll() {
        return null;
    }

    @Override
    public List<Enrollment> queryLikeField(String field, String value) {
        if(field.endsWith("name")){
            return enrollDao.query(
                    "select * from enroll natural join course " +
                            "where " + field + " like '%" + value + "%'");
        }else return enrollDao.query(
                "select * from enroll " +
                        "where " + field + " like '%" + value + "%'");
    }

    @Override
    public int queryNotDeletable(Course course) {
        return dao.query(
                "select * from enroll where " +
                        "c_id='" + course.getUuid() + "'").size();
    }

    @Override
    public int queryNotUnenrollable(Course course, User user) {
        return dao.query(
                "select * from enroll where " +
                        "c_id='" + course.getUuid() + "' " +
                        "and u_applicant='" + user.getUuid() + "' " +
                        "and e_status<>'submitted'").size();
    }

    @Override
    public Enrollment queryByID(String enrollID) {
        List<Enrollment> enrollments = dao.query(
                "select * from enroll where " +
                        "e_id='" + enrollID + "'");
        return enrollments.size() == 0 ? new Enrollment() : enrollments.get(0);
    }

    @Override
    public List<Enrollment> queryUnExamined() {
        return dao.query("select * from enroll where u_examine='0' and " +
                "e_status='submitted' order by a_date");
    }

    @Override
    public List<Enrollment> queryByApplicant(User user) {
        return dao.query("select * from enroll where u_applicant='"
                + user.getUuid() + "' order by a_date");
    }

    @Override
    public List<Enrollment> queryByExamineUser(User user) {
        return dao.query("select * from enroll where u_examine='"
                + user.getUuid() + "'" +
                "and e_status<>'submitted' order by e_date");
    }

    @Override
    public boolean submit(Enrollment enrollment) {
        return dao.add(enrollment);
    }

    @Override
    public boolean statusChange(Enrollment enrollment) {
        return dao.update(enrollment);
    }

    @Override
    public boolean withdraw(Enrollment enrollment) {
        List<Enrollment> fullEnroll = dao.query(
                "select * from enroll where c_id='" + enrollment.getCourse().getUuid()
                        + "' and u_applicant='" + enrollment.getApplicant().getUuid() + "'");
        if (fullEnroll.size() == 0) return false;
        else
            return dao.delete(fullEnroll.get(0));
    }
}
