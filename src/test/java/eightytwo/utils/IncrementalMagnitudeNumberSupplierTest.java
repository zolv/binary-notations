package eightytwo.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import eightytwo.model.NotationalNumber;

class IncrementalMagnitudeNumberSupplierTest {

  @Test
  void test() {
    /*
     * Given
     */
    IncrementalMagnitudeNumberSupplier underTest = new IncrementalMagnitudeNumberSupplier(1);

    /*
     * When
     */
    NotationalNumber n1 = underTest.apply(10);
    NotationalNumber n2 = underTest.apply(10);
    NotationalNumber n3 = underTest.apply(10);
    NotationalNumber n4 = underTest.apply(10);
    NotationalNumber n5 = underTest.apply(10);

    /*
     * Then
     */
    Assertions.assertEquals(new NotationalNumber("10"), n1);
    Assertions.assertEquals(new NotationalNumber("100"), n2);
    Assertions.assertEquals(new NotationalNumber("1000"), n3);
    Assertions.assertEquals(new NotationalNumber("10000"), n4);
    Assertions.assertEquals(new NotationalNumber("100000"), n5);
  }
}
