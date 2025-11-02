package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Stream;
import lotto.domain.Lotto;
import lotto.domain.LottoNumbers;
import lotto.domain.Ticket;

public class LottoMachine {

    private LottoMachine() {
    }

    public static List<Lotto> generateLottos(Ticket ticket) {
        return Stream.generate(() -> new Lotto(new LottoNumbers(Randoms.pickUniqueNumbersInRange(1, 45, 6))))
                .limit(ticket.count())
                .toList();
    }

}
