package day19.day19dbtest;

import util.DBUtil;

import java.sql.*;

public class JDBC_LAB1 {
    static Connection conn;
    static Statement st;
    static ResultSet rs;

    public static void main(String[] args) {
        method3("steven");
    }

    private static void method3(String fname) {
        String sql = "select salary, hire_date, first_name || ' ' || last_name 이름\n" +
                "from employees\n" +
                "where first_name = initcap(?) ";
        PreparedStatement pst = null;
        try {
            conn = DBUtil.getConnection();
            pst = conn.prepareStatement(sql);
            pst.setString(1, fname);
            rs = pst.executeQuery();
            while (rs.next()) {
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");
                String firstName = rs.getString("이름");
                System.out.println(salary + "\t" + hireDate + "\t" + firstName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
    }

    private static void method2(int did) {
        String sql = "select salary, hire_date, first_name || ' ' || last_name 이름, department_id\n" +
                "from employees\n" +
                "where department_id = ?"; //?: 바인딩변수
        conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, did);
            rs = pst.executeQuery();
            while (rs.next()) {
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");
                String name = rs.getString("이름");
                int departmentID = rs.getInt("department_id");
                System.out.println(salary + "\t" + hireDate + "\t" + name + "\t" + departmentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
    }

    private static void method1(String fname) {
        String sql = "select salary, hire_date, first_name || ' ' || last_name 이름\n" +
                "from employees\n" +
                "where first_name = initcap('" +
                fname +
                "')";
        try {
            conn = DBUtil.getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");
                String firstName = rs.getString("이름");
                System.out.println(salary + "\t" + hireDate + "\t" + firstName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }
    }
}
