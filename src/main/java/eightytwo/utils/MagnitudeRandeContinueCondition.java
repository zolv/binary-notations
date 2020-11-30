package eightytwo.utils;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;

public class MagnitudeRandeContinueCondition implements BooleanSupplier {
  int minMagnitude;
  int maxMagnitude;

  private final AtomicInteger magnitude;

  public MagnitudeRandeContinueCondition(int maxMagnitude) {
    this(1, maxMagnitude);
  }

  public MagnitudeRandeContinueCondition(int minMagnitude, int maxMagnitude) {
    super();
    this.minMagnitude = minMagnitude;
    this.maxMagnitude = maxMagnitude;
    magnitude = new AtomicInteger(minMagnitude);
  }

  @Override
  public boolean getAsBoolean() {
    return magnitude.getAndIncrement() <= maxMagnitude;
  }
}
