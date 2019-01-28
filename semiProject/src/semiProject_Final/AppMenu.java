package semiProject_Final;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppMenu {

	public BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// ListService ls = new ListService();
	private UserSelectMenu usm = new UserSelectMenu();
	private TruckSelectMenu tsm = new TruckSelectMenu();
	private AdminSelectMenu asm = new AdminSelectMenu();

	public void mainMenu() {
		while (true) {
			try {
				int ch;
				do {
					Clear.clearScreen();
					System.out.println("======================================");
					System.out.println("����");
					System.out.println("1.���� 2.���� 3.������ 4.����");
					System.out.print("���� => ");
					ch = Integer.parseInt(br.readLine());
				} while (ch < 1 || ch > 4);

				switch (ch) {
				case 1:
					usm.mainMenu();
					break;
				case 2:
					tsm.mainMenu();
					break;
				case 3:
					asm.mainMenu();
					break;
				case 4:
					System.exit(0);
				}
			} catch (InterruptedException e) {
				System.out.println(e);
			}catch (IOException e) {
				System.out.println(e);
			}
		}
	}

}
