package lotto.domain;

import java.util.List;

public class Cashier {

    public Cashier() {
    }

    public Ticket makeTicket(int money) {
        return new Ticket(money);
    }

    public List<Lotto> issueLottos(Ticket ticket) {
        int amount = LottoMachine.calculateLottoAmount(ticket.money());
        return LottoMachine.generateLottos(amount);
    }

}
