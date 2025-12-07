package refactoring.performanceinvoice.domain.play;

import refactoring.performanceinvoice.domain.play.playtype.PlayType;
import refactoring.performanceinvoice.domain.play.playtype.PlayTypes;

public class Play {
    private final String id;
    private final String name;

    private final PlayType type;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.type = PlayTypes.get(typeName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int calPoints(int audienceCount) {
        return type.calPoints(audienceCount);
    }

    public int calAmount(int audienceCount) {
        return type.calAmount(audienceCount);
    }

    public PlayType getType() {
        return type;
    }
}
