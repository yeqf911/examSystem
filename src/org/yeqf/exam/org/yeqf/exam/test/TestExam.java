package org.yeqf.exam.org.yeqf.exam.test;

import org.junit.Test;
import org.yeqf.exam.dao.StudentDao;
import org.yeqf.exam.dao.StudentDaoImpl;
import org.yeqf.exam.entity.Student;
import org.yeqf.exam.exception.StudentNotExistException;

/**
 * Created by yeqf on 2015/9/21.
 */
public class TestExam {

    @Test
    public void testAdd() {
        StudentDao dao = new StudentDaoImpl();
        Student student = new Student();
        student.setId("20131418");
        student.setExamId("52131418");
        student.setName("yeqianfeng");
        student.setLocation("安徽");
        student.setGrade(91);
        dao.add(student);
    }

    @Test
    public void testDel() {
        StudentDao dao = new StudentDaoImpl();
        String name = "xiaohdsaong";
        try {
            dao.delete(name);
        } catch (StudentNotExistException e) {
            e.printStackTrace();
        }
    }
}
