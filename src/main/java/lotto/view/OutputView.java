package lotto.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lotto.domain.dto.LottoNumbersDto;
import lotto.domain.dto.RankResult;
import lotto.domain.dto.WinningStatistics;

public class OutputView {

    private OutputView() {
    }

    public static void printPurchasePrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printWinningNumbersPrompt() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberPrompt() {
        System.out.println();
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    public static void printLottoNumbers(List<LottoNumbersDto> lottoNumbersDtos) {
        System.out.println();
        System.out.printf("%d개를 구매했습니다.", lottoNumbersDtos.size());

        System.out.println();
        lottoNumbersDtos.stream()
                .map(LottoNumbersDto::numbers)
                .forEach(System.out::println);
    }

    public static void printWinningStatistics(WinningStatistics winningStatistics) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");

        List<RankResult> rankResults = new ArrayList<>(winningStatistics.toRankResults());
        Collections.reverse(rankResults);

        for (RankResult result : rankResults) {
            System.out.println(result.formatForPrint());
        }
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익률은 %.1f%%입니다.", yield);
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

}
