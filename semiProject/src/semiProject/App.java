package semiProject;

import java.io.IOException;

public class App {
		
	public static void main(String[] args) {
		AppMenu menu = new AppMenu();
		
		try {
			System.out.println("==================================================");
			System.out.println("Ǫ��Ʈ�� �ܼ� ��");
			System.out.println("==================================================");
			
			menu.mainMenu();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
}
