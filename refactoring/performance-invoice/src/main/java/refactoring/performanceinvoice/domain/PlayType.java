package refactoring.performanceinvoice.domain;

public abstract class PlayType {
    protected final String name;

    public PlayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int calPoints(int audienceCount);

    public abstract int calAmount(int audience);
}
