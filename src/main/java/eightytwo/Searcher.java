package eightytwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Searcher {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		final Algorithm algorithm = new Algorithm(5, new BigInteger(
				"82001"));

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
		timer.schedule(consoleLogger, 0, 300000);
		final String fileName = "last-checked-" + DateTimeFormatter.ISO_DATE_TIME.format(OffsetDateTime.now()) + ".txt";

		final TimerTask fileLogger = new TimerTask() {

			@Override
			public void run() {
				final File file = new File(fileName);
				try (PrintWriter pw = new PrintWriter(file)) {
					final Number candidate = algorithm.getCandidate();

					final BigInteger value = candidate.getValue();
					String digitsInBase10 = value.toString();
					pw.write("digits: " + digitsInBase10.length());
					pw.write("\n");
					pw.write(" 10: ");
					pw.write(digitsInBase10);
					pw.write("\n");
					pw.write("  6: ");
					pw.write(value.toString(6));
					pw.write("\n");
					pw.write("  5: ");
					pw.write(value.toString(5));
					pw.write("\n");
					pw.write("  4: ");
					pw.write(value.toString(4));
					pw.write("\n");
					pw.write("  3: ");
					pw.write(value.toString(3));
					pw.write("\n");
					pw.write("  2: ");
					pw.write(value.toString(2));
					pw.write("\n");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
		};
		timer.schedule(fileLogger, 0, 300000);

		System.out.println("Result of searching: " + searcher.get().getDigits());
		timer.cancel();
		es.shutdown();
	}

}
