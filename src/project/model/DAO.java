package project.model;

import util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public List<VO> selectReview(String resName) { // 전체 리뷰 조회
        List<VO> resList = new ArrayList<VO>();
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
                resList.add(makeRev(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }

        return resList;
    }

//	public int insertReview() {
//		String sql = "insert into review values(?,?,?,?,?,?,?,?,?,?,?)";
//		Connection conn = DBUtil.getConnection();
//		PreparedStatement st = null;
//		int result = 0;
//
//		try {
//			st = conn.prepareStatement(sql);
//			st.setInt(1, emp.getEmployee_id());
//			st.setString(2, emp.getFirst_name());
//			st.setString(3, emp.getLast_name());
//			st.setString(4, emp.getEmail());
//			st.setString(5, emp.getPhone_number());
//			st.setDate(6, emp.getHire_date());
//			st.setString(7, emp.getJob_id());
//			st.setInt(8, emp.getSalary());
//			st.setDouble(9, emp.getCommission_pct());
//			st.setInt(10, emp.getManager_id());
//			st.setInt(11, emp.getDepartment_id());
//			result = st.executeUpdate();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBUtil.dbClose(null, st, conn);
//		}
//		return result;
//	}

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

//	private VO makeRev(ResultSet rs) throws SQLException {
//		VO review = new VO();
//		review.setResNo(rs.getInt("res_no"));
//        review.setResName(rs.getString("res_name"));
//        review.setResAddr(rs.getString("res_addr"));
//        review.setAreaNo(rs.getString("area_nm"));
//        review.setResUrl(rs.getString("homepage_url"));
//        review.setResTel(rs.getString("tel_no"));
//        review.setResBestMenu(rs.getString("res_best_menu"));
//        review.setMenuPrice(rs.getInt("menu_price"));
//		review.setRevGpa(rs.getFloat("res_gpa"));
//		review.setRevContents(rs.getString("rev_contents"));
//		review.setRevVisitDate(rs.getDate("rev_visit_date"));
//		return review;
//	}
}
