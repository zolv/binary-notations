package eightytwo.model;

import java.util.Optional;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MagnitudeResult {
  private final NotationalNumber initialCandidate;
  private final Optional<NotationalNumber> lastFail;
  private final int failPosition;
  private final Optional<NotationalNumber> success;
}
