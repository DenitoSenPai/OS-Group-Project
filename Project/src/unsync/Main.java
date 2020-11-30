package unsync;

public class Main {	
	
	public static int BarrelRefills = 3; // Number Of Shots
	public static int Counter = 0; // Student Shots Tracker

	static ProcessControlBlock PCB = new ProcessControlBlock(); 
	
	public static void main (String[] args)
	{
		System.out.println("----Program----Has----Begun----");
		Barrel barrel = new Barrel(); 
		
		barrel.put(50);
		
		// Creates 2 Student Thread And 1 Bartender Thread
		Thread student_one = new Thread (new Student (barrel)); 
		Thread student_two = new Thread (new Student (barrel)); 
		Thread bartender = new Thread (new Bartender (barrel)); 
		
		// Initiates The Threads 
		student_one.start();
		student_two.start();
		bartender.start();
		
		try {
			
			// Allows each thread to complete their execution before running another. 
			student_one.join();
			student_two.join();
			bartender.join();
		} catch (InterruptedException e)
		{
			e.getMessage(); 
		}
	}

}
