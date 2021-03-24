package project.model;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public List<VO> selectReview(String resName) { // 전체 리뷰 조회
        List<VO> revList = new ArrayList<VO>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from review right join restaurant using (res_no)\n" +
                "where restaurant.res_name like '%'||?||'%'";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, resName);
            rs = st.executeQuery();
            while (rs.next()) {
                revList.add(makeRev(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

        return revList;
    }

	public int insertReview(int resNo, float revGpa, String revContents) {
		String sql = "insert into review values(?, ?, ?, sysdate, rev_no_sequence.nextval)";
		Connection conn = DBUtil.getConnection();
		PreparedStatement st = null;
		int result = 0;

		try {
			st = conn.prepareStatement(sql);
			st.setInt(1, resNo);
			st.setFloat(2, revGpa);
			st.setString(3, revContents);

			result = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(null, st, conn);
		}
		return result;
	}

    public VO selectRes(String search) {
        VO res = null;
        String sql = "select * from restaurant where res_name = ?";

        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, search);

            rs = st.executeQuery();
            while (rs.next()) {
                res = makeRes(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }
        return res;
    }

    public List<VO> selectNear(String search) { // 전체 음식점 조회
        List<VO> resList = new ArrayList<VO>();
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from restaurant\n" +
                "where res_addr like '%'||?||'%'";

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, search);
            rs = st.executeQuery();
            while (rs.next()) {
                resList.add(makeRes(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

        return resList;
    }

    public List<VO> selectAll() { // 전체 음식점 조회
        List<VO> resList = new ArrayList<VO>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from restaurant";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                resList.add(makeRes(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

        return resList;
    }

    private VO makeRes(ResultSet rs) throws SQLException {
        VO restaurant = new VO();
        restaurant.setResNo(rs.getInt("res_no"));
        restaurant.setResName(rs.getString("res_name"));
        restaurant.setResAddr(rs.getString("res_addr"));
        restaurant.setAreaNo(rs.getString("area_nm"));
        restaurant.setResUrl(rs.getString("homepage_url"));
        restaurant.setResTel(rs.getString("tel_no"));
        restaurant.setResBestMenu(rs.getString("res_best_menu"));
        restaurant.setMenuPrice(rs.getInt("menu_price"));
        return restaurant;
    }

	private VO makeRev(ResultSet rs) throws SQLException {
		VO review = new VO();
        review.setResName(rs.getString("res_name"));
		review.setResNo(rs.getInt("res_no"));
		review.setRevGpa(rs.getFloat("rev_gpa"));
		review.setRevContents(rs.getString("rev_contents"));
		review.setRevVisitDate(rs.getDate("rev_visit_date"));
		return review;
	}
}
