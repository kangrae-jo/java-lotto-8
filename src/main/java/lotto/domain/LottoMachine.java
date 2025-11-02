package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {

    private LottoMachine() {
    }

    public static List<Lotto> generateLottos(Ticket ticket) {
        return Stream.generate(() -> new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)))
                .limit(ticket.count())
                .toList();
    }

}
