package com.dao;

import com.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * 教师操作的DAO层
 */
public class TeacherDao {
    /**
     * 判断教师登陆是否成功，成功后进入教师主界面
     * @param teachId
     * @param pwd
     * @return
     */
    public  boolean teacherLogIn(String teachId, String pwd) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select teachId,pwd from teachInfo");
            rs = ps.executeQuery();
            String user = null;
            String realPwd = null;
            while (rs.next()) {
                user = rs.getString("teachId");
                realPwd = rs.getString("pwd");
                if (user.equals(teachId) && realPwd.equals(pwd)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("sql语句输入错误");
            JDBCUtil.close(rs, ps, conn);
            return false;
        }
    }

    /**
     * 根据教师的用户名查询教师的科目
     * @param teachId
     * @return
     */
    public String searchSubject(String teachId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String subject = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select subject from teachInfo where teachId = ?");
            ps.setString(1,teachId);
            rs = ps.executeQuery();
            while (rs.next()){
                subject = rs.getString("subject");
            }
            return subject;
        } catch (SQLException e) {
            e.printStackTrace();
            return subject;
        }
    }

    /**
     *  获取到教师的学科，并对学生的该学科打分
     * @param subject
     * @param list
     * @return
     */
    public boolean inputGradeDao(String subject,List<Student> list){
        Connection conn = null;
        PreparedStatement ps = null;
        String sub = toSub(subject);

        try {
            conn = JDBCUtil.getConnection();
            Iterator it = list.iterator();
            Scanner scan = new Scanner(System.in);
            int i = 1;
            while (it.hasNext()){
                Student stu = (Student) it.next();
                System.out.println("-----请录入第" + i++ +"名学生的"+ subject +"成绩-----");
                System.out.println(stu.getUser() + "  " + stu.getName() + "   " + stu.getMajor() + "   "
                             + stu.getClassroom() +"班   " + stu .getSerial()+"号  ");
                System.out.println("请输入他的成绩:");
                Integer grade = scan.nextInt();
                ps = conn.prepareStatement("update stugrade set  "+ sub +" = ? where userId = ?" );
                ps.setInt(1,grade);
                ps.setString(2,stu.getUser());
                ps.executeUpdate();
            }
            System.out.println("-----录入完成！-----");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断学生成绩表是否为空
     * @return
     */
    public boolean isEmpty(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select count(*) from stugrade");
            rs = ps.executeQuery();
            Integer count = null;
            while (rs.next()){
                count = rs.getInt(1);
            }
            if (count == 0){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 初始化学生成绩表(只加入学生的学号)
     */
    public void initStuGrade() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            List<Student> list = new ManageDao().printAllStudentDao("*");
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Student stu = (Student) it.next();

                ps = conn.prepareStatement("select userId from stugrade");
                ResultSet rs2 = ps.executeQuery();
                boolean flag = true;
                while (rs2.next()) {
                    String sourceId = rs2.getString("userId");
                    if (sourceId.equals(stu.getUser())){
                        flag = false;
                    }
                }
                if (flag) {
                    ps = conn.prepareStatement("insert into stugrade (userId) values (?)");
                    ps.setString(1, stu.getUser());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新学生信息表
     */
    public void updateGrade(){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer math = null;
        Integer english = null;
        Integer cProgram = null;
        try {
            conn = JDBCUtil.getConnection();

                ps = conn.prepareStatement("select userId,math,english,cProgram from stugrade");
                rs = ps.executeQuery();
                while (rs.next()) {
                    String userId = rs.getString("userId");
                    math = rs.getInt("math");
                    english = rs.getInt("english");
                    cProgram = rs.getInt("cProgram");
                    Integer sum = add(math, english, cProgram);
                    Integer point = sum / 60;
                    ps = conn.prepareStatement("update stugrade set gSum = ?,point = ? where userId = ?");
                    ps.setInt(1, sum);
                    ps.setInt(2, point);
                    ps.setString(3,userId);
                    ps.executeUpdate();
                }

            } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    /**
     * 求学生的各门科目的总分
     * @param a
     * @param b
     * @param c
     * @return
     */
    public Integer add(Integer a, Integer b, Integer c){
        Integer sum = 0;
        if (a != null)
            sum += a;
        if (b != null)
            sum += b;
        if (c != null)
            sum += c;
        return sum;
    }

    /**
     * 修改指定学生的成绩
     * @param userId
     */
    public void alterGradeDao(String userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select * from stugrade where userId = ?");
            ps.setString(1,userId);
            rs = ps.executeQuery();
            Integer math = null;
            Integer english = null;
            Integer cProgram = null;
            while (rs.next()){
                math = rs.getInt("math");
                english = rs.getInt("english");
                cProgram = rs.getInt("cProgram");
            }
            System.out.println("当前学号：" + userId + "  高数:"+math+"  英语:"+english+"  C语言:"+cProgram);
            System.out.println("-----请选择要修改的成绩-----");
            System.out.println("1.高数    2.英语    3.C语言");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();
            String sub = null;
            switch(choice){
                case 1:sub = "math";break;
                case 2:sub = "english";break;
                case 3:sub = "cProgram";break;
            }
            ps = conn.prepareStatement("update stugrade set "+ sub +"  = ? where userId = ?");
            Integer grade = null;
            System.out.print("请输入修改后的成绩:");
            grade = scan.nextInt();
            ps.setInt(1,grade);
            ps.setString(2,userId);
            ps.executeUpdate();
            System.out.println("修改成功");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    /**
     * 根据学号查找学生信息
     * @param userId
     */
    public void searchStudentDao(String userId){
        searchStuDao(userId);
        searchGraDao(userId);
    }

    /**
     * 查询学生的基本信息
     * @param userId
     */
    public void searchGraDao(String userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Integer math = null;
        Integer english = null;
        Integer cProgram = null;
        Integer sum = null;
        Integer point = null;
        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select * from stugrade where userId = ?");
            ps.setString(1,userId);
            rs = ps.executeQuery();
            while (rs.next()){
                math = rs.getInt("math");
                english = rs.getInt("english");
                cProgram = rs.getInt("cProgram");
                sum = rs.getInt("gSum");
                point = rs.getInt("point");
            }
            System.out.println("---------成绩信息---------");
            System.out.println("高数: " + math +"  英语: "+ english + "C语言: " + cProgram);
            System.out.println("总分: " + sum +"  绩点: " + point);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("查询失败,请检查学号是否正确");
        }
    }

    /**
     * 查询学生的成绩信息
     * @param userId
     */
    public void searchStuDao(String userId){
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String name = null;
        String major = null;
        Integer classroom = null;
        Integer serial = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select stuName,major,classroom,serial from stuifo where userId = ?");
            ps.setString(1,userId);
            rs = ps.executeQuery();
            while (rs.next()){
                major = rs.getString("major");
                name = rs.getString("stuName");
                classroom = rs.getInt("classroom");
                serial = rs.getInt("serial");
            }
            System.out.println("--------该学生信息--------");
            System.out.println("学号: " + userId +"   姓名: "+ name);
            System.out.println("专业: " + major +"   班级: "+ classroom + "  班内序号: " + serial);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 对该科目学生的成绩进行排名
     * @param teachId
     */
    public void gradeSortDao(String teachId){
        String subject = searchSubject(teachId);
        String sub = toSub(subject);

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement("select userId," + sub + " from stugrade order by "
                    + sub+" desc");
            rs = ps.executeQuery();
            System.out.println("-------" + subject + "排名------");
            int i = 1;
            while (rs.next()){
                String user = rs.getString("userId");
                Integer grade = rs.getInt(sub);
                String name = null;
                String major = null;
                Integer classroom = null;
                Integer serial = null;

                ps = conn.prepareStatement("select stuName,major,classroom,serial from stuifo where userId = ?");
                ps.setString(1,user);
                ResultSet rs2 = ps.executeQuery();
                while (rs2.next()){
                    name = rs2.getString("stuName");
                    major = rs2.getString("major");
                    serial = rs2.getInt("serial");
                    classroom = rs2.getInt("classroom");

                }
                System.out.println("--------第" + i++ + "名-----");
                System.out.println(name + ":  " + grade + "  " + major + "  " + classroom + "  " + serial);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String toSub(String subject){
        String sub = null;
        switch (subject){
            case "高数": sub = "math"; break;
            case "英语": sub = "english";  break;
            case "C语言": sub = "cProgram";break;
        }
        return sub;
    }

}

