package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {

    private final static int LOTTO_PRICE = 1_000;

    public LottoMachine() {
    }

    public int calculateLottoAmount(int money) {
        return money / LOTTO_PRICE;
    }

    public List<Lotto> generateLottos(int amount) {
        return Stream.generate(() -> new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6)))
                .limit(amount)
                .toList();
    }

}
