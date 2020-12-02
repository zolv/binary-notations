package eightytwo;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Function;

import eightytwo.model.MagnitudeResult;
import eightytwo.model.NotationalNumber;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlgorithmSingle {

  private final int initialBase;

  private final Function<Integer, NotationalNumber> magnitudeSupplier;
  private final Consumer<MagnitudeResult> resultConsumer;
  private final BooleanSupplier continueCondition;

  private Consumer<MagnitudeResult> failsConsumer;

  public AlgorithmSingle(
      int initialBase,
      Function<Integer, NotationalNumber> magnitudeNumberSupplier,
      Consumer<MagnitudeResult> resultConsumer,
      BooleanSupplier continueContidionSupplier) {
    super();
    this.initialBase = initialBase;
    this.magnitudeSupplier = magnitudeNumberSupplier;
    this.resultConsumer = resultConsumer;
    this.continueCondition = continueContidionSupplier;
  }

  public void search() {
    while (continueCondition.getAsBoolean()) {
      NotationalNumber candidate = magnitudeSupplier.apply(initialBase);
      MagnitudeChecker checker = new MagnitudeChecker(initialBase, candidate);
      MagnitudeResult calculationResult = checker.getMinimal();
      resultConsumer.accept(calculationResult);
    }
  }
}
