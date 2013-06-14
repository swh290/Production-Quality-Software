package edu.nyu.pqs.stopwatch.impl;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import edu.nyu.pqs.stopwatch.api.IStopwatch;

public class Test {

  public static void main(String[] args) {
    final int numThreads = 4;
    final StopwatchFactory swFac = new StopwatchFactory();
    final CountDownLatch ready = new CountDownLatch(numThreads);
    final CountDownLatch start = new CountDownLatch(1);
    final CountDownLatch done = new CountDownLatch(numThreads);
    ExecutorService executor = Executors.newCachedThreadPool();
    final IStopwatch sw = swFac.getStopwatch("1");
    sw.start();
    for (int i = 0; i < numThreads; i++) {
      executor.execute(new Runnable() {
        public void run() {
          ready.countDown();
          try {
            start.await();
            Thread.sleep(10);
            sw.lap();
            Thread.sleep(20);
            sw.lap();
            Thread.sleep(30);
          } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
          } finally {
            done.countDown();
          }
        }
      });
    }
    try {
      ready.await();
      start.countDown();
      done.await();
      sw.stop();
      System.out.println(swFac.getStopwatches());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

}
