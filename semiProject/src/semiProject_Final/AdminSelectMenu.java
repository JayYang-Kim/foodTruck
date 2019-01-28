package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AdminSelectMenu {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private AdminService as = new AdminServiceImpl();
	
	public void mainMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("관리자 메인");
					System.out.println("==================================================");
					System.out.println("1.로그인 2.뒤로가기 3.종료");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : if (as.logIn()) { // 성공 : ture / 실패 : false
					adminLoginMenu();
				} break; //로그인 성공하면 userServiceMenu로 넘어간다.
				case 2 : return; 
				case 4 :	
					System.out.println("==================================================");
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public void adminLoginMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("관리자 로그인 메인");
					System.out.println("==================================================");
					System.out.println("1.푸드트럭 리스트 조회 2.유저 리스트 조회 3.유저차단 설정 4.로그아웃 5.종료");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 5);

				switch (ch) {
				case 1 : as.listFoodTruck(); break;
				case 2 : as.listUser(); break;
				case 3 : setBlackList(); break;
				case 4 : if (as.logOut()) {
					mainMenu();
				} break;
				case 5 :
					System.out.println("==================================================");
					System.out.println("프로그램을 종료합니다.");
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public void setBlackList() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("유저차단 설정");
					System.out.println("==================================================");
					System.out.println("1.유저 2.점주 3.뒤로가기");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : System.out.println("차단되지 않은 유저 리스트 출력"); break;
				case 2 : System.out.println("차단되지 않은 점주 리스트 출력"); break;
				case 3 : return;
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
}
