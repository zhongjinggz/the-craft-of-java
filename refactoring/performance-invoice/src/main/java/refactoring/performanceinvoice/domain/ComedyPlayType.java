package refactoring.performanceinvoice.domain;

public class ComedyPlayType extends PlayType {

    private static final int POINTS_THRESHOLD = 30;
    private static final int POINTS_UNIT = 5;
    private static final int AMOUNT_THRESHOLD = 20;
    private static final int BASE_PRICE = 30000;
    private static final int UNIT_PRICE = 300;
    private static final int EXTRA_BASE_PRICE = 10000;
    private static final int EXTRA_UNIT_PRICE = 500;
    public static final String NAME = "comedy";

    @Override
    public int calPoints(int audienceCount) {
        return Math.max(audienceCount - POINTS_THRESHOLD, 0)
            + Math.floorDiv(audienceCount, POINTS_UNIT);
    }

    @Override
    public int calAmount(int audience) {
        int amount = BASE_PRICE + UNIT_PRICE * audience;
        if (audience > AMOUNT_THRESHOLD) {
            amount += EXTRA_BASE_PRICE
                + EXTRA_UNIT_PRICE * (audience - AMOUNT_THRESHOLD);
        }
        return amount;
    }
}
