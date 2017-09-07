public class OddEvenPrintingThreads {

    public static Integer number = new Integer(0);
    public static  final Object doneLock = new Object();
    static final int COUNT_UPTO=10;

    public static void main(String args[])
    {

    Thread odd =new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (OddEven.doneLock) {
                    while(OddEven.number < COUNT_UPTO){
                        if (OddEven.number % 2 != 0) {
                            try {
                                Thread.sleep(100);
                                OddEven.doneLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            OddEven.number++;
                            System.out.println(Thread.currentThread().getName() + " : " + OddEven.number);
                            OddEven.doneLock.notifyAll();

                        }
                    }
                }

            }
        });
        odd.setName("ODD");

        Thread even =new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (OddEven.doneLock) {
                    while(OddEven.number < COUNT_UPTO){
                        if (OddEven.number % 2 == 0) {
                            try {
                                Thread.sleep(100);
                                OddEven.doneLock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            OddEven.number++;
                            System.out.println(Thread.currentThread().getName() + " : " + OddEven.number);
                            OddEven.doneLock.notifyAll();

                        }
                    }
                }
            }
        });
        even.setName("EVEN");

        odd.start();
        even.start();

        try {
            odd.join();
            even.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
