package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoTest {

    @Test
    void 로또는_당첨_번호와_일치한_개수를_반환한다() {
        Lotto lotto = new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumbers winningNumbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));

        int matchCount = lotto.getMatchCount(winningNumbers);

        assertThat(matchCount).isEqualTo(6);
    }

    @Test
    void 로또_번호에_보너스_번호가_포함되면_true_반환() {
        Lotto lotto = new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(5);

        boolean result = lotto.contains(bonus);

        assertThat(result).isTrue();
    }

    @Test
    void 로또_번호에_보너스_번호가_포함되지_않으면_false_반환() {
        Lotto lotto = new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)));
        LottoNumber bonus = new LottoNumber(10);

        boolean result = lotto.contains(bonus);

        assertThat(result).isFalse();
    }

}
