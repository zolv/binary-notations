package eightytwo;

import java.math.BigInteger;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import eightytwo.model.NotationalNumber;

public class NumberTest {

  @Test
  public void testNumberInBase2() {
    final NotationalNumber n1 = new NotationalNumber(new BigInteger("21"));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(0));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(1));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(2));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(3));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(4));

    Assertions.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumberInBase3() {
    final NotationalNumber n1 = new NotationalNumber(new BigInteger("21"));
    Assertions.assertEquals(NotationalNumber.TWO, n1.getDigits(3).charAt(0));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(3).charAt(1));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(2));

    Assertions.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumbesInBase4() {
    final NotationalNumber n1 = new NotationalNumber(new BigInteger("21"));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(4).charAt(0));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(4).charAt(1));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(4).charAt(2));
    Assertions.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumberStringInteger() {
    final NotationalNumber n1 = new NotationalNumber(new BigInteger("10101", 2));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(0));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(1));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(2));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(3));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(4));

    Assertions.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testGetNextOne2() {
    final NotationalNumber n1 =
        new NotationalNumber(new BigInteger("10101", 2)).getNextCandidate(2);

    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(0));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(1));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(2));
    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(2).charAt(3));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(2).charAt(4));

    Assertions.assertEquals(new BigInteger("22"), n1.getValue());
    Assertions.assertTrue(n1.hasBinaryRepresentation(2));
  }

  @Test
  public void testGetNextOne3() {
    final NotationalNumber n1 =
        new NotationalNumber(new BigInteger("20121", 3)).getNextCandidate(3);

    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(3).charAt(0));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(1));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(2));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(3));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(4));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(3).charAt(5));

    Assertions.assertEquals(new BigInteger("243"), n1.getValue());
    Assertions.assertTrue(n1.hasBinaryRepresentation(3));
  }

  @Test
  public void testGetNextOne4() {
    final NotationalNumber n1 = new NotationalNumber(new BigInteger("3", 4)).getNextCandidate(4);

    Assertions.assertEquals(NotationalNumber.ONE, n1.getDigits(4).charAt(0));
    Assertions.assertEquals(NotationalNumber.ZERO, n1.getDigits(4).charAt(1));

    Assertions.assertEquals(new BigInteger("4"), n1.getValue());
    Assertions.assertTrue(n1.hasBinaryRepresentation(4));
  }
}
