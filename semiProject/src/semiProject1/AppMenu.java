package semiProject1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// ListService ls = new ListService();
	private UserService us = new UserServiceImpl();
	//private TruckService ts = new TruckServiceImpl();
	//private AdminService as = new AdminServiceImpl();
	
	public void mainMenu() throws IOException {
		int ch;
		do {
			clearScreen();
			System.out.println("======================================");
			System.out.println("메인");
			System.out.println("1.유저 2.점주 3.관리자 4.종료");
			System.out.print("선택 => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1:
			userMenu();
			break;
		case 2:
			truckMenu();
			break;
		case 3:
			adminMenu();
			break;
		case 4:
			System.exit(0);
		}
	}

	// UserMenu
	
	public void userMenu() throws IOException{
		int ch;
		do {
			clearScreen();
			System.out.println("======================================");
			System.out.println("유저 메인");
			System.out.println("1.로그인 2.회원가입 3.뒤로가기 4.종료");
			System.out.print("선택 => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1:				
			if(us.userLogin()) //로그인 성공하면 userServiceMenu로 넘어간다.
				userServiceMenu();
			break;
		case 2:
			us.userRegister(); //회원가입은 성공하든 실패하든 유저메인으로 돌아온다.
			break;
		case 3:
			mainMenu();
			break;
		case 4:
			System.exit(0);
		}
	}	
	
	
	
	// user Menu
	public void userServiceMenu() throws IOException{
		
		while(true) {
			int ch;
			do {
				clearScreen();
				System.out.println("======================================");
				System.out.println("점주 메인");
				System.out.println("1.주변 푸드트럭 검색 2.분류별 찾기 3.키워드 검색 4.즐겨찾기 5.예약 확인 6.회원정보 7.로그아웃");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 7);

			switch (ch) {
			case 1:
				us.aroundSearch();
				break;
			case 2:
				foodCartegoryMenu();
				break;
			case 3:
				us.kewordSearch();
				break;
			case 4:
				us.bookMark();
				break;
			case 5:
				us.reservationCheck();
				break;
			case 6:
				us.userInfo();
				break;
			case 7:
				if(us.logout()) //로그아웃 성공하면 userMenu로.
					userMenu();
				break;
			}
		}		
	}

	
	
	
	
	public void foodCartegoryMenu() throws IOException{
	
		System.out.println("0이면 뒤로가기. 음식 대분류가 있다아아");
		
		int a = Integer.parseInt(br.readLine()); //숫자 입력받기
		
		if(a==0)
			return; //UserServiceMenu로 돌아간다.
		else
			System.out.println("음식 상세보기있다");
		
	}	
	
	
	
	
	
	
	
	
	//truck menu
	public void truckMenu() throws IOException{
		while(true) {
			int ch;
			do {
				clearScreen();
				System.out.println("======================================");
				System.out.println("유저 메뉴");
				System.out.println("1.로그인 2.회원가입 3.뒤로가기 4.종료");
				System.out.print("선택 => ");
				ch = Integer.parseInt(br.readLine());
			} while (ch < 1 || ch > 4);

			switch (ch) {
			case 1:				
				if(us.userLogin())
					userServiceMenu();
				break;
			case 2:
				us.userRegister();
				break;
			case 3:
				mainMenu();
				break;
			case 4:
				System.exit(0);
			}
			
		}
		
	}

	
	
	// Admin Menu
	public void adminMenu() throws IOException {
		int ch;
		do {
			clearScreen();
			System.out.println("======================================");
			System.out.println("관리자 메인");
			System.out.println("1.로그인  2.뒤로가기 3.종료");
			System.out.print("선택 => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 3);

		switch (ch) {
		case 1:
			System.out.println("로그인되었다.");
			break;
		case 2:
			mainMenu();
			break;
		case 3:
			System.exit(0);
		}
	}

	private void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

}
