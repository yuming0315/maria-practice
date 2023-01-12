package bookshop.dao.test;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookDaoTest {
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
//		Book[] books = new Book[10];
//
//		books[0] = new Book(1, "트와일라잇", "스테파니메이어");
//		books[1] = new Book(2, "뉴문", "스테파니메이어");
//		books[2] = new Book(3, "이클립스", "스테파니메이어");
//		books[3] = new Book(4, "브레이킹던", "스테파니메이어");
//		books[4] = new Book(5, "아리랑", "조정래");
//		books[5] = new Book(6, "젊은그들", "김동인");
//		books[6] = new Book(7, "아프니깐 청춘이다", "김난도");
//		books[7] = new Book(8, "귀천", "천상병");
//		books[8] = new Book(9, "태백산맥", "조정래");
//		books[9] = new Book(10, "풀하우스", "원수연");
//		
//		for (Book book : books) {
//			testInsert(book.getTitle());
//		}
		testFindAll();
	}

	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo : list) {
			System.out.println(vo);
		}
		
	}

	private static void testInsert(String title) {
		BookVo vo = null;
		BookDao dao = new BookDao();
		
		vo = new BookVo();
		vo.setTitle(title);
		
		AuthorDaoTest.testFindAll();
		System.out.println(title);
		System.out.print("해당하는 작가의 no 입력 > ");
		Long authorNo = scanner.nextLong();
		vo.setAuthorNo(authorNo);
		
		dao.insert(vo);
	}

}
