package eightytwo;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eightytwo.utils.IncrementalMagnitudeNumberSupplier;
import eightytwo.utils.MagnitudeRandeContinueCondition;

class AlgorithmSingleTest {

  IncrementalMagnitudeNumberSupplier magnitudeNumberSupplier;

  @Mock Consumer<NotationalNumber> resultConsumerMock;

  @Mock Consumer<NotationalNumber> failsConsumerMock;

  private BooleanSupplier continueCondition;

  @BeforeEach
  private void beforeEach() {
    MockitoAnnotations.initMocks(this);
    magnitudeNumberSupplier = new IncrementalMagnitudeNumberSupplier(1);
  }

  @Test
  void test() {
    /*
     * Given
     */
    continueCondition = new MagnitudeRandeContinueCondition(1, 100);
    /*
     * When
     */
    AlgorithmSingle underTest =
        new AlgorithmSingle(
            5, magnitudeNumberSupplier, resultConsumerMock, failsConsumerMock, continueCondition);

    underTest.search();

    Mockito.verify(resultConsumerMock, Mockito.times(1))
        .accept(Mockito.eq(new NotationalNumber("82000")));
    Mockito.verify(failsConsumerMock, Mockito.times(99)).accept(Mockito.any());
  }
}
