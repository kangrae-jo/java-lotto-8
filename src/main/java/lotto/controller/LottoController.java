package lotto.controller;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Cashier;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.LottoNumbers;
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

    public void run(Cashier cashier) {
        // 금액 입력 및 티켓 발급
        Ticket ticket = readTicketWithRetry();

        // 로또 발급 및 출력
        List<Lotto> lottos = cashier.issueLottos(ticket);
        printLottos(lottos);

        // 당첨 번호, 보너스 번호 입
        WinningNumbers winningNumbers = readWinningNumbersWithRetry();

        // 결과 출력
        printResult(lottos, winningNumbers, ticket.money());
    }

    private Ticket readTicketWithRetry() {
        return retryUntilValid(() -> {
            int money = InputParser.parseMoney(inputView.readPurchaseMoney());

            return new Ticket(money);
        });
    }

    private WinningNumbers readWinningNumbersWithRetry() {
        return retryUntilValid(() -> {
            LottoNumbers numbers = readNumbersWithRetry();
            LottoNumber bonus = readBonusNumberWithRetry();

            return new WinningNumbers(numbers, bonus);
        });
    }

    private LottoNumbers readNumbersWithRetry() {
        return retryUntilValid(() ->
                new LottoNumbers(InputParser.parseWinningNumbers(inputView.readWinningNumbers()))
        );
    }

    private LottoNumber readBonusNumberWithRetry() {
        return retryUntilValid(() ->
                new LottoNumber(InputParser.parseBonusNumber(inputView.readBonusNumber()))
        );
    }

    private void printLottos(List<Lotto> lottos) {
        List<LottoNumbersDto> lottoNumbersDtos = lottos.stream()
                .map(lotto -> new LottoNumbersDto(lotto.extractNumbers()))
                .toList();

        OutputView.printLottoNumbers(lottoNumbersDtos);
    }

    private void printResult(List<Lotto> lottos, WinningNumbers winnings, int money) {
        WinningStatistics statistics = winnings.evaluateLottos(lottos);
        OutputView.printWinningStatistics(statistics);

        double yield = statistics.calculateYield(money);
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
