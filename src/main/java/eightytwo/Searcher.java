package eightytwo;

import java.math.BigInteger;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Searcher {

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    final Algorithm algorithm = new Algorithm(5, new BigInteger("82001"));

    final ExecutorService es = Executors.newFixedThreadPool(3);
    final Future<Number> searcher = es.submit(() -> algorithm.getMinimal());

    final Timer timer = new Timer(true);

    final TimerTask consoleLogger = new TimerTask() {

      @Override
      public void run() {
        System.out.println("Candidate: ");
        final Number candidate = algorithm.getCandidate();
        final BigInteger value = candidate.getValue();

        final String digitsInBase10 = value.toString();
        System.out.println(" digits: " + digitsInBase10.length());
        System.out.println(" 10: " + digitsInBase10);
        System.out.println("  5: " + value.toString(5));
        System.out.println("  4: " + value.toString(4));
        System.out.println("  3: " + value.toString(3));
        System.out.println("  2: " + value.toString(2));
      }
    };
    timer.schedule(consoleLogger, 0, 10000);

    final TimerTask fileLogger = new TimerTask() {

      @Override
      public void run() {
        System.out.println("Candidate: ");
        final Number candidate = algorithm.getCandidate();
        final BigInteger value = candidate.getValue();

        final String digitsInBase10 = value.toString();
        System.out.println(" digits: " + digitsInBase10.length());
        System.out.println(" 10: " + digitsInBase10);
        System.out.println("  5: " + value.toString(5));
        System.out.println("  4: " + value.toString(4));
        System.out.println("  3: " + value.toString(3));
        System.out.println("  2: " + value.toString(2));
      }
    };
    timer.schedule(consoleLogger, 0, 10000);


    System.out.println("Result of searching: " + searcher.get().getDigits());
    timer.cancel();
    es.shutdown();
  }

}
