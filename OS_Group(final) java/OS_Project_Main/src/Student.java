public class Student implements Runnable {

    private Barrel barrel;

    public Student(Barrel barrel) {
        this.barrel = barrel;
    }


    @Override
    public void run() {
        while (true) {
            barrel.get(); //gets, think and drink
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
