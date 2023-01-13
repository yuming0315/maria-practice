package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class UserDaoTest {

	public static void main(String[] args) {
//		insertMember();
		findAll();

	}

	private static void findAll() {
		List<Object> list = new MemberDao().findAll();
		for(Object vo : list) {
			MemberVo mvo = (MemberVo) vo;
			System.out.println(mvo);
		}
		
	}

	private static void insertMember() {
		MemberVo vo = new MemberVo();
		vo.setName("강소리");
		vo.setEmail("rkdthfl22@naver.com");
		vo.setPw("1234");
		vo.setPhonenum("010-1234-5678");
		
		new MemberDao().insert(vo);
	}

}
