import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class NThreadSyn {
    final static Random r = new Random();
    final static CyclicBarrier cb;

    static {
        cb = new CyclicBarrier(Runtime.getRuntime().availableProcessors());
    }

    public static void main(String args[]) {
        for (int i = 0; i < Runtime.getRuntime().availableProcessors(); i++) {
            Thread thread = new Thread(new SpecialThread(cb, r));
            thread.setName(String.valueOf(i));
            thread.start();
        }
    }
}
class SpecialThread implements Runnable {
    MyCustomLatch countDownLatch = new MyCustomLatch();
    private CyclicBarrier cb;
    private final Random random;

    public SpecialThread(CyclicBarrier cb, final Random random) {
        this.random = random;
        this.cb = cb;
    }

    @Override
    public void run() {
        int i = 3;
        while (i > 0) {
            try {
                Thread.sleep(random.nextInt(100));
                rendezvous();
                countDownLatch.await();
                critical();
                countDownLatch.reset();
                cb.await();
                i--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    private void critical() {
        synchronized (random) {
            System.out.println(Thread.currentThread().getName() + " :  Critical Section " + random.nextInt(100));
        }
    }

    void rendezvous() {
        //System.out.println(Thread.currentThread().getName() + " :  Initial Section" );
        countDownLatch.countDown();
    }
}

class MyCustomLatch {
    private int initialValue = 1;
    private volatile CountDownLatch countDownLatch;

    public MyCustomLatch() {
        countDownLatch = new CountDownLatch(initialValue);
    }

    public void reset() {
        countDownLatch = new CountDownLatch(initialValue);
    }

    public void await() throws InterruptedException {
        countDownLatch.await();
    }

    public void countDown() {
        countDownLatch.countDown();
    }

}

