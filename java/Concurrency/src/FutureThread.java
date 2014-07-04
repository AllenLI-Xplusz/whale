import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created with IntelliJ IDEA.
 * User: wadehuang
 * Date: 6/25/14
 * Time: 2:29 PM
 * To change this template use File | Settings | File Templates.
 */
public class FutureThread implements Future {

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isCancelled() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isDone() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object get() throws InterruptedException, ExecutionException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
