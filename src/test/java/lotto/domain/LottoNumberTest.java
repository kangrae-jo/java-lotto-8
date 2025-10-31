package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class LottoNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 46})
    void Lotto_Number는_1부터_45사이의_숫자이다(int number) {
        assertThatThrownBy(() -> new LottoNumber(number))
                .isInstanceOf(IllegalArgumentException.class);
    }

}