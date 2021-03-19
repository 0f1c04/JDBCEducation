package day24.controller;

import java.util.List;
import java.util.Scanner;

import day24.model.BoardDAO;
import day24.model.BoardVO;
import day24.view.BoardView;

public class BoardController {

	public static void main(String[] args) {

		BoardDAO boardDAO = new BoardDAO();
		Scanner sc = new Scanner(System.in);
		BoardVO board = null;
		int ID = 0;
		String passwd = null;
		int result = 0;

		while (true) {
			System.out.println("====================================================");
			System.out.println("0.로그인 1.모두 조회 2.번호로 조회 3.입력 4.수정 5.삭제 9.종료");
			System.out.print("작업 선택 > ");
			int work = sc.nextInt();
			switch (work) {
				case 0:
					System.out.print("아이디를 입력하세요 > ");
					ID = sc.nextInt();
					System.out.println("비밀번호를 입력하세요 > ");
					passwd = sc.next();
					break;
				case 1:
					BoardView.display(boardDAO.selectAll());
					break;
				case 2:
					System.out.print("번호 입력 > ");
					int num = sc.nextInt();
					BoardView.display(boardDAO.selectSeq(num));
					break;
				case 3:
					if(ID==0 || passwd == null) {
						System.out.println("로그인이 필요한 작업입니다.");
						break;
					}
					board = new BoardVO();
					System.out.print("제목을 입력하세요 > ");
					board.setTitle(sc.next());
					System.out.print("내용을 입력하세요 > ");
					board.setContents(sc.next());
					board.setWriter(ID);
					board.setPassword(passwd);
					System.out.print("이미지 경로를 입력하세요 > ");
					board.setImage(sc.next());
					result = boardDAO.insertBoard(board);
					BoardView.display(result > 0 ? "게시성공" : "게시실패");
					break;
				case 4:
					if(ID==0 || passwd == null) {
						System.out.println("로그인이 필요한 작업입니다.");
						break;
					}
					board = new BoardVO();
					System.out.print("수정할 board 번호를 입력하세요 > ");
					board.setSeq(sc.nextLong());
					System.out.print("수정할 제목을 입력하세요 > ");
					board.setTitle(sc.next());
					System.out.print("수정할 내용을 입력하세요 > ");
					board.setContents(sc.next());
					System.out.print("수정할 비밀번호를 입력하세요 > ");
					passwd = sc.next();
					board.setPassword(passwd);
					System.out.print("수정할 이미지 경로를 입력하세요 > ");
					board.setImage(sc.next());
					result = boardDAO.updateBoard(board);
					BoardView.display(result > 0 ? "업데이트성공" : "업데이트실패");
					break;
				case 5:
					if(ID==0 || passwd == null) {
						System.out.println("로그인이 필요한 작업입니다.");
						break;
					}
					board = new BoardVO();
					System.out.print("삭제할 board 번호와 비밀번호를 입력하세요 > ");
					result = boardDAO.deleteBoard(sc.nextInt(), sc.next());
					BoardView.display(result > 0 ? result+"건 삭제성공" : "삭제실패");
					break;
				case 9:
					System.out.println("종료합니다.");
					System.exit(0);
				default:
					break;
			}
		}
		// board insert
//      int user = 100;
//      String pass = "패스워드";
//      for(int i=0; i<10; i++) {
//         BoardVO board = new BoardVO();
//         board.setTitle("제목"+i);
//         board.setContents("게시내용"+i);
//         board.setWriter(user);
//         board.setPassword(pass);
//         board.setImage("iamges/logo"+i+".png");
//         int result = boardDAO.insertBoard(board);
//         BoardView.display(result>0?"게시성공":"게시실패");
//      }
		// board_insert();

		// board select
		// board_select();
		// BoardView.display(boardDAO.selectAll());

		// board update
//      BoardVO board = new BoardVO(10, "타이틀 수정", "내용 수정", 0, null, 0, "pass 수정", "이미지 경로 수정");
//      BoardView.display(boardDAO.updateBoard(board)+"건 수정");
		// board_update();

		// board delete
		// BoardView.display(boardDAO.deleteBoard(9, "패스워드")+"건 삭제");
		// board_delete();

		// board id로 select ... view count 증가
//      BoardView.display(boardDAO.selectSeq(10));
	}

	private static void board_select() {
		BoardDAO dao = new BoardDAO();
		List<BoardVO> boardlist = dao.selectAll();
		BoardView.display(boardlist);
	}

	private static void board_delete() {
		BoardDAO dao = new BoardDAO();
		int result = dao.deleteBoard(10, "1234");
		BoardView.display(result > 0 ? "삭제성공" : "삭제실패");
	}

	private static void board_update() {
		BoardDAO dao = new BoardDAO();
		BoardVO board = makeEmp();
		int result = dao.updateBoard(board);
		BoardView.display(result > 0 ? "수정성공" : "수정실패");
	}

	private static void board_insert() {
		BoardDAO dao = new BoardDAO();
		BoardVO board = makeEmp();
		int result = dao.insertBoard(board);
		BoardView.display(result > 0 ? "입력성공" : "입력실패");
	}

	private static BoardVO makeEmp() {

		BoardVO board = new BoardVO();
		board.setSeq(10);
		board.setTitle("게시물 제목");
		board.setContents("내용");
		board.setWriter(100);
		// board.setDate(new Date(new java.util.Date().getTime()));
		board.setViewcount(1);
		board.setPassword("1234");
		board.setImage("image.png");
		return board;
	}
}