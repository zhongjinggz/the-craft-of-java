package refactoring.performanceinvoice.domain;

public class ComedyPlayType extends PlayType {
    public ComedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calAmount(int audienceCount) {
        int amount = 30000;
        if (audienceCount > 20) {
            amount += 10000 + 500 * (audienceCount - 20);
        }
        amount += 300 * audienceCount;
        return amount;
    }

    @Override
    public int calAudiencePoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        points += Math.floorDiv(audienceCount, 5);
        return points;
    }
}
