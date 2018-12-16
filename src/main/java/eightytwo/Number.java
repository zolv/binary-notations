package eightytwo;

import java.math.BigInteger;

public class Number {

  private BigInteger value;

  private String digits;

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
    this.digits = digits;
  }

  private Number(BigInteger value, String digits, int system) {
    this.base = system;
    this.value = value;
    this.digits = digits;
  }

  public String getDigits() {
    if (this.digits == null) {
      this.digits = this.value.toString(this.base);
    }
    return this.digits;
  }

  public BigInteger getValue() {
    if (this.value == null) {
      this.value = new BigInteger(this.digits, this.base);
    }
    return this.value;
  }

  public Number getNextCandidate() {
    final int firstZeroFromRight = this.getFirstIndexOfZeroFromLeft();

    final int length = this.digits.length();
    final StringBuilder sb = new StringBuilder(length + 1);

    if (firstZeroFromRight > 0) {
      for (int i = 0; i < firstZeroFromRight; i++) {
        sb.append(this.digits.charAt(i));
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
    this.getDigits();
    for (int i = 0; i < this.digits.length(); i++) {
      if (this.digits.charAt(i) == ZERO) {
        current = i;
      } else {
        if (this.digits.charAt(i) != ONE) {
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
    return new Number(this.value, this.digits, this.base);
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
