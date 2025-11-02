package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoNumbersTest {

    @Test
    void 로또_번호가_6개가_아니면_오류를_일으킨다() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(1, 2, 3, 4, 5, 6, 7)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 로또_번호에_중복이_있으면_오류를_일으킨다() {
        assertThatThrownBy(() -> new LottoNumbers(List.of(1, 2, 3, 4, 5, 5)))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
