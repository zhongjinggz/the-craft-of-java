package refactoring.performanceinvoice.domain;

public class ComedyPlayType extends PlayType {

    public ComedyPlayType(String name) {
        super(name);
    }

    @Override
    public int calPoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        points += Math.floorDiv(audienceCount, 5);
        return points;
    }

    @Override
    public int calAmount(int audience) {
        int amount;


        amount = 30000;
        if (audience > 20) {
            amount += 10000 + 500 * (audience - 20);
        }
        amount += 300 * audience;
        return amount;
    }
}
