package lotto.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LottoNumbers {

    private final List<LottoNumber> numbers;

    public LottoNumbers(List<Integer> numbers) {
        validateSize(numbers);
        validateUnique(numbers);

        this.numbers = numbers.stream()
                .sorted()
                .map(LottoNumber::new)
                .toList();
    }

    public int getMatchCount(LottoNumbers other) {
        Set<LottoNumber> set = new HashSet<>(numbers);
        set.retainAll(other.numbers);

        return set.size();
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> extractValues() {
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
