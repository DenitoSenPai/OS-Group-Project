package unsync;

public class Student implements Runnable {
	
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
