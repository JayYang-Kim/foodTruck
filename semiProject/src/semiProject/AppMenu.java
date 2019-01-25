package semiProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {
	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private UserSelectMenu usm = new UserSelectMenu();
	
	public void mainMenu() throws IOException {
		while (true) {
			try {
				int ch;
				do {
					System.out.println("Ǫ��Ʈ�� �ֹ� ��");
					System.out.println("======================================");
					System.out.println("����");
					System.out.println("1.���� 2.���� 3.������ 4.����");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1: usm.userMenu(); break;
				case 2: System.out.println("���� �޴��� ���ϴ�."); break;
				case 3: System.out.println("������ �޴��� ���ϴ�."); break;
				case 4: System.exit(0);
				}
			} catch (IOException e) {
				System.out.println(e.toString());
			} catch (NumberFormatException e) {
				System.out.println(e.toString());
			}
		}
	}
}
