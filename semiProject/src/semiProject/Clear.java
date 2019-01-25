package semiProject;

public class Clear {
	public static void clearScreen() throws InterruptedException {	
		Thread.sleep(1000);
		
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}
}
