package ThreadTest.CallableThread;

//import javafx.concurrent.Worker;

import sun.tools.jconsole.Worker;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        CallableThread callableThread = new CallableThread(1);
        CallableThread callableThread1 = new CallableThread(2);
        CallableThread callableThread2 = new CallableThread(3);

        FutureTask<Integer> futureTask = new FutureTask<Integer>(callableThread);
        FutureTask<Integer> futureTask1 = new FutureTask<Integer>(callableThread1);
        FutureTask<Integer> futureTask2 = new FutureTask<Integer>(callableThread2);
        runTaskOnThreadPool(futureTask, futureTask1, futureTask2);
    }

    private static void runTaskOnThreadPool(FutureTask<Integer> futureTask, FutureTask<Integer> futureTask1, FutureTask<Integer> futureTask2) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(futureTask);
        forkJoinPool.execute(futureTask1);
        forkJoinPool.execute(futureTask2);

        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(futureTask);
        executorService.execute(futureTask1);
        executorService.execute(futureTask2);

        executorService.shutdown();
//        Integer result= futureTask.get();
//        Integer result2 = futureTask1.get();
//        IN

        while (true) {
            try {
                if (futureTask.isDone() && futureTask1.isDone() && futureTask2.isDone()) {
                    System.out.println("All Task is done");
                    Integer result= futureTask.get();
                    System.out.printf("Result = %d \n", result);
                    Integer result2 = futureTask2.get();
                    System.out.printf("Result = %d \n", result2);

                    executorService.shutdown();
                    return;
                }

                if (!futureTask.isDone()) {
                    System.out.println("Wait future task 1");
                }

                if (futureTask1.isDone()) {
                    System.out.println("Future task 2 is Done");
                    Integer result= futureTask1.get();
                    System.out.printf("Future task 2 = %d \n", result);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }
    }

}
