package project.controller;

import day23.view.EmpView;
import project.model.DAO;
import project.model.VO;
import project.view.View;

import java.util.List;
import java.util.Scanner;

public class Controller {
    static final int MAINMENU = 0;
    static final int USERMENU = 1;
    static final int ADMINMENU = 2;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            View.printBanner();
            View.printMenu(MAINMENU); //Main Menu

            int menu = sc.nextInt();

            if (menu == USERMENU) {
                View.printMenu(USERMENU); //User Menu
                String search = null;
                switch (sc.nextInt()) {
                    case 1:
                        // 전체 음식점 조회
                        resSelectAll();
                        break;
                    case 2:
                        // 주변 음식점 조회
                        System.out.println("주변 음식점 조회\n현재 계신 곳이 어디인가요? (ex: 금천구) > ");
                        search = sc.next();
                        selectNear(search); //조회
                        break;
                    case 3:
                        System.out.println("특정 음식점 조회\n어느 음식점을 알려드릴까요? (ex: 텐마루) >");
                        search = sc.next();
                        selectRes(search);
                        break;
                    case 4:
                        // 리뷰 메뉴
                        System.out.println("리뷰메뉴\n" +
                                "1. 리뷰 볼래\n" +
                                "2. 리뷰 쓸래\n" +
                                "3. 프로그램 종료\n> ");
                        switch (sc.nextInt()) {
                            case 1: // 리뷰 조회
                                System.out.println("어떤 음식점의 리뷰를 알려드릴까요? (ex: 음식점이름)");
                                String resName = sc.next();
                                selectReview(resName);
                                break;
                            case 2: // 리뷰 작성
                                System.out.println("음식점 고유번호를 입력해주세요\n> ");
                                int resNo = sc.nextInt(); // 이름으로 바꾸든 생각 ㄱ
                                System.out.println("맛은 어땠나요? 평점을 알려주세요 (5점 만점)\n> ");
                                float revGpa = sc.nextFloat();
                                System.out.println("간단한 한줄평을 알려주세요 (100자 이내)\n> ");
                                String revContents = sc.next();
                                insertReview(resNo, revGpa, revContents);
                                break;
                            case 3:
                                System.out.println("프로그램을 종료합니다");
                                return;
                        }
                        break;
                    default:
                        System.out.print("없는 메뉴번호입니다");
                        break;
                }
            } else if (menu == ADMINMENU) { //관리자 메뉴
                View.printMenu(ADMINMENU); //User Menu
                switch (sc.nextInt()) {
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    default:
                        System.out.print("없는 메뉴번호입니다");
                }
            } else {
                break;
            }
        }
        System.out.println("프로그램을 종료합니다");
    }

    // 리뷰 조회
    private static void selectReview(String resName) {
        DAO dao = new DAO();
        List<VO> revList = dao.selectReview(resName);
        View.revDisplay(revList);
    }

    // 리뷰 작성
    private static void insertReview(int resNo, float revGpa, String revContents) {
        DAO dao = new DAO();
        int result = dao.insertReview(resNo, revGpa, revContents);
        EmpView.display(result > 0 ? "리뷰 입력성공\n" : "리뷰 입력실패\n");
    }

    // 특정 음식점 조회
    private static void selectRes(String search) {
        DAO dao = new DAO();
        VO res = dao.selectRes(search);
        View.resDisplay(res);
    }

    // 주변 음식점 조회
    private static void selectNear(String search) {
        DAO dao = new DAO();
        List<VO> resList = dao.selectNear(search);
        View.resDisplay(resList);
    }

    // 전체 음식점 조회
    private static void resSelectAll() {
        DAO dao = new DAO();
        List<VO> resList = dao.selectAll();
        View.resDisplay(resList);
    }

    //
}