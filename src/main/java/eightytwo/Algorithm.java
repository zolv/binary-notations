package eightytwo;

import java.math.BigInteger;

public class Algorithm {

  private final int base;
  private Number candidate;

  private int digits;
  private int digitsTotal;

  public Algorithm(int base) {
    this(base, new BigInteger("2"));
  }

  public Algorithm(int base, BigInteger candidateToStartFrom) {
    super();
    this.base = base;
    this.candidate = new Number(candidateToStartFrom, base);
  }

  public Number getMinimal() {

    boolean wasBreak = true;
    while (wasBreak) {
      wasBreak = false;
      for (int i = this.base; i >= 3; i--) {

        final Number candidateInGivenBase =
            this.candidate.getBase() == i
                ? this.candidate
                : new Number(this.candidate.getValue(), i);
        if (!candidateInGivenBase.hasBinaryRepresentation()) {
          final Number nextOne = candidateInGivenBase.getNextCandidate();
          this.candidate = new Number(nextOne.getValue(), this.base);
          wasBreak = true;
          break;
        }
      }
    }
    return this.candidate;
  }

  public Number getCandidate() {
    return this.candidate;
  }
}
