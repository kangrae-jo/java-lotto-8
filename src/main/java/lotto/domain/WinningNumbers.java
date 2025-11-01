package lotto.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WinningNumbers {

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
