package refactoring.performanceinvoice.domain;

import refactoring.performanceinvoice.drivingadapter.Performance;

public class Play {
    private String id;
    private String name;

    public ComedyPlayType playType;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.playType = new ComedyPlayType(typeName);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeName() {
        return playType.getName();
    }


    public int calPoints(Performance perf) {
        return playType.calPoints(perf.getAudience());
    }

    public int calAmount(Performance perf) {
        return playType.calAmount(perf.getAudience());
    }
}
