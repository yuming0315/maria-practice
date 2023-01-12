package email.dao.test;

import java.util.List;

import email.dao.EmaillistDao;
import email.vo.EmaillistVo;

public class EmaillistDaoTest {

	public static void main(String[] args) {
		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName("둘");
		vo.setLastName("리");
		vo.setEmail("dooly@gmail.com");
		
		testInsert(vo);
		testDelete("dooly@gmail.com");
		testFindAll();
	}

	private static void testDelete(String email) {
		new EmaillistDao().deleteByEmail(email);
		
	}

	private static void testFindAll() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for(EmaillistVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(EmaillistVo vo) {
		new EmaillistDao().insert(vo);
	}

}
