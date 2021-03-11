package util;

import java.sql.*;

public class DBUtil {
    //1.DB 연결
    public static Connection getConnection() {
        String driverName = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String userID = "hr", password = "hr";
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, userID, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    //2.자원 반납
    public static void dbClose(ResultSet rs, Statement st, Connection conn) {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
