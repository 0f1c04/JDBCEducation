package Java_cmd_project.controller;

import day23.view.EmpView;
import Java_cmd_project.model.DAO;
import Java_cmd_project.model.VO;
import Java_cmd_project.view.View;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Thread.sleep;

public class Controller {
    static final int MAINMENU = 0;
    static final int USERMENU = 1;
    static final int ADMINMENU = 2;

    public static void main(String[] args) throws InterruptedException, IOException {
        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            View.printBanner();
            View.printMenu(MAINMENU); //Main Menu

            int menu = sc.nextInt();
            userMode:
            while (true) {
                if (menu == USERMENU) {
                    View.printMenu(USERMENU); //User Menu
                    String search = null;
                    switch (sc.nextInt()) {
                        case 1:
                            // 전체 음식점 조회
                            selectAllres();
                            pause();
                            continue userMode;
                        case 2:
                            // 주변 음식점 조회
                            sc.nextLine();
                            System.out.println("주변 음식점 조회\n현재 계신 곳이 어디인가요? (ex: 금천구) > ");
                            search = sc.nextLine();
                            selectNear(search); //조회
                            pause();
                            continue userMode;
                        case 3:
                            // 특정 음식점 조회
                            sc.nextLine();
                            System.out.println("특정 음식점 조회\n어느 음식점을 알려드릴까요? (ex: 텐마루) >");
                            search = sc.nextLine();
                            try { //가게명이 없다면 null을 리턴하므로 NullPointerException 예외처리
                                selectRes(search);
                            } catch (NullPointerException e) {
                                System.out.println("없는 가게명이에요");
                            }
                            pause();
                            continue userMode;
                        case 4:
                            // 리뷰 메뉴
                            System.out.println("리뷰메뉴\n" +
                                    "1. 리뷰 볼래\n" +
                                    "2. 리뷰 쓸래\n" +
                                    "3. 프로그램 종료\n> ");
                            switch (sc.nextInt()) {
                                case 1: // 리뷰 조회
                                    sc.nextLine();
                                    System.out.print("어떤 음식점의 리뷰를 알려드릴까요? (ex: 음식점이름 or 모든 리뷰 보려면 enter)");
                                    String resName = sc.nextLine();
                                    selectReview(resName);
                                    pause();
                                    continue userMode;
                                case 2: // 리뷰 작성
                                    sc.nextLine();
                                    System.out.print("음식점 고유번호를 입력해주세요\n> ");
                                    int resNo = sc.nextInt(); // 이름으로 바꾸든 생각 ㄱ
                                    System.out.print("맛은 어땠나요? 평점을 알려주세요 (5점 만점)\n> ");
                                    float revGpa = sc.nextFloat();
                                    System.out.print("간단한 한줄평을 알려주세요 (100자 이내)\n> ");
                                    String revContents = sc.next();
                                    insertReview(resNo, revGpa, revContents);
                                    pause();
                                    continue userMode;
                                case 3:
                                    System.out.println("프로그램을 종료합니다");
                                    return;
                                default:
                                    System.out.println("없는 메뉴번호입니다. 처음 화면으로 돌아갑니다.");
                                    sleep(2000);
                                    continue loop;
                            }
                        case 5:
                            System.out.println("프로그램을 종료합니다");
                            return;
                        default:
                            System.out.println("없는 메뉴번호입니다. 처음 화면으로 돌아갑니다.");
                            sleep(2000);
                            continue loop;
                    }
                }
                break userMode;
            }
            if (menu == ADMINMENU) { //관리자 메뉴
                System.out.print("관리자 ID와 PW를 입력하세요(ex: admin admin): ");
                boolean loginCheck = adminLogin(sc.next(), sc.next());
                if (loginCheck) { //계정확인
                    adminMode:
                    while (true) {
                        View.printMenu(ADMINMENU); //Admin Menu
                        switch (sc.nextInt()) {
                            case 1: // 음식점 추가
                                VO res = new VO();
                                insertValue(res, false);
                                insertRestaurant(res);
                                pause();
                                continue adminMode;
                            case 2: // 음식점 삭제
                                sc.nextLine();
                                System.out.print("삭제하실 음식점 고유번호를 입력해주세요 > ");
                                int resNo = sc.nextInt();
                                deleteRestaurant(resNo);
                                pause();
                                continue adminMode;
                            case 3: // 음식점 수정
                                res = new VO();
                                insertValue(res, true);
                                updateRestaurant(res);
                                pause();
                                continue adminMode;
                            case 4: // 음식점 리뷰 삭제
                                sc.nextLine();
                                selectAllrev(); // 전체 리뷰 확인
                                System.out.print("삭제하실 리뷰번호를 입력해주세요 > ");
                                int revNo = sc.nextInt();
                                deleteReview(revNo); // 해당 리뷰 삭제
                                pause();
                                continue adminMode;
                            case 5: // 프로그램 종료
                                System.out.println("프로그램을 종료합니다");
                                return;
                            default:
                                System.out.println("없는 메뉴번호입니다. 처음 화면으로 돌아갑니다.");
                                sleep(2000);
                                continue loop;
                        }
                    }
                } else { //로그인 실패 시
                    System.out.println("계속 하시려면 Enter키를 눌러주세요.");
                    System.in.read();
                }
            }
        }
    }

    // 관리자 리뷰삭제
    private static void deleteReview(int revNo) {
        DAO dao = new DAO();
        int result = dao.deleteReview(revNo);
        View.display(result > 0 ? "해당 리뷰가 삭제되었습니다.\n" : "해당 리뷰가 삭제되지 않았습니다.\n");
    }

    // 관리자 음식점 수정
    private static void updateRestaurant(VO res) {
        DAO dao = new DAO();
        int result = dao.updateRestaurant(res);
        View.display(result > 0 ? "음식점을 수정했습니다.\n" : "음식점 수정 실패했습니다.\n");
    }

    // 관리자 음식점 삭제
    private static void deleteRestaurant(int resNo) {
        DAO dao = new DAO();
        int result = dao.deleteRestaurant(resNo);
        View.display(result > 0 ? "해당 음식점 정보가 삭제되었습니다.\n" : "해당 음식점 정보가 삭제되지 않았습니다.\n");
    }

    // 관리자 음식점 추가
    private static void insertRestaurant(VO res) {
        DAO dao = new DAO();
        int result = dao.insertRestaurant(res);
        View.display(result > 0 ? "음식점을 추가했습니다.\n" : "음식점 추가가 실패했습니다.\n");
    }

    // 관리자 접근제어
    private static boolean adminLogin(String id, String pw) {
        DAO dao = new DAO();
        boolean check = dao.adminLoginCheck(id, pw);
        if (check) {
            ClearConsole();
            System.out.println("반갑습니다 " + id + "님 관리메뉴를 선택해주세요\n");
            return true;
        } else System.out.println("로그인 실패");
        return false;
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
    private static void selectAllres() {
        DAO dao = new DAO();
        List<VO> resList = dao.selectAllres();
        View.resDisplay(resList);
    }

    // 전체 리뷰 조회
    private static void selectAllrev() {
        DAO dao = new DAO();
        List<VO> revList = dao.selectAllrev();
        View.revDisplay(revList);
    }

    private static VO insertValue(VO res, boolean check) {
        Scanner sc = new Scanner(System.in);
        if (check) { //0이면 추가, 1이면 수정
            System.out.print("음식점 고유번호를 입력하세요: "); res.setResNo(sc.nextInt());
            sc.nextLine();
        }
        System.out.print("음식점 이름을 입력하세요: "); res.setResName(sc.nextLine());
        System.out.print("음식점 주소를 입력하세요: "); res.setResAddr(sc.nextLine());
        System.out.print("음식점 소재지를 입력하세요 (ex: 서울, 경기, 강원 등등): "); res.setAreaNo(sc.nextLine());
        System.out.print("음식점 홈페이지 주소를 입력하세요 (없다면 enter): "); res.setResUrl(sc.nextLine());
        System.out.print("음식점 전화번호를 입력하세요 (없다면 enter): "); res.setResTel(sc.nextLine());
        System.out.print("음식점 대표메뉴를 입력하세요 (없다면 enter): "); res.setResBestMenu(sc.nextLine());
        if (res.getResBestMenu().length() > 0) {
            System.out.print("음식점 대표메뉴의 가격을 입력하세요 (없다면 enter): ");
            res.setMenuPrice(sc.nextInt());
        }
        return res;
    }

    private static void pause() throws IOException {
        System.out.println("계속 하시려면 Enter키를 눌러주세요.\n");
        System.in.read();
        ClearConsole();
    }

    public static void ClearConsole() {
        for (int i = 0; i < 80; i++) System.out.println("");
    }
}