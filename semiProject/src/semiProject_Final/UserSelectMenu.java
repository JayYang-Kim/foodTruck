package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserSelectMenu {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserService us = new UserServiceImpl();
	
	public void userMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("유저 메인");
					System.out.println("==================================================");
					System.out.println("1.로그인 2.회원가입 3.뒤로가기 4.종료");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1 : if (us.logIn()) { // 성공 : ture / 실패 : false
					userLoginMenu();
				} break; //로그인 성공하면 userServiceMenu로 넘어간다.
				case 2 : us.signUP(); break; //회원가입은 성공하든 실패하든 유저메인으로 돌아온다. 
				case 3 : return;
				case 4 : System.exit(0);
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
	
	public void userLoginMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("유저 로그인 메인");
					System.out.println("==================================================");
					System.out.println("1.자기주변 푸드트럭 검색 2.분류별 찾기 3.키워드 검색 4.즐겨찾기 5.예약 확인 6.회원정보 7.로그아웃 8.종료");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 8);

				switch (ch) {
				case 1 : System.out.println("자기주변 푸드트럭 검색"); break;
				case 2 : break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 6 : userInfoMenu(); break;
				case 7 : userMenu(); break;
				case 8 :
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
	
	public void userBookMarkMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("유저 즐겨찾기");
					System.out.println("==================================================");
					System.out.println("1.즐겨찾기 삭제 2.상세보기 3.뒤로가기");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : System.out.println("즐겨찾기 삭제"); break;
				case 2 : System.out.println("상세보기"); break;
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
	
	public void userInfoMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("회원정보");
					System.out.println("==================================================");
					System.out.println("1.조회 2.수정 3.포인트 조회 4.탈퇴 5.뒤로가기");
					System.out.println("==================================================");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 5);

				switch (ch) {
				case 1 : us.userInfo(); break;
				case 2 : us.updateUserInfo(); break;
				case 3 : us.myPoint(); break;
				case 4 : us.deleteUser(); break;
				case 5 : return;
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
