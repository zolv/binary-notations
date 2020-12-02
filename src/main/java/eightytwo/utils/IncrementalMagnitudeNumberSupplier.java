package eightytwo.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class IncrementalMagnitudeNumberSupplier
    implements Function<Integer, eightytwo.model.NotationalNumber> {

  private final AtomicInteger magnitude;

  @Override
  public eightytwo.model.NotationalNumber apply(Integer base) {
    int currentMagnitude = magnitude.getAndIncrement();
    return eightytwo.model.NotationalNumber.buildMagnitudeNumber(base, currentMagnitude);
  }

  public IncrementalMagnitudeNumberSupplier(int magnitude) {
    super();
    this.magnitude = new AtomicInteger(magnitude);
  }
}
