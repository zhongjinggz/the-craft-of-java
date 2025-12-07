package refactoring.performanceinvoice.domain;

public class TragedyPlayType extends PlayType {

    public TragedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calPoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        return points;
    }

    @Override
    public int calAmount(int audience) {
        int amount;

        amount = 40000;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }
        return amount;
    }
}
