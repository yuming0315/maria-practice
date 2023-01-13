package bookmall.main;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderVo;

public class BookMall {
	static String quit[] = { "q", "quit" };
	static String pay[] = {"현금","카드","1","2"};
	static Scanner sc = new Scanner(System.in);
	static Long userNo;
	
	public static void main(String[] args) {
		wjscjfl();
		
		List<Object> list;
		String id,pw;
		do {
			System.out.println("id: kickscar pw: 1234");
			System.out.print("로그인 ID > ");
			id = sc.nextLine();
			System.out.print("로그인 PW > ");
			pw = sc.nextLine();
			list = new MemberDao().find(new MemberVo(id,pw));
		}while(list.isEmpty());

		userNo = ((MemberVo) list.get(0)).getNo();
		String control;
		do {
			System.out.print("(L)ist (C)ategory (P)roduct (Cart) (O)rder (Q)uit > ");

			control = sc.nextLine().toLowerCase();
			switch (control) {
			case "l":
			case "list":
				findAll(new MemberVo(),new MemberDao().findAll());
				break;
			case "c":
			case "category":
				findAll(new CategoryVo(),new CategoryDao().findAll());
				break;
			case "p":
			case "product":
				addCart();
				break;
			case "cart":
				inCart();
				break;
				
			case "o":
			case "order":
				new OrderDao().findMyOrder(new OrderVo(userNo));
				break;
			}

		} while (!Arrays.asList(quit).contains(control));

	}
	
	public static <T> void findAll(T obj,List<Object> list) {
		for(Object vo : list) {
			T mvo = (T) vo;
			System.out.println(mvo);
		}
		
	}
	private static int totalMoney(List<Object> list) {
		int result=0;
		for(Object vo : list) {
			CartVo mvo = (CartVo) vo;
			result += mvo.getPrice().intValue() * mvo.getAmount().intValue();
		}
		return result;
	}
	
	private static void inCart() {
		List<Object> list = new CartDao().find(new CartVo(userNo));
		findAll(new CartVo(),list);
		
		int money = totalMoney(list);
		if(money<0) {
			return;
		}
		System.out.println("총 금액 "+money +"원");
		
		System.out.print("(q)uit 외의 키 입력시 결제로 이동 > ");
		String command = sc.nextLine().toLowerCase();
		
		if(!Arrays.asList(quit).contains(command)) {
			while(money>0) {
				System.out.print("결제방식 선택 (1)현금 (2)카드 (Q)uit 취소 >");
				String payas = sc.nextLine();
				
				if(Arrays.asList(quit).contains(payas)) {
					break;
				}
				
				if(Arrays.asList(pay).contains(payas)) {
					System.out.println(money+"원을 "
				+ (payas.matches(".*[\\D].*")? payas : pay[Integer.parseInt(payas)-1])
				+"으로 결제");
					String address;
					while(true) {
						System.out.print("배송할 주소 입력 > ");
						address = sc.nextLine();
						
						if(null != address || !"".equals(address)) {
							break;
						}
						System.out.println("잘못된 입력입니다.");
					}
					
					OrderVo vo = new OrderVo();
					vo.setMembers_no(userNo);
					vo.setPayas((payas.matches(".*[\\D].*")? payas : pay[Integer.parseInt(payas)-1]));
					vo.setAddress(address);
					vo.setTotalamount(Long.valueOf(money));
					
					new OrderDao().insert(vo);
					break;
				}
				else {
					System.out.println("잘못된 입력 다시 입력해주세요.");
				}
			}
		}
	}
	
	private static void addCart() {
		while (true) {
			findAll(new BookVo(),new BookDao().findAll());
			
			System.out.print("카트에 추가할 책 번호 or 제목, 수량 입력(생략시 1) ex)1,1 또는 데미안,3 (Q)uit 나가기 > ");
			String product = sc.nextLine();

			if (Arrays.asList(quit).contains(product)) {
				break;
			}
			
			
			String[] tokens = product.replaceAll(" ","").split(",");
			CartVo cvo = new CartVo();
			BookVo bvo = new BookVo(tokens[0]);
			List<Object> list = new BookDao().find(bvo);
			switch (tokens.length) {
			case 1:
				cvo.setAmount(1L);
			case 2:
				cvo.setMembers_no(userNo);
				if(tokens.length!=1) {
					if(!tokens[1].matches(".*[\\D].*")||cvo.getAmount()==0) {
						cvo.setAmount(Long.parseLong(tokens[1]));
					}
					else if(cvo.getAmount()!=1L) {
						System.out.println("잘못된 수량 입력입니다.");
						break;
					}
				}	
				
				if(list.size()>1) {
					findAll(new BookVo(),list);
					System.out.print("카트에 넣을 책번호를 입력해 주세요 >");
					cvo.setBook_no((sc.nextLong()));
				}
				else if(!tokens[0].matches(".*[\\D].*")){
					cvo.setBook_no(Long.parseLong(tokens[0]));
				}
				else if(!list.isEmpty()) {
					cvo.setBook_no(((BookVo) list.get(0)).getNo());
				}
				else {
					System.out.println("잘못된 입력입니다.");
					break;
				}
				new CartDao().insert(cvo);
				break;
				
				default:
				System.out.println("잘못된 입력입니다.");
			}

		}
	}

	private static void wjscjfl() {
		{
			MemberVo vo = new MemberVo();
			vo.setName("kickscar");
			vo.setEmail("kickscar@gmail.com");
			vo.setPw("1234");
			vo.setPhonenum("010-1234-5678");

			new MemberDao().insert(vo);

			vo = new MemberVo();
			vo.setName("안지민");
			vo.setEmail("awlals@naver.com");
			vo.setPw("1234");
			vo.setPhonenum("010-1234-5678");

			new MemberDao().insert(vo);

			vo = new MemberVo();
			vo.setName("강소리");
			vo.setEmail("rkdthfl22@naver.com");
			vo.setPw("1234");
			vo.setPhonenum("010-1234-5678");

			new MemberDao().insert(vo);
		}

		{
			BookVo vo = new BookVo();
			vo.setTitle("데미안");
			vo.setPrice(30000L);
			vo.setCategory("소설");

			new BookDao().insert(vo);

			vo = new BookVo();
			vo.setTitle("리액트를 다루는 기술");
			vo.setPrice(35000L);
			vo.setCategory("컴퓨터/IT");

			new BookDao().insert(vo);

			vo = new BookVo();
			vo.setTitle("대규모 서비스를 지탱하는 기술");
			vo.setPrice(20000L);
			vo.setCategory("컴퓨터/IT");

			new BookDao().insert(vo);

			vo = new BookVo();
			vo.setTitle("총, 균, 쇠");
			vo.setPrice(25200L);
			vo.setCategory("문학사상");

			new BookDao().insert(vo);
		}
	}

}
