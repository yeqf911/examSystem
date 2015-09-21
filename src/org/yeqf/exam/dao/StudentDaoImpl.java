package org.yeqf.exam.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.yeqf.exam.entity.Student;
import org.yeqf.exam.exception.StudentNotExistException;
import org.yeqf.exam.util.XmlUtil;

/**
 * Created by yeqf on 2015/9/21.
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public void add(Student student) {
        try {
            Document document = XmlUtil.getDocument();
            /**
             * 新建student节点
             */
            Element s = document.createElement("student");
            s.setAttribute("id", student.getId());
            s.setAttribute("examId", student.getExamId());

            /**
             * 新建student下的子标签
             */
            Element name = document.createElement("name");
            Element location = document.createElement("location");
            Element grade = document.createElement("grade");
            /**
             * 为子标签赋值
             */
            name.setTextContent(student.getName());
            location.setTextContent(student.getLocation());
            grade.setTextContent(student.getGrade() + "");

            /**
             * 挂载子标签
             */
            s.appendChild(name);
            s.appendChild(location);
            s.appendChild(grade);

            //找到需要挂载的节点
            Element parent = (Element) document.getElementsByTagName("exam").item(0);
            parent.appendChild(s);
            //把内存更新到文件
            XmlUtil.writeToXml(document);

        } catch (Exception e) {
            throw  new RuntimeException(e);
        }

    }

    @Override
    public void find(int id) {

    }

    @Override
    public void delete(String name) throws StudentNotExistException {
        try {
            Document document = XmlUtil.getDocument();
            NodeList list = document.getElementsByTagName("name");
            for (int i = 0; i < list.getLength(); i++) {
                if (list.item(i).getTextContent().equals(name)) {
                    list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
                    XmlUtil.writeToXml(document);
                    return;
                }
            }
            throw new StudentNotExistException("student " + name + " 不存在");

        } catch (StudentNotExistException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {

    }
}
