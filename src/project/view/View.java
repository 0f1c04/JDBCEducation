package project.view;

import project.model.VO;

import java.util.List;

public class View {

	public static void display(List<VO> resList) {
		System.out.println("------- 모든 음식점 조회 -------");
		for (VO list : resList) {
			System.out.println(list.getResName());
		}
	}

	public static void display(VO board) {
		System.out.println("------- 게시판 정보 한건 출력 -------");
		System.out.println(board);
	}

	public static void display(String message) {
		System.out.println("------- 알림 -------");
		System.out.println(message);
	}
}