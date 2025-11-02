package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {

    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validateSize(numbers);
        validateUnique(numbers);

        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    // TODO: 정렬하고 순차 비교로 변경
    public int getMatchCount(List<LottoNumber> winningNumbers) {
        Set<LottoNumber> lottoNumbersSet = new HashSet<>(numbers);
        lottoNumbersSet.retainAll(winningNumbers);
        return lottoNumbersSet.size();
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> extractNumbers() {
        return numbers.stream()
                .map(LottoNumber::value)
                .toList();
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
