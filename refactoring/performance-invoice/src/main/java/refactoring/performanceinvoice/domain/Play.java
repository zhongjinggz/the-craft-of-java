package refactoring.performanceinvoice.domain;

import refactoring.performanceinvoice.drivingadapter.Performance;

public class Play {
    private final String id;
    private final String name;

    private final PlayType playType;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.playType = PlayTypes.get(typeName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int calPoints(Performance perf) {
        return playType.calPoints(perf.getAudience());
    }

    public int calAmount(Performance perf) {
        return playType.calAmount(perf.getAudience());
    }

    public PlayType getType() {
        return playType;
    }
}
