package lotto.domain;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Stream;

public class LottoMachine {

    private final static int LOTTO_PRICE = 1_000;

    public LottoMachine() {
    }

    public void validateAmountUnit(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로만 구매할 수 있습니다.");
        }
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
