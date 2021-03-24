package project.view;

import project.model.VO;

import java.util.List;

public class View {

    public static void revDisplay(List<VO> revList) {
        System.out.println("------- 모든 리뷰 조회 -------");
        for (VO list : revList) {
            System.out.println(list.getResName() + "의 평점: " + list.getRevGpa() + "\n" +
                    "리뷰 내용: " + list.getRevContents() + "\n" +
                    "리뷰 작성일자: " + list.getRevVisitDate());
        }
    }

    public static void resDisplay(List<VO> resList) {
        System.out.println("---------- 모든 음식점 조회 ----------");
        for (VO list : resList) {
            System.out.println("고유번호: " + list.getResNo() +
            		"\n업소명: " + list.getResName() +
					"\n주소: " + list.getResAddr() +
					"\n전화번호: " + list.getResTel() +
					"\n대표메뉴: " + list.getResBestMenu() + " " + list.getMenuPrice() + "원");
			System.out.println("----------------------------------");
        }
    }

    public static void resDisplay(VO res) {
        System.out.println("---------- 해당 음식점 조회 ----------");
		System.out.println("고유번호: " + res.getResNo() +
				"\n업소명: " + res.getResName() +
				"\n주소: " + res.getResAddr() +
				"\n전화번호: " + res.getResTel() +
				"\n대표메뉴: " + res.getResBestMenu() + " " + res.getMenuPrice() + "원");
		System.out.println("----------------------------------");
    }

    public static void printBanner() {
        System.out.println("" +
                "╔═╗┌─┐┌┬┐┌─┐┌┬┐┬ ┬┬┌┐┌┌─┐  ┌─┐┌─┐┬─┐  \n" +
                "╚═╗│ ││││├┤  │ ├─┤│││││ ┬  ├┤ │ │├┬┘  \n" +
                "╚═╝└─┘┴ ┴└─┘ ┴ ┴ ┴┴┘└┘└─┘  └  └─┘┴└─  \n" +
                "┬  ┬ ┬┌┐┌┌─┐┬ ┬  ┌┬┐┌─┐┌┬┐┌─┐┬ ┬┌─┐   \n" +
                "│  │ │││││  ├─┤   │ │ │ ││├─┤└┬┘ ┌┘   \n" +
                "┴─┘└─┘┘└┘└─┘┴ ┴   ┴ └─┘─┴┘┴ ┴ ┴  o    \n");
    }

    public static void printMenu(int kind) {
        if (kind == 0) { // program main menu
            System.out.print("오점뭐 프로그램입니다. 메뉴를 선택하세요.\n" +
                    "1. 사용자 메뉴\n" +
                    "2. 관리자 메뉴\n" +
                    "3. 프로그램 종료\n" +
                    "> ");
        } else if (kind == 1) { // user menu
            System.out.println("" +
                    "사용자 메뉴\n" +
                    "1. 전체 음식점 모두 알려줘\n" +
                    "2. 주변 음식점 알려줘\n" +
                    "3. 특정 음식점 알려줘\n" +
                    "4. 리뷰 메뉴\n" +
                    "> ");
        } else if (kind == 2) { // admin memu
			System.out.println("" +
					"관리자 메뉴\n" +
					"1. 음식점 추가\n" +
					"2. 음식점 삭제\n" +
					"3. 음식점 수정\n" +
					"4. 리뷰 삭제\n" +
					"> ");
        }
    }
}