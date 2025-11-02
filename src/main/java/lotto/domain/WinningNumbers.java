package lotto.domain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lotto.domain.dto.WinningStatistics;

public class WinningNumbers {

    // TODO: 용어 정리 필요
    //  당첨 숫자 == 당첨 번호 + 보너스 번호
    private final LottoNumbers numbers;
    private final LottoNumber bonusNumber;

    public WinningNumbers(LottoNumbers numbers, LottoNumber bonusNumber) {
        validateUniqueBonus(numbers, bonusNumber);
        this.numbers = numbers;
        this.bonusNumber = bonusNumber;
    }

    // WinningNumbers를 기준으로 List<Lotto>를 평가하고,
    //  각 Rank별 당첨 개수를 계산하여 WinningStatistics로 변환한다.
    public WinningStatistics evaluateLottos(List<Lotto> lottos) {
        Map<Rank, Long> statistics = lottos.stream()
                .map(this::calculateRank)
                .collect(Collectors.groupingBy(rank -> rank, Collectors.counting()));

        return new WinningStatistics(statistics);
    }

    private Rank calculateRank(Lotto lotto) {
        int matchCount = lotto.getMatchCount(numbers);
        boolean matchBonus = lotto.contains(bonusNumber);

        return Rank.of(matchCount, matchBonus);
    }

    // TODO: 이 로직 WinningNumbers가 알고있는건 별로인 듯하여 분리 예정
    @Deprecated
    public double calculateYield(WinningStatistics winningStatistics, int money) {
        Map<Rank, Long> results = winningStatistics.results();
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        return (double) totalPrize / money * 100;
    }

    private void validateUniqueBonus(LottoNumbers numbers, LottoNumber bonusNumber) {
        if (numbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

}
