package lotto.domain;

public record Ticket(int money) {

    private final static int LOTTO_PRICE = 1_000;
    private final static int MIN_PURCHASE_AMOUNT = 1_000;
    private final static int MAX_PURCHASE_AMOUNT = 10_000;

    public Ticket {
        validatePurchaseMoneyRange(money);
        validateAmountUnit(money);
    }

    public int count() {
        return money / LOTTO_PRICE;
    }

    private void validatePurchaseMoneyRange(int money) {
        if (money < MIN_PURCHASE_AMOUNT || MAX_PURCHASE_AMOUNT < money) {
            throw new IllegalArgumentException("[ERROR] 1,000원에서 10,000원까지 구매할 수 있습니다.");
        }
    }

    private static void validateAmountUnit(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 1,000원 단위로만 구매할 수 있습니다.");
        }
    }

}
