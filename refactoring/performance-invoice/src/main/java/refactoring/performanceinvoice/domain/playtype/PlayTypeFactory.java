package refactoring.performanceinvoice.domain.playtype;

import static refactoring.performanceinvoice.domain.playtype.PlayType.COMEDY_NAME;
import static refactoring.performanceinvoice.domain.playtype.PlayType.TRAGEDY_NAME;

public class PlayTypeFactory {
    private static final PlayType TRAGEDY = new TragedyPlayType(TRAGEDY_NAME);
    private static final PlayType COMEDY = new ComedyPlayType(COMEDY_NAME);
    static PlayType getType(String name) {
        if (TRAGEDY_NAME.equals(name)) {
            return TRAGEDY;
        } else {
            return COMEDY;
        }
    }
}
