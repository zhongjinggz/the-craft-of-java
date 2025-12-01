package refactoring.performanceinvoice.domain;

//TODO 避免重复创建PlayType对象
public class PlayType {
    private final String name;

    public PlayType(String name) {
        this.name = name;
    }

    public static PlayType valueOf(String name) {
        if ("tragedy".equals(name)) {
            return new TragedyPlayType(name);
        } else {
            return new ComedyPlayType(name);
        }
    }

    public int calAmount(int audienceCount) {
        int amount;

//        if (play.getTypeString().equals("tragedy")) {
        if (name.equals("tragedy")) {
            amount = 40000;
            if (audienceCount > 30) {
                amount += 1000 * (audienceCount - 30);
            }
        } else if (name.equals("comedy")) {
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
        if ("comedy".equals(name)) {
            points += Math.floorDiv(audienceCount, 5);
        }
        return points;
    }
}
