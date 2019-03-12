package eightytwo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Searcher {

	private static final String LAST_CHECKED_FILE_NAMES_PREFIX = "last-checked-";
	private static final String LAST_CHECKED_STARTING_POINT_FILE_NAME = "src/main/resources/last-checked.txt";
	private static final String DEFAULT_STARTING_POINT = "82000";
	private static final int LOG_PERIOD = 15 * 60 * 1000;
	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		final String startValueFromFile = getStartValue();
		final String startValue = startValueFromFile != null && !startValueFromFile.isEmpty() ? startValueFromFile
				: DEFAULT_STARTING_POINT;

		final Algorithm algorithm = new Algorithm(5, new BigInteger(startValue));

		final ExecutorService es = Executors.newFixedThreadPool(3);
		final Future<Number> searcher = es.submit(algorithm::getMinimal);

		final Timer timer = new Timer(true);

		final TimerTask consoleLogger = new TimerTask() {

			@Override
			public void run() {
				logDetailsToConsole(algorithm);
			}

		};
		timer.schedule(consoleLogger, 0, LOG_PERIOD);

		final String fileName = LAST_CHECKED_FILE_NAMES_PREFIX + sdf.format(new Date()) + ".txt";
		final TimerTask fileLogger = new TimerTask() {

			@Override
			public void run() {
				logDetailsToFiles(algorithm, fileName);

			}
		};
		timer.schedule(fileLogger, 0, LOG_PERIOD);

		System.out.println("Result of searching: " + searcher.get().getDigits());

		logDetailsToConsole(algorithm);
		logDetailsToFiles(algorithm, fileName);
		timer.cancel();
		es.shutdown();
	}

	private static String getStartValue() {
		String fileName = LAST_CHECKED_STARTING_POINT_FILE_NAME;
		final File file = new File(fileName);
		try (FileInputStream fis = new FileInputStream(file)) {
			Reader inputStreamReader = new InputStreamReader(fis);
			BufferedReader pw = new BufferedReader(inputStreamReader);
			return pw.readLine();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	private static synchronized void logDetailsToConsole(final Algorithm algorithm) {
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

	private static synchronized void logDetailsToFiles(final Algorithm algorithm, final String fileName) {
		final File fileFull = new File(fileName);
		final File file = new File(LAST_CHECKED_STARTING_POINT_FILE_NAME);
		try (PrintWriter pwFull = new PrintWriter(fileFull); PrintWriter pw = new PrintWriter(file)) {
			final Number candidate = algorithm.getCandidate();

			final BigInteger value = candidate.getValue();
			String digitsInBase10 = value.toString();
			pwFull.write("digits: " + digitsInBase10.length());
			pwFull.write("\n");
			pwFull.write(" 10: ");
			pwFull.write(digitsInBase10);

			pw.write(digitsInBase10);

			pwFull.write("\n");
			pwFull.write("  6: ");
			pwFull.write(value.toString(6));
			pwFull.write("\n");
			pwFull.write("  5: ");
			pwFull.write(value.toString(5));
			pwFull.write("\n");
			pwFull.write("  4: ");
			pwFull.write(value.toString(4));
			pwFull.write("\n");
			pwFull.write("  3: ");
			pwFull.write(value.toString(3));
			pwFull.write("\n");
			pwFull.write("  2: ");
			pwFull.write(value.toString(2));
			pwFull.write("\n");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
