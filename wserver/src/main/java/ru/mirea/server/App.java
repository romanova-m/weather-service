package ru.mirea.server;

public class App {

    public static void main( String[] args ) {
        try {
            Thread tserver = new Thread(new EchoServer());
            Thread tclient1 = new Thread(new EchoClient());
            Thread tclient2 = new Thread(new EchoClient());

            tserver.start();
            tclient1.start();
            tclient2.start();

            Thread.sleep(5000);

            tclient1.interrupt();
            tclient2.interrupt();
            tserver.interrupt();

        } catch (InterruptedException e) {
        }
    }
}
