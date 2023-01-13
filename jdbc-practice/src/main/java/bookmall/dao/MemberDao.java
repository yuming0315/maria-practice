package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.MemberVo;

public class MemberDao implements BookMallDao{
	@Override
	public void insert(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into members values(no,?,?,password(?),?)";
			pstmt = conn.prepareStatement(sql);
			
			MemberVo mvo = (MemberVo) vo;
			
			pstmt.setString(1, mvo.getName());
			pstmt.setString(2, mvo.getEmail());
			pstmt.setString(3, mvo.getPw());
			pstmt.setString(4, null == mvo.getPhonenum() ? null : mvo.getPhonenum().replaceAll("[^\\d]", ""));
			
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
		MemberVo vo = new MemberVo();
		vo.setName("");
		return find(vo);
	}

	@Override
	public List<Object> find(Object vo) {
		List<Object> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			MemberVo mvo = (MemberVo) vo;
			
			String sql = "".equals(mvo.getName()) ? "select name,email,phonenum,no from members" 
					: "select name,email,phonenum,no from members where name = ? and pw = password(?)";
			pstmt = conn.prepareStatement(sql);
			
			if(!"".equals(mvo.getName())) {
				pstmt.setString(1, mvo.getName());
				pstmt.setString(2, mvo.getPw());
			}
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo addvo = new MemberVo();
				addvo.setName(rs.getString(1));
				addvo.setEmail(rs.getString(2));
				addvo.setPhonenum(rs.getString(3));
				addvo.setNo(rs.getLong(4));
				
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
		
		
	}

	@Override
	public void update(Object vo) {
		
		
	}

	
}
