package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMall;
import bookmall.vo.CartVo;
import bookmall.vo.OrderVo;

public class OrderDao implements BookMallDao{

	@Override
	public void insert(Object vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			//1.orders 만들기
			OrderVo mvo = (OrderVo) vo;
			
			String sql ="insert into orders values(no,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, mvo.getMembers_no());
			pstmt.setString(2, mvo.getAddress());
			
			pstmt.executeUpdate();
			
			List <Object> list = find(mvo);
			mvo.setNo(((OrderVo) list.get(list.size()-1)).getNo());
			
			//2.cash 만들기
			sql ="insert into cash values(no,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, mvo.getNo());
			pstmt.setLong(2, mvo.getTotalamount());
			pstmt.setString(3, mvo.getPayas());
			
			pstmt.executeUpdate();
			
			//3. order_book에 넣어주기
			list = new CartDao().find(new CartVo(mvo.getNo()));
			
			for(Object v : list) {
				CartVo cv = (CartVo) v;
				
				sql ="insert into order_book values(no,?,?,?)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, mvo.getNo());
				pstmt.setLong(2, cv.getBook_no());
				pstmt.setLong(3, cv.getAmount());
				
				pstmt.executeUpdate();
			}
			
			//4. 삭제해주기
			new CartDao().delete(new CartVo(mvo.getMembers_no()));
			
			
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
	public List<Object> find(Object vo) {
		List<Object> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			OrderVo mvo = (OrderVo) vo;
			
			String sql = "select no"
					+ " from orders"
					+ " where members_no = ?"
					+ " order by no";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, mvo.getMembers_no());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				OrderVo addvo = new OrderVo();
				addvo.setNo(rs.getLong(1));
				
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

	public void findMyOrder(Object vo) {
		List<Object> result = new ArrayList<>();
		List<Object> templist = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			
			OrderVo mvo = (OrderVo) vo;
			
			result = find(vo);
			
			for(Object obj : result) {
				//주문내역 상단
				templist.clear();
				String sql = " select b.title,b.price,a.amount"
						+ " from order_book a join book b on a.book_no = b.no"
						+ " where a.orders_no = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, ((OrderVo) obj).getNo());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					CartVo addvo = new CartVo();
					addvo.setTitle(rs.getString(1));
					addvo.setPrice(rs.getLong(2));
					addvo.setAmount(rs.getLong(3));
					
					templist.add(addvo);
				}
				BookMall.findAll(new CartVo(),templist);
				
				templist.clear();
				
				sql = "select b.address,a.payment,a.totalamount,a.coupon"
						+ " from cash a join orders b on a.orders_no = b.no"
						+ " where a.orders_no = ?";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setLong(1, ((OrderVo) obj).getNo());
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					OrderVo temp = new OrderVo();
					temp.setAddress(rs.getString(1));
					temp.setPayas(rs.getString(2));
					temp.setTotalamount(rs.getLong(3));
					
					templist.add(temp);
				}
				BookMall.findAll(new OrderVo(),templist);
				
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
	}


	@Override
	public List<Object> findAll() {
		return null;
	}

	
	@Override
	public void delete(Object vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Object vo) {
		// TODO Auto-generated method stub
		
	}

}
