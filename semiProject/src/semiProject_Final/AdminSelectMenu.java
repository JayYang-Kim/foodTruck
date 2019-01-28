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
					System.out.println("������ ����");
					System.out.println("==================================================");
					System.out.println("1.�α��� 2.�ڷΰ��� 3.����");
					System.out.println("==================================================");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : if (as.logIn()) { // ���� : ture / ���� : false
					adminLoginMenu();
				} break; //�α��� �����ϸ� userServiceMenu�� �Ѿ��.
				case 2 : return; 
				case 4 :	
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
	
	public void adminLoginMenu() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("������ �α��� ����");
					System.out.println("==================================================");
					System.out.println("1.Ǫ��Ʈ�� ����Ʈ ��ȸ 2.���� ����Ʈ ��ȸ 3.�������� ���� 4.�α׾ƿ� 5.����");
					System.out.println("==================================================");
					System.out.print("���� => ");
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
	
	public void setBlackList() {
		int ch;
		
		while (true) {
			try {
				do {
					Clear.clearScreen();
					System.out.println("==================================================");
					System.out.println("�������� ����");
					System.out.println("==================================================");
					System.out.println("1.���� 2.���� 3.�ڷΰ���");
					System.out.println("==================================================");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 3);

				switch (ch) {
				case 1 : System.out.println("���ܵ��� ���� ���� ����Ʈ ���"); break;
				case 2 : System.out.println("���ܵ��� ���� ���� ����Ʈ ���"); break;
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
