package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TruckSelectMenu {
	// Truck Menu
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private TruckService tm = new TruckServiceImpl();

	public void mainMenu() {
		int ch;
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("점주 메인");
					System.out.println("1.로그인 2.회원가입 3.뒤로가기 4.종료");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1:
					if (tm.login())
						serviceMenu();
					break;
				case 2:
					tm.register();
					break;
				case 3:
					return;
				case 4:
					System.exit(0);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			}
		}
	}

	public void serviceMenu() {

		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("점주 메인");
					System.out.println("1.가게 정보 2.후기 조회 3.개점 4.이동  5.마감 6.예약 확인 7.회원정보 수정 8.로그아웃 9.종료");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 9);

				switch (ch) {
				case 1:
					truckInfoMenu();
					break;
				case 2:
					tm.showReview();
					break;
				case 3:
					tm.open();
					break;
				case 4:
					tm.move();
					break;
				case 5:
					tm.close();
					break;
				case 6:
					tm.checkReservation();
					break;
				case 7:
					userInfoMenu();
					break;
				case 8:
					if (tm.logOut())
						return;
					break;
				case 9:
					System.out.println("종료합니다.");
					System.exit(0);
				}

			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	public void truckInfoMenu() {
		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("가게 정보");
					System.out.println("유저 메소드에서 트럭 상세정보 보기 가져오기");

					System.out.println("======================================");

					System.out.println("1.가게 정보 수정 2.뒤로가기");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 2);

				if (ch == 2)
					return;

				truckUpdateMenu();

			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	public void truckUpdateMenu() {
		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("가게 정보 수정");
					System.out.println("1.메뉴 2.예약 가능 여부 3.공지사항 4.뒤로가기");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1:
					foodMenu();
					break;
				case 2:
					tm.updateReservation();
					break;
				case 3:
					tm.updateMemo();
					break;
				case 4:
					return;
				}

			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}

	public void foodMenu() {
		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("메뉴 수정");
					System.out.println("======================================");
					tm.showFoodMenu();
					System.out.println("======================================");
					System.out.println("1.메뉴 추가 2.메뉴 수정 3.메뉴 삭제 4.뒤로가기");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1:
					tm.insertMenu();
					break;
				case 2:
					tm.updateMenu();
					break;
				case 3:
					tm.deleteMenu();
					break;
				case 4:
					return;
				}

			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}
	}

	public void userInfoMenu() {
		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("점주 정보");
					tm.showUserInfo();

					System.out.println("======================================");

					System.out.println("1.정보 수정  2.탈퇴하기 3.뒤로가기");
					System.out.print("선택 => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 2);

				switch (ch) {
				case 1:
					tm.updateUserInfo();
					break;
				case 2:
					if (tm.withdraw())
						System.exit(0);
					break;
				case 3:
					return;
				}
			} catch (InterruptedException e) {
				System.out.println(e.toString());
			} catch (IOException e) {
				System.out.println(e.toString());
			}
		}

	}
}
