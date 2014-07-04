package ThreadTest.NormalThread;


import sun.tools.jconsole.Worker;

public class Main {
    public static void main (String[] args) {
//        runThreadByWorker();
        runThread();
        System.out.print("Continue to do other things. \n");
    }

//    private static void runThreadByWorker() {
//        Worker worker = new Worker("worker");
//        for (int i = 1; i < 10; i ++) {
//            Calculator calculator = new Calculator(i);
//            worker.add(calculator);
//        }
//        worker.start();
////        System.out.printf("Is Queue full %b \n", worker.queueFull());
////        do {
////            System.out.printf("Alive");
////        } while (worker.isAlive());
//        worker.stopWorker();
//    }

    private static void runThread() {
        for (int i = 1; i < 10; i ++) {
            Calculator calculator = new Calculator(i);
            Thread thread = new Thread(calculator);
            thread.start();
            //thread.run();
        }
    }
}
