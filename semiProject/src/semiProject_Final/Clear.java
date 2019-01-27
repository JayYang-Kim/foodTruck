package semiProject_Final;

public class Clear {
	public static void clearScreen() throws InterruptedException {
		Thread.sleep(500);		
		
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}
}
