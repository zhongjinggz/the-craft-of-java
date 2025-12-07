package refactoring.performanceinvoice.domain;

public class TragedyPlayType extends PlayType {

    private static final int POINTS_THRESHOLD = 30;
    private static final int AMOUNT_THRESHOLD = 30;
    private static final int BASE_PRICE = 40000;
    private static final int UNIT_PRICE = 1000;
    public static final String NAME = "tragedy";

    public TragedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calPoints(int audienceCount) {
        return Math.max(audienceCount - POINTS_THRESHOLD, 0);
    }

    @Override
    public int calAmount(int audienceCount) {
        int amount= BASE_PRICE;
        if (audienceCount > AMOUNT_THRESHOLD) {
            amount += UNIT_PRICE * (audienceCount - AMOUNT_THRESHOLD);
        }
        return amount;
    }
}
