package lotto.view;

import java.util.Map;
import lotto.domain.Rank;
import lotto.domain.dto.WinningStatistics;

public class OutputView {

    // TODO: static 고민
    private OutputView() {
    }

    public static void printPurchasePrompt() {
        System.out.println("구입금액을 입력해 주세요.");
    }

    public static void printWinningNumbersPrompt() {
        System.out.println("당첨 번호를 입력해 주세요.");
    }

    public static void printBonusNumberPrompt() {
        System.out.println("보너스 번호를 입력해 주세요.");
    }

    // TODO: Rank를 알아도 괜찮은가?
    // TODO: 3항 연산자 지양
    // TODO: 수익률 계산 및 출력
    public static void printWinningStatistics(WinningStatistics winningStatistics) {
        System.out.println("당첨 통계");
        System.out.println("---");

        Map<Rank, Long> results = winningStatistics.results();
        for (Rank rank : Rank.values()) {
            if (rank == Rank.NONE) {
                continue;
            }
            long count = results.getOrDefault(rank, 0L);
            System.out.printf("%d개 일치%s (%,d원) - %d개%n",
                    rank.getMatchCount(),
                    rank.hasBonus() ? ", 보너스 볼 일치" : "",
                    rank.getPrize(),
                    count
            );
        }
    }

    public static void printYield(double yield) {
        System.out.printf("총 수익률은 %.1f%%입니다.", yield);
    }

}
