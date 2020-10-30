public class Main {

    public static int attempts = 3; //refill attempts
    public static int count = 0;

    static ProcessControlBlock pcb = new ProcessControlBlock();

    public static void main(String[] args) {
        System.out.println("---------------------------Simulation has started---------------------------");
        Barrel barrel = new Barrel();

        barrel.put(50); //starts the simulation with 50 beer

        Thread student = new Thread(new Student(barrel));
        Thread student1 = new Thread(new Student(barrel));
        Thread bartender = new Thread(new Bartender(barrel));

        student.start();
        student1.start();
        bartender.start();

        try {
            student.join();
            student1.join();
            bartender.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
