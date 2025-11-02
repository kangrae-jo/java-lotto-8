package lotto;

import lotto.controller.LottoController;
import lotto.domain.Cashier;
import lotto.view.InputView;

public class Application {

    public static void main(String[] args) {
        LottoController controller = new LottoController(new InputView());
        controller.run(new Cashier());
    }

}
