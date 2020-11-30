package eightytwo.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class IncrementalMagnitudeNumberSupplier
    implements Function<Integer, eightytwo.NotationalNumber> {

  private final AtomicInteger magnitude;

  @Override
  public eightytwo.NotationalNumber apply(Integer base) {
    int currentMagnitude = magnitude.getAndIncrement();
    return eightytwo.NotationalNumber.buildMagnitudeNumber(base, currentMagnitude);
  }

  public IncrementalMagnitudeNumberSupplier(int magnitude) {
    super();
    this.magnitude = new AtomicInteger(magnitude);
  }
}
