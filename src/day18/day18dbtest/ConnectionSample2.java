package day18.day18dbtest;

import java.sql.*;

public class ConnectionSample2 {
    public static void main(String[] args) {
        //JDBC(JAVA Database Connection)
        String drvicerName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@192.168.0.172:1521:XE";
        String userID = "hr", password = "hr";
        String sql = "select * from employees";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName(drvicerName);
            System.out.println("1.class(driver) load 성공");
            conn = DriverManager.getConnection(url, userID, password);
            System.out.println("2.Connection 성공");
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("3.SQL문 실행 성공");
            while (rs.next()) {
                String fname = rs.getString("first_name");
                int salary = rs.getInt("salary");
                Date hireDay = rs.getDate("hire_date");
                System.out.println(fname + "\t" + salary + "\t" + hireDay);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (st != null) st.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
