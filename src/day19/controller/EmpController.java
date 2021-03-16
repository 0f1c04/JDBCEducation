package day19.controller;

import day19.model.EmpDAO;
import day19.model.EmpVO;
import day19.view.EmpView;

import java.sql.Date;
import java.util.List;

public class EmpController {
    public static void main(String[] args) {
        //1. 모든 직원 조회
        //method1();

        //2. 특정 직원 조회 (직원번호)
        //method2(100);

        //3. 특정 부서 모든 직원 조회
        //method3(80);

        //4. 특정 직책을 가진 모든 직원 조회
        //method4("IT_PROG");

        //5. 특정 급여 이상인 직원 모드 조회
        //method5(5000, 10000);

        //6. 특정 입사일로 모드 직원 입사일 조회
        //method6("2004/01/01", "2005/12/31");
        //method6_2("2004-01-01", "2005-12-31");

        //7. 이름에 특정문자가 들어간 직원 조회
        //method7("c");

        //8. 여러 조건으로 조회 (ex: 부서(department_id), 직책(job_id), 급여(salary), 입사일(hire_date))
        method8(60, "IT_PROG", 2000, 15000, "2005-01-01");
    }

    private static void method8(int deptId, String jobId, int salaryMin, int salaryMax, String date) {
        //8. 여러 조건으로 조회 (ex: 부서(department_id), 직책(job_id), 급여(salary), 입사일(hire_date))
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectByConditions(deptId, jobId, salaryMin, salaryMax, Date.valueOf(date));
        EmpView.display(empList);
    }

    private static void method7(String ch) {
        //7. 이름에 특정문자가 들어간 직원 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectByChar(ch);
        EmpView.display(empList);
    }

    private static void method6_2(String sDate, String dDate) {
        //6-2. 특정 입사일로 모드 직원 입사일 조회
        EmpDAO dao = new EmpDAO();

//        Date d1 = new Date(Integer.parseInt(sDate.substring(0, 4))-1900,0,1);
//        Date d2 = new Date(Integer.parseInt(dDate.substring(0, 4))-1900,11,31);
        Date d1 = Date.valueOf(sDate);
        Date d2 = Date.valueOf(dDate);

        List<EmpVO> empList = dao.selectByHireDate2(d1, d2);
        EmpView.display(empList);
    }

    private static void method6(String sDate, String dDate) {
        //6-1. 특정 입사일로 모드 직원 입사일 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectByHireDate(sDate, dDate);
        EmpView.display(empList);
    }

    private static void method5(int min, int max) {
        //5. 특정 급여 이상인 직원 모드 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectBySalary(min, max);
        EmpView.display(empList);
    }

    private static void method4(String jobId) {
        //4. 특정 직책을 가진 모든 직원 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectByJobId(jobId);
        EmpView.display(empList);
    }

    private static void method3(int deptId) {
        //3. 특정 부서 모든 직원 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectByDept(deptId);
        EmpView.display(empList);
    }

    private static void method2(int empId) {
        //2. 특정 직원 조회 (직원번호)
        EmpDAO dao = new EmpDAO();
        EmpVO emp = dao.selectById(empId);
        EmpView.display(emp);
    }

    private static void method1() {
        //1. 모든 직원 조회
        EmpDAO dao = new EmpDAO();
        List<EmpVO> empList = dao.selectAll();
        EmpView.display(empList);
    }
}
