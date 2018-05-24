package eightytwo;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Number {
	
	private final List< Integer > digits;
	
	private final Integer system;
	
	public static final Integer ZERO = Integer.valueOf( 0 );
	
	public static final Integer ONE = Integer.valueOf( 1 );
	
	public static final Integer TWO = Integer.valueOf( 2 );
	
	public static final Integer THREE = Integer.valueOf( 3 );
	
	public static final Integer FOUR = Integer.valueOf( 4 );
	
	public static final Integer FIVE = Integer.valueOf( 5 );
	
	public static final Integer SIX = Integer.valueOf( 6 );
	
	public static final Integer SEVEN = Integer.valueOf( 7 );
	
	public static final Integer EIGHT = Integer.valueOf( 8 );
	
	public static final Integer NINE = Integer.valueOf( 9 );
	
	public Number( String numberString, Integer system ) {
		this( Arrays.asList( numberString.split("")).stream().map( c -> Integer.valueOf(  c  ) ).collect( Collectors.toList() ), system );
	}
	
	public Number(BigInteger num, Integer system) {
		final BigInteger systemBig = BigInteger.valueOf( system );
		final List<Integer> digitsTemp = new ArrayList<>();
		BigInteger partial = num;
		
		while(!partial.equals( BigInteger.ZERO )) {
			final BigInteger rest = partial.mod( systemBig );
			digitsTemp.add( 0, rest.intValue() );
			partial = partial.divide( systemBig );
		}
		digitsTemp.add( 0, ZERO );
		this.digits = digitsTemp;
		this.system = system;
	}
	
	public Number( List< Integer > number, Integer system ) {
		super();
		this.normalize( number );
		this.digits = number;
		this.system = system;
	}
	
	public Number getNextOne() {
		final Number result;
		result = addOneToBinary();
		return result;
	}
	
	private Number addOneToBinary() {
		final int firstZeroFromRight = getFirstIndexOfZeroFromLeft();
		final List< Integer > newDigits;
		newDigits = new ArrayList<>( digits.size() + 1 );
		newDigits.addAll( digits );
		newDigits.set( firstZeroFromRight, ONE );
		for ( int i = firstZeroFromRight + 1 ; i < newDigits.size() ; i++ ) {
			newDigits.set( i, ZERO );
		}
		normalize( newDigits );
		return new Number( newDigits, system );
	}

	protected void normalize( final List< Integer > newDigits ) {
		if ( !newDigits.get( 0 ).equals( ZERO ) ) {
			newDigits.add( 0, ZERO );
		}
	}
	
	private int getFirstIndexOfZeroFromLeft() {
		int current = 0;
		for ( int i = 0 ; i < digits.size() ; i++ ) {
			if ( digits.get( i ).equals( ONE ) ) {

			} else {
				if ( digits.get( i ).equals( ZERO) ) {
					current = i;
				} else {
					return current;
				}
			}
		}
		return current;
	}
	
	private int getFirstIndexOfWrongDigit() {
		for ( int i = 0 ; i < digits.size() ; i++ ) {
			if ( digits.get( i ) > 1 ) {
				return i;
			}
		}
		return -1;
	}
	
	public BigInteger getValue() {
		final BigInteger systemBig = BigInteger.valueOf( system );
		BigInteger partial = BigInteger.ZERO;
		
		for ( int i = 0 ; i < digits.size() ; i++ ) {
			Integer digit = digits.get( i );
			partial = partial.multiply( systemBig ).add( BigInteger.valueOf( digit ) );
		}
		return partial;
	}
	
	
	public boolean isOne() {
		return digits.stream().allMatch( c -> c.equals( ZERO ) || c.equals( ONE ) );
	}
	
	public List< Integer > getNumber() {
		return digits;
	}
	
	public int getSystem() {
		return system;
	}
	
}
