package refactoring.performanceinvoice.domain;

public class Play {
    String id;
    String name;
    String type;

    public Play(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int calPoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(type)) {
            points += Math.floorDiv(audienceCount, 5);
        }
        return points;
    }

    public int calAmount(int audience) {
        int amount;

        if (type.equals("tragedy")) {
            amount = 40000;
            if (audience > 30) {
                amount += 1000 * (audience - 30);
            }
        } else if (type.equals("comedy")) {
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
