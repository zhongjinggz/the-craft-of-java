package refactoring.performanceinvoice.domain;

public abstract class SuperPlayType {
    protected final String name;

    public SuperPlayType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int calPoints(int audienceCount);

    public abstract int calAmount(int audience);
}
