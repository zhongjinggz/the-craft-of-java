package refactoring.performanceinvoice.domain;

public class ComedyPlayType extends PlayType {

    public ComedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calPoints(int audienceCount) {
        return Math.max(audienceCount - 30, 0)
            + Math.floorDiv(audienceCount, 5);
    }

    @Override
    public int calAmount(int audience) {
        int amount = 30000 + 300 * audience;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        return amount;
    }
}
