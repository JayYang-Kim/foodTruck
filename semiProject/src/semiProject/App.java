package semiProject;

import java.io.IOException;

public class App {
		
	public static void main(String[] args) {
		AppMenu menu = new AppMenu();
		
		try {
			menu.mainMenu();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}
	
}
