package bookshop.main;

import java.util.List;
import java.util.Scanner;

import bookshop.dao.BookDao;
import bookshop.vo.BookVo;

public class BookShop {

	public static void main(String[] args) {
		displayBookInfo();
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("대여 하고 싶은 책의 번호를 입력하세요:");
		Long no = scanner.nextLong();
		scanner.close();

		BookVo vo = new BookVo();
		vo.setNo(no);
		vo.setRent("Y");
		
		new BookDao().update(vo);
		
		displayBookInfo();
	}

	private static void displayBookInfo() {
		List<BookVo> list = new BookDao().findAll();
		
		for(BookVo vo : list) {
			System.out.printf("책번호:%-10d 책명:%-45.45s 작가명:%-45.45s 대여%-2.2s %n",
			vo.getNo(),vo.getTitle(),vo.getAuthor(),("Y".equals(vo.getRent()) ? "중" : "가능"));
		}
	}

}
