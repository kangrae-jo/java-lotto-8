package lotto.domain;

import java.util.List;

public class Lotto {

    private final LottoNumbers numbers;

    public Lotto(LottoNumbers numbers) {
        this.numbers = numbers;
    }

    public int getMatchCount(LottoNumbers winningNumbers) {
        return numbers.getMatchCount(winningNumbers);
    }

    public boolean contains(LottoNumber bonusNumber) {
        return numbers.contains(bonusNumber);
    }

    public List<Integer> extractNumbers() {
        return numbers.extractValues();
    }

}
