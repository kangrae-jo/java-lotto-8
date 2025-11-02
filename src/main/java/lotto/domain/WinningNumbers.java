package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.domain.dto.WinningStatistics;

public class WinningNumbers {

    // TODO: 용어 정리 필요
    //  당첨 숫자 == 당첨 번호 + 보너스 번호
    private final List<LottoNumber> numbers;
    private final LottoNumber bonusNumber;

    // TODO: List<LottoNumber> -> LottoNumbers 생성 및 내부 검증
    //  현재 6개 숫자에대한 검증 로직이 겹침
    public WinningNumbers(List<Integer> numbers, int bonusNumber) {
        List<Integer> winningNumbers = new ArrayList<>(numbers);
        winningNumbers.add(bonusNumber);

        validateUnique(winningNumbers);
        validateSize(numbers);

        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
        this.bonusNumber = new LottoNumber(bonusNumber);
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
    public double calculateYield(WinningStatistics winningStatistics, int money) {
        Map<Rank, Long> results = winningStatistics.results();
        long totalPrize = results.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();

        return (double) totalPrize / money * 100;
    }

    private void validateSize(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void validateUnique(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 서로 중복되지 않아야 합니다.");
        }
    }

}
