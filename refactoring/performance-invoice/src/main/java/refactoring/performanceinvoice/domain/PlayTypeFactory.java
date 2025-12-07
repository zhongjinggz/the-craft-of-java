package refactoring.performanceinvoice.domain;

public class PlayTypeFactory {
    private static final PlayType TRAGEDY = new TragedyPlayType();
    private static final PlayType COMEDY = new ComedyPlayType();
    static PlayType buildPlayType(String typeName) {
        return switch (typeName) {
            case TragedyPlayType.NAME -> TRAGEDY;
            case ComedyPlayType.NAME -> COMEDY;
            default -> throw new IllegalArgumentException("戏剧类型不正确!");
        };
    }
}
