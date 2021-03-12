package day18dbtest;
import util.DBUtil;

import java.sql.*;
import java.util.Date;
import java.util.Scanner;
//[LAB] 급여를 가변으로 입력받아 입력값 이상의 급여를 받는 직원들 조회
//직원이름, 급여, 입사일, 부서번호를 출력한다.
public class LAB1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        String sql = "select LAST_NAME, SALARY, HIRE_DATE, DEPARTMENT_ID " +
                "from EMPLOYEES where SALARY >= " + input + "";

        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                String name = rs.getString("last_name");
                int salary = rs.getInt("salary");
                Date hireDate = rs.getDate("hire_date");
                int departmentID = rs.getInt("department_id");
                System.out.println(name + "\t" + salary + "\t" + hireDate + "\t" + departmentID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }
        sc.close();
    }
}
