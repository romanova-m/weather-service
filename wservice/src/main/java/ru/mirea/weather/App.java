package ru.mirea.weather;

import ru.mirea.data.DataSource;
import ru.mirea.server.EchoClient;
import ru.mirea.server.EchoServer;

public class App {
public static void main( String[] args ) {

    /*            CustomQueue inQueue = new CustomQueue();
                CustomQueue outQueue = new CustomQueue();

                TaskGenerator tg = new TaskGenerator(inQueue, dc);
                TaskExecutor te = new TaskExecutor(inQueue, outQueue, dc);
                Logger lg = new Logger(outQueue);

                Thread ttg = new Thread(tg);
                Thread tlg = new Thread(lg);
                Thread t1 = new Thread(te);
                //Thread t2 = new Thread(te);
		//Thread t3 = new Thread(te);
                //Thread t4 = new Thread(te);
*/
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
                   // Thread.sleep(10);
                    //tserver.interrupt();
                }
                catch (Exception e){};
/*
                ttg.start();
                tlg.start();
                t1.start();
                //t2.start();
		//t3.start();
		//t4.start();

                try {
                        Thread.sleep(300);
                } catch (InterruptedException e) {
                        e.printStackTrace();
                }

                ttg.interrupt();
                t1.interrupt();
                //t2.interrupt();
		//t3.interrupt();
		//t4.interrupt();
                tlg.interrupt();*/
}
}
