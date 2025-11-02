package lotto.domain;

public record Ticket(int money) {

    private final static int MIN_PURCHASE_AMOUNT = 1_000;
    private final static int MAX_PURCHASE_AMOUNT = 10_000;

    public Ticket {
        validatePurchaseMoneyRange(money);
    }

    private void validatePurchaseMoneyRange(int money) {
        if (money < MIN_PURCHASE_AMOUNT || MAX_PURCHASE_AMOUNT < money) {
            throw new IllegalArgumentException("[ERROR] 1,000원에서 10,000원까지 구매할 수 있습니다.");
        }
    }

}
