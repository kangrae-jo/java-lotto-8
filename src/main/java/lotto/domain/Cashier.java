package lotto.domain;

import java.util.List;
import lotto.util.LottoMachine;

public class Cashier {

    public Cashier() {
    }

    public Ticket makeTicket(int money) {
        return new Ticket(money);
    }

    public List<Lotto> issueLottos(Ticket ticket) {
        return LottoMachine.generateLottos(ticket);
    }

}
