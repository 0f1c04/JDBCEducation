package day22.view;

import day22.model.EmpVO;

import java.util.List;

public class EmpView {
    public static void display(List<EmpVO> empList) {
        System.out.println("------- 직원 정보 출력 -------");
        for (EmpVO emp : empList) {
            System.out.println("**** " + emp.getFirst_name() + " ****");
            System.out.println(emp.toString());
        }
    }

    public static void display(EmpVO emp) {
        System.out.println("------- 직원 정보 출력 -------");
        System.out.println("**** " + emp.getFirst_name() + " ****");
        System.out.println(emp.toString());
    }

    public static void display(String message) {
        System.out.println("------- 알림 -------");
        System.out.printf(message);
    }
}
