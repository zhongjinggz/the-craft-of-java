package refactoring.performanceinvoice.domain;

public class PlayTypeFactory {
    static PlayType buildPlayType(String typeName) {
        return switch (typeName) {
            case TragedyPlayType.NAME -> new TragedyPlayType();
            case ComedyPlayType.NAME -> new ComedyPlayType();
            default -> throw new IllegalArgumentException("戏剧类型不正确!");
        };
    }
}
