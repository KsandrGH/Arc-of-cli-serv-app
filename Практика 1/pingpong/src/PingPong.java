public class PingPong {
    private static final Object lock = new Object();
    private static boolean isPingTurn = true;
    private static final int MAX_CYCLES = 10;

    public static void main(String[] args) {
        Thread pingThread = new Thread(() -> {
            try {
                synchronized (lock) {
                    for (int i = 0; i < MAX_CYCLES; i++) {
                        while (!isPingTurn) {
                            lock.wait();
                        }
                        System.out.println("Ping");
                        isPingTurn = false;
                        lock.notify();
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread pongThread = new Thread(() -> {
            try {
                synchronized (lock) {
                    for (int i = 0; i < MAX_CYCLES; i++) {
                        while (isPingTurn) {
                            lock.wait();
                        }
                        System.out.println("Pong");
                        isPingTurn = true;
                        lock.notify();
                        Thread.sleep(500);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pongThread.start();
        pingThread.start();
        //pongThread.start();
    }
}
