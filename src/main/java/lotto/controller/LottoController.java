package lotto.controller;

import java.util.List;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.dto.LottoNumbersDto;
import lotto.view.OutputView;

public class LottoController {

    private final LottoMachine lottoMachine;

    public LottoController(LottoMachine lottoMachine) {
        this.lottoMachine = lottoMachine;
    }

    public List<Lotto> purchaseLottos(int money) {
        Cashier cashier = makeCashier(money);
        List<Lotto> lottos = cashier.purchaseLottos();

        List<LottoNumbersDto> lottoNumbersDtos = lottos.stream()
                .map(lotto -> new LottoNumbersDto(lotto.extractNumbers()))
                .toList();

        OutputView.printLottoNumbers(lottoNumbersDtos);

        return lottos;
    }

    // TODO: Cashier 내부 팩토리 메서드로 분리
    private Cashier makeCashier(int money) {
        return new Cashier(money, lottoMachine);
    }

}
