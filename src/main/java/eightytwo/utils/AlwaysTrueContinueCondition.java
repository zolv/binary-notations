package eightytwo.utils;

import java.util.function.BooleanSupplier;

public class AlwaysTrueContinueCondition implements BooleanSupplier {

  @Override
  public boolean getAsBoolean() {
    return true;
  }
}
