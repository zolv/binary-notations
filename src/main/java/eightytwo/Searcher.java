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
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;

import eightytwo.model.MagnitudeResult;
import eightytwo.model.NotationalNumber;
import eightytwo.utils.AlwaysTrueContinueCondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Searcher {

  private static final String DOT_EXTENTION = ".txt";
  private static final String LAST_CHECKED_FILE_NAMES_PREFIX = "last-checked-";
  private static final String RESULT_FOLDER = "src/main/resources";
  private static final String RESULT_FILE_PREFIX = "result";
  private static final String MAGNITUDE_FILE_PREFIX = "last-magnitude";
  private static final String LAST_CHECKED_STARTING_POINT_FILE_NAME =
      RESULT_FOLDER + "/" + MAGNITUDE_FILE_PREFIX + DOT_EXTENTION;
  private static final String DEFAULT_STARTING_POINT = "82000";
  private static final int LOG_PERIOD = 15 * 60 * 1000;
  private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS");

  private static final Date startDate = new Date();

  private static final int initialBase = 5;

  public static void main(String[] args) throws InterruptedException, ExecutionException {

    final int startMagnitude = Integer.valueOf(getStartValue(initialBase));
    log.info("Starting from magnitude :" + startMagnitude);
    Consumer<MagnitudeResult> resultConsumer = buildResultConsumer();

    AlgorithmMulti algorith =
        new AlgorithmMulti(
            initialBase,
            startMagnitude,
            null,
            resultConsumer,
            new AlwaysTrueContinueCondition(),
            8);
    algorith.search();
  }

  private static Consumer<MagnitudeResult> buildResultConsumer() {
    return new Consumer<MagnitudeResult>() {

      private LocalDateTime lastLog;

      @Override
      public synchronized void accept(MagnitudeResult result) {
        if (result.getSuccess().isPresent()) {
          NotationalNumber successResult = result.getSuccess().get();
          logResultMagnitude(successResult, initialBase);

          logDetailsToConsole(successResult);
        } else {
          final LocalDateTime now = LocalDateTime.now();
          int magnitude = result.getInitialCandidate().getDigits(initialBase).length();
          if (lastLog == null || lastLog.until(now, ChronoUnit.SECONDS) >= 60) {
            lastLog = now;
            logLastCheckedMagnitude(initialBase, magnitude);
          }
          log.info(
              "Failed in base "
                  + initialBase
                  + " at magnitude "
                  + magnitude
                  + " at max position: "
                  + result.getFailPosition());
          //                  + " 5: "
          //                  + result
          //                      .getLastFail()
          //                      .get()
          //                      .getDigits(initialBase)
          //                      .substring(0, result.getFailPosition() + 1)
          //                  + ", 4: "
          //                  + result
          //                      .getLastFail()
          //                      .get()
          //                      .getDigits(4)
          //                      .substring(0, result.getFailPosition() + 1));
        }
      }
    };
  }

  private static String getStartValue(int base) {
    String fileName = buildLastCheckFilePath(base);
    final File file = new File(fileName);
    try (FileInputStream fis = new FileInputStream(file)) {
      Reader inputStreamReader = new InputStreamReader(fis);
      BufferedReader pw = new BufferedReader(inputStreamReader);
      return pw.readLine();

    } catch (Exception e) {
      e.printStackTrace();
    }

    return "1";
  }

  private static synchronized void logDetailsToConsole(final NotationalNumber candidate) {
    log.info("Candidate on " + new Date() + ": ");
    final BigInteger value = candidate.getValue();

    final String digitsInBase10 = value.toString();
    log.info(" digits: " + digitsInBase10.length());
    log.info(" 10: " + digitsInBase10.substring(0, Math.min(digitsInBase10.length(), 1000)));
    String digitsInBase5 = value.toString(5);
    log.info("  5: " + digitsInBase5.substring(0, Math.min(digitsInBase5.length(), 1000)));
    String digitsInBase4 = value.toString(4);
    log.info("  4: " + digitsInBase4.substring(0, Math.min(digitsInBase4.length(), 1000)));
    String digitsInBase3 = value.toString(3);
    log.info("  3: " + digitsInBase3.substring(0, Math.min(digitsInBase3.length(), 1000)));
    String digitsInBase2 = value.toString(2);
    log.info("  2: " + digitsInBase2.substring(0, Math.min(digitsInBase2.length(), 1000)));
  }

  private static synchronized void logLastCheckedMagnitude(int base, final int magnitude) {
    final File file = new File(buildLastCheckFilePath(base));
    try (PrintWriter pw = new PrintWriter(file)) {

      final String digitsInBase10 = String.valueOf(magnitude);
      pw.write(digitsInBase10);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static String buildLastCheckFilePath(int base) {
    return RESULT_FOLDER + "/" + MAGNITUDE_FILE_PREFIX + "-" + base + DOT_EXTENTION;
  }

  private static synchronized void logResultMagnitude(NotationalNumber result, int base) {
    final int magnitude = result.getDigits(initialBase).length();
    final File file =
        new File(
            RESULT_FOLDER
                + "/"
                + RESULT_FILE_PREFIX
                + "-"
                + base
                + "-"
                + magnitude
                + DOT_EXTENTION);
    try (PrintWriter pw = new PrintWriter(file)) {

      final String digitsInBase10 = result.getDigits(10);
      pw.write(digitsInBase10);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static synchronized void logLastChecked(final NotationalNumber candidate) {
    final File file = new File(LAST_CHECKED_STARTING_POINT_FILE_NAME);
    try (PrintWriter pw = new PrintWriter(file)) {

      final String digitsInBase10 = candidate.getDigits(10);
      pw.write(digitsInBase10);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }
}
