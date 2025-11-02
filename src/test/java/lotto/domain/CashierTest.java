package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CashierTest {

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 999, 10_001, 11_000})
    void 구매_금액이_1000원에서_10000원_사이가_아니면_오류를_일으킨다(int money) {
        assertThatThrownBy(() -> {
                    Cashier cashier = new Cashier();
                    Ticket ticket = cashier.makeTicket(money);
                    cashier.issueLottos(ticket);
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest
    @ValueSource(ints = {1_001, 2_001, 9_999})
    void 구매_금액이_1000원_단위가_아니면_오류를_일으킨다(int money) {
        assertThatThrownBy(() -> {
                    Cashier cashier = new Cashier();
                    Ticket ticket = cashier.makeTicket(money);
                    cashier.issueLottos(ticket);
                }
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 구매_금액에_맞게_정상적으로_Lotto를_반환한다() {
        Cashier cashier = new Cashier();

        Ticket ticket = cashier.makeTicket(10_000);
        List<Lotto> lottos = cashier.issueLottos(ticket);

        assertThat(lottos).hasSize(10);
    }

}
