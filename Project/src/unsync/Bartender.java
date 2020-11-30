package unsync;

import java.util.Random;

import unsync.ProcessControlBlock.ThreadState;

public class Bartender implements Runnable {
	private Barrel barrel;
	

	public Bartender(Barrel barrel) {
		super();
		this.barrel = barrel;
	}
	private static int random() {
        Random random = new Random();
        return random.nextInt(50 + 1 - 10) + 10; // Generates a Random Number between Min: 10 | Max: 50
    }

	@Override
	public void run() {
		 while (true)
	            try {
	                if (Barrel.getTotal() == 0) {

	                    if (Main.BarrelRefills >= 1) // Checks to see if the Maximum Number Of Refills Have been met. 
	                    {
	                        Main.PCB.setWakeCount(Main.BarrelRefills);
	                        Thread.sleep(2000); // Sleeps for 2 seconds
	                        Main.BarrelRefills--;
	                        System.out.println("*******************************************************************");
	                        System.out.println("             Bartender Refilling Barrel                            ");
	                        System.out.println("             attempts remaining: " + Main.BarrelRefills + "/3");
	                        System.out.println("*******************************************************************");
	                        barrel.put(random());
	                    }
	                    else
	                    {
	                    	if (Main.BarrelRefills == 0) // Terminates The Program When Max Refills Have Been Met
	                    	{
	                    		System.out.println("Refills have been exceeded, simulation has finished.");
	    	                    Main.PCB.setThreadState(ThreadState.Terminated);
	    	                    System.out.println("[THREAD STATE]: " + Main.PCB.getThreadState());
	    	                    System.exit(0);
	                    	}
	                    }
	                }
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		
	}


}
