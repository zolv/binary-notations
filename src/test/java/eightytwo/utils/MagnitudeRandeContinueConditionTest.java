package eightytwo.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class MagnitudeRandeContinueConditionTest {

  @Test
  void test() {
    /*
     * Given
     */

    MagnitudeRandeContinueCondition underTest = new MagnitudeRandeContinueCondition(1, 3);

    /*
     * When
     */
    boolean m1 = underTest.getAsBoolean();
    boolean m2 = underTest.getAsBoolean();
    boolean m3 = underTest.getAsBoolean();
    boolean m4 = underTest.getAsBoolean();
    boolean m5 = underTest.getAsBoolean();

    /*
     * Then
     */
    assertTrue(m1);
    assertTrue(m2);
    assertTrue(m3);
    assertFalse(m4);
    assertFalse(m5);
  }
}
