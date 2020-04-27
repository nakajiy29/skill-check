package q009;

public class MyTask implements Runnable {
    @Override
    public void run() {
        int count = 0;
        Q009.getValue();
        for (int i = 0; i < 5; i ++) {
            try {
                count++;
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}