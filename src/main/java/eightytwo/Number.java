package eightytwo;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Number {

  private BigInteger value;

  private Map<Integer, String> digits = new HashMap<>(3);

  private final int base;

  public static final char ZERO = '0';

  public static final char ONE = '1';

  public static final char TWO = '2';

  public Number(BigInteger value, int system) {
    this.value = value;
    this.base = system;
  }

  private Number(String digits, int system) {
    this.base = system;
    this.digits.put(Integer.valueOf(system), digits);
  }

  private Number(BigInteger value, String digits, int system) {
    this.base = system;
    this.value = value;
    this.digits.put(Integer.valueOf(system), digits);
  }

  public String getDigits() {
    return getDigits(base);
  }

  public String getDigits(int baseParam) {
    final String digitsResult;
    final String digitssTemp;
    if ((digitssTemp = this.digits.get(Integer.valueOf(baseParam))) == null) {
      final String digitsInBase = this.value.toString(baseParam);
      this.digits.put(Integer.valueOf(baseParam), digitsInBase);
      System.out.println(
          DateUtils.format(new Date())
              + " digits "
              + this.base
              + ": "
              + digitsInBase.substring(0, Math.min(digitsInBase.length(), 100))
              + "... total: "
              + digitsInBase.length());
      digitsResult = digitsInBase;
    } else {
      digitsResult = digitssTemp;
    }
    return digitsResult;
  }

  public BigInteger getValue() {
    if (this.value == null) {
      this.value = new BigInteger(this.digits.get(Integer.valueOf(this.base)), this.base);
    }
    return this.value;
  }

  public Number getNextCandidate() {
    final int firstZeroFromRight = this.getFirstIndexOfZeroFromLeft();
    String digitsInBase = this.digits.get(this.base);
    final int length = digitsInBase.length();
    final StringBuilder sb = new StringBuilder(length + 1);

    if (firstZeroFromRight > 0) {
      for (int i = 0; i < firstZeroFromRight; i++) {
        sb.append(digitsInBase.charAt(i));
      }

      sb.append(ONE);
      for (int i = firstZeroFromRight + 1; i < length; i++) {
        sb.append(ZERO);
      }
    } else {
      sb.append(ONE);
      for (int i = 0; i < length; i++) {
        sb.append(ZERO);
      }
    }

    return new Number(sb.toString(), this.base);
  }

  private int getFirstIndexOfZeroFromLeft() {
    int current = 0;
    String digitsInBase = this.getDigits();
    for (int i = 0; i < digitsInBase.length(); i++) {
      final char charAt = digitsInBase.charAt(i);
      if (charAt == ZERO) {
        current = i;
      } else {
        if (charAt != ONE) {
          return current;
        }
      }
    }
    return current;
  }

  public boolean hasBinaryRepresentation() {
    return this.getDigits().chars().allMatch(c -> c == ZERO || c == ONE);
  }

  public int getBase() {
    return this.base;
  }

  @Override
  public Number clone() {
    return new Number(this.value, this.digits.get(Integer.valueOf(this.base)), this.base);
  }

  @Override
  public String toString() {
    final String digitsInBase10 = this.getValue().toString();
    final StringBuilder sb = new StringBuilder(digitsInBase10.length() * 9);
    sb.append("Digits: ").append(digitsInBase10.length()).append("\n");
    sb.append(" 10: ").append(digitsInBase10).append("\n");
    sb.append("  5: ").append(this.value.toString(5)).append("\n");
    sb.append("  4: ").append(this.value.toString(4)).append("\n");
    sb.append("  3: ").append(this.value.toString(3));
    return sb.toString();
  }
}
