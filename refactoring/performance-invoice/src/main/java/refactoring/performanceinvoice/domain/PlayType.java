package refactoring.performanceinvoice.domain;

//TODO 避免重复创建PlayType对象
public abstract class PlayType {
    protected final String name;

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

    public abstract int calAmount(int audienceCount);

    public abstract int calAudiencePoints(int audienceCount);
}
