package eightytwo;

import java.util.Optional;

import eightytwo.model.MagnitudeResult;
import eightytwo.model.NotationalNumber;

public class MagnitudeChecker {

  private final int base;
  private NotationalNumber initialCandidate;

  public MagnitudeChecker(int base, NotationalNumber candidate) {
    super();
    this.base = base;
    this.initialCandidate = candidate;
  }

  public MagnitudeResult getMinimal() {
    Optional<NotationalNumber> candidateOpt = Optional.of(this.initialCandidate);
    Optional<NotationalNumber> lastFail = Optional.empty();
    int failPosition = -1;
    boolean wasBreak = true;
    while (wasBreak && candidateOpt.isPresent()) {
      wasBreak = false;
      final NotationalNumber candidate = candidateOpt.get();
      for (int i = this.base; i >= 3; i--) {
        final int currentFailPosition = candidate.getFailIndex(i);
        if (currentFailPosition >= 0) {
          if (failPosition < currentFailPosition) {
            failPosition = currentFailPosition;
          }
          consoleLog(candidate, i);
          lastFail = Optional.of(candidate);
          final Optional<NotationalNumber> nextOne = candidate.getNextCandidateInSameMagnitude(i);
          candidateOpt = nextOne;
          wasBreak = true;
          break;
        }
      }
    }
    final MagnitudeResult result =
        MagnitudeResult.builder()
            .initialCandidate(initialCandidate)
            .lastFail(lastFail)
            .success(candidateOpt)
            .failPosition(failPosition)
            .build();
    return result;
  }

  private static void consoleLog(NotationalNumber initialCandidate, int i) {
    if (i <= 4) {
      final String digitsInBaseI = initialCandidate.getDigits(i);
      final String digitsInBase5 = initialCandidate.getDigits(10);
      //      System.out.println(
      //          DateUtils.format(new Date())
      //              + "Digits in base "
      //              + 10
      //              + ": "
      //              + digitsInBase5.length()
      //              + ", failed at "
      //              + (initialCandidate.getIndexOfFailure(digitsInBaseI) + 2)
      //              + " in base "
      //              + i
      //              + ": "
      //              + digitsInBaseI.substring(0, Math.min(digitsInBaseI.length(), 100)));
    }
  }

  public NotationalNumber getCandidate() {
    return this.initialCandidate;
  }
}
