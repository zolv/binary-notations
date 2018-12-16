package eightytwo;

import java.math.BigInteger;
import org.junit.Assert;
import org.junit.Test;


public class AlgorithmTest {

  @Test
  public void test2() {
    final Algorithm algorithm = new Algorithm(2);
    final Number output = algorithm.getMinimal();
    Assert.assertEquals(new BigInteger("2"), output.getValue());
  }

  @Test
  public void test3() {
    final Algorithm algorithm = new Algorithm(3);
    final Number output = algorithm.getMinimal();
    Assert.assertEquals(new BigInteger("3"), output.getValue());
  }

  @Test
  public void test4() {
    final Algorithm algorithm = new Algorithm(4);
    final Number output = algorithm.getMinimal();
    Assert.assertEquals(new BigInteger("4"), output.getValue());
  }

  @Test
  public void test82000() {
    final Algorithm algorithm = new Algorithm(5);
    final Number output = algorithm.getMinimal();
    Assert.assertEquals(new BigInteger("82000"), output.getValue());
  }

  @Test
  public void test82000CustomStartPoint() {
    final Algorithm algorithm = new Algorithm(5, new BigInteger("12345"));
    final Number output = algorithm.getMinimal();
    Assert.assertEquals(new BigInteger("82000"), output.getValue());
  }

}
