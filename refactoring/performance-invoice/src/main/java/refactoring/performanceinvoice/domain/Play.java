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
        int thisPoints = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(getType())) {
            thisPoints += Math.floorDiv(audienceCount, 5);
        }
        return thisPoints;
    }

    public int calAmount(int audience) {
        int thisAmt;

        if (getType().equals("tragedy")) {
            thisAmt = 40000;
            if (audience > 30) {
                thisAmt += 1000 * (audience - 30);
            }
        } else if (getType().equals("comedy")) {
            thisAmt = 30000;
            if (audience > 20) {
                thisAmt += 10000 + 500 * (audience - 20);
            }
            thisAmt += 300 * audience;
        } else {
            throw new IllegalArgumentException("戏剧类型不正确!");
        }
        return thisAmt;
    }
}
