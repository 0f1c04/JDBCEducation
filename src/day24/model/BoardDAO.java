package day24.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class BoardDAO {

	//CRUD(Create:insert, Read:select, U:update, D:delete)
	public BoardVO selectSeq(int boardNo) {
		BoardVO board = null;
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from board where board_seq = ?";
		//String sql = "select board_seq, board_title, board_contents, board_writer, board_date, nvl(board_viewcount,0)+1, board_password, board_image from board where board_seq = ?";
		String sql2 = "update board set board_viewcount = nvl(board_viewcount,0)+1 where board_seq = ?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, boardNo);
			rs = pst.executeQuery();
			while(rs.next()) {
				board = makeBoard(rs);
				pst = conn.prepareStatement(sql2);
				pst.setInt(1, boardNo);
				int result = pst.executeUpdate();
				System.out.println(result>0?"board_viewcount 수정성공":"board_viewcount 수정실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}

		return board;
	}

	public List<BoardVO> selectBoard(long seq) {
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		Connection conn = DBUtil.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select * from board where board_seq = ?";

		try {
			pst = conn.prepareStatement(sql);
			pst.setLong(1, seq);
			rs = pst.executeQuery();
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}

		return boardlist;
	}

	public List<BoardVO> selectAll(){
		List<BoardVO> boardlist = new ArrayList<BoardVO>();
		Connection conn = DBUtil.getConnection();
		Statement st = null;
		ResultSet rs = null;
		String sql = "select * from board";

		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, st, conn);
		}

		return boardlist;
	}

	private BoardVO makeBoard(ResultSet rs) {
		BoardVO board = new BoardVO();
		try {
			board.setSeq(rs.getLong("board_seq"));
			board.setTitle(rs.getString("board_title"));
			board.setContents(rs.getString("board_contents"));
			board.setWriter(rs.getInt("board_writer"));
			board.setDate(rs.getDate("board_date"));
			board.setViewcount(rs.getInt("board_viewcount"));
			board.setPassword(rs.getString("board_password"));
			board.setImage(rs.getString("board_image"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return board;
	}

	public int deleteBoard(int board_seq, String passwd) {
		int result = 0;
		String sql = "delete from board"
				+ " where board_seq = ? and board_password = ?";
		Connection conn = null;
		PreparedStatement pst = null;

		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setInt(1, board_seq);
			pst.setString(2, passwd);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, pst, conn);
		}
		return result;
	}

	public int updateBoard(BoardVO board) {
		int result = 0;
		String sql = "update board set"
				+ " board_title = ?,"
				+ " board_contents = ?,"
				+ " board_date = sysdate,"
				+ " board_password = ?,"
				+ " board_image = ?"
				+ " where board_seq = ?";

		Connection conn = null;
		PreparedStatement pst = null;

		conn = DBUtil.getConnection();
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContents());
			pst.setString(3, board.getPassword());
			pst.setString(4, board.getImage());
			pst.setLong(5, board.getSeq());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, pst, conn);
		}
		return result;
	}

	public int insertBoard(BoardVO board) {
		String sql = "insert into board values(board_autonum.nextval,?,?,?,sysdate,0,?,?)";
		Connection conn = null;
		PreparedStatement pst = null;
		int result = 0;

		conn = DBUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			//pst.setLong(1, board.getSeq());
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContents());
			pst.setInt(3, board.getWriter());
			//pst.setDate(5, board.getDate());
			//pst.setInt(4, board.getViewcount());
			pst.setString(4, board.getPassword());
			pst.setString(5, board.getImage());
			result = pst.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, pst, conn);
		}
		return result;
	}
}