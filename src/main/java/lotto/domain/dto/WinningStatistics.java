package lotto.domain.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import lotto.domain.Rank;

public record WinningStatistics(Map<Rank, Long> results) {

    public double calculateYield(int money) {
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        return (double) totalPrize / money * 100;
    }

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
