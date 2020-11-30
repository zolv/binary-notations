package eightytwo;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MagnitudeCheckerTest {

  @ParameterizedTest
  @ValueSource(ints = {2, 3, 4, 5, 6, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20})
  public void noCandidateInMagnitudes(int magnitude) {
    /*
     * Given
     */

    BigInteger decimalValue = new BigInteger(NotationalNumber.buildMagnitudeString(magnitude), 5);
    NotationalNumber initialCandidate = new NotationalNumber(decimalValue);
    MagnitudeChecker mc = new MagnitudeChecker(5, initialCandidate);

    /*
     * When
     */
    Optional<NotationalNumber> minimal = mc.getMinimal();

    /*
     * Then
     */
    Assertions.assertTrue(minimal.isEmpty());
  }

  @Test
  public void noCandidateInMagnitude7() {
    /*
     * Given
     */

    BigInteger decimalValue = new BigInteger(NotationalNumber.buildMagnitudeString(7), 5);
    NotationalNumber initialCandidate = new NotationalNumber(decimalValue);
    MagnitudeChecker mc = new MagnitudeChecker(5, initialCandidate);

    /*
     * When
     */
    Optional<NotationalNumber> minimal = mc.getMinimal();

    /*
     * Then
     */
    Assertions.assertTrue(minimal.isPresent());
    NotationalNumber result = minimal.get();
    Assertions.assertEquals("82000", result.getDigits(10));
  }
}
