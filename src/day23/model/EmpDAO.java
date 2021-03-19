package day23.model;

import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//DAO(Data Access Object)
public class EmpDAO {
    //CRUD(Create:insert, Read:select, U:Update, D:Delete)
    //Delete
    public int deleteEmp(int empId) {
        int result = 0;
        String sql = "delete\n" +
                "from EMPLOYEES\n" +
                "where EMPLOYEE_ID = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            conn.setAutoCommit(true); // auto commit
            st.setInt(1, empId);
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, st, conn);
        }
        return result;
    }

    //Update
    public int updateEmp(EmpVO emp) {
        int result = 0;
        String sql = "update EMPLOYEES\n" +
                "set FIRST_NAME     = ?,\n" +
                "    LAST_NAME      = ?,\n" +
                "    EMAIL          = ?,\n" +
                "    PHONE_NUMBER   = ?,\n" +
                "    HIRE_DATE      = ?,\n" +
                "    JOB_ID         = ?,\n" +
                "    SALARY         = ?,\n" +
                "    COMMISSION_PCT = ?,\n" +
                "    MANAGER_ID     = ?,\n" +
                "    DEPARTMENT_ID  = ?\n" +
                "where EMPLOYEE_ID = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(sql);
            conn.setAutoCommit(true); // auto commit

            st.setString(1, emp.getFirst_name());
            st.setString(2, emp.getLast_name());
            st.setString(3, emp.getEmail());
            st.setString(4, emp.getPhone_number());
            st.setDate(5, emp.getHire_date());
            st.setString(6, emp.getJob_id());
            st.setInt(7, emp.getSalary());
            st.setDouble(8, emp.getCommission_pct());
            st.setInt(9, emp.getManager_id());
            st.setInt(10, emp.getDepartment_id());
            st.setInt(11, emp.getEmployee_id());

            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, st, conn);
        }
        return result;
    }

    //Create:insert
    //사용자가 웹을 통해 회원가입 -> day24.controller -> DAO -> DB
    public int insertEmp(EmpVO emp) {
        String sql = "insert into EMPLOYEES values(?,?,?,?,?,?,?,?,?,?,?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        int result = 0;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, emp.getEmployee_id());
            st.setString(2, emp.getFirst_name());
            st.setString(3, emp.getLast_name());
            st.setString(4, emp.getEmail());
            st.setString(5, emp.getPhone_number());
            st.setDate(6, emp.getHire_date());
            st.setString(7, emp.getJob_id());
            st.setInt(8, emp.getSalary());
            st.setDouble(9, emp.getCommission_pct());
            st.setInt(10, emp.getManager_id());
            st.setInt(11, emp.getDepartment_id());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, st, conn);
        }
        return result;
    }

    //1. 모든 직원 조회
    public List<EmpVO> selectAll() {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees";

        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }
        return empList;
    }

    //2. 기본키(Primary key) 직원 번호(employee_id)로 특정 직원 상세조회
    //기본키는 null 불가, 필수컬럼, 중복없음
    public EmpVO selectById(int empId) {
        EmpVO emp = null;
        String sql = "select * from employees where EMPLOYEE_ID = ?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, empId);
            rs = pst.executeQuery();
            while (rs.next()) {
                emp = makeEmp(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return emp;
    }

    //3. 특정 부서(department_id)로 조회
    public List<EmpVO> selectByDept(int deptId) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees where DEPARTMENT_ID = ?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, deptId);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //4. 특정 직책(job_id)로 조회
    public List<EmpVO> selectByJobId(String jobId) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees where JOB_ID = ?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, jobId);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //5. 특정 급여(salary)이상인 직원 조회
    public List<EmpVO> selectBySalary(int min, int max) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from EMPLOYEES where SALARY >= ? and SALARY <= ?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, min);
            pst.setInt(2, max);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //6-1. 입사일 조회
    public List<EmpVO> selectByHireDate(String sDate, String dDate) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees where HIRE_DATE between ? and ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, sDate);
            pst.setString(2, dDate);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //6-2. 입사일 조회
    public List<EmpVO> selectByHireDate2(Date sDate, Date dDate) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees where HIRE_DATE between ? and ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setDate(1, sDate);
            pst.setDate(2, dDate);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //7. 이름에 특정문자가 들어간 직원 조회
    public List<EmpVO> selectByChar(String ch) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from employees where last_name like '%' || ? || '%'";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, ch);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //8. 여러 조건으로 조회 (ex: 부서(department_id), 직책(job_id), 급여(salary), 입사일(hire_date))
    public List<EmpVO> selectByConditions(int deptId, String jobId, int salaryMin, int salaryMax, Date hireDate) {
        List<EmpVO> empList = new ArrayList<>();
        String sql = "select * from EMPLOYEES where (DEPARTMENT_ID = ?) " +
                "and (JOB_ID = ?) " +
                "and (SALARY between ? and ?) " +
                "and (HIRE_DATE between ? and add_months(?, 24))";

        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, deptId);
            pst.setString(2, jobId);
            pst.setInt(3, salaryMin);
            pst.setInt(4, salaryMax);
            pst.setDate(5, hireDate);
            pst.setDate(6, hireDate);
            rs = pst.executeQuery();
            while (rs.next()) {
                empList.add(makeEmp(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, pst, conn);
        }
        return empList;
    }

    //9.
    private EmpVO makeEmp(ResultSet rs) throws SQLException {
        EmpVO emp = new EmpVO();
        emp.setCommission_pct(rs.getDouble("commission_pct"));
        emp.setDepartment_id(rs.getInt("department_id"));
        emp.setEmail(rs.getString("email"));
        emp.setEmployee_id(rs.getInt("employee_id"));
        emp.setFirst_name(rs.getString("first_name"));
        emp.setHire_date(rs.getDate("hire_date"));
        emp.setJob_id(rs.getString("job_id"));
        emp.setLast_name(rs.getString("last_name"));
        emp.setManager_id(rs.getInt("manager_id"));
        emp.setPhone_number(rs.getString("phone_number"));
        emp.setSalary(rs.getInt("salary"));
        return emp;
    }
}
