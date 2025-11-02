package lotto.domain.dto;

public record RankResult(
        int matchCount,
        boolean hasBonus,
        long prize,
        long count
) {

    public String formatForPrint() {
        String bonusText = "";
        if (hasBonus) {
            bonusText = ", 보너스 볼 일치";
        }

        return String.format("%d개 일치%s (%,d원) - %d개", matchCount, bonusText, prize, count);
    }

}
