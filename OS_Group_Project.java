import java.util.Random; 

// Dinito Thompson : 1801202
// Shanice Facey : 
// Tyeree Tinka : 
// Doneque Smith : 

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
	
	public static class Student implements Runnable {
		
		private Barrel barrel; 
		
		public Student (Barrel barrel)
		{
			this.barrel = barrel; 
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (true)
			{
				barrel.retrieve(); // Calls the Retrieve Barrel Method 
				try {
					Thread.sleep(1000); // Sleeps The Thread For 1 Second
				} catch (InterruptedException e)
				{
					e.getMessage(); 
				}
			}
		}

	}
	
	public static class Barrel {
		 private static int total;
		    private final int MAX = 50;
		    private int count = 0;

		    public Barrel() {
		        total = 0;
		    }

		    public static int getTotal() {
		        return total;
		    }
		    public synchronized void retrieve() // Initiates And Displays The Thread State Every Time A Student Takes A Flask. 
		    {
		        try {
		            if (total > 0) // Displays The Thread State Until The Barrel Is Empty 
		            {
		                Main.PCB.setThreadID((int) Thread.currentThread().getId());
		                count+=total;
		                Main.PCB.setProcessState(Main.ProcessControlBlock.ProcessState.Student);
		                Main.PCB.setLightBeerRequired(true);
		                Main.PCB.setLightBeerConsumption(count);
		                total--;

		                System.out.println("Student picked up a beer, currently in barrel: " + total);
		                Main.PCB.setLightBeerRequired(false);
		                Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Drinking);
		                System.out.println("[THREAD STATE] Student is " + Main.PCB.getThreadState());
		                Thread.sleep(1000);
		                Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Thinking);
		                System.out.println("[THREAD STATE] Student is " + Main.PCB.getThreadState());
		                Thread.sleep(500);
		                notify();

		                Main.PCB.setLightBeerConsumption(Main.Counter++);
		            } 
		            else // Awakens The Bartender Thread If The Barrel Is Empty 
		            {
		                Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Waiting);
		                System.out.println("[THREAD STATE]: " + Main.PCB.getThreadState());
		                
		                System.out.println(" " +Main.PCB.toString()); // When Student Thread Is Paused, The Process Control Block Is Displayed
		                
		                System.out.println("Barrel is empty, notifying the bartender...");
		                if (Main.BarrelRefills == 0) // If The Refill Attempts Have Been Met, The Program Will Terminate/Exit.  
		                {
		                    System.out.println("Refills have been exceeded, simulation has finished.");
		                    Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Terminated);
		                    System.out.println("[THREAD STATE]: " + Main.PCB.getThreadState());
		                    System.exit(0);
		                }
		                wait();
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
		    public synchronized void put(int n) // Initiates The Bartender Thread So That The Barrel Can Be Refilled.
		    {
		        try {
		            if (total <= 1) {
		                Main.PCB.setProcessState(Main.ProcessControlBlock.ProcessState.Bartender);
		                total += n;
		                System.out.println("Bartender has refilled: " + n + ", Currently in barrel: " + total);
		                Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Refilling);
		                notify();
		            } else if (total + n > MAX) {
		                System.out.println("Barrel is full!");
		                Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Waiting);
		                wait();
		            }
		        } catch (InterruptedException e) {
		            e.printStackTrace();
		        }
		    }
	}


public static class Bartender implements Runnable {
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
	    	                    Main.PCB.setThreadState(Main.ProcessControlBlock.ThreadState.Terminated);
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


public static class ProcessControlBlock {
	
	enum ThreadState {
		Refilling, Drinking, Waiting, Terminated, Thinking; 
	}

	enum ProcessState{
		Student, Bartender; 
	}

	protected static int ProcessID; 
	protected static int ThreadID; 
	protected static ThreadState ThreadState; 
	protected static ProcessState ProcessState; 
	protected static boolean LightBeerRequired; 
	protected static int LightBeerConsumption; 
	protected static int WakeCount; 
	protected static int TurnAroundTime; 
	
	public ProcessControlBlock ()
	{
		ProcessControlBlock.ProcessID = 0; 
		ProcessControlBlock.ThreadID = 0; 
		ProcessControlBlock.ThreadState = null; 
		ProcessControlBlock.ProcessState = null; 
		ProcessControlBlock.LightBeerRequired = true; 
		ProcessControlBlock.LightBeerConsumption = 0;
		ProcessControlBlock.WakeCount = 0; 
		ProcessControlBlock.TurnAroundTime = 0; 
	}
	public ProcessControlBlock (int ProcessID, int ThreadID, ThreadState ThreadState, ProcessState ProcessState, boolean LightBeerRequired, int LightBeerConsumption, int WakeCount, int TurnAroundTime)
	{
		ProcessControlBlock.ProcessID = ProcessID; 
		ProcessControlBlock.ThreadID = ThreadID; 
		ProcessControlBlock.ThreadState = ThreadState; 
		ProcessControlBlock.ProcessState = ProcessState; 
		ProcessControlBlock.LightBeerRequired = LightBeerRequired; 
		ProcessControlBlock.LightBeerConsumption = LightBeerConsumption;
		ProcessControlBlock.WakeCount = WakeCount; 
		ProcessControlBlock.TurnAroundTime = TurnAroundTime; 
	} 
	
	public int getProcessID() {
		return ProcessID;
	}

	public void setProcessID(int processID) {
		ProcessControlBlock.ProcessID = processID;
	}

	public int getThreadID() {
		return ThreadID;
	}

	public void setThreadID(int threadID) {
		ProcessControlBlock.ThreadID = threadID;
	}

	public ThreadState getThreadState() {
		return ThreadState;
	}

	public void setThreadState(ThreadState threadState) {
		ProcessControlBlock.ThreadState = threadState;
	}

	public ProcessState getProcessState() {
		return ProcessState;
	}

	public void setProcessState(ProcessState processState) {
		ProcessControlBlock.ProcessState = processState;
	}

	public boolean isLightBeerRequired() {
		return LightBeerRequired;
	}

	public void setLightBeerRequired(boolean lightBeerRequired) {
		ProcessControlBlock.LightBeerRequired = lightBeerRequired;
	}

	public int getLightBeerConsumption() {
		return LightBeerConsumption;
	}

	public void setLightBeerConsumption(int lightBeerConsumption) {
		ProcessControlBlock.LightBeerConsumption = lightBeerConsumption;
	}

	public int getWakeCount() {
		return WakeCount;
	}

	public void setWakeCount(int wakeCount) {
		ProcessControlBlock.WakeCount = wakeCount;
	}

	public int getTurnAroundTime() {
		return TurnAroundTime;
	}

	public void setTurnAroundTime(int turnAroundTime) {
		ProcessControlBlock.TurnAroundTime = turnAroundTime;
	}
	
	@Override
	public String toString() {
		return "ProcessControlBlock [ProcessID=" + ProcessID + ", ThreadID=" + ThreadID + ", ThreadState=" + ThreadState
				+ ", LightBeerRequired=" + LightBeerRequired + ", LightBeerConsumption=" + LightBeerConsumption
				+ ", WakeCount=" + WakeCount + ", TurnAroundTime=" + TurnAroundTime + ", getProcessID()="
				+ getProcessID() + ", getThreadID()=" + getThreadID() + ", getThreadState()=" + getThreadState()
				+ ", isLightBeerRequired()=" + isLightBeerRequired() + ", getLightBeerConsumption()="
				+ getLightBeerConsumption() + ", getWakeCount()=" + getWakeCount() + ", getTurnAroundTime()="
				+ getTurnAroundTime() + "]";
	}
	
}

	
}