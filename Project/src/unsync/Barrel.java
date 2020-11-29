package unsync;

public class Barrel {
	 private static int total;
	    private final int MAX = 50;
	    private int count = 0;

	    public Barrel() {
	        total = 0;
	    }

	    public static int getTotal() {
	        return total;
	    }
	    public synchronized void retrieve() {
	        try {
	            if (total > 0) {
	                Main.pcb.setTid((int) Thread.currentThread().getId());
	                count+=total;
	                Main.pcb.setProcessType(ProcessControlBlock.ProcessType.STUDENT);
	                Main.pcb.setLightBeerRequired(true);
	                Main.pcb.setLightBeerConsumption(count);
	                total--;

	                System.out.println("Student picked up a beer, currently in barrel: " + total);
	                Main.pcb.setLightBeerRequired(false);
	                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.DRINKING);
	                System.out.println("[THREAD STATE] Student is " + Main.pcb.getThreadState());
	                Thread.sleep(1000);
	                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.THINKING);
	                System.out.println("[THREAD STATE] Student is " + Main.pcb.getThreadState());
	                Thread.sleep(500);
	                notify();

	                Main.pcb.setLightBeerConsumption(Main.count++);
	            } else {
	                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.WAITING);
	                System.out.println("[THREAD STATE]: " + Main.pcb.getThreadState());

	                System.out.println("Barrel is empty, notifying the bartender...");
	                if (Main.attempts == 0) {
	                    System.out.println("Refills have been exceeded, simulation has finished.");
	                    Main.pcb.setThreadState(ProcessControlBlock.ThreadState.TERMINATED);
	                    System.out.println("[THREAD STATE]: " + Main.pcb.getThreadState());
	                    System.exit(0);
	                }
	                wait();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	    public synchronized void put(int n) {
	        try {
	            if (total <= 1) {
	                Main.pcb.setProcessType(ProcessControlBlock.ProcessType.BARTENDER);
	                total += n;
	                System.out.println("Bartender has refilled: " + n + ", currently in barrel: " + total);
	                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.REFILLING);
	                notify();
	            } else if (total + n > MAX) {
	                System.out.println("Barrel is full!");
	                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.WAITING);
	                wait();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }

}
