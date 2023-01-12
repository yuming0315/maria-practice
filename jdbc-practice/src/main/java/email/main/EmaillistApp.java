package email.main;

import java.util.List;
import java.util.Scanner;

import email.dao.EmaillistDao;
import email.vo.EmaillistVo;

public class EmaillistApp {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		while (true) {
			System.out.print("(l)ist (d)elete (i)nsert (q)uit > ");
			String command = scanner.nextLine();

			if ("q".equals(command) || "Q".equals(command)) {
				break;
			}

			switch (command) {
			case "l":
			case "L":
				doList();
				break;
			case "d":
			case "D":
				doDelete();
				break;
			case "i":
			case "I":
				doInsert();
				break;
			default:
				System.out.println("잘못된 입력입니다.");
			}

		}

		scanner.close();
	}

	private static void doDelete() {
		System.out.print("이메일:");
		String email = scanner.nextLine();

		new EmaillistDao().deleteByEmail(email);
		doList();
	}

	private static void doInsert() {
		System.out.print("성:");
		String firstName = scanner.nextLine();

		System.out.print("이름:");
		String lastName = scanner.nextLine();

		System.out.print("이메일:");
		String email = scanner.nextLine();

		EmaillistVo vo = new EmaillistVo();
		vo.setFirstName(firstName);
		vo.setLastName(lastName);
		vo.setEmail(email);

		new EmaillistDao().insert(vo);
		doList();
	}

	private static void doList() {
		List<EmaillistVo> list = new EmaillistDao().findAll();
		for (EmaillistVo vo : list) {
			System.out.println("이름:" + vo.getFirstName() + vo.getLastName() + ", 이메일:" + vo.getEmail());
		}
	}
}
