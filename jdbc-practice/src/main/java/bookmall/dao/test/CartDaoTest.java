package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CartDao;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		insertCart();
		findAll();
	}

	private static void findAll() {
		CartVo cvo = new CartVo();
		cvo.setMembers_no(1L);
		List<Object> list = new CartDao().find(cvo);
		for(Object obj : list ) {
			CartVo vo = (CartVo) obj;
			System.out.println(vo);
		}
	}

	private static void insertCart() {
		CartVo vo = new CartVo();
		vo.setAmount(2L);
		vo.setBook_no(1L);
		vo.setMembers_no(1L);
		
		new CartDao().insert(vo);	
	}

}
