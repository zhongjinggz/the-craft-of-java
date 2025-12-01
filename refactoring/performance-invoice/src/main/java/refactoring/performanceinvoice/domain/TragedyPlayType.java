package refactoring.performanceinvoice.domain;

public class TragedyPlayType extends PlayType {

    private static final int BASE_PRICE = 40000;
    private static final int PRICE_THRESHOLD = 30;
    private static final int POINTS_THRESHOLD = 30;
    private static final int EXTRA_UNIT_PRICE = 1000;

    public TragedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calAmount(int audienceCount) {
        int amount = BASE_PRICE;
        if (audienceCount > PRICE_THRESHOLD) {
            amount += EXTRA_UNIT_PRICE * (audienceCount - PRICE_THRESHOLD);
        }
        return amount;
    }

    @Override
    public int calAudiencePoints(int audienceCount) {
        return Math.max(audienceCount - POINTS_THRESHOLD, 0);
    }
}
