package refactoring.performanceinvoice.domain.playtype;

public class ComedyPlayType extends PlayType {

    public static final int BASE_PRICE = 30000;
    public static final int PRICE_THRESHOLD = 20;
    public static final int EXTRA_BASE_PRICE = 10000;
    public static final int EXTRA_UNIT_PRICE = 500;
    public static final int UNIT_PRICE = 300;

    public static final int POINTS_THRESHOLD = 30;
    public static final int POINTS_UNIT = 5;

    public ComedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calAmount(int audienceCount) {
        int amount = BASE_PRICE + UNIT_PRICE * audienceCount;

        if (audienceCount > PRICE_THRESHOLD) {
            amount += EXTRA_BASE_PRICE + EXTRA_UNIT_PRICE * (audienceCount - PRICE_THRESHOLD);
        }
        return amount;
    }

    @Override
    public int calAudiencePoints(int audienceCount) {
        return Math.max(audienceCount - POINTS_THRESHOLD, 0)
            + Math.floorDiv(audienceCount, POINTS_UNIT);
    }
}
