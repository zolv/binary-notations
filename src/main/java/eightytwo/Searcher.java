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
import java.time.Instant;
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
    
    private static final Date startDate = new Date();
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
	
	    final String startValueFromFile = getStartValue();
	    final String startValue = startValueFromFile != null && !startValueFromFile.isEmpty() ? startValueFromFile
		    : DEFAULT_STARTING_POINT;

	    final Algorithm algorithm = new Algorithm(5, new BigInteger(startValue));
	    
	    final String fileName = LAST_CHECKED_FILE_NAMES_PREFIX + sdf.format(startDate) + ".txt";
	try {


	    final ExecutorService es = Executors.newFixedThreadPool(3);
	    final Future<Number> searcher = es.submit(algorithm::getMinimal);

	    final Timer timer = new Timer(true);

	    final TimerTask consoleLogger = new TimerTask() {

		@Override
		public void run() {
		    logCombined(algorithm);
		}

	    };
	    timer.schedule(consoleLogger, 0, LOG_PERIOD);

	    System.out.println("Result of searching: " + searcher.get().getDigits());

	    timer.cancel();
	    es.shutdown();
	} finally {
	    logCombined(algorithm);
	}
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

    private static synchronized void logDetailsToConsole(final Number candidate) {
	System.out.println("Candidate on " + new Date() + ": ");
	final BigInteger value = candidate.getValue();

	final String digitsInBase10 = value.toString();
	System.out.println(" digits: " + digitsInBase10.length());
//		System.out.println(" 10: " + digitsInBase10);
//		System.out.println("  5: " + value.toString(5));
//		System.out.println("  4: " + value.toString(4));
//		System.out.println("  3: " + value.toString(3));
//		System.out.println("  2: " + value.toString(2));
    }

    private static synchronized void logLastChecked(final Number candidate) {
	final File file = new File(LAST_CHECKED_STARTING_POINT_FILE_NAME);
	try (PrintWriter pw = new PrintWriter(file)) {

	    final String digitsInBase10 = candidate.getDigits(10);
	    pw.write(digitsInBase10);
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
    }
    
    private static synchronized void logDetailsToFiles(Number candidate) {
 	final File fileFull = new File( LAST_CHECKED_FILE_NAMES_PREFIX + sdf.format(startDate) + ".txt");
 	try (PrintWriter pwFull = new PrintWriter(fileFull);) {

	    final String digitsInBase10 = candidate.getDigits(10);
 	    pwFull.write("digits: " + digitsInBase10.length());
 	    pwFull.write("\n");
 	    pwFull.write(" 10: ");
 	    pwFull.write(digitsInBase10);
 	    pwFull.write("\n");
 	    pwFull.write("  6: ");
 	    pwFull.write(candidate.getDigits(6));
 	    pwFull.write("\n");
 	    pwFull.write("  5: ");
 	    pwFull.write(candidate.getDigits(5));
 	    pwFull.write("\n");
 	    pwFull.write("  4: ");
 	    pwFull.write(candidate.getDigits(4));
 	    pwFull.write("\n");
 	    pwFull.write("  3: ");
 	    pwFull.write(candidate.getDigits(3));
 	    pwFull.write("\n");
 	    pwFull.write("  2: ");
 	    pwFull.write(candidate.getDigits(2));
 	    pwFull.write("\n");
 	    
 	} catch (FileNotFoundException e) {
 	    e.printStackTrace();
 	}
     }


    private static synchronized void logCombined(final Algorithm algorithm) {
	Number candidate = algorithm.getCandidate();
	logLastChecked(candidate);
	logDetailsToFiles(candidate);
	logDetailsToConsole(candidate);
    }
    
    

}
