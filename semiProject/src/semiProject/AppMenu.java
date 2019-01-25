package semiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserSelectMenu usm = new UserSelectMenu();
	
	public void mainMenu() throws IOException {
		while (true) {
			try {
				int ch;
				do {
					System.out.println("푸드트럭 주문 앱");
					System.out.println("======================================");
					System.out.println("메인");
					System.out.println("1.유저 2.점주 3.관리자 4.종료");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1: usm.userMenu(); break;
				case 2: System.out.println("점주 메뉴가 들어갑니다."); break;
				case 3: System.out.println("관리자 메뉴가 들어갑니다."); break;
				case 4: System.exit(0);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (NumberFormatException e) {
				System.out.println(e.toString());
			}
		}
	}
}
