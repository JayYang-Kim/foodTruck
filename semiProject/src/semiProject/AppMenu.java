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
			System.out.println("[���� ȭ��]");
			System.out.println("==================================================");
			System.out.println("1.���� 2.���� 3.������ 4.����");
			System.out.println("==================================================");
			System.out.print("���� => ");
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
			System.out.println("���� ����");
			System.out.println("==================================================");
			System.out.println("1.�α��� 2.ȸ������ 3.�ڷΰ��� 4.����");
			System.out.println("==================================================");
			System.out.print("���� => ");
			ch = Integer.parseInt(br.readLine());
		} while (ch < 1 || ch > 4);

		switch (ch) {
		case 1 : us.logIn(); break; //�α��� �����ϸ� userServiceMenu�� �Ѿ��.			
		case 2 : us.signUP(); break; //ȸ�������� �����ϵ� �����ϵ� ������������ ���ƿ´�. 
		case 3 : mainMenu(); break; 
		case 4 : System.exit(0);
		}
	}	
	
	// Screen �ʱ�ȭ
	private void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

}
