package refactoring.performanceinvoice.domain.play.playtype;

public class PlayTypes {
    private static final PlayType TRAGEDY = new TragedyPlayType();
    private static final PlayType COMEDY = new ComedyPlayType();
    public static PlayType get(String typeName) {
        return switch (typeName) {
            case TragedyPlayType.NAME -> TRAGEDY;
            case ComedyPlayType.NAME -> COMEDY;
            default -> throw new IllegalArgumentException("戏剧类型不正确!");
        };
    }
}
