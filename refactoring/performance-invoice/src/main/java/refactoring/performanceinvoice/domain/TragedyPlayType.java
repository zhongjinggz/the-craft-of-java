package refactoring.performanceinvoice.domain;

public class TragedyPlayType extends PlayType {

    public TragedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calPoints(int audienceCount) {
        return Math.max(audienceCount - 30, 0);
    }

    @Override
    public int calAmount(int audience) {
        int amount= 40000;
        if (audience > 30) {
            amount += 1000 * (audience - 30);
        }
        return amount;
    }
}
