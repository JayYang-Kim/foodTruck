package semiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private User us = new UserImpl();
	
	public void mainMenu() throws IOException {
		int ch;
		do {
			//clearScreen();
			System.out.println("[메인 화면]");
			System.out.println("==================================================");
			System.out.println("1.유저 2.점주 3.관리자 4.종료");
			System.out.println("==================================================");
			System.out.print("선택 => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1 : userMenu(); break;
		case 2: //truckMenu(); break;
		case 3: //adminMenu(); break;
		case 4: System.exit(0);
		}
	}

	// UserMenu
	
	public void userMenu() throws IOException{
		int ch;
		do {
			//clearScreen();
			System.out.println("유저 메인");
			System.out.println("==================================================");
			System.out.println("1.로그인 2.회원가입 3.뒤로가기 4.종료");
			System.out.println("==================================================");
			System.out.print("선택 => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1 : us.logIn(); break; //로그인 성공하면 userServiceMenu로 넘어간다.			
		case 2 : us.signUP(); break; //회원가입은 성공하든 실패하든 유저메인으로 돌아온다. 
		case 3 : mainMenu(); break; 
		case 4 : System.exit(0);
		}
	}	
	
	// Screen 초기화
	private void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

}
