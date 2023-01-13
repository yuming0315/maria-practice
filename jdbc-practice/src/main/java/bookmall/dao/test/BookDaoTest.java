package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {

	public static void main(String[] args) {
		insertbook();
		findbook();
	}

	private static void findbook() {
		List<Object> list = new BookDao().findAll();
		for(Object vo : list) {
			BookVo mvo = (BookVo) vo;
			System.out.println(mvo);
		}
		
		
	}

	private static void insertbook() {
		BookVo vo = new BookVo();
		vo.setTitle("데미안");
		vo.setPrice(35000L);
		vo.setCategory("소설");
		
		new BookDao().insert(vo);
	}

}
