package project.controller;

import project.model.DAO;
import project.model.VO;
import project.view.View;

import java.util.List;
import java.util.Scanner;

public class Controller {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("" +
                    "╔═╗┌─┐┌┬┐┌─┐┌┬┐┬ ┬┬┌┐┌┌─┐  ┌─┐┌─┐┬─┐  \n" +
                    "╚═╗│ ││││├┤  │ ├─┤│││││ ┬  ├┤ │ │├┬┘  \n" +
                    "╚═╝└─┘┴ ┴└─┘ ┴ ┴ ┴┴┘└┘└─┘  └  └─┘┴└─  \n" +
                    "┬  ┬ ┬┌┐┌┌─┐┬ ┬  ┌┬┐┌─┐┌┬┐┌─┐┬ ┬┌─┐   \n" +
                    "│  │ │││││  ├─┤   │ │ │ ││├─┤└┬┘ ┌┘   \n" +
                    "┴─┘└─┘┘└┘└─┘┴ ┴   ┴ └─┘─┴┘┴ ┴ ┴  o    \n");

            System.out.print("오점뭐 프로그램입니다. 메뉴를 선택하세요.\n" +
                    "1. 사용자 메뉴\n" +
                    "2. 관리자 메뉴\n" +
                    "3. 프로그램 종료\n" +
                    "> ");

            int flag = sc.nextInt();

            if (flag == 1) {
                System.out.println("" +
                        "사용자 메뉴\n" +
                        "1. 전체 음식점 모두 알려줘\n" +
                        "2. 주변 음식점 알려줘\n" +
                        "3. 리뷰 볼래\n" +
                        "> ");
                switch (sc.nextInt()) {
                    case 1:
                        // 전체 음식점 조회
                        resSelectAll();
                        break;
                    case 2:
                        // 주변 음식점 조회
                        System.out.println("" +
                                "주변 음식점 조회\n" +
                                "현재 계신 곳이 어디인가요? (ex: 금천구) > ");
                        String search = sc.next();
                        selectNear(search); //조회
                        break;
                    case 3:
                        // 리뷰 메뉴
                        System.out.println("" +
                                "리뷰메뉴\n" +
                                "1. 리뷰 볼래\n" +
                                "2. 리뷰 쓸래\n" +
                                "3. 프로그램 종료\n" +
                                "> ");
                        switch (sc.nextInt()) {
                            case 1:
                                // 리뷰 조회
                                String resName = sc.next();
                                selectReview(resName);
                                break;
                            case 2:
                                // 리뷰 작성
                                insertReview();
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
            } else if (flag == 2) {
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
        List<VO> resList = dao.selectReview(resName);
        View.display(resList);
    }

    // 리뷰 작성
    private static void insertReview() {
    }

    // 주변 음식점 조회
    private static void selectNear(String search) {
        DAO dao = new DAO();
        List<VO> resList = dao.selectNear(search);
        View.display(resList);
    }

    // 전체 음식점 조회
    private static void resSelectAll() {
        DAO dao = new DAO();
        List<VO> resList = dao.selectAll();
        View.display(resList);
    }

    //
}