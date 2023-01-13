package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;
import bookmall.vo.CategoryVo;

public class BookDao implements BookMallDao{
	
	@Override
	public void insert(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql ="insert into book values(no,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			BookVo mvo = (BookVo) vo;
			
			Long CategoryNo = ((CategoryVo) new CategoryDao().find(new CategoryVo(mvo.getCategory())).get(0)).getNo();
			
			pstmt.setLong(1, CategoryNo);
			pstmt.setString(2, mvo.getTitle());
			pstmt.setLong(3, mvo.getPrice());
			
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
		BookVo vo = new BookVo();
		vo.setTitle("");
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
			
			BookVo mvo = (BookVo) vo;
			
			String sql = "select a.title, b.category, a.price, a.no"
					+ " from book a join categorys b on a.category_no = b.no" 
					+ ("".equals(mvo.getTitle()) ? 
					"" : " where title like ?");
			pstmt = conn.prepareStatement(sql);
			
			
			
			if(!"".equals(mvo.getTitle())) {
				pstmt.setString(1, '%'+mvo.getTitle()+'%');
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BookVo addvo = new BookVo();
				addvo.setTitle(rs.getString(1));
				addvo.setCategory(rs.getString(2));
				addvo.setPrice(rs.getLong(3));
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
