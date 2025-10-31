package lotto.domain;

public class Cashier {

    private final int money;

    public Cashier(int money) {
        validateMoneyRange(money);
        validateMoney(money);
        this.money = money;
    }

    // TODO: 싱수 분리
    public int getLottoAmount() {
        return money / 1_000;
    }

    private void validateMoneyRange(int money) {
        if (money < 1_000 || 10_000 < money) {
            throw new IllegalArgumentException("[ERROR] 1,000원에서 10,000원까지 구매할 수 있습니다.");
        }
    }

    private void validateMoney(int money) {
        if (money % 1_000 != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로만 구매할 수 있습니다.");
        }
    }

}
