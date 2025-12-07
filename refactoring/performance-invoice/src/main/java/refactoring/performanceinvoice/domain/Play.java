package refactoring.performanceinvoice.domain;

public class Play {
    private String id;
    private String name;
    private String typeName;

    private PlayType playType;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.setTypeName(typeName);
        this.playType = new PlayType(typeName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return playType.getName();
    }

    public int calPoints(int audienceCount) {
        int points = Math.max(audienceCount - 30, 0);
        if ("comedy".equals(getTypeName())) {
            points += Math.floorDiv(audienceCount, 5);
        }
        return points;
    }

    public int calAmount(int audience) {
        int amount;

        if (getTypeName().equals("tragedy")) {
            amount = 40000;
            if (audience > 30) {
                amount += 1000 * (audience - 30);
            }
        } else if (getTypeName().equals("comedy")) {
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

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
