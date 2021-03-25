package Java_cmd_project.model;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAO {
    public int updateRestaurant(VO res) {
        int result = 0;
        String sql = "update RESTAURANT set" + " RES_NAME = ?," + " RES_ADDR = ?," + " AREA_NM = ?,"
                + " HOMEPAGE_URL = ?," + " TEL_NO = ?," + " RES_BEST_MENU = ?," + " MENU_PRICE = ?"
                + " where RES_NO = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(sql);
            pst.setString(1, res.getResName());
            pst.setString(2, res.getResAddr());
            pst.setString(3, res.getAreaNo());
            pst.setString(4, res.getResUrl());
            pst.setString(5, res.getResTel());
            pst.setString(6, res.getResBestMenu());
            pst.setInt(7, res.getMenuPrice());
            pst.setInt(8, res.getResNo());
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, pst, conn);
        }
        return result;
    }

    // 관리자 음식점 삭제
    public int deleteRestaurant(int resNo) {
        int result = 0;
        String sql = "delete from RESTAURANT where res_no = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, resNo);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, pst, conn);
        }
        return result;
    }

    // 관리자 리뷰 삭제
    public int deleteReview(int revNo) {
        int result = 0;
        String sql = "delete from review where rev_no = ?";
        Connection conn = DBUtil.getConnection();
        PreparedStatement pst = null;

        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, revNo);
            result = pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, pst, conn);
        }
        return result;
    }

    // 관리자 음식점 추가
    public int insertRestaurant(VO res) {
        String sql = "insert into RESTAURANT values(RES_NO_SEQUENCE.nextval, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        int result = 0;

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, res.getResName());
            st.setString(2, res.getResAddr());
            st.setString(3, res.getResAddr());
            st.setString(4, res.getResUrl());
            st.setString(5, res.getResTel());
            st.setString(6, res.getResBestMenu());
            st.setInt(7, res.getMenuPrice());
            result = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(null, st, conn);
        }
        return result;
    }

    public List<VO> selectReview(String resName) { // 특정 음식점 전체 리뷰 조회
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
            System.out.println("없는 가게명이에요");
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

    public List<VO> selectAllres() { // 전체 음식점 조회
        List<VO> resList = new ArrayList<VO>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select distinct *\n" +
                "from RESTAURANT order by RES_NO";

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

    public List<VO> selectAllrev() { // 전체 음식점 조회
        List<VO> revList = new ArrayList<VO>();
        Connection conn = DBUtil.getConnection();
        Statement st = null;
        ResultSet rs = null;
        String sql = "select * from review join RESTAURANT using (res_no)";

        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
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
		review.setRevNo(rs.getInt("rev_no"));
		return review;
	}

	public boolean adminLoginCheck(String id, String pw) {
        Connection conn = DBUtil.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "select * from admin where adm_id = ? and adm_pw = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setString(1, id);
            st.setString(2, pw);
            rs = st.executeQuery();
            while(rs.next()) {
                if (rs.getRow() == 1) return true; //행이 있다면 관리자가 존재
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.dbClose(rs, st, conn);
        }
        return false;
    }
}
