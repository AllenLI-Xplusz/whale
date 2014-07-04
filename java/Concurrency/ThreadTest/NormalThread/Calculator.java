package ThreadTest.NormalThread;

public class Calculator implements Runnable {

    private int number;

    public Calculator(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 100; i ++) {
            System.out.printf("%s: %d * %d = %d \n", Thread.currentThread().getName(), number, i, i * number);
            if (i == 5) {
                //System.exit(0);
                // Exit Exception.
                // System.exit(1);
                // System.exit(-1);
            }
        }
    }

    public synchronized void critial() {
        // some thread critical stuff
        // here
    }

}
