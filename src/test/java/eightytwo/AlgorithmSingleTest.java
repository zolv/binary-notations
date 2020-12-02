package eightytwo;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eightytwo.model.MagnitudeResult;
import eightytwo.model.NotationalNumber;
import eightytwo.utils.IncrementalMagnitudeNumberSupplier;
import eightytwo.utils.MagnitudeRandeContinueCondition;

class AlgorithmSingleTest {

  IncrementalMagnitudeNumberSupplier magnitudeNumberSupplier;

  @Mock Consumer<MagnitudeResult> resultConsumerMock;

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
    final ArgumentCaptor<MagnitudeResult> resultCaptor =
        ArgumentCaptor.forClass(MagnitudeResult.class);
    continueCondition = new MagnitudeRandeContinueCondition(1, 100);
    /*
     * When
     */
    AlgorithmSingle underTest =
        new AlgorithmSingle(5, magnitudeNumberSupplier, resultConsumerMock, continueCondition);

    underTest.search();

    Mockito.verify(resultConsumerMock, Mockito.times(100)).accept(resultCaptor.capture());
    Assertions.assertEquals(
        1,
        resultCaptor
            .getAllValues()
            .stream()
            .filter(
                result ->
                    result.getFailPosition() == -1
                        && result.getSuccess().isPresent()
                        && result.getInitialCandidate() != null)
            .count());
    Assertions.assertEquals(
        99,
        resultCaptor
            .getAllValues()
            .stream()
            .filter(
                result ->
                    result.getLastFail().isPresent()
                        && result.getFailPosition() >= 0
                        && result.getSuccess().isEmpty()
                        && result.getInitialCandidate() != null)
            .count());
  }
}
