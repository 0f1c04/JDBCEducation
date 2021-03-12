package day19dbtest;

import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBC_LAB2 {
    static Connection conn;
    static PreparedStatement pst;
    static ResultSet rs;
    static String sql;

    //CONTROLLER
    public static void main(String[] args) throws SQLException {
//        int[] departmentID = {30, 50, 80};
//        int minSalary = 3000;
//        int maxSalary = 17000;
//        method1(departmentID, minSalary, maxSalary);

        List<EmpData> empList = method2("r");
        myPrint(empList);
    }

    //VIEW
    private static void myPrint(List<EmpData> empList) {
        for(EmpData emp:empList) {
            System.out.println(emp);
        }
    }

    //MODEL - DAO
    private static List<EmpData> method2(String ch) throws SQLException {
        List<EmpData> empList = new ArrayList<>();

        sql = "select FIRST_NAME, LAST_NAME,\n" +
                "       SALARY,\n" +
                "       HIRE_DATE\n" +
                "from EMPLOYEES\n" +
                "where LAST_NAME like '__'||?||'%'";

        conn = DBUtil.getConnection();
        pst = conn.prepareStatement(sql);
        pst.setString(1, ch);
        rs = pst.executeQuery();

        while (rs.next()) {
            empList.add(new EmpData(
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getInt("salary"),
                    rs.getDate("hire_date")));
        }
        DBUtil.dbClose(rs, pst, conn);
        return empList;
    }

    private static void method1(int[] departmentID, int minSalary, int maxSalary) throws SQLException {
        String dept_where = " and department_id in (";
        for (Integer i : departmentID) {
            dept_where += "?,";
        }
        dept_where = dept_where.substring(0, dept_where.length() - 1) + ")";

        sql = "select *\n" +
                "from EMPLOYEES\n" +
                "where HIRE_DATE between '2001/01/01' and '2009/12/31'\n" +
                dept_where + '\n' +
                "  and SALARY between ? and ?\n" +
                "  and COMMISSION_PCT is not null\n" +
                "order by HIRE_DATE asc, SALARY desc";

        conn = DBUtil.getConnection();
        pst = conn.prepareStatement(sql);

        int cnt = 1;
        for (Integer i : departmentID) {
            pst.setInt(cnt++, i);
        }
        pst.setInt(cnt++, minSalary);
        pst.setInt(cnt++, maxSalary);

        rs = pst.executeQuery();
        while (rs.next()) {
            int empid = rs.getInt(1);
            String fname = rs.getString("first_name");
            Date date = rs.getDate("hire_date");
            int salary = rs.getInt("salary");
            int departID = rs.getInt("department_id");
            double comm = rs.getDouble("commission_pct");
            System.out.println(empid + "\t" + fname + "\t" + date + "\t" + salary + "\t" + departID + "\t" + comm);
        }
        DBUtil.dbClose(rs, pst, conn);
    }
}
