package refactoring.performanceinvoice.domain;

public class PlayType {
    private final String name;

    public PlayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int calPoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(getName())) {
            points += Math.floorDiv(audienceCount, 5);
        }
        return points;
    }

    public int calAmount(int audience) {
        int amount;

        if (getName().equals("tragedy")) {
            amount = 40000;
            if (audience > 30) {
                amount += 1000 * (audience - 30);
            }
        } else if (getName().equals("comedy")) {
            amount = 30000;
            if (audience > 20) {
                amount += 10000 + 500 * (audience - 20);
            }
            amount += 300 * audience;
        } else {
            throw new IllegalArgumentException("戏剧类型不正确!");
        }
        return amount;
    }
}
