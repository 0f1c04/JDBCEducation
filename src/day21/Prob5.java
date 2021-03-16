package day21;

import util.DBUtil;

import java.sql.*;
import java.util.Scanner;

public class Prob5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회하고자하는 직원의 이름 일부를 입력하세요>> ");
        String name = sc.next();
        new Prob5().printEmployee(name);
    }

    public void printEmployee(String name) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select EMPLOYEE_ID, FIRST_NAME, SALARY\n" +
                "from EMPLOYEES\n" +
                "where FIRST_NAME like '%'||?||'%'";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, name);
            rs = st.executeQuery();
            while (rs.next()) {
                int empID = rs.getInt("EMPLOYEE_ID");
                String empName = rs.getString("FIRST_NAME");
                int empSal = rs.getInt("SALARY");
                System.out.println(empID + "\t" + empName + "\t\t" + empSal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            DBUtil.dbClose(rs, st, conn);
        }
    }
}
