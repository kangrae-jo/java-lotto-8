package lotto.domain.dto;

import java.util.Map;
import lotto.domain.Rank;

public record WinningStatistics(Map<Rank, Long> results) {

}
