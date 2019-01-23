package semiProject1;

import java.io.IOException;

public class App {

		
	public static void main(String[] args) {
		AppMenu am = new AppMenu();

		try {
			am.mainMenu();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	
}
