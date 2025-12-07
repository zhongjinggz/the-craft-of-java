package refactoring.performanceinvoice.domain;

import refactoring.performanceinvoice.drivingadapter.Performance;

public class Play {
    private String id;
    private String name;

    public PlayType playType;

    public Play(String id, String name, String typeName) {
        this.id = id;
        this.name = name;
        this.playType = buildPlayType(typeName);
    }

    private static PlayType buildPlayType(String typeName) {
        return switch (typeName) {
            case TragedyPlayType.NAME -> new TragedyPlayType(typeName);
            case ComedyPlayType.NAME -> new ComedyPlayType(typeName);
            default -> throw new IllegalArgumentException("戏剧类型不正确!");
        };
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
