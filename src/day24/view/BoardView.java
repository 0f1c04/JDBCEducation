package day24.view;

import java.util.List;

import day24.model.BoardVO;

public class BoardView {

	public static void display(List<BoardVO> boardlist) {
		System.out.println("------- 게시판 정보 여러건 출력 -------");
		for (BoardVO board : boardlist) {
			System.out.println("****" + board.getTitle() + "****");
			System.out.println(board);
		}
	}

	public static void display(BoardVO board) {
		System.out.println("------- 게시판 정보 한건 출력 -------");
		System.out.println(board);
	}

	public static void display(String message) {
		System.out.println("------- 알림 -------");
		System.out.println(message);
	}
}