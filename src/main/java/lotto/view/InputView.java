package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {

    public InputView() {
    }

    public String readPurchaseMoney() {
        OutputView.printPurchasePrompt();
        return Console.readLine();
    }

    public String readWinningNumbers() {
        OutputView.printWinningNumbersPrompt();
        return Console.readLine();
    }

    public String readBonusNumber() {
        OutputView.printBonusNumberPrompt();
        return Console.readLine();
    }

}
