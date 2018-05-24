package eightytwo;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;


public class NumberTest {
	
	
	@Test
	public void testNumberSBigIntegerInteger2() {
		final Number n1 = new Number( new BigInteger( "21"), 2 );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get(3 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get(4 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 5 ) );
		
		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
	@Test
	public void testNumberSBigIntegerInteger3() {
		final Number n1 = new Number( new BigInteger( "21"), 3);
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.TWO, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get(3 ) );
		
		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
	@Test
	public void testNumberSBigIntegerInteger4() {
		final Number n1 = new Number( new BigInteger( "21"), 4);
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get(3 ) );
		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
	@Test
	public void testNumberStringInteger() {
		final Number n1 = new Number( "10101", 2 );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get(3 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get(4 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 5 ) );
		
		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
	@Test
	public void testGetNextOne2() {
		final Number n1 = new Number( "10101", 2 ).getNextOne();

		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get(3 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get(4 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 5 ) );

		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
	@Test
	public void testGetNextOne3() {
		final Number n1 = new Number( "20121", 3 ).getNextOne();

		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 0 ) );
		Assert.assertEquals( Number.ONE, n1.getNumber().get( 1 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 2 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get(3 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get(4 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 5 ) );
		Assert.assertEquals( Number.ZERO, n1.getNumber().get( 6 ) );

		Assert.assertEquals( new BigInteger( "21"), n1.getValue() );
	}
	
}
