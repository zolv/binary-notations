package eightytwo;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eightytwo.utils.IncrementalMagnitudeNumberSupplier;
import eightytwo.utils.MagnitudeRandeContinueCondition;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AlgorithmMultiTest {
  IncrementalMagnitudeNumberSupplier magnitudeNumberSupplier;

  @Mock Consumer<NotationalNumber> resultConsumerMock;

  @Mock Consumer<NotationalNumber> failsConsumerMock;

  private BooleanSupplier continueCondition;

  @BeforeEach
  private void beforeEach() {
    MockitoAnnotations.initMocks(this);
    magnitudeNumberSupplier = new IncrementalMagnitudeNumberSupplier(1);
    continueCondition = new MagnitudeRandeContinueCondition(1, 100);
  }

  @Test
  void test2() {
    /*
     * Given
     */
    final AlgorithmMulti algorithm =
        new AlgorithmMulti(2, 1, null, resultConsumerMock, failsConsumerMock, continueCondition, 1);
    algorithm.search();
    Mockito.verify(resultConsumerMock, Mockito.times(1))
        .accept(Mockito.eq(new NotationalNumber("2")));
  }

  @Test
  public void test3() {
    /*
     * Given
     */
    ArgumentCaptor<NotationalNumber> valueCapture = ArgumentCaptor.forClass(NotationalNumber.class);
    /*
     * When
     */
    final AlgorithmMulti algorithm =
        new AlgorithmMulti(3, 1, null, resultConsumerMock, failsConsumerMock, continueCondition, 1);
    algorithm.search();
    Mockito.verify(resultConsumerMock, Mockito.atLeast(1)).accept(valueCapture.capture());
    List<NotationalNumber> allValues = valueCapture.getAllValues();
    Assertions.assertEquals(new NotationalNumber("3"), allValues);
  }

  @Test
  public void test4() {
    /*
     * Given
     */
    final AlgorithmMulti algorithm =
        new AlgorithmMulti(4, 1, null, resultConsumerMock, failsConsumerMock, continueCondition, 1);
    algorithm.search();
    Mockito.verify(resultConsumerMock, Mockito.times(1))
        .accept(Mockito.eq(new NotationalNumber("4")));
  }

  @Test
  public void test82000() {
    /*
     * Given
     */
    final AlgorithmMulti algorithm =
        new AlgorithmMulti(5, 1, null, resultConsumerMock, failsConsumerMock, continueCondition, 1);
    algorithm.search();
    Mockito.verify(resultConsumerMock, Mockito.times(1))
        .accept(Mockito.eq(new NotationalNumber("82000")));
  }

  @Test
  public void test82000CustomStartPoint() {
    /*
     * Given
     */
    final AlgorithmMulti algorithm =
        new AlgorithmMulti(5, 5, null, resultConsumerMock, failsConsumerMock, continueCondition, 1);
    algorithm.search();
    Mockito.verify(resultConsumerMock, Mockito.times(1))
        .accept(Mockito.eq(new NotationalNumber("82000")));
  }
}
