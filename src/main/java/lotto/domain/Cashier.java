package lotto.domain;

import java.util.List;

public class Cashier {

    private final static int MIN_PURCHASE_AMOUNT = 1_000;
    private final static int MAX_PURCHASE_AMOUNT = 10_000;

    private final int money;
    private final LottoMachine lottoMachine;

    public Cashier(int money, LottoMachine lottoMachine) {
        validatePurchaseRange(money);
        this.money = money;

        lottoMachine.validateAmountUnit(money);
        this.lottoMachine = lottoMachine;
    }

    public List<Lotto> purchaseLottos() {
        int amount = lottoMachine.calculateLottoAmount(money);
        return lottoMachine.generateLottos(amount);
    }

    private void validatePurchaseRange(int money) {
        if (money < MIN_PURCHASE_AMOUNT || MAX_PURCHASE_AMOUNT < money) {
            throw new IllegalArgumentException("[ERROR] 1,000원에서 10,000원까지 구매할 수 있습니다.");
        }
    }

}
