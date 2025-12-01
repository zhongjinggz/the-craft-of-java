package refactoring.performanceinvoice.domain.playtype;

//DONE 避免重复创建PlayType对象
public abstract class PlayType {
    static final String TRAGEDY_NAME = "tragedy";
    static final String COMEDY_NAME = "comedy";

    protected final String name;

    public PlayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int calAmount(int audienceCount);

    public abstract int calAudiencePoints(int audienceCount);
}
