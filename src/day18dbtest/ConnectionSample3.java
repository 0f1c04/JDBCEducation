package day18dbtest;

import util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionSample3 {
    public static void main(String[] args) {
        String sql = "select * from departments";
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
                int departmentID = rs.getInt("department_id");
                String departmentName = rs.getString("department_name");
                System.out.println(departmentID + "\t" + departmentName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

    }
}
