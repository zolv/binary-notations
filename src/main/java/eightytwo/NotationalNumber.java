package eightytwo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = {"value"})
public class NotationalNumber {

  private BigInteger value;

  private Map<Integer, String> digits = new HashMap<>(3);

  public static final char ZERO = '0';

  public static final char ONE = '1';

  public static final char TWO = '2';

  public NotationalNumber(String value) {
    this(new BigInteger(value));
  }

  public NotationalNumber(BigInteger value) {
    this.value = value;
  }

  private NotationalNumber(String digits, int system) {
    this.value = new BigInteger(digits, system);
    this.digits.put(Integer.valueOf(system), digits);
  }

  public String getDigits(int baseParam) {
    return this.digits.computeIfAbsent(baseParam, k -> this.value.toString(baseParam));
  }

  public BigInteger getValue() {
    return this.value;
  }

  public NotationalNumber getNextCandidate(int base) {

    return getNextCandidateInSameMagnitude(base)
        .orElseGet(
            () -> new NotationalNumber(buildMagnitudeString(this.getDigits(base).length()), base));
  }

  public static NotationalNumber buildMagnitudeNumber(int base, final int magnitude) {
    return new NotationalNumber(new BigInteger(buildMagnitudeString(magnitude), base));
  }

  public static String buildMagnitudeString(final int length) {
    final StringBuilder sb = new StringBuilder(length + 1);
    sb.append(ONE);
    for (int i = 0; i < length; i++) {
      sb.append(ZERO);
    }
    return sb.toString();
  }

  public Optional<NotationalNumber> getNextCandidateInSameMagnitude(int base) {
    final String digitsInBase = this.getDigits(base);
    final int firstZeroFromLeft = this.getIndexOfFailure(digitsInBase);
    final int length = digitsInBase.length();

    final Optional<NotationalNumber> candidateOpt;
    if (firstZeroFromLeft > 0) {
      final StringBuilder sb = new StringBuilder(length + 1);
      for (int i = 0; i < firstZeroFromLeft; i++) {
        sb.append(digitsInBase.charAt(i));
      }

      sb.append(ONE);
      for (int i = firstZeroFromLeft + 1; i < length; i++) {
        sb.append(ZERO);
      }
      candidateOpt = Optional.of(new NotationalNumber(sb.toString(), base));
    } else {
      candidateOpt = Optional.empty();
    }

    return candidateOpt;
  }

  /**
   * Gives an index of last zero before a non binary digit (1 or 0) E.g. 10000100100000200010
   * returns 13
   *
   * @param digitsString
   * @return
   */
  public int getIndexOfFailure(final String digitsString) {

    int current = 0;
    for (int i = 0; i < digitsString.length(); i++) {
      final char charAt = digitsString.charAt(i);
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

  public boolean hasBinaryRepresentation(int base) {
    return this.getDigits(base).chars().allMatch(c -> c == ZERO || c == ONE);
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
