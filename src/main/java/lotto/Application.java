package lotto;

import java.util.Arrays;
import java.util.List;
import lotto.controller.LottoController;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.WinningNumbers;
import lotto.view.InputView;

public class Application {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        int money = Integer.parseInt(inputView.readPurchaseMoney());

        LottoController lottoController = new LottoController();
        List<Lotto> lottos = lottoController.purchaseLottos(new Cashier(), money);

        String winningNumbers = inputView.readWinningNumbers();
        String bonusNumber = inputView.readBonusNumber();
        String[] split = winningNumbers.split(",");

        List<Integer> numbers = Arrays.stream(split)
                .map(Integer::parseInt)
                .toList();
        WinningNumbers winnings = lottoController.makeWinningNumbers(numbers, Integer.parseInt(bonusNumber));
        lottoController.printResult(lottos, winnings, money);
    }

}
