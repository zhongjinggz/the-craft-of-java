package refactoring.performanceinvoice.domain;

//TODO 避免对 Performance 的依赖
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

    public int calAmount(int audienceCount) {
        int amount;

        if (getType().equals("tragedy")) {
            amount = 40000;
            if (audienceCount > 30) {
                amount += 1000 * (audienceCount - 30);
            }
        } else if (getType().equals("comedy")) {
            amount = 30000;
            if (audienceCount > 20) {
                amount += 10000 + 500 * (audienceCount - 20);
            }
            amount += 300 * audienceCount;
        } else {
            throw new IllegalArgumentException("戏剧类型不正确!");
        }
        return amount;
    }

    public int calAudiencePoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(getType())) {
            points += Math.floorDiv(audienceCount, 5);
        }
        return points;
    }
}
