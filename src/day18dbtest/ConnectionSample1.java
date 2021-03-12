package day18dbtest;

import java.sql.*;

public class ConnectionSample1 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //1.Oracle Diver 준비
        String driverName = "oracle.jdbc.driver.OracleDriver";
        Class.forName(driverName);
        System.out.println("1.driver load 성공");

        //2.DB연결: Connection
        Connection conn;
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "hr", password = "hr";
        conn = DriverManager.getConnection(url, userid, password);
        System.out.println("2.Connection 성공");

        //3.SQL문 실행
        String sql = "select first_name, last_name, salary\n" +
                "from EMPLOYEES\n" +
                "where EMPLOYEE_ID = 100";

        Statement st = conn.createStatement(); //Statement: DB와 JAVA의 소통길
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {
            String fname = rs.getString(1);
            String lname = rs.getString(2);
            int sal = rs.getInt(3);
            System.out.println(fname + '\t' + lname + '\t' + sal);
        }

        //4.자원반납
        rs.close();
        st.close();
        conn.close();
    }
}
