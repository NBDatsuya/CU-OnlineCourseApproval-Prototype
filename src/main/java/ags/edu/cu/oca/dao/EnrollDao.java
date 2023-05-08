package ags.edu.cu.oca.dao;

import ags.edu.cu.oca.bean.Enrollment;

import java.util.List;

public interface EnrollDao {
    List<Enrollment> getAll();

    List<Enrollment> query(String sql);

    boolean add(Enrollment enrollment);

    boolean update(Enrollment enrollment);

    boolean delete(Enrollment enrollment);
}
