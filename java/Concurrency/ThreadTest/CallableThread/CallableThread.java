package ThreadTest.CallableThread;

import java.util.concurrent.Callable;

public class CallableThread implements Callable<Integer> {

    private int which;

    public CallableThread(int which) {
        this.which = which;
    }

    @Override
    public Integer call() throws Exception {
        String currentThreadName = Thread.currentThread().getName();
        System.out.printf("%s : start to test call able %d \n", currentThreadName, which);
        //Thread.sleep(waitTime);
        int result = which;
        for (int i = 0; i < 100; i ++) {
            System.out.printf("%s : current i : %d \n", currentThreadName, i);
            result += i;
        }
        return result;
    }
}
