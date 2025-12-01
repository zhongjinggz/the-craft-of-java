package refactoring.performanceinvoice.domain;

public class TragedyPlayType extends PlayType {
    public TragedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calAmount(int audienceCount) {
        int amount = 40000;
        if (audienceCount > 30) {
            amount += 1000 * (audienceCount - 30);
        }
        return amount;
    }

    @Override
    public int calAudiencePoints(int audienceCount) {
        return Math.max(audienceCount - 30, 0);
    }
}
