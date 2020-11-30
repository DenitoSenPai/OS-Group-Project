package unsync;

import unsync.ProcessControlBlock.ProcessState;
import unsync.ProcessControlBlock.ThreadState;

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
	    public synchronized void retrieve() // Initiates And Displays The Thread State Every Time A Student Takes A Flask. 
	    {
	        try {
	            if (total > 0) // Displays The Thread State Until The Barrel Is Empty 
	            {
	                Main.PCB.setThreadID((int) Thread.currentThread().getId());
	                count+=total;
	                Main.PCB.setProcessState(ProcessState.Student);
	                Main.PCB.setLightBeerRequired(true);
	                Main.PCB.setLightBeerConsumption(count);
	                total--;

	                System.out.println("Student picked up a beer, currently in barrel: " + total);
	                Main.PCB.setLightBeerRequired(false);
	                Main.PCB.setThreadState(ThreadState.Drinking);
	                System.out.println("[THREAD STATE] Student is " + Main.PCB.getThreadState());
	                Thread.sleep(1000);
	                Main.PCB.setThreadState(ThreadState.Thinking);
	                System.out.println("[THREAD STATE] Student is " + Main.PCB.getThreadState());
	                Thread.sleep(500);
	                notify();

	                Main.PCB.setLightBeerConsumption(Main.Counter++);
	            } 
	            else // Awakens The Bartender Thread If The Barrel Is Empty 
	            {
	                Main.PCB.setThreadState(ThreadState.Waiting);
	                System.out.println("[THREAD STATE]: " + Main.PCB.getThreadState());
	                
	                System.out.println(" " +Main.PCB.toString()); // When Student Thread Is Paused, The Process Control Block Is Displayed
	                
	                System.out.println("Barrel is empty, notifying the bartender...");
	                if (Main.BarrelRefills == 0) // If The Refill Attempts Have Been Met, The Program Will Terminate/Exit.  
	                {
	                    System.out.println("Refills have been exceeded, simulation has finished.");
	                    Main.PCB.setThreadState(ThreadState.Terminated);
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
	                Main.PCB.setProcessState(ProcessState.Bartender);
	                total += n;
	                System.out.println("Bartender has refilled: " + n + ", Currently in barrel: " + total);
	                Main.PCB.setThreadState(ThreadState.Refilling);
	                notify();
	            } else if (total + n > MAX) {
	                System.out.println("Barrel is full!");
	                Main.PCB.setThreadState(ThreadState.Waiting);
	                wait();
	            }
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
}
