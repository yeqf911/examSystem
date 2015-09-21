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


    /**
     * @param name
     * @throws StudentNotExistException 自定义的异常，用于处理找不到学生的情况
     */
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

            /**
             * 这里要注意了，自定义的异常应先捕获，再抛出去，以便于跟Excption区分开来
             */
        } catch (StudentNotExistException e) {
            throw e;
        } catch (Exception e) {
            /**
             * 此处是把Checked Exception(编译时异常)转化成Checked Exception(运行时异常)。
             * 上层捕获到此异常可以自己选择处理或者不处理
             */
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Student student) {

    }
}
