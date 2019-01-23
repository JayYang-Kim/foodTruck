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
			System.out.println("����");
			System.out.println("1.���� 2.���� 3.������ 4.����");
			System.out.print("���� => ");
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
			System.out.println("���� ����");
			System.out.println("1.�α��� 2.ȸ������ 3.�ڷΰ��� 4.����");
			System.out.print("���� => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1:				
			if(us.userLogin()) //�α��� �����ϸ� userServiceMenu�� �Ѿ��.
				userServiceMenu();
			break;
		case 2:
			us.userRegister(); //ȸ�������� �����ϵ� �����ϵ� ������������ ���ƿ´�.
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
				System.out.println("���� ����");
				System.out.println("1.�ֺ� Ǫ��Ʈ�� �˻� 2.�з��� ã�� 3.Ű���� �˻� 4.���ã�� 5.���� Ȯ�� 6.ȸ������ 7.�α׾ƿ�");
				System.out.print("���� => ");
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
				if(us.logout()) //�α׾ƿ� �����ϸ� userMenu��.
					userMenu();
				break;
			}
		}		
	}

	
	
	
	
	public void foodCartegoryMenu() throws IOException{
	
		System.out.println("0�̸� �ڷΰ���. ���� ��з��� �ִپƾ�");
		
		int a = Integer.parseInt(br.readLine()); //���� �Է¹ޱ�
		
		if(a==0)
			return; //UserServiceMenu�� ���ư���.
		else
			System.out.println("���� �󼼺����ִ�");
		
	}	
	
	
	
	
	
	
	
	
	//truck menu
	public void truckMenu() throws IOException{
		while(true) {
			int ch;
			do {
				clearScreen();
				System.out.println("======================================");
				System.out.println("���� �޴�");
				System.out.println("1.�α��� 2.ȸ������ 3.�ڷΰ��� 4.����");
				System.out.print("���� => ");
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
			System.out.println("������ ����");
			System.out.println("1.�α���  2.�ڷΰ��� 3.����");
			System.out.print("���� => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 3);

		switch (ch) {
		case 1:
			System.out.println("�α��εǾ���.");
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
