package eightytwo;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;

public class NumberTest {

  @Test
  public void testNumberInBase2() {
    final Number n1 = new Number(new BigInteger("21"), 2);
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(2));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(3));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(4));

    Assert.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumberInBase3() {
    final Number n1 = new Number(new BigInteger("21"), 3);
    Assert.assertEquals(Number.TWO, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(2));

    Assert.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumbesInBase4() {
    final Number n1 = new Number(new BigInteger("21"), 4);
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(2));
    Assert.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testNumberStringInteger() {
    final Number n1 = new Number(new BigInteger("10101", 2), 2);
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(2));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(3));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(4));

    Assert.assertEquals(new BigInteger("21"), n1.getValue());
  }

  @Test
  public void testGetNextOne2() {
    final Number n1 = new Number(new BigInteger("10101", 2), 2).getNextCandidate();

    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(2));
    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(3));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(4));

    Assert.assertEquals(new BigInteger("22"), n1.getValue());
    Assert.assertTrue(n1.hasBinaryRepresentation());
  }

  @Test
  public void testGetNextOne3() {
    final Number n1 = new Number(new BigInteger("20121", 3), 3).getNextCandidate();

    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(1));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(2));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(3));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(4));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(5));

    Assert.assertEquals(new BigInteger("243"), n1.getValue());
    Assert.assertTrue(n1.hasBinaryRepresentation());
  }

  @Test
  public void testGetNextOne4() {
    final Number n1 = new Number(new BigInteger("3", 4), 4).getNextCandidate();

    Assert.assertEquals(Number.ONE, n1.getDigits().charAt(0));
    Assert.assertEquals(Number.ZERO, n1.getDigits().charAt(1));

    Assert.assertEquals(new BigInteger("4"), n1.getValue());
    Assert.assertTrue(n1.hasBinaryRepresentation());
  }
}
