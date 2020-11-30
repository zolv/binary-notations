package eightytwo;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

import eightytwo.utils.IncrementalMagnitudeNumberSupplier;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlgorithmMulti {

  private final int initialBase;

  private int digits;
  private int digitsTotal;

  private Consumer<NotationalNumber> resultConsumer;

  private BooleanSupplier continueContidionSupplier;

  private Function<Integer, eightytwo.NotationalNumber> magnitudeSupplier;

  private Consumer<NotationalNumber> failsConsumer;

  private final int numberOfThreads;

  public AlgorithmMulti(
      int initialBase,
      int magnitude,
      BigInteger candidateToStartFrom,
      Consumer<NotationalNumber> resultConsumer,
      Consumer<NotationalNumber> failsConsumer,
      BooleanSupplier continueContidionSupplier,
      int numberOfThreads) {
    super();
    this.initialBase = initialBase;
    this.resultConsumer = resultConsumer;
    this.failsConsumer = failsConsumer;
    this.continueContidionSupplier = continueContidionSupplier;

    magnitudeSupplier = new IncrementalMagnitudeNumberSupplier(magnitude);
    this.numberOfThreads = numberOfThreads;
  }

  public void search() {

    final ExecutorService es = Executors.newFixedThreadPool(numberOfThreads);

    for (int i = 0; i < numberOfThreads; i++) {

      final AlgorithmSingle algorithm =
          new AlgorithmSingle(
              initialBase,
              magnitudeSupplier,
              resultConsumer,
              failsConsumer,
              continueContidionSupplier);
      es.execute(() -> algorithm.search());
    }
    log.info("After threads executions");
  }
}
