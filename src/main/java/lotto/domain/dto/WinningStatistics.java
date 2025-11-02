package lotto.domain.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public record WinningStatistics(Map<Rank, Long> results) {

    public List<RankResult> toRankResults() {
        return Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NONE)
                .map(rank -> new RankResult(
                        rank.getMatchCount(),
                        rank.hasBonus(),
                        rank.getPrize(),
                        results.getOrDefault(rank, 0L)
                ))
                .toList();
    }

}
