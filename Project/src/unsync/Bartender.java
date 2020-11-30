package unsync;

import java.util.Random;

public class Bartender implements Runnable {
	private Barrel barrel;
	

	public Bartender(Barrel barrel) {
		super();
		this.barrel = barrel;
	}
	private static int random() {
        Random random = new Random();
        return random.nextInt(50 + 1 - 10) + 10; //min: 10 | max: 50
    }

	@Override
	public void run() {
		 while (true)
	            try {
	                if (Barrel.getTotal() == 0) {

	                    if (Main.BarrelRefills > 0) {
	                        Main.PCB.setWakeCount(Main.BarrelRefills);
	                        Thread.sleep(2000);
	                        Main.BarrelRefills--;
	                        System.out.println("*******************************************************************");
	                        System.out.println("             Bartender Refilling Barrel                            ");
	                        System.out.println("             attempts remaining: " + Main.BarrelRefills + "/3");
	                        System.out.println("*******************************************************************");
	                        barrel.put(random());
	                    }
	                }
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
		
	}


}
