package refactoring.performanceinvoice.domain.playtype;

//DONE 避免重复创建PlayType对象
public abstract class PlayType {
    private static final String TRAGEDY_NAME = "tragedy";
    private static final String COMEDY_NAME = "comedy";

    private static final PlayType TRAGEDY = new TragedyPlayType(TRAGEDY_NAME);
    private static final PlayType COMEDY = new ComedyPlayType(COMEDY_NAME);

    protected final String name;

    public PlayType(String name) {
        this.name = name;
    }

    public static PlayType valueOf(String name) {
        if (TRAGEDY_NAME.equals(name)) {
            return TRAGEDY;
        } else {
            return COMEDY;
        }
    }

    public abstract int calAmount(int audienceCount);

    public abstract int calAudiencePoints(int audienceCount);
}
