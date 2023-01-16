package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CartVo;

public class CartDao implements BookMallDao{

	@Override
	public void insert(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into cart values(no,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			CartVo mvo = (CartVo) vo;
			
			pstmt.setLong(1, mvo.getMembers_no());
			pstmt.setLong(2, mvo.getBook_no());
			pstmt.setLong(3, mvo.getAmount());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Object> findAll() {
		//필요없음
		return null;
	}

	@Override
	public List<Object> find(Object vo) {
		List<Object> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			CartVo mvo = (CartVo) vo;
			
			String sql = "select b.title, b.price, sum(a.amount), b.no"
					+ " from cart a join book b on a.book_no = b.no"
					+ " where a.members_no = ?"
					+ " group by a.book_no";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, mvo.getMembers_no());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				CartVo addvo = new CartVo();
				addvo.setTitle(rs.getString(1));
				addvo.setPrice(rs.getLong(2));
				addvo.setAmount(rs.getLong(3));
				addvo.setBook_no(rs.getLong(4));
				result.add(addvo);
			}
			
		} catch (SQLException e) {
			System.out.println("error: "+e);
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				
				if(pstmt!=null) {
					pstmt.close();
				}
				
				if(conn!=null){
					conn.close();	
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public void delete(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();			
			
			String sql ="delete from cart where members_no = ?";
			pstmt = conn.prepareStatement(sql);
			
			CartVo mvo = (CartVo) vo;
			
			pstmt.setLong(1, mvo.getMembers_no());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void update(Object vo) {
		
		
	}
	
	
}
