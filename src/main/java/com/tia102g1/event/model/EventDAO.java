package com.tia102g1.event.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class EventDAO implements EventDAO_interface {

	// DataSource
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext(); // 建立JNDI初始上下文物件
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TIA102G1");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// SQL query
	private static final String INSERT_STMT = "INSERT INTO event (eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, createdBy, lastUpdatedBy) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT * FROM event order by eventId";
	private static final String GET_ONE_STMT = "SELECT * FROM event where eventId = ?";
	private static final String DELETE = "DELETE FROM event where eventId = ?";
	private static final String UPDATE = "UPDATE event set eventName=?, startDt=?, endDt=?, eventDiscount=?, eventPic=?, status=?, eventContent=?, lastUpdatedBy=? where eventId = ?";

	@Override
	public void insert(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		"INSERT INTO event (eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, createdBy, lastUpdatedBy)..."

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, eventVO.getEventName());
			pstmt.setDate(2, eventVO.getStartDt());
			pstmt.setDate(3, eventVO.getEndDt());
			pstmt.setDouble(4, eventVO.getEventDiscount());
			pstmt.setBytes(5, eventVO.getEventPic());
			pstmt.setInt(6, eventVO.getStatus());
			pstmt.setString(7, eventVO.getEventContent());
			pstmt.setString(8, eventVO.getCreatedBy());
			pstmt.setString(9, eventVO.getLastUpdatedBy());

			pstmt.executeUpdate();

			// 處理SQL例外錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// 關閉連線資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update(EventVO eventVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		"UPDATE FROM event set eventName=?, startDt=?, endDt=?, eventDiscount=?, eventPic=?, status=?, eventContent=?, lastUpdatedBy=? where eventId=?";

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, eventVO.getEventName());
			pstmt.setDate(2, eventVO.getStartDt());
			pstmt.setDate(3, eventVO.getEndDt());
			pstmt.setDouble(4, eventVO.getEventDiscount());
			pstmt.setBytes(5, eventVO.getEventPic());
			pstmt.setInt(6, eventVO.getStatus());
			pstmt.setString(7, eventVO.getEventContent());
			pstmt.setString(8, eventVO.getLastUpdatedBy());
			pstmt.setInt(9, eventVO.getEventId());

			pstmt.executeUpdate();

			// 處理SQL例外錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// 關閉連線資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void delete(Integer eventId) {

		Connection con = null;
		PreparedStatement pstmt = null;
//		"DELETE FROM event where eventId = ?";

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, eventId);

			pstmt.executeUpdate();

			// 處理SQL例外錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// 關閉連線資源
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public EventVO findByPrimaryKey(Integer eventId) { //單筆查詢

		EventVO eventVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		"eventId, eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, createdBy, dateCreated, lastUpdatedBy, lastUpdated"

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, eventId);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				eventVO = new EventVO();
				eventVO.setEventId(rs.getInt("eventId"));
				eventVO.setEventName(rs.getString("eventName"));
				eventVO.setStartDt(rs.getDate("startDt"));
				eventVO.setEndDt(rs.getDate("endDt"));
				eventVO.setEventDiscount(rs.getDouble("eventDiscount"));
				eventVO.setEventPic(rs.getBytes("eventPic"));
				eventVO.setStatus(rs.getInt("status"));
				eventVO.setEventContent(rs.getString("eventContent"));
				eventVO.setCreatedBy(rs.getString("createdBy"));
				eventVO.setDateCreated(rs.getTimestamp("dateCreated"));
				eventVO.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
				eventVO.setLastUpdated(rs.getTimestamp("lastUpdated"));
			}

			// 處理SQL例外錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// 關閉連線資源
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return eventVO;
	}

	@Override
	public List<EventVO> getAll() {  //多筆查詢
		
		List<EventVO> list = new ArrayList<EventVO>();
		EventVO eventVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		"eventId, eventName, startDt, endDt, eventDiscount, eventPic, status, eventContent, createdBy, dateCreated, lastUpdatedBy, lastUpdated"

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				eventVO = new EventVO();
				eventVO.setEventId(rs.getInt("eventId"));
				eventVO.setEventName(rs.getString("eventName"));
				eventVO.setStartDt(rs.getDate("startDt"));
				eventVO.setEndDt(rs.getDate("endDt"));
				eventVO.setEventDiscount(rs.getDouble("eventDiscount"));
				eventVO.setEventPic(rs.getBytes("eventPic"));
				eventVO.setStatus(rs.getInt("status"));
				eventVO.setEventContent(rs.getString("eventContent"));
				eventVO.setCreatedBy(rs.getString("createdBy"));
				eventVO.setDateCreated(rs.getTimestamp("dateCreated"));
				eventVO.setLastUpdatedBy(rs.getString("lastUpdatedBy"));
				eventVO.setLastUpdated(rs.getTimestamp("lastUpdated"));
				
				list.add(eventVO);
			}

			// 處理SQL例外錯誤
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

			// 關閉連線資源
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
		return list;
	}

}
