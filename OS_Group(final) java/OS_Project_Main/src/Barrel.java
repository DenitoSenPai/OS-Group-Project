public class Barrel {

    private static int amount;
    private final int MAX = 50;
    private int count = 0;

    public Barrel() {
        amount = 0;
    }

    public static int getAmount() {
        return amount;
    }

    public synchronized void get() {
        try {
            if (amount > 0) {
                Main.pcb.setTid((int) Thread.currentThread().getId());
                count+=amount;
                Main.pcb.setProcessType(ProcessControlBlock.ProcessType.STUDENT);
                Main.pcb.setLightBeerRequired(true);
                Main.pcb.setLightBeerConsumption(count);
                amount--;

                System.out.println("Student picked up a beer, currently in barrel: " + amount);
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
            if (amount <= 1) {
                Main.pcb.setProcessType(ProcessControlBlock.ProcessType.BARTENDER);
                amount += n;
                System.out.println("Bartender has refilled: " + n + ", currently in barrel: " + amount);
                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.REFILLING);
                notify();
            } else if (amount + n > MAX) {
                System.out.println("Barrel is full!");
                Main.pcb.setThreadState(ProcessControlBlock.ThreadState.WAITING);
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
