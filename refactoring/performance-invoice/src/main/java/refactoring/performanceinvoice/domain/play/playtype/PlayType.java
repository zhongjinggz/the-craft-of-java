package refactoring.performanceinvoice.domain.play.playtype;

public abstract class PlayType {
    public abstract int calPoints(int audienceCount);
    public abstract int calAmount(int audience);
}
