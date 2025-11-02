package lotto.controller;

import java.util.List;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.Ticket;
import lotto.domain.WinningNumbers;
import lotto.domain.dto.LottoNumbersDto;
import lotto.domain.dto.WinningStatistics;
import lotto.view.OutputView;

public class LottoController {

    public List<Lotto> purchaseLottos(Cashier cashier, int money) {
        Ticket ticket = cashier.makeTicket(money);
        List<Lotto> lottos = cashier.issueLottos(ticket);

        printLottos(lottos);

        return lottos;
    }

    public WinningNumbers makeWinningNumbers(List<Integer> numbers, int bonusNumber) {
        return new WinningNumbers(numbers, bonusNumber);
    }

    public void printResult(List<Lotto> lottos, WinningNumbers winnings, int money) {
        WinningStatistics winningStatistics = winnings.evaluateLottos(lottos);
        OutputView.printWinningStatistics(winningStatistics);

        double yield = winnings.calculateYield(winningStatistics, money);
        OutputView.printYield(yield);
    }

    private void printLottos(List<Lotto> lottos) {
        List<LottoNumbersDto> lottoNumbersDtos = lottos.stream()
                .map(lotto -> new LottoNumbersDto(lotto.extractNumbers()))
                .toList();
        OutputView.printLottoNumbers(lottoNumbersDtos);
    }

}
