package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CashierTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 10_001, 11_000})
    void Lotto_구매_비용은_1000원에서_10000원까지이다(int money) {
        assertThatThrownBy(() -> new Cashier(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_001, 2_001, 9_999})
    void Lotto_구매_비용은_1000원_단위이다(int money) {
        assertThatThrownBy(() -> new Cashier(money))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void Lotto_구매_비용에따라_올바른_구매_개수를_알_수_있다() {
        final int money = 4_000;
        Cashier cashier = new Cashier(money);

        int lottoAmount = cashier.getLottoAmount();

        assertThat(lottoAmount).isEqualTo(money / 1_000);
    }

}