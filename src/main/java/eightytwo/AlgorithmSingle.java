package eightytwo;

import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlgorithmSingle {

  private final int initialBase;

  private final Function<Integer, NotationalNumber> magnitudeSupplier;
  private final Consumer<NotationalNumber> resultConsumer;
  private final BooleanSupplier continueCondition;

  private Consumer<NotationalNumber> failsConsumer;

  public AlgorithmSingle(
      int initialBase,
      Function<Integer, NotationalNumber> magnitudeNumberSupplier,
      Consumer<NotationalNumber> resultConsumer,
      Consumer<NotationalNumber> failsConsumer,
      BooleanSupplier continueContidionSupplier) {
    super();
    this.initialBase = initialBase;
    this.magnitudeSupplier = magnitudeNumberSupplier;
    this.resultConsumer = resultConsumer;
    this.failsConsumer = failsConsumer;
    this.continueCondition = continueContidionSupplier;
  }

  public void search() {
    while (continueCondition.getAsBoolean()) {
      NotationalNumber candidate = magnitudeSupplier.apply(initialBase);
      MagnitudeChecker checker = new MagnitudeChecker(initialBase, candidate);
      Optional<NotationalNumber> minimalOpt = checker.getMinimal();
      log.info("minimal opt: " + minimalOpt);
      Consumer<? super NotationalNumber> ifPresentAction =
          result -> {
            resultConsumer.accept(result);
          };
      Runnable ifEmptyAction =
          () -> {
            failsConsumer.accept(candidate);
          };
      minimalOpt.ifPresentOrElse(ifPresentAction, ifEmptyAction);
    }
  }
}
