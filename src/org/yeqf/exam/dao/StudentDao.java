package org.yeqf.exam.dao;

import org.yeqf.exam.entity.Student;
import org.yeqf.exam.exception.StudentNotExistException;

/**
 * Created by yeqf on 2015/9/21.
 */
public interface StudentDao {

    void add(Student student);

    void find(int id);

    void delete(String name) throws StudentNotExistException;

    void update(Student student);

}
