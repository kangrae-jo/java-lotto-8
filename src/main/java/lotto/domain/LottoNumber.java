package lotto.domain;

public record LottoNumber(int number) {

    public LottoNumber {
        validateLottoNumber(number);
    }

    public int value() {
        return number;
    }

    private void validateLottoNumber(int number) {
        if (number < 1 || 45 < number) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LottoNumber that)) {
            return false;
        }
        return number == that.number;
    }

}
