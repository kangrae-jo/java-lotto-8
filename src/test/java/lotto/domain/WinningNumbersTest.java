package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.domain.dto.WinningStatistics;
import org.junit.jupiter.api.Test;

class WinningNumbersTest {

    @Test
    void 당첨_번호와_보너스_번호가_중복되면_예외가_발생한다() {
        LottoNumbers numbers = new LottoNumbers(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber bonus = new LottoNumber(6);

        assertThatThrownBy(() -> new WinningNumbers(numbers, bonus))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 정상적으로_Rank별_통계를_반환한다() {
        WinningNumbers winningNumbers = new WinningNumbers(
                new LottoNumbers(List.of(1, 2, 3, 4, 5, 6)),
                new LottoNumber(7)
        );

        List<Lotto> lottos = List.of(
                new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 6))),   // 1등
                new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 7))),   // 2등
                new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 5, 8))),   // 3등
                new Lotto(new LottoNumbers(List.of(1, 2, 3, 4, 9, 10))),  // 4등
                new Lotto(new LottoNumbers(List.of(1, 2, 3, 11, 12, 13))),// 5등
                new Lotto(new LottoNumbers(List.of(1, 2, 14, 15, 16, 17)))// NONE
        );

        WinningStatistics statistics = winningNumbers.evaluateLottos(lottos);

        assertThat(statistics.results().get(Rank.FIRST)).isEqualTo(1);
        assertThat(statistics.results().get(Rank.SECOND)).isEqualTo(1);
        assertThat(statistics.results().get(Rank.THIRD)).isEqualTo(1);
        assertThat(statistics.results().get(Rank.FOURTH)).isEqualTo(1);
        assertThat(statistics.results().get(Rank.FIFTH)).isEqualTo(1);
        assertThat(statistics.results().get(Rank.NONE)).isEqualTo(1);
    }

}
