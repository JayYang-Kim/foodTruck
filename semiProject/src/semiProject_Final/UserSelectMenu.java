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
					System.out.println("���� ����");
					System.out.println("==================================================");
					System.out.println("1.�α��� 2.ȸ������ 3.�ڷΰ��� 4.����");
					System.out.println("==================================================");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1 : if (us.logIn()) { // ���� : ture / ���� : false
					userLoginMenu();
				} break; //�α��� �����ϸ� userServiceMenu�� �Ѿ��.
				case 2 : us.signUP(); break; //ȸ�������� �����ϵ� �����ϵ� ������������ ���ƿ´�. 
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
					System.out.println("���� �α��� ����");
					System.out.println("==================================================");
					System.out.println("1.�ڱ��ֺ� Ǫ��Ʈ�� �˻� 2.�з��� ã�� 3.Ű���� �˻� 4.���ã�� 5.���� Ȯ�� 6.ȸ������ 7.�α׾ƿ� 8.����");
					System.out.println("==================================================");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 8);

				switch (ch) {
				case 1 : System.out.println("�ڱ��ֺ� Ǫ��Ʈ�� �˻�"); break;
				case 2 : break;
				case 3 : break;
				case 4 : break;
				case 5 : break;
				case 6 : userInfoMenu(); break;
				case 7 : userMenu(); break;
				case 8 :
					System.out.println("==================================================");
					System.out.println("���α׷��� �����մϴ�.");
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
					System.out.println("���� ���ã��");
					System.out.println("==================================================");
					System.out.println("1.���ã�� ���� 2.�󼼺��� 3.�ڷΰ���");
					System.out.println("==================================================");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : System.out.println("���ã�� ����"); break;
				case 2 : System.out.println("�󼼺���"); break;
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
					System.out.println("ȸ������");
					System.out.println("==================================================");
					System.out.println("1.��ȸ 2.���� 3.����Ʈ ��ȸ 4.Ż�� 5.�ڷΰ���");
					System.out.println("==================================================");
					System.out.print("���� => ");
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
