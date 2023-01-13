package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.CategoryVo;

public class CategoryDao implements BookMallDao{

	@Override
	public void insert(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into categorys values(no, ?)";
			pstmt = conn.prepareStatement(sql);
			
			CategoryVo mvo = (CategoryVo) vo;
			
			pstmt.setString(1, mvo.getCategory());
			
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
		CategoryVo vo = new CategoryVo();
		vo.setCategory("");;
		return findCategory(vo);
	}
	
	@Override
	public List<Object> find(Object vo) {
		List<Object> list = findCategory(vo);
		if(list.isEmpty()) {
			insert(vo);
			list = findCategory(vo);
		}
		return list;
	}

	public List<Object> findCategory(Object vo) {
		List<Object> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			CategoryVo mvo = (CategoryVo) vo;
			
			String sql = "".equals(mvo.getCategory()) ? "select no,category from categorys" 
					: "select no,category from categorys where category = ?";
			pstmt = conn.prepareStatement(sql);
			
			if(!"".equals(mvo.getCategory())) {
				pstmt.setString(1, mvo.getCategory());
			}
			
			rs = pstmt.executeQuery();
			
			
			while(rs.next()) {
				CategoryVo addvo = new CategoryVo();
				addvo.setNo(rs.getLong(1));
				addvo.setCategory(rs.getString(2));
				
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
