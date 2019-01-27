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
					System.out.println("���� ����");
					System.out.println("1.�α��� 2.ȸ������ 3.�ڷΰ��� 4.����");
					System.out.print("���� => ");
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
					System.out.println("���� ����");
					System.out.println("1.���� ���� 2.�ı� ��ȸ 3.���� 4.�̵�  5.���� 6.���� Ȯ�� 7.ȸ������ ���� 8.�α׾ƿ� 9.����");
					System.out.print("���� => ");
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
					System.out.println("�����մϴ�.");
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
					System.out.println("���� ����");
					System.out.println("���� �޼ҵ忡�� Ʈ�� ������ ���� ��������");

					System.out.println("======================================");

					System.out.println("1.���� ���� ���� 2.�ڷΰ���");
					System.out.print("���� => ");
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
					System.out.println("���� ���� ����");
					System.out.println("1.�޴� 2.���� ���� ���� 3.�������� 4.�ڷΰ���");
					System.out.print("���� => ");
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
					System.out.println("�޴� ����");
					System.out.println("======================================");
					tm.showFoodMenu();
					System.out.println("======================================");
					System.out.println("1.�޴� �߰� 2.�޴� ���� 3.�޴� ���� 4.�ڷΰ���");
					System.out.print("���� => ");
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
					System.out.println("���� ����");
					tm.showUserInfo();

					System.out.println("======================================");

					System.out.println("1.���� ����  2.Ż���ϱ� 3.�ڷΰ���");
					System.out.print("���� => ");
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
