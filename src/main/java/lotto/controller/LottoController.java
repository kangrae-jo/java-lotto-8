package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.Ticket;
import lotto.domain.WinningNumbers;
import lotto.domain.dto.LottoNumbersDto;
import lotto.domain.dto.WinningStatistics;
import lotto.util.InputParser;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoController {

    private final InputView inputView;

    public LottoController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        Cashier cashier = new Cashier();

        // 구임 금액 입력 받기
        Ticket ticket = retryUntilValid(() -> readAndCreateTicket());

        // 로또 발급 및 출력
        List<Lotto> lottos = cashier.issueLottos(ticket);
        printLottos(lottos);

        // 당첨 번호 입력 받기
        WinningNumbers winningNumbers = retryUntilValid(() -> readAndCreateWinningNumbers());

        // 결과 출력
        printResult(lottos, winningNumbers, ticket.money());
    }

    private Ticket readAndCreateTicket() {
        int money = InputParser.parseMoney(inputView.readPurchaseMoney());
        return new Ticket(money);
    }

    private void printLottos(List<Lotto> lottos) {
        List<LottoNumbersDto> lottoNumbersDtos = lottos.stream()
                .map(lotto -> new LottoNumbersDto(lotto.extractNumbers()))
                .toList();

        OutputView.printLottoNumbers(lottoNumbersDtos);
    }

    private WinningNumbers readAndCreateWinningNumbers() {
        List<Integer> numbers = InputParser.parseWinningNumbers(inputView.readWinningNumbers());
        int bonus = InputParser.parseBonusNumber(inputView.readBonusNumber());
        return new WinningNumbers(numbers, bonus);
    }

    private void printResult(List<Lotto> lottos, WinningNumbers winnings, int money) {
        WinningStatistics statistics = winnings.evaluateLottos(lottos);
        OutputView.printWinningStatistics(statistics);

        double yield = winnings.calculateYield(statistics, money);
        OutputView.printYield(yield);
    }

    private <T> T retryUntilValid(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

}
